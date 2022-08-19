package net.kaupenjoe.resourceslimes.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kaupenjoe.resourceslimes.entity.client.models.EasterEggEntityModel;
import net.kaupenjoe.resourceslimes.entity.client.models.WizardHat;
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

import java.util.HashMap;
import java.util.Map;

public class ResourceSlimeHatLayer<T extends LivingEntity> extends RenderLayer<T, SlimeModel<T>> {
    private final Map<String, EasterEggEntityModel<T>> HATS;

    public ResourceSlimeHatLayer(RenderLayerParent<T, SlimeModel<T>> pRenderer, EntityModelSet modelSet) {
        super(pRenderer);
        HATS = createHats(modelSet);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity,
                       float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks,
                       float pNetHeadYaw, float pHeadPitch) {
        for (var entry : HATS.entrySet()) {
            if(pLivingEntity.hasCustomName() && entry.getKey().equals(pLivingEntity.getCustomName().getContents())) {
                EntityModel<T> model = entry.getValue();
                VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entitySolid(entry.getValue().getTextureLocation()));
                pMatrixStack.translate(0, 0, 0);
                pMatrixStack.scale(0.75f, 0.75f, 0.75f);

                this.getParentModel().copyPropertiesTo(model);
                model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
                model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight,
                        LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F),
                        1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    private Map<String, EasterEggEntityModel<T>> createHats(EntityModelSet modelSet) {
        Map<String, EasterEggEntityModel<T>> map = new java.util.HashMap<>();
        setModel(map, "joe_", new WizardHat<>(modelSet.bakeLayer(WizardHat.LAYER_LOCATION)));
        return map;
    }

    private void setModel(Map<String, EasterEggEntityModel<T>> map, String name, EasterEggEntityModel<T> model) {
        map.put(name, model);
    }
}
