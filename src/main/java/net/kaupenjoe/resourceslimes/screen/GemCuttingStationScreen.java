package net.kaupenjoe.resourceslimes.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Transformation;
import mezz.jei.api.gui.drawable.IDrawable;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.screen.renderer.FluidStackRenderer;
import net.kaupenjoe.resourceslimes.util.TooltipRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Optional;

public class GemCuttingStationScreen extends AbstractContainerScreen<GemCuttingStationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID,"textures/gui/gem_cutting_station_with_water_gui.png");
    private FluidStackRenderer renderer;

    public GemCuttingStationScreen(GemCuttingStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        renderer = new FluidStackRenderer(16000,true,16, 32);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(isMouseOver(pMouseX, pMouseY, x + 55, y + 45, renderer.getWidth(), renderer.getHeight())) {
            renderTooltip(pPoseStack, renderer.getTooltip(menu.getFluid(), TooltipFlag.Default.NORMAL),
                    Optional.empty(),pMouseX - x, pMouseY - y);
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if(menu.isCrafting()) {
            blit(pPoseStack, x + 102, y + 41, 176, 0, 8, menu.getScaledProgress());
        }

        renderer.render(pPoseStack, x + 55,y + 45, menu.getFluid());
    }

    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y) {
        return isMouseOver(mouseX, mouseY, x, y, 16);
    }

    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int size) {
        return isMouseOver(mouseX, mouseY, x, y, size, size);
    }

    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int sizeX, int sizeY) {
        return (mouseX >= x && mouseX <= x + sizeX) && (mouseY >= y && mouseY <= y + sizeY);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
