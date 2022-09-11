package net.kaupenjoe.resourceslimes.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.kaupenjoe.resourceslimes.block.custom.SlimeIncubationStationBlock;
import net.kaupenjoe.resourceslimes.block.entity.SlimeIncubationStationBlockEntity;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class SlimeIncubationStationBlockEntityRenderer implements BlockEntityRenderer<SlimeIncubationStationBlockEntity> {

    private final ItemRenderer itemRenderer;
    private final EntityRenderDispatcher entityRenderer;

    public SlimeIncubationStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemRenderer = Minecraft.getInstance().getItemRenderer();
        entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher();
    }

    @Override
    public void render(SlimeIncubationStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        ItemStack[] itemStack = new ItemStack[]{pBlockEntity.itemHandler.getStackInSlot(0), pBlockEntity.itemHandler.getStackInSlot(1), pBlockEntity.itemHandler.getStackInSlot(2)};

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.75f, 0.5f);
        pPoseStack.mulPose(Vector3f.YN.rotationDegrees(pBlockEntity.getBlockState().getValue(SlimeIncubationStationBlock.FACING).toYRot()));
        pPoseStack.pushPose();
        pPoseStack.translate(0.0f, 0.0f, 0.1875f);
        float scaledProgress = pBlockEntity.getScaledProgress();
        pPoseStack.scale(scaledProgress, scaledProgress, scaledProgress);

        ResourceSlimeEntity entity = pBlockEntity.getRenderEntity();
        if (entity != null) {
            entityRenderer.render(entity, 0.0d, 0.0d, 0.0d,
                    0.0f, 0.0f, pPoseStack, pBufferSource, pPackedLight);
        }
        pPoseStack.popPose();

        pPoseStack.mulPose(Vector3f.YN.rotationDegrees(180));
        pPoseStack.pushPose();
        pPoseStack.translate(0.3125f, 0.0f, 0.3125f);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        itemRenderer.renderStatic(itemStack[0], ItemTransforms.TransformType.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
        OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
        pPoseStack.pushPose();
        pPoseStack.translate(0.0f, 0.0f, 0.25f);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        itemRenderer.renderStatic(itemStack[1], ItemTransforms.TransformType.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
        OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
        pPoseStack.pushPose();
        pPoseStack.translate(-0.3125f, 0.0f, 0.3125f);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        itemRenderer.renderStatic(itemStack[2], ItemTransforms.TransformType.FIXED, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
        OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
