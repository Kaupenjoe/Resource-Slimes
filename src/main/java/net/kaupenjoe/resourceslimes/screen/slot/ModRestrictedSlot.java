package net.kaupenjoe.resourceslimes.screen.slot;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;
import java.util.function.Supplier;

public class ModRestrictedSlot extends SlotItemHandler {
    private Supplier<Item>[] possibleItems;

    @SafeVarargs
    public ModRestrictedSlot(IItemHandler itemHandler, int index, int x, int y, Supplier<Item>... possibleItems) {
        super(itemHandler, index, x, y);
        this.possibleItems = possibleItems;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return Arrays.stream(possibleItems).anyMatch(supplier -> supplier.get() == stack.getItem());
    }
}
