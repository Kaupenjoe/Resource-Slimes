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

// Made by Noba
public class AxolotlHat<T extends Entity> extends EasterEggEntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ResourceSlimes.MOD_ID, "axolotl_hat"), "main");
	private final ModelPart bone;
	public ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ResourceSlimes.MOD_ID,
			"textures/eastereggs/axolotl_hat.png");

	public AxolotlHat(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -11.0F, -5.0F, 8.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(8, 0).addBox(-4.0F, -8.0F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 6).addBox(4.01F, -2.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(4.01F, -3.0F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 0).addBox(4.01F, -8.0F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-4.0F, -3.0F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 5).addBox(-4.0F, -2.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 13).addBox(-4.0F, -14.0F, -2.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(4.0F, -12.0F, -2.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -12.0F, -2.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -0.25F, -3.25F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, 8.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return TEXTURE_LOCATION;
	}
}