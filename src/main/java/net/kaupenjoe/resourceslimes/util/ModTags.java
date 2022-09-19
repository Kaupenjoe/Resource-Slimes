package net.kaupenjoe.resourceslimes.util;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

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
        public static final TagKey<Item> SLIMEY_EXTRACTS = tag("extracts/slimey_extracts");
        public static final TagKey<Item> EXTRACTS = tag("extracts/extracts");

        public static final TagKey<Item> OILS = tag("oils");
        public static final TagKey<Item> SLIME_PEARL = tag("gems/slime_pearl");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ResourceSlimes.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Fluids {
        public static final TagKey<Fluid> SLIME_FLUIDS = tag("slime_fluids");

        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(new ResourceLocation(ResourceSlimes.MOD_ID, name));
        }

        private static TagKey<Fluid> forgeTag(String name) {
            return FluidTags.create(new ResourceLocation("forge", name));
        }
    }
}
