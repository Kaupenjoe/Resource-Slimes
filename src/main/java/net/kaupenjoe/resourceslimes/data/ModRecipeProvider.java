package net.kaupenjoe.resourceslimes.data;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        this.customOreSmeltingRecipes(pFinishedRecipeConsumer);
        this.customOreBlastingRecipes(pFinishedRecipeConsumer);

    }



    private void customOreSmeltingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.CITRINE_ORE.get(), ModBlocks.DEEPSLATE_CITRINE_ORE.get(), ModItems.RAW_CITRINE.get()),
                ModItems.UNCUT_CITRINE.get(),0.35F, 200, "citrine");
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.ZIRCON_ORE.get(), ModBlocks.DEEPSLATE_ZIRCON_ORE.get(), ModItems.RAW_ZIRCON.get()),
                ModItems.UNCUT_ZIRCON.get(),0.5F, 200, "zircon");
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.TANZANITE_ORE.get(), ModBlocks.DEEPSLATE_TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()),
                ModItems.UNCUT_TANZANITE.get(),0.75F, 200, "tanzanite");
        oreSmelting(pFinishedRecipeConsumer, List.of(ModBlocks.BLACK_OPAL_ORE.get(), ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(),
                        ModBlocks.END_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()),
                ModItems.UNCUT_BLACK_OPAL.get(),1.15F, 200, "black_opal");
    }

    private void customOreBlastingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.CITRINE_ORE.get(), ModBlocks.DEEPSLATE_CITRINE_ORE.get(), ModItems.RAW_CITRINE.get()),
                ModItems.UNCUT_CITRINE.get(),0.35F, 100, "citrine");
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.ZIRCON_ORE.get(), ModBlocks.DEEPSLATE_ZIRCON_ORE.get(), ModItems.RAW_ZIRCON.get()),
                ModItems.UNCUT_ZIRCON.get(),0.5F, 100, "zircon");
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.TANZANITE_ORE.get(), ModBlocks.DEEPSLATE_TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()),
                ModItems.UNCUT_TANZANITE.get(),0.75F, 100, "tanzanite");
        oreBlasting(pFinishedRecipeConsumer, List.of(ModBlocks.BLACK_OPAL_ORE.get(), ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(),
                        ModBlocks.END_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()),
                ModItems.UNCUT_BLACK_OPAL.get(),1.15F, 100, "black_opal");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE,
                pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                      ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                     SimpleCookingSerializer<?> pCookingSerializer, List<ItemLike> pIngredients,
                                     ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(itemlike), pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer,
                            ResourceSlimes.MOD_ID + ":" + (pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}