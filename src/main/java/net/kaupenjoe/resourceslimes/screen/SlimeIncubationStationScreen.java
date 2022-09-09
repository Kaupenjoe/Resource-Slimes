package net.kaupenjoe.resourceslimes.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.entity.SlimeIncubationStationBlockEntity;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.kaupenjoe.resourceslimes.screen.renderer.EnergyInfoArea;
import net.kaupenjoe.resourceslimes.screen.renderer.EntityInfoArea;
import net.kaupenjoe.resourceslimes.screen.renderer.EntityWidget;
import net.kaupenjoe.resourceslimes.screen.renderer.FluidStackRenderer;
import net.kaupenjoe.resourceslimes.util.MouseUtil;
import net.kaupenjoe.resourceslimes.util.resources.BuiltinSlimeResources;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidStack;

import java.util.Optional;

public class SlimeIncubationStationScreen extends AbstractContainerScreen<SlimeIncubationStationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ResourceSlimes.MOD_ID,"textures/gui/slime_incubation_station_gui.png");
    private EnergyInfoArea energyInfoArea;
    private EntityInfoArea entityInfoArea;


    public SlimeIncubationStationScreen(SlimeIncubationStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignEnergyInfoArea();
        assignEntityInfoArea();
    }

    private void assignEntityInfoArea() {
        entityInfoArea = new EntityInfoArea(((width - imageWidth) / 2) + 72,
                ((height - imageHeight) / 2) + 44, menu.getResourceSlimeEntity(), 32, 32);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderEnergyArea(pPoseStack, pMouseX, pMouseY, x, y);
        renderEntityArea(pPoseStack,pMouseX, pMouseY, x, y);
    }

    private void renderEntityArea(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(menu.isCrafting() && isMouseAboveArea(pMouseX, pMouseY, x, y,73, 45, 32, 32)) {
            renderTooltip(pPoseStack, entityInfoArea.getTooltips(),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private void renderEnergyArea(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 156, 11, 8, 64)) {
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(),
                    Optional.empty(), pMouseX - x, pMouseY - y);
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
        energyInfoArea.draw(pPoseStack);
        if(menu.isCrafting() && isMouseAboveArea(pMouseX, pMouseY, x, y,73, 47, 32, 32)) {
            entityInfoArea.draw(pPoseStack);
        }

        renderSlime(pPoseStack, x + 88, y + 72);
    }

    private void renderSlime(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            int size = menu.getScaledProgress() + 5;
            Vec3 entitySize = new Vec3(size, size, size);
            EntityWidget.renderEntity(pPoseStack, menu.getResourceSlimeEntity(), new Vec3(15, -225, 0),
                    entitySize, Vec3.ZERO, x, y);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyInfoArea(((width - imageWidth) / 2) +  156,
                ((height - imageHeight) / 2) + 11, menu.blockEntity.getEnergyStorage());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
