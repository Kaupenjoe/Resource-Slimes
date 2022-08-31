package net.kaupenjoe.resourceslimes.integration;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.recipe.SlimeIncubationRecipe;
import net.kaupenjoe.resourceslimes.screen.renderer.EntityInfoArea;
import net.kaupenjoe.resourceslimes.screen.renderer.JEIEnergyInfoArea;
import net.kaupenjoe.resourceslimes.util.MouseUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlimeIncubationStationRecipeCategory implements IRecipeCategory<SlimeIncubationRecipe>
{
    public final static ResourceLocation UID = new ResourceLocation(ResourceSlimes.MOD_ID, "slime_incubation");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID, "textures/gui/slime_incubation_station_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private JEIEnergyInfoArea energyInfoArea;

    public SlimeIncubationStationRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.SLIME_INCUBATION_STATION.get()));
    }

    @Override
    public RecipeType<SlimeIncubationRecipe> getRecipeType()
    {
        return RecipeType.create(UID.getNamespace(), UID.getPath(), SlimeIncubationRecipe.class);
    }
    
    @Override
    public Component getTitle() {
        return Component.literal("Slime Incubation Station");
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
    public List<Component> getTooltipStrings(SlimeIncubationRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if(isMouseAboveArea((int) Math.round(mouseX), (int) Math.round(mouseY),0, 0,156, 13, energyInfoArea.getWidth(), energyInfoArea.getHeight())) {
            return energyInfoArea.getTooltips();
        }

        return List.of();
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull SlimeIncubationRecipe recipe, @NotNull IFocusGroup focusGroup) {
        energyInfoArea = new JEIEnergyInfoArea(156, 13, 8, 64, 60000,7800);

        builder.addSlot(RecipeIngredientRole.INPUT, 44, 22).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 7).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 116, 22).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 0, 0)
                .setOverlay(energyInfoArea, 0,0);

        builder.addSlot(RecipeIngredientRole.OUTPUT,72, 56)
                .setOverlay(new EntityDrawable(32, 32, recipe.getIngredients().get(1).getItems()[0].getItem()), 16, 16);

        // TODO: Display custom tooltip on hover
        builder.addSlot(RecipeIngredientRole.OUTPUT,88, 72)
                .setOverlay(new EntityDrawable(32, 32,
                        recipe.getIngredients().get(1).getItems()[0].getItem()), 0, 0)
                .setOverlay(new EntityInfoArea(88, 72,
                                recipe.getIngredients().get(1).getItems()[0].getItem(), 32,32), 0, 0);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}