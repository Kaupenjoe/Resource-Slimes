package net.kaupenjoe.resourceslimes.client;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.ModelManager;

public class SlimeyResourceItemRenderer extends ItemRenderer {
    public SlimeyResourceItemRenderer(TextureManager textureManager, ModelManager modelManager,
                                      ItemColors itemColors, BlockEntityWithoutLevelRenderer blockEntityWithoutLevelRenderer) {
        super(textureManager, modelManager, itemColors, blockEntityWithoutLevelRenderer);
    }
}
