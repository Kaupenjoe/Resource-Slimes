package net.kaupenjoe.resourceslimes.entity.client;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation RES_SLIME_EYES = new ModelLayerLocation(
            new ResourceLocation(ResourceSlimes.MOD_ID,"resource_slime_eyes"), "resource_slime_eyes");
    public static final ModelLayerLocation RES_SLIME_INNER_CUBE = new ModelLayerLocation(
            new ResourceLocation(ResourceSlimes.MOD_ID,"resource_slime_inner"), "resource_slime_inner");
    public static final ModelLayerLocation RES_SLIME_OUTER_CUBE = new ModelLayerLocation(
            new ResourceLocation(ResourceSlimes.MOD_ID,"resource_slime_outer"), "resource_slime_outer");
}
