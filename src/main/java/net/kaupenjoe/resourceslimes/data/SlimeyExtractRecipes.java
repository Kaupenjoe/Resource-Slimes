package net.kaupenjoe.resourceslimes.data;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class SlimeyExtractRecipes extends RecipeProvider {
    public SlimeyExtractRecipes(DataGenerator pGenerator) {
        super(pGenerator);
    }

    static void stoneSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike stone_slimey_extract, ItemLike grass_slimey_extract) {
        ShapedRecipeBuilder.shaped(Blocks.STONE, 8)
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', stone_slimey_extract)
                .unlockedBy("has_stone_slimey_extract", has(stone_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "stone_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.ANDESITE, 16)
                .pattern("EEE")
                .pattern("EEE")
                .pattern("E E")
                .define('E', stone_slimey_extract)
                .unlockedBy("has_stone_slimey_extract", has(stone_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "andesite_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.GRANITE, 16)
                .pattern("EE ")
                .pattern("E E")
                .pattern("EEE")
                .define('E', stone_slimey_extract)
                .unlockedBy("has_stone_slimey_extract", has(stone_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "granite_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.DIORITE, 16)
                .pattern("EE ")
                .pattern("E E")
                .pattern("EE ")
                .define('E', stone_slimey_extract)
                .unlockedBy("has_stone_slimey_extract", has(stone_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "diorite_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.COBBLESTONE, 32)
                .pattern("EEE")
                .pattern("E E")
                .pattern("EEE")
                .define('E', stone_slimey_extract)
                .unlockedBy("has_stone_slimey_extract", has(stone_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "cobblestone_from_slimey_extract"));

        ShapedRecipeBuilder.shaped(Blocks.MOSSY_COBBLESTONE, 16)
                .pattern("GEG")
                .pattern("EGE")
                .pattern("GEG")
                .define('E', stone_slimey_extract)
                .define('G', grass_slimey_extract)
                .unlockedBy("has_stone_slimey_extract", has(stone_slimey_extract))
                .unlockedBy("has_grass_slimey_extract", has(grass_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "mossy_cobblestone_from_slimey_extract"));
    }

    static void dirtSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike dirt_slimey_extract,
                                   ItemLike grass_slimey_extract, ItemLike fire_slimey_extract) {
        ShapedRecipeBuilder.shaped(Blocks.DIRT, 32)
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', dirt_slimey_extract)
                .unlockedBy("has_dirt_slimey_extract", has(dirt_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "dirt_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.GRASS, 16)
                .pattern("GGG")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', dirt_slimey_extract)
                .define('G', grass_slimey_extract)
                .unlockedBy("has_dirt_slimey_extract", has(dirt_slimey_extract))
                .unlockedBy("has_grass_slimey_extract", has(grass_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "grass_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.PODZOL, 16)
                .pattern("FFF")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', dirt_slimey_extract)
                .define('F', fire_slimey_extract)
                .unlockedBy("has_dirt_slimey_extract", has(dirt_slimey_extract))
                .unlockedBy("has_fire_slimey_extract", has(fire_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "podzol_from_slimey_extract"));
    }

    static void waterSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike water_slimey_extract, ItemLike fire_slimey_extract) {
        ShapedRecipeBuilder.shaped(Blocks.OBSIDIAN, 2)
                .pattern("FWF")
                .pattern("WFW")
                .pattern("FWF")
                .define('W', water_slimey_extract)
                .define('F', fire_slimey_extract)
                .unlockedBy("has_water_slimey_extract", has(water_slimey_extract))
                .unlockedBy("has_fire_slimey_extract", has(fire_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "obsidian_from_slimey_extract"));

        ShapedRecipeBuilder.shaped(Items.WATER_BUCKET, 1)
                .pattern(" W ")
                .pattern(" W ")
                .pattern(" B ")
                .define('W', water_slimey_extract)
                .define('B', Items.BUCKET)
                .unlockedBy("has_water_slimey_extract", has(water_slimey_extract))
                .unlockedBy("has_bucket", has(Items.BUCKET))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "water_bucket_from_slimey_extract"));
    }

    static void sandSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike sand_slimey_extract, ItemLike fire_slimey_extract) {
        ShapedRecipeBuilder.shaped(Blocks.SAND, 16)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', sand_slimey_extract)
                .unlockedBy("has_sand_slimey_extract", has(sand_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "sand_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.SANDSTONE, 16)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("FFF")
                .define('S', sand_slimey_extract)
                .define('F', fire_slimey_extract)
                .unlockedBy("has_sand_slimey_extract", has(sand_slimey_extract))
                .unlockedBy("has_fire_slimey_extract", has(fire_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "sandstone_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.GLASS, 8)
                .pattern("FSF")
                .pattern("SFS")
                .pattern("FSF")
                .define('S', sand_slimey_extract)
                .define('F', fire_slimey_extract)
                .unlockedBy("has_sand_slimey_extract", has(sand_slimey_extract))
                .unlockedBy("has_fire_slimey_extract", has(fire_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "glass_from_slimey_extract"));
    }

    static void woodSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike wood_slimey_extract) {
        ShapedRecipeBuilder.shaped(Blocks.ACACIA_LOG, 8)
                .pattern("WWW")
                .pattern("W  ")
                .pattern("W  ")
                .define('W', wood_slimey_extract)
                .unlockedBy("has_wood_slimey_extract", has(wood_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "acacia_log_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.BIRCH_LOG, 8)
                .pattern("WWW")
                .pattern("  W")
                .pattern(" W ")
                .define('W', wood_slimey_extract)
                .unlockedBy("has_wood_slimey_extract", has(wood_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "birch_log_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.DARK_OAK_LOG, 8)
                .pattern(" W ")
                .pattern("W  ")
                .pattern("WWW")
                .define('W', wood_slimey_extract)
                .unlockedBy("has_wood_slimey_extract", has(wood_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "dark_oak_log_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.JUNGLE_LOG, 8)
                .pattern("  W")
                .pattern("  W")
                .pattern("WWW")
                .define('W', wood_slimey_extract)
                .unlockedBy("has_wood_slimey_extract", has(wood_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "jungle_log_from_slimey_extract"));
        ShapedRecipeBuilder.shaped(Blocks.OAK_LOG, 8)
                .pattern(" W ")
                .pattern("W W")
                .pattern("WWW")
                .define('W', wood_slimey_extract)
                .unlockedBy("has_wood_slimey_extract", has(wood_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "oak_log_from_slimey_extract"));
    }

    static void ironSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike iron_slimey_extract) {
        ShapedRecipeBuilder.shaped(Items.IRON_INGOT, 4)
                .pattern("III")
                .pattern("III")
                .define('I', iron_slimey_extract)
                .unlockedBy("has_iron_slimey_extract", has(iron_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "iron_ingot_from_slimey_extract"));
    }

    static void quartzSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike quartz_slimey_extract) {
        ShapedRecipeBuilder.shaped(Items.QUARTZ, 8)
                .pattern("QQQ")
                .pattern("QQQ")
                .define('Q', quartz_slimey_extract)
                .unlockedBy("has_iron_slimey_extract", has(quartz_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "quartz_from_slimey_extract"));
    }

    static void copperSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike copper_slimey_extract) {
        ShapedRecipeBuilder.shaped(Items.COPPER_INGOT, 4)
                .pattern("CCC")
                .pattern("CCC")
                .define('C', copper_slimey_extract)
                .unlockedBy("has_copper_slimey_extract", has(copper_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "copper_ingot_from_slimey_extract"));
    }

    static void lapisSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike lapis_slimey_extract) {
        ShapedRecipeBuilder.shaped(Items.LAPIS_LAZULI, 4)
                .pattern("LLL")
                .pattern("LLL")
                .define('L', lapis_slimey_extract)
                .unlockedBy("has_lapis_slimey_extract", has(lapis_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "lapis_from_slimey_extract"));
    }

    static void goldSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike gold_slimey_extract) {
        ShapedRecipeBuilder.shaped(Items.GOLD_INGOT, 3)
                .pattern("GGG")
                .pattern("GGG")
                .define('G', gold_slimey_extract)
                .unlockedBy("has_gold_slimey_extract", has(gold_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "gold_ingot_from_slimey_extract"));
    }

    static void deepslateSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike deepslate_slimey_extract) {
        ShapedRecipeBuilder.shaped(Items.DEEPSLATE, 8)
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', deepslate_slimey_extract)
                .unlockedBy("has_deepslate_slimey_extract", has(deepslate_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "deepslate_from_slimey_extract"));

        ShapedRecipeBuilder.shaped(Items.COBBLED_DEEPSLATE, 8)
                .pattern("DDD")
                .pattern("D D")
                .pattern("DDD")
                .define('D', deepslate_slimey_extract)
                .unlockedBy("has_deepslate_slimey_extract", has(deepslate_slimey_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "cobbled_deepslate_from_slimey_extract"));
    }

    static void xSlimeyExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

    }
}
