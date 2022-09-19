package net.kaupenjoe.resourceslimes.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.kaupenjoe.resourceslimes.entity.client.models.*;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class ResourceSlimeHatLayer<T extends LivingEntity> extends RenderLayer<T, SlimeModel<T>> {
    private final List<EasterEggData<T>> hats;

    public ResourceSlimeHatLayer(RenderLayerParent<T, SlimeModel<T>> pRenderer, EntityModelSet modelSet) {
        super(pRenderer);
        hats = createHats(modelSet);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity,
                       float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks,
                       float pNetHeadYaw, float pHeadPitch) {
        for (var entry : hats) {
            if(pLivingEntity.hasCustomName() && entry.getName().equals(pLivingEntity.getCustomName().getContents())) {
                EntityModel<T> model = entry.getModel();
                VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(entry.getModel().getTextureLocation()));
                pMatrixStack.translate(entry.getTranslation().x(), entry.getTranslation().y(), entry.getTranslation().z());
                pMatrixStack.scale(entry.getScale().x(), entry.getScale().y(), entry.getScale().z());

                this.getParentModel().copyPropertiesTo(model);
                model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
                model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
                model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight,
                        LivingEntityRenderer.getOverlayCoords(pLivingEntity, 0.0F),
                        1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    private List<EasterEggData<T>> createHats(EntityModelSet modelSet) {
        List<EasterEggData<T>> list = new ArrayList<>();
        list.add(new EasterEggData<>("JoeFoxe", new WizardHat<>(modelSet.bakeLayer(WizardHat.LAYER_LOCATION)),
                        new Vector3f(0, 0, 0), new Vector3f(0.75f, 0.75f, 0.75f), new Vector3f(0, 0, 0)));

        list.add(new EasterEggData<>("Kaupenjoe", new FaceModel<>(modelSet.bakeLayer(FaceModel.LAYER_LOCATION)),
                        new Vector3f(0.0f, 0.45f, 0), new Vector3f(0.65f, 0.65f, 0.65f),
                new Vector3f(0, 0, 0)));

        list.add(new EasterEggData<>("KaupenCat", new CatEars<>(modelSet.bakeLayer(CatEars.LAYER_LOCATION)),
                        new Vector3f(0.0f, 0f, 0), new Vector3f(1f, 1f, 1f),
                new Vector3f(0, 0, 0)));

        list.add(new EasterEggData<>("Axolotli", new AxolotlHat<>(modelSet.bakeLayer(AxolotlHat.LAYER_LOCATION)),
                        new Vector3f(0.0f, 0f, 0), new Vector3f(1f, 1f, 1f),
                new Vector3f(0, 0, 0)));

        list.add(new EasterEggData<>("Thermal", new ThermalHat<>(modelSet.bakeLayer(ThermalHat.LAYER_LOCATION)),
                        new Vector3f(0.0f, 0f, 0), new Vector3f(1f, 1f, 1f),
                new Vector3f(0, 0, 0)));

        list.add(new EasterEggData<>("Headphones", new HeadphoneHat<>(modelSet.bakeLayer(HeadphoneHat.LAYER_LOCATION)),
                new Vector3f(0.0f, 0f, 0), new Vector3f(1f, 1f, 1f),
                new Vector3f(0, 0, 0)));

        list.add(new EasterEggData<>("Duck", new DuckHat<>(modelSet.bakeLayer(DuckHat.LAYER_LOCATION)),
                new Vector3f(0.0f, 0f, 0), new Vector3f(1f, 1f, 1f),
                new Vector3f(0, 0, 0)));

        return list;
    }
}
