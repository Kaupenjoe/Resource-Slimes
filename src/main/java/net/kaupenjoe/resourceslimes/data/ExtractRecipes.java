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

public class ExtractRecipes extends RecipeProvider {
    public ExtractRecipes(DataGenerator pGenerator) {
        super(pGenerator);
    }

    static void stoneExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike stone_extract, ItemLike grass_extract) {
        ShapedRecipeBuilder.shaped(Blocks.STONE, 16)
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', stone_extract)
                .unlockedBy("has_stone_extract", has(stone_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "stone_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.ANDESITE, 32)
                .pattern("EEE")
                .pattern("EEE")
                .pattern("E E")
                .define('E', stone_extract)
                .unlockedBy("has_stone_extract", has(stone_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "andesite_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.GRANITE, 32)
                .pattern("EE ")
                .pattern("E E")
                .pattern("EEE")
                .define('E', stone_extract)
                .unlockedBy("has_stone_extract", has(stone_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "granite_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.DIORITE, 32)
                .pattern("EE ")
                .pattern("E E")
                .pattern("EE ")
                .define('E', stone_extract)
                .unlockedBy("has_stone_extract", has(stone_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "diorite_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.COBBLESTONE, 64)
                .pattern("EEE")
                .pattern("E E")
                .pattern("EEE")
                .define('E', stone_extract)
                .unlockedBy("has_stone_extract", has(stone_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "cobblestone_from_extract"));

        ShapedRecipeBuilder.shaped(Blocks.MOSSY_COBBLESTONE, 32)
                .pattern("GEG")
                .pattern("EGE")
                .pattern("GEG")
                .define('E', stone_extract)
                .define('G', grass_extract)
                .unlockedBy("has_stone_extract", has(stone_extract))
                .unlockedBy("has_grass_extract", has(grass_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "mossy_cobblestone_from_extract"));
    }

    static void dirtExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike dirt_extract,
                                   ItemLike grass_extract, ItemLike fire_extract) {
        ShapedRecipeBuilder.shaped(Blocks.DIRT, 64)
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', dirt_extract)
                .unlockedBy("has_dirt_extract", has(dirt_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "dirt_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.GRASS, 32)
                .pattern("GGG")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', dirt_extract)
                .define('G', grass_extract)
                .unlockedBy("has_dirt_extract", has(dirt_extract))
                .unlockedBy("has_grass_extract", has(grass_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "grass_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.PODZOL, 32)
                .pattern("FFF")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', dirt_extract)
                .define('F', fire_extract)
                .unlockedBy("has_dirt_extract", has(dirt_extract))
                .unlockedBy("has_fire_extract", has(fire_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "podzol_from_extract"));
    }

    static void waterExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike water_extract, ItemLike fire_extract) {
        ShapedRecipeBuilder.shaped(Blocks.OBSIDIAN, 4)
                .pattern("FWF")
                .pattern("WFW")
                .pattern("FWF")
                .define('W', water_extract)
                .define('F', fire_extract)
                .unlockedBy("has_water_extract", has(water_extract))
                .unlockedBy("has_fire_extract", has(fire_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "obsidian_from_extract"));

        ShapedRecipeBuilder.shaped(Items.WATER_BUCKET, 1)
                .pattern("W")
                .pattern("B")
                .define('W', water_extract)
                .define('B', Items.BUCKET)
                .unlockedBy("has_water_extract", has(water_extract))
                .unlockedBy("has_bucket", has(Items.BUCKET))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "water_bucket_from_extract"));
    }

    static void sandExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike sand_extract, ItemLike fire_extract) {
        ShapedRecipeBuilder.shaped(Blocks.SAND, 32)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', sand_extract)
                .unlockedBy("has_sand_extract", has(sand_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "sand_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.SANDSTONE, 32)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("FFF")
                .define('S', sand_extract)
                .define('F', fire_extract)
                .unlockedBy("has_sand_extract", has(sand_extract))
                .unlockedBy("has_fire_extract", has(fire_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "sandstone_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.GLASS, 16)
                .pattern("FSF")
                .pattern("SFS")
                .pattern("FSF")
                .define('S', sand_extract)
                .define('F', fire_extract)
                .unlockedBy("has_sand_extract", has(sand_extract))
                .unlockedBy("has_fire_extract", has(fire_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "glass_from_extract"));
    }

    static void woodExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike wood_extract) {
        ShapedRecipeBuilder.shaped(Blocks.ACACIA_LOG, 16)
                .pattern("WWW")
                .pattern("W  ")
                .pattern("W  ")
                .define('W', wood_extract)
                .unlockedBy("has_wood_extract", has(wood_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "acacia_log_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.BIRCH_LOG, 16)
                .pattern("WWW")
                .pattern("  W")
                .pattern(" W ")
                .define('W', wood_extract)
                .unlockedBy("has_wood_extract", has(wood_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "birch_log_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.DARK_OAK_LOG, 16)
                .pattern(" W ")
                .pattern("W  ")
                .pattern("WWW")
                .define('W', wood_extract)
                .unlockedBy("has_wood_extract", has(wood_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "dark_oak_log_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.JUNGLE_LOG, 16)
                .pattern("  W")
                .pattern("  W")
                .pattern("WWW")
                .define('W', wood_extract)
                .unlockedBy("has_wood_extract", has(wood_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "jungle_log_from_extract"));
        ShapedRecipeBuilder.shaped(Blocks.OAK_LOG, 16)
                .pattern(" W ")
                .pattern("W W")
                .pattern("WWW")
                .define('W', wood_extract)
                .unlockedBy("has_wood_extract", has(wood_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "oak_log_from_extract"));
    }

    static void ironExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike iron_extract) {
        ShapedRecipeBuilder.shaped(Items.IRON_INGOT, 8)
                .pattern("III")
                .pattern("III")
                .define('I', iron_extract)
                .unlockedBy("has_iron_extract", has(iron_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "iron_ingot_from_extract"));
    }

    static void quartzExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike quartz_extract) {
        ShapedRecipeBuilder.shaped(Items.QUARTZ, 16)
                .pattern("QQQ")
                .pattern("QQQ")
                .define('Q', quartz_extract)
                .unlockedBy("has_iron_extract", has(quartz_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "quartz_from_extract"));
    }

    static void copperExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike copper_extract) {
        ShapedRecipeBuilder.shaped(Items.COPPER_INGOT, 8)
                .pattern("CCC")
                .pattern("CCC")
                .define('C', copper_extract)
                .unlockedBy("has_copper_extract", has(copper_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "copper_ingot_from_extract"));
    }

    static void lapisExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike lapis_extract) {
        ShapedRecipeBuilder.shaped(Items.LAPIS_LAZULI, 8)
                .pattern("LLL")
                .pattern("LLL")
                .define('L', lapis_extract)
                .unlockedBy("has_lapis_extract", has(lapis_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "lapis_from_extract"));
    }

    static void goldExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike gold_extract) {
        ShapedRecipeBuilder.shaped(Items.GOLD_INGOT, 6)
                .pattern("GGG")
                .pattern("GGG")
                .define('G', gold_extract)
                .unlockedBy("has_gold_extract", has(gold_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "gold_ingot_from_extract"));
    }

    static void deepslateExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike deepslate_extract) {
        ShapedRecipeBuilder.shaped(Items.DEEPSLATE, 16)
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', deepslate_extract)
                .unlockedBy("has_deepslate_extract", has(deepslate_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "deepslate_from_extract"));

        ShapedRecipeBuilder.shaped(Items.COBBLED_DEEPSLATE, 16)
                .pattern("DDD")
                .pattern("D D")
                .pattern("DDD")
                .define('D', deepslate_extract)
                .unlockedBy("has_deepslate_extract", has(deepslate_extract))
                .save(pFinishedRecipeConsumer, new ResourceLocation(ResourceSlimes.MOD_ID, "cobbled_deepslate_from_extract"));
    }

    static void xExtractRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

    }
}
