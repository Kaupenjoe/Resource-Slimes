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
import net.minecraft.world.entity.*;
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

import java.util.Arrays;

// TODO: Using Slime as it super class mean it has TWO size values
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
        if(pHand == InteractionHand.MAIN_HAND) {
            if(pPlayer.isCrouching()) {
                outputInfo(pPlayer);
                changeResourceDEBUG(pPlayer.getItemInHand(pHand));

                if(pPlayer.getItemInHand(pHand).getItem() == Items.STICK) {
                    growSlimeDEBUG();
                }
            } else {
                harvestResource(pPlayer);
            }
        }

        return super.mobInteract(pPlayer, pHand);
    }

    private void changeResourceDEBUG(ItemStack stack) {
        if(SlimeResource.getResourceByItem(stack.getItem()) != SlimeResource.EMPTY && !stack.isEmpty()) {
            this.setResource(stack);
        }
    }

    private void growSlimeDEBUG() {
        ItemStack stack = this.entityData.get(RESOURCE);
        stack.setCount(64);
        this.setResource(stack);
    }

    private void harvestResource(Player pPlayer) {
        if(this.entityData.get(RESOURCE).getCount() > 1) {
            ItemStack stackToSpawn = this.entityData.get(RESOURCE).copy();
            stackToSpawn.setCount(stackToSpawn.getCount() - 1);

            this.spawnAtLocation(stackToSpawn);
            shrinkOnHarvest(stackToSpawn);
        }

        ResourceSlimes.LOGGER.debug(pPlayer.getName() + " harvested: " + this.entityData.get(RESOURCE));
    }

    // TODO: customize to what size the slime can be reduced to
    private void shrinkOnHarvest(ItemStack stack) {
        setResource(new ItemStack(stack.getItem(), 1));
    }

    private void outputInfo(Player pPlayer) {
        pPlayer.sendMessage(new TextComponent("Resource: " + this.entityData.get(RESOURCE)), pPlayer.getUUID());
        pPlayer.sendMessage(new TextComponent("Size: " + this.entityData.get(ID_SIZE)), pPlayer.getUUID());
        pPlayer.sendMessage(new TextComponent("Counter: " + this.entityData.get(GROWTH_COUNTER)), pPlayer.getUUID());
        pPlayer.sendMessage(new TextComponent("Tier: " +
                SlimeResource.getResourceByItem(this.entityData.get(RESOURCE).getItem())), pPlayer.getUUID());
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_,
                                        @Nullable CompoundTag p_146750_) {
        SlimeResource resource = Util.getRandom(Arrays.stream(SlimeResource
                .values()).filter(sr -> sr != SlimeResource.EMPTY && sr.isEnabled()).toList(), this.random);
        this.setResource(new ItemStack(resource.getSlimeyExtractItem()));

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
        pCompound.putInt("size", this.entityData.get(ID_SIZE));

        ResourceSlimes.LOGGER.debug("Saving Resource: " + this.entityData.get(RESOURCE));
        ResourceSlimes.LOGGER.debug("Saving Growth Counter: " + this.entityData.get(GROWTH_COUNTER));
        ResourceSlimes.LOGGER.debug("Saving Size: " + this.entityData.get(ID_SIZE));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(RESOURCE, ItemStack.of(pCompound.getCompound("resource")));
        this.entityData.set(ID_SIZE, pCompound.getInt("size"));

        ResourceSlimes.LOGGER.debug("Reading Resource: " + ItemStack.of(pCompound.getCompound("resource")) +
                " | " + this.entityData.get(RESOURCE));
        ResourceSlimes.LOGGER.debug("Reading Growth Counter: " + pCompound.getInt("growth_counter"));
        ResourceSlimes.LOGGER.debug("Reading Size: " + pCompound.getInt("size"));
    }

    public void setResource(ItemStack stack) {
        this.entityData.set(RESOURCE, stack);
        resetGrowthCount();
        recalculateSize();
    }

    public Item getResourceItem() {
        if(!this.entityData.get(RESOURCE).isEmpty()) {
            return this.entityData.get(RESOURCE).getItem();
        }

        return Items.AIR;
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
        if(!isDeadOrDying()) {
            countGrowth();

            if(readyForNewResource()) {
                addResource();
                resetGrowthCount();
            }
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
        return this.getSize() > 0;
    }

    @Override
    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }

    @Override
    protected void setSize(int pSize, boolean pResetHealth) {
        // Setting the size based on the number of resources
        // int newSize = this.entityData.get(RESOURCE).getCount() * 2 - 1; // INSANE GROWTH (64 -> Size 127)
        int newSize = (this.entityData.get(RESOURCE).getCount() / 16) + 1; // Normal Growth (64 -> Size   5)
        int sizeBefore = this.entityData.get(ID_SIZE);
        this.entityData.set(ID_SIZE, newSize);

        this.reapplyPosition();
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((newSize * newSize));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((0.2F + 0.1F * (float)newSize));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(newSize);
        if (sizeBefore != newSize) {
            this.setHealth(this.getMaxHealth());
        }

        this.xpReward = newSize;
    }

    // Makes the Slimes non-splittable atm
    // TODO: I got "zombie slimes" twice... idk weird bug!
    @Override
    public void remove(Entity.RemovalReason pReason) {
        this.setRemoved(pReason);
        if (pReason == Entity.RemovalReason.KILLED) {
            this.gameEvent(GameEvent.ENTITY_KILLED);

            this.spawnAtLocation(this.entityData.get(RESOURCE));
        }

        this.invalidateCaps();
    }
}