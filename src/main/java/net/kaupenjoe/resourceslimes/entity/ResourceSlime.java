package net.kaupenjoe.resourceslimes.entity;

import java.util.EnumSet;

import org.jetbrains.annotations.Nullable;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.item.custom.ExtractItem;
import net.kaupenjoe.resourceslimes.util.ModRegistries;
import net.kaupenjoe.resourceslimes.util.resources.BuiltinSlimeResources;
import net.kaupenjoe.resourceslimes.util.resources.ResourceTier;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.Util;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;

// TODO: Using Slime as it super class mean it has TWO size values
public class ResourceSlime extends Slime {
	private static final EntityDataAccessor<ItemStack> RESOURCE = SynchedEntityData.defineId(ResourceSlime.class,
			EntityDataSerializers.ITEM_STACK);
	private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(ResourceSlime.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> GROWTH_COUNTER = SynchedEntityData.defineId(ResourceSlime.class,
			EntityDataSerializers.INT);

	// TODO: Should be based on Resource / Tier
	public static final int GROWTH_TIME = 400; // 20 Seconds for each growth

	public ResourceSlime(EntityType<? extends Slime> entityType, Level level) {
		super(entityType, level);
		this.moveControl = new ResourceSlime.SlimeMoveControl(this);
	}

	// TODO: MAKE THIS CLEAN! YES PLEASE :D
	@Override
	public Component getName() {
		final String string = I18n.get("entity.resourceslimes.resource_slime");
		final Item item = this.entityData.get(RESOURCE).getItem();
		final SlimeResource resourceBySlimeyExtractItem = SlimeResource.getResourceBySlimeyExtractItem(item);
		final ItemStack stack = new ItemStack(resourceBySlimeyExtractItem.getExtractItem());
		final Component extractNameKey = ((ExtractItem) resourceBySlimeyExtractItem.getExtractItem())
				.getExtractNameKey(stack);
		return Component.literal(I18n.get(extractNameKey.getString()) + " " + string);
	}

	@Override
	protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
		if (pHand == InteractionHand.MAIN_HAND) {
			if (pPlayer.isCrouching()) {
				outputInfo(pPlayer);
				changeResourceDEBUG(pPlayer.getItemInHand(pHand));

				if (pPlayer.getItemInHand(pHand).getItem() == Items.STICK) {
					growSlimeDEBUG();
				}
			} else {
				if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.BUCKET) {
					harvestResource(pPlayer, pHand);
				}
			}
		}

		return super.mobInteract(pPlayer, pHand);
	}

	private void changeResourceDEBUG(ItemStack stack) {
		if (SlimeResource.getResourceBySlimeyExtractItem(stack.getItem()) != BuiltinSlimeResources.EMPTY.get()
				&& !stack.isEmpty()) {
			this.setResource(stack);
		}
	}

	private void growSlimeDEBUG() {
		ItemStack stack = this.entityData.get(RESOURCE);
		stack.setCount(64);
		this.setResource(stack);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new ResourceSlime.SlimeFloatGoal(this));
		this.goalSelector.addGoal(3, new ResourceSlime.SlimeRandomDirectionGoal(this));
		this.goalSelector.addGoal(5, new ResourceSlime.SlimeKeepOnJumpingGoal(this));
	}

	private void harvestResource(Player pPlayer, InteractionHand hand) {
		if (this.entityData.get(RESOURCE).getCount() >= 17) {
			ItemStack stackToSpawn = this.entityData.get(RESOURCE).copy();
			stackToSpawn.setCount(16);

			harvestTierFluid(pPlayer, hand);
			this.spawnAtLocation(stackToSpawn);
			shrinkOnHarvest(stackToSpawn);
		}

		ResourceSlimes.LOGGER.debug(pPlayer.getName() + " harvested: " + this.entityData.get(RESOURCE));
	}

	private void harvestTierFluid(Player pPlayer, InteractionHand hand) {
		ItemStack inHand = pPlayer.getItemInHand(hand);
		ResourceTier tier = SlimeResource.getTierByItem(this.entityData.get(RESOURCE).getItem());

		ItemStack tierFluid = ItemUtils.createFilledResult(inHand, pPlayer,
				new ItemStack(ResourceTier.getFluidBucketItem(tier)));
		pPlayer.setItemInHand(hand, tierFluid);
	}

	// TODO: customize to what size the slime can be reduced to
	private void shrinkOnHarvest(ItemStack stack) {
		setResource(new ItemStack(stack.getItem(), this.entityData.get(RESOURCE).getCount() - stack.getCount()));
	}

	private void outputInfo(Player pPlayer) {
		pPlayer.sendSystemMessage(Component.literal("Resource: " + this.entityData.get(RESOURCE)));
		pPlayer.sendSystemMessage(Component.literal("Size: " + this.entityData.get(ID_SIZE)));
		pPlayer.sendSystemMessage(Component.literal("Counter: " + this.entityData.get(GROWTH_COUNTER)));
		pPlayer.sendSystemMessage(Component.literal(
				"Tier: " + SlimeResource.getResourceBySlimeyExtractItem(this.entityData.get(RESOURCE).getItem())));
	}

	@Override
	protected boolean isDealsDamage() {
		return false;
	}

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
			MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {
		SlimeResource resource = Util.getRandom(ModRegistries.SLIME_RESOURCES.get().getValues().stream()
				.filter(sr -> sr != BuiltinSlimeResources.EMPTY.get() && sr.isEnabled()).toList(), this.random);
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

		ResourceSlimes.LOGGER.debug("Reading Resource: " + ItemStack.of(pCompound.getCompound("resource")) + " | "
				+ this.entityData.get(RESOURCE));
		ResourceSlimes.LOGGER.debug("Reading Growth Counter: " + pCompound.getInt("growth_counter"));
		ResourceSlimes.LOGGER.debug("Reading Size: " + pCompound.getInt("size"));
	}

	public void setResource(ItemStack stack) {
		this.entityData.set(RESOURCE, stack);
		resetGrowthCount();
		recalculateSize();
	}

	public ItemStack getResourceItem() {
		if (!this.entityData.get(RESOURCE).isEmpty()) {
			return this.entityData.get(RESOURCE);
		}

		return ItemStack.EMPTY;
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
		if (!isDeadOrDying()) {
			countGrowth();

			if (readyForNewResource()) {
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
		if (stack.getCount() < 64) {
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
		return false;
	}

	@Override
	public int getSize() {
		return this.entityData.get(ID_SIZE);
	}

	@Override
	public void setSize(int pSize, boolean pResetHealth) {
		// Setting the size based on the number of resources
		// int newSize = this.entityData.get(RESOURCE).getCount() * 2 - 1; // INSANE
		// GROWTH (64 -> Size 127)
		int newSize = (this.entityData.get(RESOURCE).getCount() / 16) + 1; // Normal Growth (64 -> Size 5)
		int sizeBefore = this.entityData.get(ID_SIZE);
		this.entityData.set(ID_SIZE, newSize);

		this.reapplyPosition();
		this.refreshDimensions();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((newSize * newSize));
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((0.2F + 0.1F * (float) newSize));
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
			this.gameEvent(GameEvent.ENTITY_DIE);

			this.spawnAtLocation(this.entityData.get(RESOURCE));
		}

		this.invalidateCaps();
	}

	private float getSoundPitch() {
		float f = this.isTiny() ? 1.4F : 0.8F;
		return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
	}

	static class SlimeFloatGoal extends Goal {
		private final ResourceSlime slime;

		public SlimeFloatGoal(ResourceSlime p_33655_) {
			this.slime = p_33655_;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
			p_33655_.getNavigation().setCanFloat(true);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		public boolean canUse() {
			return (this.slime.isInWater() || this.slime.isInLava())
					&& this.slime.getMoveControl() instanceof ResourceSlime.SlimeMoveControl;
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (this.slime.getRandom().nextFloat() < 0.8F) {
				this.slime.getJumpControl().jump();
			}

			((ResourceSlime.SlimeMoveControl) this.slime.getMoveControl()).setWantedMovement(1.2D);
		}
	}

	static class SlimeKeepOnJumpingGoal extends Goal {
		private final ResourceSlime slime;

		public SlimeKeepOnJumpingGoal(ResourceSlime p_33660_) {
			this.slime = p_33660_;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		public boolean canUse() {
			return !this.slime.isPassenger();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			((ResourceSlime.SlimeMoveControl) this.slime.getMoveControl()).setWantedMovement(1.0D);
		}
	}

	static class SlimeMoveControl extends MoveControl {
		private float yRot;
		private int jumpDelay;
		private final ResourceSlime slime;
		private boolean isAggressive;

		public SlimeMoveControl(ResourceSlime p_33668_) {
			super(p_33668_);
			this.slime = p_33668_;
			this.yRot = 180.0F * p_33668_.getYRot() / (float) Math.PI;
		}

		public void setDirection(float pYRot, boolean pAggressive) {
			this.yRot = pYRot;
			this.isAggressive = pAggressive;
		}

		public void setWantedMovement(double pSpeed) {
			this.speedModifier = pSpeed;
			this.operation = MoveControl.Operation.MOVE_TO;
		}

		public void tick() {
			this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90.0F));
			this.mob.yHeadRot = this.mob.getYRot();
			this.mob.yBodyRot = this.mob.getYRot();
			if (this.operation != MoveControl.Operation.MOVE_TO) {
				this.mob.setZza(0.0F);
			} else {
				this.operation = MoveControl.Operation.WAIT;
				if (this.mob.isOnGround()) {
					this.mob.setSpeed(
							(float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
					if (this.jumpDelay-- <= 0) {
						this.jumpDelay = this.slime.getJumpDelay();
						if (this.isAggressive) {
							this.jumpDelay /= 3;
						}

						this.slime.getJumpControl().jump();
						if (this.slime.doPlayJumpSound()) {
							this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(),
									this.slime.getSoundPitch());
						}
					} else {
						this.slime.xxa = 0.0F;
						this.slime.zza = 0.0F;
						this.mob.setSpeed(0.0F);
					}
				} else {
					this.mob.setSpeed(
							(float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
				}

			}
		}
	}

	static class SlimeRandomDirectionGoal extends Goal {
		private final ResourceSlime slime;
		private float chosenDegrees;
		private int nextRandomizeTime;

		public SlimeRandomDirectionGoal(ResourceSlime p_33679_) {
			this.slime = p_33679_;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		public boolean canUse() {
			return this.slime.getTarget() == null
					&& (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava()
							|| this.slime.hasEffect(MobEffects.LEVITATION))
					&& this.slime.getMoveControl() instanceof ResourceSlime.SlimeMoveControl;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (--this.nextRandomizeTime <= 0) {
				this.nextRandomizeTime = this.adjustedTickDelay(40 + this.slime.getRandom().nextInt(60));
				this.chosenDegrees = (float) this.slime.getRandom().nextInt(360);
			}

			((SlimeMoveControl) this.slime.getMoveControl()).setDirection(this.chosenDegrees, false);
		}
	}
}