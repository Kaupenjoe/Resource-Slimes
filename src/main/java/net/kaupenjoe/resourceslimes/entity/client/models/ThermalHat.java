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

// ThermalHat by Roomenn
public class ThermalHat<T extends Entity> extends EasterEggEntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(new ResourceLocation(ResourceSlimes.MOD_ID, "thermal_hat"), "main");
	private final ModelPart hat;
	public ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ResourceSlimes.MOD_ID,
			"textures/eastereggs/thermal_hat.png");

	public ThermalHat(ModelPart root) {
		this.hat = root.getChild("hat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(24, 16).addBox(-4.9194F, -6.273F, -4.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(19, 23).addBox(-1.4768F, -8.8835F, -4.5F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(18, 16).addBox(-4.4194F, -8.273F, -2.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 16).mirror().addBox(2.9194F, -6.273F, -4.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(18, 16).mirror().addBox(1.4194F, -8.273F, -2.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = hat.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 29).mirror().addBox(-3.25F, -6.5F, -8.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 28).mirror().addBox(-3.25F, -6.5F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = hat.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 29).addBox(0.25F, -6.5F, -8.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(0.25F, -6.5F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r3 = hat.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 30).addBox(3.75F, -6.0F, -8.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 1).addBox(3.75F, -6.0F, -7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r4 = hat.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1768F, -8.8085F, 0.5F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
							   float blue, float alpha) {
		hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return TEXTURE_LOCATION;
	}
}