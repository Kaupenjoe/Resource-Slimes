package net.kaupenjoe.resourceslimes.block.entity;

import net.kaupenjoe.resourceslimes.util.KaupenEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public abstract class AbstractRSMachineBlockEntity extends BlockEntity implements
        MenuProvider, IFluidHandlingBlockEntity, IEnergyHandlingBlockEntity, IInventoryHandlingBlockEntity {
    public AbstractRSMachineBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    abstract FluidTank createFluidTank();
    abstract KaupenEnergyStorage createEnergyStorage();



}
