package net.kaupenjoe.resourceslimes.entity.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class DuckHat<T extends Entity> extends EasterEggEntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(new ResourceLocation(ResourceSlimes.MOD_ID, "duck_hat"), "main");
	private final ModelPart duck;
	public ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ResourceSlimes.MOD_ID,
			"textures/eastereggs/duck_hat.png");

	public DuckHat(ModelPart root) {
		this.duck = root.getChild("duck");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition duck = partdefinition.addOrReplaceChild("duck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition base = duck.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 28).addBox(-5.0F, 14.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(4.0F, 16.0F, -5.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-5.0F, 16.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition very_bottom_r1 = base.addOrReplaceChild("very_bottom_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-5.0F, 16.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(4.0F, 16.0F, -5.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition foot = base.addOrReplaceChild("foot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition l = foot.addOrReplaceChild("l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition footl_r1 = l.addOrReplaceChild("footl_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-0.75F, 3.5F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 17.0F, 0.0F, -0.2182F, -0.3927F, -0.2182F));

		PartDefinition footl_r2 = l.addOrReplaceChild("footl_r2", CubeListBuilder.create().texOffs(0, 28).addBox(-0.75F, 3.5F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 17.0F, 0.0F, -0.2182F, -0.7418F, -0.2182F));

		PartDefinition footl_r3 = l.addOrReplaceChild("footl_r3", CubeListBuilder.create().texOffs(0, 28).addBox(-0.5F, 3.5F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 17.0F, 0.0F, -0.2182F, 0.0F, -0.2182F));

		PartDefinition r = foot.addOrReplaceChild("r", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, 17.0F, 0.0F, 1.4266F, 1.2624F, 1.4333F));

		PartDefinition footr_r1 = r.addOrReplaceChild("footr_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-0.75F, 3.5F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.2182F, -0.3927F, -0.2182F));

		PartDefinition footr_r2 = r.addOrReplaceChild("footr_r2", CubeListBuilder.create().texOffs(0, 28).addBox(-0.75F, 3.5F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.2182F, -0.7418F, -0.2182F));

		PartDefinition footr_r3 = r.addOrReplaceChild("footr_r3", CubeListBuilder.create().texOffs(0, 28).addBox(-0.5F, 3.5F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.2182F, 0.0F, -0.2182F));

		PartDefinition head = duck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = head.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(16, 28).addBox(-1.5F, 13.0F, -5.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck_bottom_r1 = neck.addOrReplaceChild("neck_bottom_r1", CubeListBuilder.create().texOffs(13, 29).addBox(-1.5F, -4.0F, -1.75F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, -3.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition neck_bottom_r2 = neck.addOrReplaceChild("neck_bottom_r2", CubeListBuilder.create().texOffs(0, 30).addBox(-1.5F, -1.5F, -5.25F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(35, 24).addBox(-2.0F, -2.5F, -2.25F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(11, 30).addBox(-1.5F, -3.5F, -3.25F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, -2.0F, -0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		duck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return TEXTURE_LOCATION;
	}
}