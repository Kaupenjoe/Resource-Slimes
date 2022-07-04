package net.kaupenjoe.resourceslimes.screen.slot;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Supplier;

public class ModFluidSourceSlot extends SlotItemHandler {
    protected Supplier<Fluid>[] possibleFluids;

    public ModFluidSourceSlot(IItemHandler itemHandler, int index, int x, int y, Supplier<Fluid>... possibleFluids) {
        super(itemHandler, index, x, y);
        this.possibleFluids = possibleFluids;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        boolean toReturn = false;

        if(stack.getItem() instanceof BucketItem bucketItem) {
            for (Supplier<Fluid> fluid : possibleFluids) {
                toReturn = bucketItem.getFluid().equals(fluid.get());
            }

            return toReturn;
        }

        return false;
    }
}
