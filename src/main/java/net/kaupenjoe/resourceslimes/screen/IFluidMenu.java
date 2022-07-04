package net.kaupenjoe.resourceslimes.screen;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidMenu {
    void setFluid(FluidStack fluidStack);
    FluidStack getFluid();
    BlockEntity getBlockEntity();
}
