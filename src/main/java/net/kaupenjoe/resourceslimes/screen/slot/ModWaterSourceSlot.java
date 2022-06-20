package net.kaupenjoe.resourceslimes.screen.slot;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;
import java.util.function.Supplier;

public class ModWaterSourceSlot extends SlotItemHandler {
    public ModWaterSourceSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if(stack.getItem() instanceof PotionItem && PotionUtils.getPotion(stack).equals(Potions.WATER)) {
            return true;
        }

        if(stack.getItem() instanceof BucketItem bucketItem
                && bucketItem.getFluid().equals(Fluids.WATER)) {
            return true;
        }

        return false;
    }
}
