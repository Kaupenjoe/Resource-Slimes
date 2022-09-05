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

// Headphone Hat by DakotaPrideModding (https://www.curseforge.com/members/dakotapridemodding/projects)
public class HeadphoneHat<T extends Entity> extends EasterEggEntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(new ResourceLocation(ResourceSlimes.MOD_ID, "headphones_hat"), "main");
	private final ModelPart headphones;
	public ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ResourceSlimes.MOD_ID,
			"textures/eastereggs/headphones_hat.png");

	public HeadphoneHat(ModelPart root) {
		this.headphones = root.getChild("headphones");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition headphones = partdefinition.addOrReplaceChild("headphones", CubeListBuilder.create().texOffs(24, 0).addBox(-5.0F, 2.0F, -1.425F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, 2.0F, -1.425F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(8, 28).addBox(4.0F, -1.0F, -0.425F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 16).addBox(-4.0F, -1.0F, -0.425F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-5.0F, -1.0F, -0.425F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		headphones.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return TEXTURE_LOCATION;
	}
}