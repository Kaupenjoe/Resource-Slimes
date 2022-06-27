package net.kaupenjoe.resourceslimes.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.screen.renderer.EnergyInfoArea;
import net.kaupenjoe.resourceslimes.screen.renderer.FluidStackRenderer;
import net.kaupenjoe.resourceslimes.util.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;

import java.util.Optional;

public class GemCuttingStationScreen extends AbstractContainerScreen<GemCuttingStationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID,"textures/gui/gem_cutting_station_with_water_gui.png");
    private FluidStackRenderer renderer;
    private EnergyInfoArea energyInfoArea;

    public GemCuttingStationScreen(GemCuttingStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
        energyInfoArea = new EnergyInfoArea(((width - imageWidth) / 2) +  156,
                ((height - imageHeight) / 2) + 13, menu.blockEntity.energyStorage);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 55, 45)) {
            renderTooltip(pPoseStack, renderer.getTooltip(menu.getFluid(), TooltipFlag.Default.NORMAL),
                    Optional.empty(),pMouseX - x, pMouseY - y);
        }

        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 156, 13, 8, 64)) {
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(),
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
        energyInfoArea.draw(pPoseStack);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    private void assignFluidRenderer() {
        renderer = new FluidStackRenderer(16000,true,16, 32);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
