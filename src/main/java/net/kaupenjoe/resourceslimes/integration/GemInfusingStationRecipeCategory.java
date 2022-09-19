package net.kaupenjoe.resourceslimes.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.recipe.GemInfusingStationRecipe;
import net.kaupenjoe.resourceslimes.screen.renderer.JEIEnergyInfoArea;
import net.kaupenjoe.resourceslimes.util.MouseUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class GemInfusingStationRecipeCategory implements IRecipeCategory<GemInfusingStationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ResourceSlimes.MOD_ID, "gem_infusing");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID, "textures/gui/gem_infusing_station_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated animatedProgressArrow;
    private JEIEnergyInfoArea energyInfoArea;

    public GemInfusingStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 76);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GEM_INFUSING_STATION.get()));
        IDrawableStatic progressArrow = helper.createDrawable(TEXTURE, 176, 0, 8, 26);
        this.animatedProgressArrow = helper.createAnimatedDrawable(progressArrow, 18, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends GemInfusingStationRecipe> getRecipeClass() {
        return GemInfusingStationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Gem Infusing Station");
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
    public List<Component> getTooltipStrings(GemInfusingStationRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if(isMouseAboveArea((int) Math.round(mouseX), (int) Math.round(mouseY),0, 0,151, 6, energyInfoArea.getWidth(), energyInfoArea.getHeight())) {
            return energyInfoArea.getTooltips();
        }

        return List.of();
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull GemInfusingStationRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        energyInfoArea = new JEIEnergyInfoArea(151, 6, 8, 64, 60000,7800);

        builder.addSlot(RecipeIngredientRole.INPUT, 81, 8).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 8)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getFluid()))
                .setFluidRenderer(64000, false, 16, 61);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 0, 0)
                .setOverlay(energyInfoArea, 0,0);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 53).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(GemInfusingStationRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        animatedProgressArrow.draw(stack, 100, 26);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
