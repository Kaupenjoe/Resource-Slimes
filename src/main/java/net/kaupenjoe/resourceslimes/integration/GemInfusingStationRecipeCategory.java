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
import net.kaupenjoe.resourceslimes.recipe.GemInfusingStationRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class GemInfusingStationRecipeCategory implements IRecipeCategory<GemInfusingStationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ResourceSlimes.MOD_ID, "gem_infusing");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID, "textures/gui/gem_infusing_station_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public GemInfusingStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GEM_INFUSING_STATION.get()));
    }
    
    @Override
    public RecipeType<GemInfusingStationRecipe> getRecipeType() {
    	return new RecipeType<>(UID, getRecipeClass());
    }

    public Class<? extends GemInfusingStationRecipe> getRecipeClass() {
        return GemInfusingStationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Gem Infusing Station");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull GemInfusingStationRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 15)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getFluid()))
                .setFluidRenderer(64000, false, 16, 61);
        // TODO: Energy Area
        //builder.addSlot(RecipeIngredientRole.INPUT, 0, 0)
        //               .setOverlay(new EnergyInfoArea(0, 0), 0, 0);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}
