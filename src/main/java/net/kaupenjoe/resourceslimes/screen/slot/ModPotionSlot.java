package net.kaupenjoe.resourceslimes.screen.slot;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;
import java.util.function.Supplier;

public class ModPotionSlot extends SlotItemHandler {
    private Supplier<Potion>[] possiblePotions;

    @SafeVarargs
    public ModPotionSlot(IItemHandler itemHandler, int index, int x, int y, Supplier<Potion>... possiblePotions) {
        super(itemHandler, index, x, y);
        this.possiblePotions = possiblePotions;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return Arrays.stream(possiblePotions).anyMatch(supplier -> supplier.get() == PotionUtils.getPotion(stack));
    }
}
