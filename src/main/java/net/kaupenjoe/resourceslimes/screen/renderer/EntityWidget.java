package net.kaupenjoe.resourceslimes.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

/* EntityWidget Class by DaRealTurtyWurty:
* https://github.com/DaRealTurtyWurty/TurtyLib/blob/main/src/main/java/io/github/darealturtywurty/turtylib/client/ui/components/EntityWidget.java
*
*/
public class EntityWidget extends AbstractWidget {
    private final Minecraft minecraft;
    private Entity entity;
    private float rotation = 135;
    private float rotationSpeed;
    private Vec3 defaultRotation, scale, offset;

    private EntityWidget(Builder builder) {
        super(builder.xPos, builder.yPos, builder.width, builder.height, Component.empty());

        this.minecraft = Minecraft.getInstance();
        this.entity = builder.entity;
        this.rotationSpeed = builder.rotationSpeed;
        this.defaultRotation = builder.defaultRotation;
        this.scale = builder.scale;
        this.offset = builder.offset;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (this.rotationSpeed != 0) {
            this.rotation += partialTicks * this.rotationSpeed;
        }

        renderEntity(stack, this.entity,
                this.rotationSpeed != 0 ? new Vec3(this.defaultRotation.x(), this.rotation, this.defaultRotation.z())
                        : this.defaultRotation,
                this.scale, this.offset, this.x, this.y);
    }

    @Override
    public void updateNarration(NarrationElementOutput narration) {
        defaultButtonNarrationText(narration);
    }

    public static class Builder {
        private float rotationSpeed;
        private Vec3 scale = new Vec3(20f, 20f, 20f), defaultRotation = new Vec3(15, 135, 0),
                offset = new Vec3(-1.25f, -1.75f, 0);
        private Entity entity;
        private int xPos, yPos, width, height;

        public Builder(Entity entity, int xPos, int yPos, int width, int height) {
            this.entity = entity;
            this.xPos = xPos;
            this.yPos = yPos;
            this.width = width;
            this.height = height;
        }

        public EntityWidget build() {
            return new EntityWidget(this);
        }

        public Builder defaultRotation(float x, float y, float z) {
            this.defaultRotation = new Vec3(x, y, z);
            return this;
        }

        public Builder offset(float x, float y, float z) {
            this.offset = new Vec3(-x, y, z);
            return this;
        }

        public Builder rotationSpeed(float rotationSpeed) {
            this.rotationSpeed = rotationSpeed;
            return this;
        }

        public Builder scale(float x, float y, float z) {
            this.scale = new Vec3(x, y, z);
            return this;
        }
    }

    public static void renderEntity(PoseStack stack, Entity entity, Vec3 rotation, Vec3 scale, Vec3 offset, int xPos,
                                    int yPos) {
        stack.pushPose();
        stack.translate(xPos, yPos, 1050.0F);
        stack.scale(1.0F, 1.0F, -1.0F);
        stack.translate(0.0D, 0.0D, 1000.0D);
        stack.scale((float) scale.x(), (float) scale.y(), (float) scale.z());
        final Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        stack.mulPose(quaternion);
        stack.translate(offset.x(), offset.y(), offset.z());
        stack.mulPose(new Quaternion((float) -rotation.x(), (float) -rotation.y(), (float) -rotation.z(), true));
        final EntityRenderDispatcher renderManager = Minecraft.getInstance().getEntityRenderDispatcher();
        final MultiBufferSource.BufferSource buffer = MultiBufferSource
                .immediate(Tesselator.getInstance().getBuilder());
        render(renderManager, entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, stack, buffer, 15728880);
        buffer.endBatch();
        stack.popPose();
    }

    private static <E extends Entity> void render(EntityRenderDispatcher renderManager, E entity, double xPos,
                                                  double yPos, double zPos, float rotation, float delta, PoseStack poseStack, MultiBufferSource buffer,
                                                  int packedLight) {
        final EntityRenderer<? super E> entityRenderer = renderManager.getRenderer(entity);

        try {
            final Vec3 renderOffset = entityRenderer.getRenderOffset(entity, delta);
            final double finalX = xPos + renderOffset.x();
            final double finalY = yPos + renderOffset.y();
            final double finalZ = zPos + renderOffset.z();
            poseStack.pushPose();
            poseStack.translate(finalX, finalY, finalZ);
            entityRenderer.render(entity, rotation, delta, poseStack, buffer, packedLight);

            // if (entity.displayFireAnimation()) {
            //     renderManager.renderFlame(poseStack, buffer, entity);
            // }

            poseStack.translate(-renderOffset.x(), -renderOffset.y(), -renderOffset.z());
            poseStack.popPose();
        } catch (final Exception exception) {
            final CrashReport crashReport = CrashReport.forThrowable(exception, "Rendering entity in world");
            final CrashReportCategory entityCategory = crashReport.addCategory("Entity being rendered");
            entity.fillCrashReportCategory(entityCategory);
            final CrashReportCategory detailsCategory = crashReport.addCategory("Renderer details");
            detailsCategory.setDetail("Assigned renderer", entityRenderer);
            assert Minecraft.getInstance().level != null;
            detailsCategory.setDetail("Location",
                    CrashReportCategory.formatLocation(Minecraft.getInstance().level, xPos, yPos, zPos));
            detailsCategory.setDetail("Rotation", rotation);
            detailsCategory.setDetail("Delta", delta);
            throw new ReportedException(crashReport);
        }
    }
}