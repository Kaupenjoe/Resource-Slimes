package net.kaupenjoe.resourceslimes.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

// A Slime that has genetics and also rolls??
public class GeneticSlime extends Slime {
    private static final EntityDataAccessor<Byte> MAIN_GENE_ID = SynchedEntityData.defineId(GeneticSlime.class, EntityDataSerializers.BYTE);

    public GeneticSlime(EntityType<? extends Slime> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
}
