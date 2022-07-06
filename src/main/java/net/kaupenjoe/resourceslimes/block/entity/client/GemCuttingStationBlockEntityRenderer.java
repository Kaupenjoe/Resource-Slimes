package net.kaupenjoe.resourceslimes.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.kaupenjoe.resourceslimes.block.entity.GemCuttingStationBlockEntity;
import net.kaupenjoe.resourceslimes.block.entity.GemInfusingStationBlockEntity;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class GemCuttingStationBlockEntityRenderer implements BlockEntityRenderer<GemCuttingStationBlockEntity> {
    public GemCuttingStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(GemCuttingStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        pPoseStack.translate(0.6f, 0.8f, 0.225f);
        pPoseStack.scale(0.35f, 0.35f, 0.35f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GUI, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);

        pPoseStack.popPose();

        if(pBlockEntity.hasGemCuttingTools()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.35f, 0.75f, 0.75f);
            pPoseStack.scale(0.35f, 0.35f, 0.35f);
            pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
            itemRenderer.renderStatic(new ItemStack(ModItems.GEM_CUTTER_TOOL.get()), ItemTransforms.TransformType.GUI, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
            pPoseStack.popPose();
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
