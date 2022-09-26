package net.kaupenjoe.resourceslimes.block.entity;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.util.KaupenEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class EnergyReceiverBlockEntity extends BlockEntity {
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
    private final KaupenEnergyStorage ENERGY_STORAGE = createEnergyStorage();

    public EnergyReceiverBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ENERGY_RECEIVER.get(), pWorldPosition, pBlockState);
    }

    @NotNull
    protected KaupenEnergyStorage createEnergyStorage() {
        return new KaupenEnergyStorage(60000, 600) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, EnergyReceiverBlockEntity entity) {
        if(level.getBlockState(blockPos.above()).getBlock() != Blocks.AIR) {
            if(level.getBlockEntity(blockPos.above()) != null) {
                BlockEntity ent = level.getBlockEntity(blockPos.above());
                ent.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent(handler -> {
                    if(handler.canReceive()) {
                        handler.receiveEnergy(600, false);
                    }
                });
            }
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();

        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }
}
