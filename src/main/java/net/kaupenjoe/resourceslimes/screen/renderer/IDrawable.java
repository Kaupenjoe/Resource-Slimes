package net.kaupenjoe.resourceslimes.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

// CREDIT: https://github.com/mezz/JustEnoughItems by mezz
// Under MIT-License: https://github.com/mezz/JustEnoughItems/blob/1.18/LICENSE.txt
public interface IDrawable {

    int getWidth();

    int getHeight();

    default void draw(PoseStack poseStack) {
        draw(poseStack, 0, 0);
    }

    void draw(PoseStack poseStack, int xOffset, int yOffset);

}