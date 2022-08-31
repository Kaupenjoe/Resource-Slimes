package net.kaupenjoe.resourceslimes.entity;

import net.kaupenjoe.resourceslimes.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import java.util.List;

public class EnergySlime extends Slime {
    public EnergySlime(EntityType<? extends Slime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();
        if(!isDeadOrDying()) {
            AABB boundingBox = this.getBoundingBox().inflate(5);
            List<BlockEntity> availableEnergyBlockEntities = BlockPos.betweenClosedStream(boundingBox)
                    .filter(p -> level.getBlockState(p).hasBlockEntity())
                    .map(this::getEntityFromState)
                    .filter(bE -> bE != null && bE.getCapability(ForgeCapabilities.ENERGY).isPresent()).toList();

            sendEnergy(availableEnergyBlockEntities,20);
        }
    }

    private BlockEntity getEntityFromState(BlockPos pos) {
        return level.getBlockEntity(pos);
    }

    private void sendEnergy(List<BlockEntity> blockEntities, int amount) {
        for (var blockEntity : blockEntities) {
            blockEntity.getCapability(ForgeCapabilities.ENERGY).ifPresent(handler -> {
                if(handler.receiveEnergy(amount, false) > 0) {
                    test(blockEntity.getBlockPos());
                }
            });
        }
    }
    
    private void test(BlockPos pos) {
        if(level.isClientSide()) {
            return;
        }

        var data = ModParticles.ENERGY_PARTICLES.get();
        var targetV = new Vec3(this.getX(), this.getY(), this.getZ());
        var blockEntity = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        var movement = blockEntity.subtract(targetV);
        double distance = blockEntity.distanceTo(targetV);

        ((ServerLevel) this.level).sendParticles(data, this.getX(), this.getY(), this.getZ(),
                0, movement.x, movement.y, movement.z, (distance / 40));
    }
}
