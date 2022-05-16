package net.kaupenjoe.resourceslimes.entity;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.Util;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ResourceSlimeEntity extends Slime {
    private static final EntityDataAccessor<ItemStack> RESOURCE =
            SynchedEntityData.defineId(ResourceSlimeEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> ID_SIZE =
            SynchedEntityData.defineId(ResourceSlimeEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GROWTH_COUNTER =
            SynchedEntityData.defineId(ResourceSlimeEntity.class, EntityDataSerializers.INT);

    // TODO: Should be based on Resource / Tier
    public static final int GROWTH_TIME = 400; // 20 Seconds for each growth

    public ResourceSlimeEntity(EntityType<? extends Slime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if(!pPlayer.getLevel().isClientSide() && pHand == InteractionHand.MAIN_HAND) {
            harvestResource(pPlayer);

            if(pPlayer.isCrouching()) {
                outputInfo(pPlayer);
            }

        }
        return super.mobInteract(pPlayer, pHand);
    }

    private void harvestResource(Player pPlayer) {
        this.spawnAtLocation(this.entityData.get(RESOURCE).copy());

        ResourceSlimes.LOGGER.debug(pPlayer.getName() + " harvested: " + this.entityData.get(RESOURCE));
    }

    private void outputInfo(Player pPlayer) {
        pPlayer.sendMessage(new TextComponent("Resource: " + this.entityData.get(RESOURCE)), pPlayer.getUUID());
        pPlayer.sendMessage(new TextComponent("Size: " + this.entityData.get(ID_SIZE)), pPlayer.getUUID());
        pPlayer.sendMessage(new TextComponent("Counter: " + this.entityData.get(GROWTH_COUNTER)), pPlayer.getUUID());
        pPlayer.sendMessage(new TextComponent("Tier: " +
                SlimeResource.getResourceByItem(this.entityData.get(RESOURCE).getItem())), pPlayer.getUUID());
    }

    // TODO: Generalize the Resources
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
        SlimeResource resource = Util.getRandom(SlimeResource.values(), this.random);
        this.setResource(new ItemStack(resource.getItem().get()));

        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    @Override
    protected ParticleOptions getParticleType() {
        return new ItemParticleOption(ParticleTypes.ITEM, this.entityData.get(RESOURCE));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("resource", this.entityData.get(RESOURCE).serializeNBT());
        pCompound.putInt("growth_counter", this.entityData.get(GROWTH_COUNTER));

        ResourceSlimes.LOGGER.debug("Saving Resource: " + this.entityData.get(RESOURCE));
        ResourceSlimes.LOGGER.debug("Saving Growth Counter: " + this.entityData.get(GROWTH_COUNTER));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(RESOURCE, ItemStack.of(pCompound.getCompound("resource")));

        ResourceSlimes.LOGGER.debug("Reading Resource: " + ItemStack.of(pCompound.getCompound("resource")) +
                " | " + this.entityData.get(RESOURCE));
        ResourceSlimes.LOGGER.debug("Reading Growth Counter: " + pCompound.getInt("growth_counter"));
    }

    public void setResource(ItemStack stack) {
        this.entityData.set(RESOURCE, stack);
    }

    @Nullable
    public Item getResourceItem() {
        if(!this.entityData.get(RESOURCE).isEmpty()) {
            return this.entityData.get(RESOURCE).getItem();
        }

        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(RESOURCE, ItemStack.EMPTY);
        this.entityData.define(ID_SIZE, 1);
        this.entityData.define(GROWTH_COUNTER, 0);
    }

    @Override
    public void tick() {
        super.tick();
        countGrowth();

        if(readyForNewResource()) {
            addResource();
            resetGrowthCount();
        }
    }

    private void resetGrowthCount() {
        this.entityData.set(GROWTH_COUNTER, 0);
    }

    private void addResource() {
        ItemStack stack = this.entityData.get(RESOURCE).copy();
        if(stack.getCount() < 64) {
            this.entityData.set(RESOURCE, new ItemStack(stack.getItem(), stack.getCount() + 1));
        }

        recalculateSize();
    }

    private void recalculateSize() {
        setSize(0, true);
    }

    private boolean readyForNewResource() {
        return this.entityData.get(GROWTH_COUNTER) >= GROWTH_TIME;
    }

    private void countGrowth() {
        this.entityData.set(GROWTH_COUNTER, this.entityData.get(GROWTH_COUNTER) + 1);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }

    @Override
    protected void setSize(int pSize, boolean pResetHealth) {
        // Setting the size based on the number of resources
        // int i = this.entityData.get(RESOURCE).getCount() * 2 - 1; // INSANE GROWTH (64 -> Size 127)
        int i = (this.entityData.get(RESOURCE).getCount() / 16) + 1; // Normal Growth (64 -> Size   5)
        this.entityData.set(ID_SIZE, i);
        this.reapplyPosition();
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((i * i));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((0.2F + 0.1F * (float)i));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(i);
        if (pResetHealth) {
            this.setHealth(this.getMaxHealth());
        }

        this.xpReward = i;
    }

    // Makes the Slimes non-splittable atm
    // TODO: I got "zombie slimes" twice... idk weird bug!
    @Override
    public void remove(Entity.RemovalReason pReason) {
        this.setRemoved(pReason);
        if (pReason == Entity.RemovalReason.KILLED) {
            this.gameEvent(GameEvent.ENTITY_KILLED);
        }

        // Drops all remaining resources
        this.spawnAtLocation(this.entityData.get(RESOURCE));

        this.invalidateCaps();
    }
}