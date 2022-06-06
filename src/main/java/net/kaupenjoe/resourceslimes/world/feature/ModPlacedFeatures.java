package net.kaupenjoe.resourceslimes.world.feature;

import net.kaupenjoe.resourceslimes.config.ResourceSlimesCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> CITRINE_ORE_PLACED_TRIANGLE = PlacementUtils.register("citrine_ore_placed_triangle",
            ModConfiguredFeatures.CITRINE_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.CITRINE_ORE_VEINS_TRIANGLE_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(60))));

    public static final Holder<PlacedFeature> CITRINE_ORE_PLACED_UNIFORM = PlacementUtils.register("citrine_ore_placed_uniform",
            ModConfiguredFeatures.CITRINE_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.CITRINE_ORE_VEINS_UNIFORM_PER_CHUNK.get(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(60))));

    public static final Holder<PlacedFeature> ZIRCON_ORE_PLACED_TRIANGLE = PlacementUtils.register("zircon_ore_placed_triangle",
            ModConfiguredFeatures.ZIRCON_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.ZIRCON_ORE_VEINS_TRIANGLE_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(28))));

    public static final Holder<PlacedFeature> ZIRCON_ORE_PLACED_UNIFORM = PlacementUtils.register("zircon_ore_placed_uniform",
            ModConfiguredFeatures.ZIRCON_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.ZIRCON_ORE_VEINS_UNIFORM_PER_CHUNK.get(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0))));


    public static final Holder<PlacedFeature> TANZANITE_ORE_PLACED_TRIANGLE = PlacementUtils.register("tanzanite_ore_placed_triangle",
            ModConfiguredFeatures.TANZANITE_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.TANZANITE_ORE_VEINS_TRIANGLE_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(32))));

    public static final Holder<PlacedFeature> TANZANITE_ORE_PLACED_TRIANGLE_MOUNTAIN = PlacementUtils.register("tanzanite_ore_placed_triangle_mountains",
            ModConfiguredFeatures.TANZANITE_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.TANZANITE_ORE_VEINS_MOUNTAIN_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(128), VerticalAnchor.absolute(300))));


    public static final Holder<PlacedFeature> BLACK_ORE_PLACED_LOW = PlacementUtils.register("black_opal_ore_placed_low",
            ModConfiguredFeatures.BLACK_OPAL_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.BLACK_OPAL_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-32))));
    public static final Holder<PlacedFeature> END_BLACK_ORE_PLACED_TRIANGLE = PlacementUtils.register("end_black_opal_ore_placed",
            ModConfiguredFeatures.END_BLACK_OPAL_ORE, ModOrePlacement.commonOrePlacement(
                    ResourceSlimesCommonConfigs.END_BLACK_OPAL_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))));

}
