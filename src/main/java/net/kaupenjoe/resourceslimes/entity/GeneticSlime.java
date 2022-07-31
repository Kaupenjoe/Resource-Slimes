package net.kaupenjoe.resourceslimes.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.Comparator;

public class GeneticSlime extends Slime {
    private static final EntityDataAccessor<Byte> DATA_MAIN_GENE = SynchedEntityData.defineId(GeneticSlime.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> HIDDEN_GENE_ID = SynchedEntityData.defineId(GeneticSlime.class, EntityDataSerializers.BYTE);
    static final TargetingConditions BREED_TARGETING = TargetingConditions.forNonCombat().range(8.0D);
    
    public GeneticSlime(EntityType<? extends Slime> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
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

    @SuppressWarnings("ReassignedVariable")
    public enum Gene {
        // @todo Kaupenjoe can add more :)
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
