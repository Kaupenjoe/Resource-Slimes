package net.kaupenjoe.resourceslimes.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab RESOURCE_SLIMES = new CreativeModeTab("resourceslimetab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CUT_CITRINE.get());
        }
    };
}
