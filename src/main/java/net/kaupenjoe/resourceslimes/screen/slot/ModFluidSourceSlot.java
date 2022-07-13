package net.kaupenjoe.resourceslimes.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ModFluidSourceSlot extends SlotItemHandler {
    protected Supplier<Fluid>[] possibleFluids;

    public ModFluidSourceSlot(IItemHandler itemHandler, int index, int x, int y, Supplier<Fluid>... possibleFluids) {
        super(itemHandler, index, x, y);
        this.possibleFluids = possibleFluids;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        AtomicBoolean toReturn = new AtomicBoolean(false);

        stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
            for (Supplier<Fluid> fluid : possibleFluids) {
                toReturn.set(handler.getFluidInTank(0).getFluid().equals(fluid.get()));
            }
        });

        return toReturn.get();
    }
}
