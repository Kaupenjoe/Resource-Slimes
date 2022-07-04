package net.kaupenjoe.resourceslimes.block.entity;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidHandlingBlockEntity {
    void setFluid(FluidStack fluid);
    FluidStack getFluid();
}
