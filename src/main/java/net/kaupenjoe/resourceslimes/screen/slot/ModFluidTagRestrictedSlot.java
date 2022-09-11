package net.kaupenjoe.resourceslimes.screen.slot;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ModFluidTagRestrictedSlot extends SlotItemHandler {
    protected Supplier<TagKey<Fluid>> possibleTag;

    public ModFluidTagRestrictedSlot(IItemHandler itemHandler, int index, int x, int y, Supplier<TagKey<Fluid>> fluidTag) {
        super(itemHandler, index, x, y);
        this.possibleTag = fluidTag;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        AtomicBoolean toReturn = new AtomicBoolean(false);
        stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
            toReturn.set(handler.getFluidInTank(0).getFluid().is(possibleTag.get()));
        });
        return toReturn.get();
    }
}
