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

public class WizardHat<T extends Entity> extends EasterEggEntityModel<T> {
	private final ModelPart Head;

	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(new ResourceLocation(ResourceSlimes.MOD_ID, "wizard_hat"), "main");
	public ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ResourceSlimes.MOD_ID,
			"textures/eastereggs/wizard_hat_texture.png");

	public WizardHat(ModelPart root) {
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition Hat = Head.addOrReplaceChild("Hat", CubeListBuilder.create(), PartPose.offset(3.9181F, 24.0542F, -9.2644F));

		PartDefinition head_r12_r1 = Hat.addOrReplaceChild("head_r12_r1", CubeListBuilder.create().texOffs(30, 16).addBox(16.0862F, 15.7626F, 9.1968F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9323F, -34.1829F, 11.26F, -0.4111F, 0.298F, 0.742F));
		PartDefinition head_r11_r1 = Hat.addOrReplaceChild("head_r11_r1", CubeListBuilder.create().texOffs(0, 39).addBox(19.619F, 2.5078F, 16.137F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9504F, -35.302F, 11.9669F, -0.5659F, 0.6012F, 1.0441F));
		PartDefinition head_r10_r1 = Hat.addOrReplaceChild("head_r10_r1", CubeListBuilder.create().texOffs(28, 31).addBox(4.8665F, 23.1158F, 7.4708F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6757F, -32.4244F, 10.1967F, -0.435F, 0.1227F, 0.3078F));
		PartDefinition head_r9_r1 = Hat.addOrReplaceChild("head_r9_r1", CubeListBuilder.create().texOffs(0, 27).addBox(-2.009F, -7.0805F, 5.2007F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0424F, -0.4419F, -2.5444F, -0.3481F, 0.0316F, 0.0844F));
		PartDefinition head_r4_r1 = Hat.addOrReplaceChild("head_r4_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5479F, -4.566F, -9.1482F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7272F, 3.6122F, 8.827F, -0.3048F, -0.0186F, -0.1276F));
		PartDefinition head_r3_r1 = Hat.addOrReplaceChild("head_r3_r1", CubeListBuilder.create().texOffs(0, 6).addBox(13.3991F, -25.3541F, 9.1272F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6618F, -35.1716F, 13.8287F, -0.4525F, 1.0241F, 2.2393F));
		PartDefinition head_r4_r2 = Hat.addOrReplaceChild("head_r4_r2", CubeListBuilder.create().texOffs(8, 6).addBox(-0.6156F, -31.9555F, 0.8844F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1221F, -34.6529F, 12.9012F, 0.1274F, 1.1259F, -3.0809F));
		PartDefinition head_r2_r1 = Hat.addOrReplaceChild("head_r2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(20.7323F, -10.313F, 15.0696F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5668F, -35.9526F, 12.8484F, -0.6773F, 0.749F, 1.4749F));
		PartDefinition head_r1_r1 = Hat.addOrReplaceChild("head_r1_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-8.5347F, -5.179F, 2.2339F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3048F, -0.0186F, -0.1276F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return TEXTURE_LOCATION;
	}
}