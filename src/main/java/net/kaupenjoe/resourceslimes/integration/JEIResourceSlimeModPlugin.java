package net.kaupenjoe.resourceslimes.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.recipe.GemCuttingStationRecipe;
import net.kaupenjoe.resourceslimes.recipe.GemInfusingStationRecipe;
import net.kaupenjoe.resourceslimes.recipe.SlimeExtractCleaningStationRecipe;
import net.kaupenjoe.resourceslimes.recipe.SlimeIncubationRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIResourceSlimeModPlugin implements IModPlugin {
    public static final RecipeType<GemCuttingStationRecipe> GEM_CUTTING = RecipeType.create(ResourceSlimes.MOD_ID, "gem_cutting", GemCuttingStationRecipe.class);
    public static final RecipeType<GemInfusingStationRecipe> GEM_INFUSING = RecipeType.create(ResourceSlimes.MOD_ID, "gem_infusing", GemInfusingStationRecipe.class);
    public static final RecipeType<SlimeExtractCleaningStationRecipe> EXTRACT_CLEANING = RecipeType.create(ResourceSlimes.MOD_ID, "extract_cleaning", SlimeExtractCleaningStationRecipe.class);
    public static final RecipeType<SlimeIncubationRecipe> SLIME_INCUBATION = RecipeType.create(ResourceSlimes.MOD_ID, "slime_incubation", SlimeIncubationRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ResourceSlimes.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                GemCuttingStationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                GemInfusingStationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                SlimeExtractCleaningStationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                SlimeIncubationStationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<GemCuttingStationRecipe> recipes = rm.getAllRecipesFor(GemCuttingStationRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(GemCuttingStationRecipeCategory.UID, GemCuttingStationRecipe.class), recipes);

        List<GemInfusingStationRecipe> recipesInfusing = rm.getAllRecipesFor(GemInfusingStationRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(GemInfusingStationRecipeCategory.UID, GemInfusingStationRecipe.class), recipesInfusing);

        List<SlimeExtractCleaningStationRecipe> recipesCleaning = rm.getAllRecipesFor(SlimeExtractCleaningStationRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(SlimeExtractCleaningStationRecipeCategory.UID, SlimeExtractCleaningStationRecipe.class), recipesCleaning);

        List<SlimeIncubationRecipe> recipesIncubation = rm.getAllRecipesFor(SlimeIncubationRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(SlimeIncubationStationRecipeCategory.UID, SlimeIncubationRecipe.class), recipesIncubation);

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.GEM_CUTTING_STATION.get().asItem()), GEM_CUTTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.GEM_INFUSING_STATION.get().asItem()), GEM_INFUSING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SLIME_EXTRACT_CLEANING_STATION.get().asItem()), EXTRACT_CLEANING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SLIME_INCUBATION_STATION.get().asItem()), SLIME_INCUBATION);
    }
}