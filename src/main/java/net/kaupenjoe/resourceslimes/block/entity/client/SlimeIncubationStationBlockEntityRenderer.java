package net.kaupenjoe.resourceslimes.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.kaupenjoe.resourceslimes.block.custom.SlimeIncubationStationBlock;
import net.kaupenjoe.resourceslimes.block.entity.SlimeIncubationStationBlockEntity;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class SlimeIncubationStationBlockEntityRenderer implements BlockEntityRenderer<SlimeIncubationStationBlockEntity> {
    public SlimeIncubationStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(SlimeIncubationStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        EntityRenderDispatcher entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher();

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 1.0f, 0.5f);
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(-pBlockEntity.getBlockState().getValue(SlimeIncubationStationBlock.FACING).toYRot()));
        pPoseStack.translate(0.0f, 0.0f, 0.1875f);
        pPoseStack.scale(pBlockEntity.getScaledProgress(), pBlockEntity.getScaledProgress(), pBlockEntity.getScaledProgress());

        ResourceSlimeEntity entity = pBlockEntity.getRenderEntity();
        if (entity != null) {
            entityRenderer.render(entity, 0.0d, 0.0d, -0.0d,
                    0.0f, 0.0f, pPoseStack, pBufferSource, pPackedLight);
        }

        // itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GUI, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
        //         OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
