package net.kaupenjoe.resourceslimes.world.dimension;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> SLIMEWARION = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(ResourceSlimes.MOD_ID, "slime_warion"));
    public static final ResourceKey<DimensionType> SLIMEWARION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, SLIMEWARION.location());
}
