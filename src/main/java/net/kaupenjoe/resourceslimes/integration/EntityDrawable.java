package net.kaupenjoe.resourceslimes.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.kaupenjoe.resourceslimes.screen.renderer.EntityWidget;
import net.kaupenjoe.resourceslimes.util.resources.BuiltinSlimeResources;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class EntityDrawable implements IDrawableAnimated {
    private LivingEntity livingEntity;
    private int width;
    private int height;

    public EntityDrawable(int height, int width, Item resourceItem) {
        this.livingEntity = ModEntityTypes.RESOURCE_SLIME.get().create(Minecraft.getInstance().level);
        ((ResourceSlimeEntity) this.livingEntity)
                .setResource(new ItemStack(SlimeResource.getResourceByCraftingItem(resourceItem).getSlimeyExtractItem()));
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void draw(PoseStack poseStack, int xOffset, int yOffset) {
        EntityWidget.renderEntity(poseStack, livingEntity, new Vec3(15, -225, 0),
                new Vec3(32, 32, 32), Vec3.ZERO, xOffset, yOffset);
    }
}
