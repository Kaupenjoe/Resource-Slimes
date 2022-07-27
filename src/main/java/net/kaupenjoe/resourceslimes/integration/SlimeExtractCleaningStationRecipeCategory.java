package net.kaupenjoe.resourceslimes.integration;

import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.recipe.SlimeExtractCleaningStationRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

public class SlimeExtractCleaningStationRecipeCategory implements IRecipeCategory<SlimeExtractCleaningStationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ResourceSlimes.MOD_ID, "extract_cleaning");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID, "textures/gui/slime_extract_cleaning_station_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public SlimeExtractCleaningStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GEM_INFUSING_STATION.get()));
    }
    
    @Override
    public RecipeType<SlimeExtractCleaningStationRecipe> getRecipeType() {
    	return new RecipeType<>(UID, getRecipeClass());
    }

    public Class<? extends SlimeExtractCleaningStationRecipe> getRecipeClass() {
        return SlimeExtractCleaningStationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Slime Extract Cleaning Station");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull SlimeExtractCleaningStationRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 12, 16).addIngredients(Ingredient.of(ModItems.SOAP.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 16).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 16)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(new FluidStack(ModFluids.SOAPY_WATER_FLUID.get(), 500)))
                .setFluidRenderer(32000, false, 16, 61);
        // TODO: Energy Area
        // builder.addSlot(RecipeIngredientRole.INPUT, 0, 0)
        //                 .setOverlay(new EnergyInfoArea(0, 0), 0, 0);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 61).addItemStack(recipe.getResultItem());
    }
}
