package net.kaupenjoe.resourceslimes.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class GeneticGuideScreen extends Screen {

    protected GeneticGuideScreen() {
        super(Component.translatable("title.guide_screen"));
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new GeneticGuideScreen());
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        font.drawWordWrap(Component.literal(""), 17, 10, 176, 0x404040);
    }
}
