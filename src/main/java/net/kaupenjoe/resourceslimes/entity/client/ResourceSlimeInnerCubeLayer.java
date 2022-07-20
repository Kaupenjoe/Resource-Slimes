package net.kaupenjoe.resourceslimes.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ResourceSlimeInnerCubeLayer<T extends LivingEntity> extends RenderLayer<T, SlimeModel<T>> {
    private final EntityModel<T> model;

    public ResourceSlimeInnerCubeLayer(RenderLayerParent<T, SlimeModel<T>> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.model = new ResourceSlimeModel<>(modelSet.bakeLayer(ModModelLayers.RES_SLIME_INNER_CUBE));
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing,
                       float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        Minecraft minecraft = Minecraft.getInstance();
        boolean flag = minecraft.shouldEntityAppearGlowing(pLivingEntity) && pLivingEntity.isInvisible();
        if (!pLivingEntity.isInvisible() || flag) {
            VertexConsumer vertexconsumer;
            if (flag) {
                vertexconsumer = pBuffer.getBuffer(RenderType.outline(this.getTextureLocation(pLivingEntity)));
            } else {
                vertexconsumer = pBuffer.getBuffer(RenderType.entitySolid(this.getTextureLocation(pLivingEntity)));
            }

            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
            this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
            this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight,
                    LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F),
                    1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        if(pEntity instanceof ResourceSlimeEntity resourceSlimeEntity) {
            return SlimeResource.getInnerCubeLocationByItem(resourceSlimeEntity.getResourceItem().getItem());
        }

        return SlimeResource.getInnerCubeLocationByItem(null);
    }
}
