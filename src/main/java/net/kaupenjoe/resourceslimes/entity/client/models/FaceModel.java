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

public class FaceModel<T extends Entity> extends EasterEggEntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(new ResourceLocation(ResourceSlimes.MOD_ID, "face_model"), "main");
	private final ModelPart bb_main;
	public ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ResourceSlimes.MOD_ID,
			"textures/eastereggs/kj_face_texture.png");


	public FaceModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 2).addBox(7.0F, -9.0F, -7.0F, 1.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-8.0F, -9.0F, -7.0F, 1.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(17, 19).addBox(-7.0F, -9.0F, 7.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return TEXTURE_LOCATION;
	}
}