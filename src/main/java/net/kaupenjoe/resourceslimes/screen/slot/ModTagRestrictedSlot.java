package net.kaupenjoe.resourceslimes.screen.slot;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;
import java.util.function.Supplier;

public class ModTagRestrictedSlot extends SlotItemHandler {
    protected Supplier<TagKey<Item>>[] possibleTags;

    public ModTagRestrictedSlot(IItemHandler itemHandler, int index, int x, int y, Supplier<TagKey<Item>>... itemTag) {
        super(itemHandler, index, x, y);
        this.possibleTags = itemTag;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        for(Supplier<TagKey<Item>> tag : possibleTags) {
            if (stack.is(tag.get())) return true;
        }
        return false;
    }
}
