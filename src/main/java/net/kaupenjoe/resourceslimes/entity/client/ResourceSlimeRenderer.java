package net.kaupenjoe.resourceslimes.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Slime;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ResourceSlimeRenderer extends MobRenderer<Slime, SlimeModel<Slime>> {
    private static final ResourceLocation EYES_MOUTH_LOC = new ResourceLocation(ResourceSlimes.MOD_ID,
            "textures/entity/resource_slime/resource_slime_eyes_mouth.png");

    public ResourceSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new ResourceSlimeModel<>(context.bakeLayer(ModModelLayers.RES_SLIME_EYES)), 0.25F);
        this.addLayer(new ResourceSlimeInnerCubeLayer<>(this, context.getModelSet()));
        this.addLayer(new ResourceSlimeOuterLayer<>(this, context.getModelSet()));
    }

    @Override
    public void render(Slime pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.25F * (float)pEntity.getSize();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }


    @Override
    protected void scale(Slime pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        pMatrixStack.scale(0.999F, 0.999F, 0.999F);
        pMatrixStack.translate(0.0D, 0.002F, 0.0D);
        float f1 = (float)pLivingEntity.getSize();
        float f2 = Mth.lerp(pPartialTickTime, pLivingEntity.oSquish, pLivingEntity.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        pMatrixStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @NotNull
    @Override
    public ResourceLocation getTextureLocation(@NotNull Slime pEntity) {
        return EYES_MOUTH_LOC;
    }

    @Nullable
    @Override
    protected RenderType getRenderType(Slime pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        if (pTranslucent) {
            return RenderType.itemEntityTranslucentCull(EYES_MOUTH_LOC);
        } else if (pBodyVisible) {
            return this.model.renderType(EYES_MOUTH_LOC);
        } else {
            return pGlowing ? RenderType.outline(EYES_MOUTH_LOC) : null;
        }
    }
}
