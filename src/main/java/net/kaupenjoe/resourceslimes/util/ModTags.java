package net.kaupenjoe.resourceslimes.util;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ResourceSlimes.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> UNCUT_GEMS = tag("gems/uncut_gems");
        public static final TagKey<Item> CUT_GEMS = tag("gems/cut_gems");
        public static final TagKey<Item> RAW_GEMS = tag("gems/raw_gems");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ResourceSlimes.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
