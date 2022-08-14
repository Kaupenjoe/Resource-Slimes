package net.kaupenjoe.resourceslimes.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

public class JEIEnergyInfoArea extends InfoArea implements IDrawable {
    private final int maxEnergy;
    private final int recipeEnergy;
    private final int width;
    private final int height;

    public JEIEnergyInfoArea(int xMin, int yMin, int width, int height, int maxEnergy, int recipeEnergy)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.maxEnergy = maxEnergy;
        this.recipeEnergy = recipeEnergy;
        this.width = width;
        this.height = height;
    }

    public List<Component> getTooltips() {
        return List.of(new TextComponent(recipeEnergy+"/"+maxEnergy+" FE"));
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
    public void draw(PoseStack transform) {

        final int height = area.getHeight();
        int stored = (int)(height*(recipeEnergy/(float)maxEnergy));
        fillGradient(
                transform,
                area.getX(), area.getY()+(height-stored),
                area.getX() + area.getWidth(), area.getY() +area.getHeight(),
                0xffb51500, 0xff600b00
        );
    }

    @Override
    public void draw(PoseStack poseStack, int xOffset, int yOffset) {
        this.draw(poseStack);
    }
}
