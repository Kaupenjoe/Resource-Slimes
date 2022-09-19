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
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.recipe.SlimeExtractCleaningStationRecipe;
import net.kaupenjoe.resourceslimes.screen.renderer.JEIEnergyInfoArea;
import net.kaupenjoe.resourceslimes.util.MouseUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;

public class SlimeExtractCleaningStationRecipeCategory implements IRecipeCategory<SlimeExtractCleaningStationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ResourceSlimes.MOD_ID, "extract_cleaning");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID, "textures/gui/slime_extract_cleaning_station_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated animatedProgressArrow;
    private JEIEnergyInfoArea energyInfoArea;

    public SlimeExtractCleaningStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 76);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SLIME_EXTRACT_CLEANING_STATION.get()));
        IDrawableStatic progressArrow = helper.createDrawable(TEXTURE, 176, 0, 8, 26);
        this.animatedProgressArrow = helper.createAnimatedDrawable(progressArrow, 18, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends SlimeExtractCleaningStationRecipe> getRecipeClass() {
        return SlimeExtractCleaningStationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Slime Extract Cleaning Station");
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
    public List<Component> getTooltipStrings(SlimeExtractCleaningStationRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if(isMouseAboveArea((int) Math.round(mouseX), (int) Math.round(mouseY),0, 0,151, 6, energyInfoArea.getWidth(), energyInfoArea.getHeight())) {
            return energyInfoArea.getTooltips();
        }

        return List.of();
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull SlimeExtractCleaningStationRecipe recipe,
                          @Nonnull IFocusGroup focusGroup) {
        energyInfoArea = new JEIEnergyInfoArea(151, 6, 8, 64, 60000,7800);

        builder.addSlot(RecipeIngredientRole.INPUT, 7, 9).addIngredients(Ingredient.of(ModItems.SOAP.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 81, 9).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 9)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(new FluidStack(ModFluids.SOAPY_WATER_FLUID.get(), 500)))
                .setFluidRenderer(32000, false, 16, 61);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 0, 0)
                .setOverlay(energyInfoArea, 0,0);


        builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 54).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(SlimeExtractCleaningStationRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        animatedProgressArrow.draw(stack, 100, 26);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
