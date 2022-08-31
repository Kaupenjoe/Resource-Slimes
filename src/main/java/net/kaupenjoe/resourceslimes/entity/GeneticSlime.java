package net.kaupenjoe.resourceslimes.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Comparator;

public class GeneticSlime extends Animal {
    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(GeneticSlime.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_MAIN_GENE = SynchedEntityData.defineId(GeneticSlime.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> HIDDEN_GENE_ID = SynchedEntityData.defineId(GeneticSlime.class, EntityDataSerializers.BYTE);
    static final TargetingConditions BREED_TARGETING = TargetingConditions.forNonCombat().range(8.0D);

    public float targetSquish;
    public float squish;
    public float oSquish;
    private boolean wasOnGround;

    public GeneticSlime(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntityTypes.GENETIC_SLIME.get().create(pLevel);
    }

    public void setSize(int pSize, boolean pResetHealth) {
        int i = Mth.clamp(pSize, 1, 127);
        this.entityData.set(ID_SIZE, i);
        this.reapplyPosition();
        this.refreshDimensions();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(i * i);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F + 0.1F * (float)i);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(i);
        if (pResetHealth) {
            this.setHealth(this.getMaxHealth());
        }

        this.xpReward = i;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_MAIN_GENE, ((byte) 0));
        this.entityData.define(HIDDEN_GENE_ID, ((byte) 0));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("MainGene", getMainGene().name);
        pCompound.putString("HiddenGene", getHiddenGene().name);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setMainGene(Gene.byName(pCompound.getString("MainGene")));
        this.setHiddenGene(Gene.byName(pCompound.getString("HiddenGene")));
    }

    private void setMainGene(Gene mainGene) {
        this.entityData.set(DATA_MAIN_GENE, ((byte) mainGene.id));
    }

    private void setHiddenGene(Gene hiddenGene) {
        this.entityData.set(HIDDEN_GENE_ID, ((byte) hiddenGene.id));
    }

    public Gene getMainGene() {
        return Gene.byId(this.entityData.get(DATA_MAIN_GENE));
    }

    public Gene getHiddenGene() {
        return Gene.byId(this.entityData.get(HIDDEN_GENE_ID));
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_33641_) -> {
            return Math.abs(p_33641_.getY() - this.getY()) <= 4.0D;
        }));
    }

    public boolean isTiny() {
        return this.getSize() <= 1;
    }

    private int getSize() {
        return this.entityData.get(ID_SIZE);
    }

    protected ParticleOptions getParticleType() {
        return ParticleTypes.ITEM_SLIME;
    }

    protected boolean shouldDespawnInPeaceful() {
        return this.getSize() > 0;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        this.squish += (this.targetSquish - this.squish) * 0.5F;
        this.oSquish = this.squish;
        super.tick();
        if (this.onGround && !this.wasOnGround) {
            int i = this.entityData.get(ID_SIZE);

            if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
            for(int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float)Math.PI * 2F);
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = Mth.sin(f) * (float)i * 0.5F * f1;
                float f3 = Mth.cos(f) * (float)i * 0.5F * f1;
                this.level.addParticle(this.getParticleType(), this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            this.targetSquish = -0.5F;
        } else if (!this.onGround && this.wasOnGround) {
            this.targetSquish = 1.0F;
        }

        this.wasOnGround = this.onGround;
        this.decreaseSquish();
    }

    protected boolean spawnCustomParticles() { return false; }

    protected void decreaseSquish() {
        this.targetSquish *= 0.6F;
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    protected int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }

    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (ID_SIZE.equals(pKey)) {
            this.refreshDimensions();
            this.setYRot(this.yHeadRot);
            this.yBodyRot = this.yHeadRot;
            if (this.isInWater() && this.random.nextInt(20) == 0) {
                this.doWaterSplashEffect();
            }
        }

        super.onSyncedDataUpdated(pKey);
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.isTiny() ? SoundEvents.SLIME_HURT_SMALL : SoundEvents.SLIME_HURT;
    }

    protected SoundEvent getDeathSound() {
        return this.isTiny() ? SoundEvents.SLIME_DEATH_SMALL : SoundEvents.SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return this.isTiny() ? SoundEvents.SLIME_SQUISH_SMALL : SoundEvents.SLIME_SQUISH;
    }

    public static boolean checkSlimeSpawnRules(EntityType<Slime> p_219113_, LevelAccessor p_219114_, MobSpawnType p_219115_, BlockPos p_219116_, RandomSource p_219117_) {
        if (p_219114_.getDifficulty() != Difficulty.PEACEFUL) {
            if (p_219114_.getBiome(p_219116_).is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS) && p_219116_.getY() > 50 && p_219116_.getY() < 70 && p_219117_.nextFloat() < 0.5F && p_219117_.nextFloat() < p_219114_.getMoonBrightness() && p_219114_.getMaxLocalRawBrightness(p_219116_) <= p_219117_.nextInt(8)) {
                return checkMobSpawnRules(p_219113_, p_219114_, p_219115_, p_219116_, p_219117_);
            }

            if (!(p_219114_ instanceof WorldGenLevel)) {
                return false;
            }

            ChunkPos chunkpos = new ChunkPos(p_219116_);
            boolean flag = WorldgenRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, ((WorldGenLevel)p_219114_).getSeed(), 987234911L).nextInt(10) == 0;
            if (p_219117_.nextInt(10) == 0 && flag && p_219116_.getY() < 40) {
                return checkMobSpawnRules(p_219113_, p_219114_, p_219115_, p_219116_, p_219117_);
            }
        }

        return false;
    }

    public enum Gene {
        // @todo Kaupenjoe can add more... right kaupen? :)
        NORMAL(0, "normal", false);

        private static final Gene[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(t -> t.id)).toArray(Gene[]::new);

        public final int id;
        public final String name;
        public final boolean isRecessive;

        Gene(int id, String name, boolean isRecessive) {
            this.id = id;
            this.name = name;
            this.isRecessive = isRecessive;
        }

        public static GeneticSlime.Gene byId(int pIndex) {
            if (pIndex < 0 || pIndex >= BY_ID.length) {
                pIndex = 0;
            }

            return BY_ID[pIndex];
        }

        public static GeneticSlime.Gene byName(String pName) {
            for(GeneticSlime.Gene panda$gene : values()) {
                if (panda$gene.name.equals(pName)) {
                    return panda$gene;
                }
            }

            return NORMAL;
        }
    }
}
