package net.kaupenjoe.resourceslimes.integration;

import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.recipe.GemCuttingStationRecipe;
import net.kaupenjoe.resourceslimes.screen.renderer.JEIEnergyInfoArea;
import net.kaupenjoe.resourceslimes.util.MouseUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

public class GemCuttingStationRecipeCategory implements IRecipeCategory<GemCuttingStationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ResourceSlimes.MOD_ID, "gem_cutting");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID, "textures/gui/gem_cutting_station_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated animatedProgressArrow;
    private JEIEnergyInfoArea energyInfoArea;

    public GemCuttingStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GEM_CUTTING_STATION.get()));
        var progressArrow = helper.createDrawable(TEXTURE, 176, 0, 8, 26);
        this.animatedProgressArrow = helper.createAnimatedDrawable(progressArrow, 18, IDrawableAnimated.StartDirection.TOP, false);
    }
    
    @Override
    public RecipeType<GemCuttingStationRecipe> getRecipeType() {
    	return RecipeType.create(UID.getNamespace(), UID.getPath(), GemCuttingStationRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.literal("Gem Cutting Station");
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
    public List<Component> getTooltipStrings(GemCuttingStationRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if(isMouseAboveArea((int) Math.round(mouseX), (int) Math.round(mouseY),0, 0,156, 13, energyInfoArea.getWidth(), energyInfoArea.getHeight())) {
            return energyInfoArea.getTooltips();
        }

        return List.of();
    }

    @Override
    public void draw(GemCuttingStationRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        animatedProgressArrow.draw(stack, 102, 41);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull GemCuttingStationRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        energyInfoArea = new JEIEnergyInfoArea(156, 13, 8, 64, 60000,7800);
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 18).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 103, 18).addIngredients(Ingredient.of(ModItems.GEM_CUTTER_TOOL.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 45)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(new FluidStack(Fluids.WATER, recipe.getWaterAmount())))
                .setFluidRenderer(32000, false, 16, 32);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 0, 0)
                .setOverlay(energyInfoArea, 0,0);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem());
    }
}
