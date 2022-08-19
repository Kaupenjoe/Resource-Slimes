package net.kaupenjoe.resourceslimes.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.kaupenjoe.resourceslimes.block.custom.GemCuttingStationBlock;
import net.kaupenjoe.resourceslimes.block.custom.SlimeIncubationStationBlock;
import net.kaupenjoe.resourceslimes.block.entity.SlimeExtractCleaningStationBlockEntity;
import net.kaupenjoe.resourceslimes.block.entity.SlimeIncubationStationBlockEntity;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
        pPoseStack.translate(0.5f, 1f, 0.5f);
        pPoseStack.scale(pBlockEntity.getScaledProgress(), pBlockEntity.getScaledProgress(), pBlockEntity.getScaledProgress());
        switch (pBlockEntity.getBlockState().getValue(SlimeIncubationStationBlock.FACING)) {
            case NORTH -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
            case EAST -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90));
            case SOUTH -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(0));
            case WEST -> pPoseStack.mulPose(Vector3f.YP.rotationDegrees(270));
        }

        ResourceSlimeEntity entity = pBlockEntity.getRenderEntity();

        entityRenderer.render(entity,0,0, 0,
                0f,0f, pPoseStack, pBufferSource, pPackedLight);

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
