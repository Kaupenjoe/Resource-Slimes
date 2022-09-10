package net.kaupenjoe.resourceslimes.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.kaupenjoe.resourceslimes.block.custom.GemCuttingStationBlock;
import net.kaupenjoe.resourceslimes.block.entity.GemCuttingStationBlockEntity;
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

    private ItemRenderer itemRenderer;

    public GemCuttingStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(GemCuttingStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        
        //DONE: rotation/flipping of item stack that is rendered?
        pPoseStack.translate(0.5f, 0.5f, 0.5f);
        pPoseStack.mulPose(Vector3f.YN.rotationDegrees(pBlockEntity.getBlockState().getValue(GemCuttingStationBlock.FACING).toYRot()));
        pPoseStack.pushPose();
        pPoseStack.translate(-0.1f, 0.3f, 0.275f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(270));

        pPoseStack.scale(0.35f, 0.35f, 0.35f);
        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GUI, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);

        pPoseStack.popPose();

        if(pBlockEntity.hasGemCuttingTools()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.15f, 0.25f, -0.25f);
            pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
            pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
            pPoseStack.scale(0.35f, 0.35f, 0.35f);
            itemRenderer.renderStatic(new ItemStack(ModItems.GEM_CUTTER_TOOL.get()), ItemTransforms.TransformType.GUI, getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
            pPoseStack.popPose();
        }
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
