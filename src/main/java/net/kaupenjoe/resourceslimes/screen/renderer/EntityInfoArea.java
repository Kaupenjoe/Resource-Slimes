package net.kaupenjoe.resourceslimes.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawable;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.entity.ResourceSlime;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class EntityInfoArea extends InfoArea implements IDrawable
{
    private final ResourceSlime entity;

    public EntityInfoArea(int xMin, int yMin, ResourceSlime entity, int width, int height)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.entity = entity;
    }

    public EntityInfoArea(int xMin, int yMin, Item resourceItem, int width, int height)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.entity = ModEntityTypes.RESOURCE_SLIME.get().create(Minecraft.getInstance().level);
        this.entity.setResource(new ItemStack(SlimeResource.getResourceByCraftingItem(resourceItem).getSlimeyExtractItem()));
    }

    // TODO: proper description!
    public List<Component> getTooltips() {
        return List.of(Component.translatable(entity.getResourceItem().getDescriptionId()));
    }

    @Override
    public int getWidth() {
        return area.getWidth();
    }

    @Override
    public int getHeight() {
        return area.getHeight();
    }

    @Override
    public void draw(PoseStack poseStack) {
        draw(poseStack, 0, 0);
    }

    @Override
    public void draw(PoseStack poseStack, int xOffset, int yOffset) {
        fillGradient(
                poseStack,
                area.getX() + xOffset, area.getY() + yOffset,
                area.getX() + area.getWidth(), area.getY() + area.getHeight(),
                0xff717171, 0xff717171
        );
    }
}