package net.kaupenjoe.resourceslimes.world.feature;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.config.ResourceSlimesCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ResourceSlimes.MOD_ID);

    public static final RegistryObject<PlacedFeature> CITRINE_ORE_PLACED_TRIANGLE = PLACED_FEATURES.register("citrine_ore_placed_triangle",
            () -> new PlacedFeature(
             ModConfiguredFeatures.CITRINE_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(60)))));

    public static final RegistryObject<PlacedFeature> CITRINE_ORE_PLACED_UNIFORM = PLACED_FEATURES.register("citrine_ore_placed_uniform",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.CITRINE_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(60)))));

    public static final RegistryObject<PlacedFeature> ZIRCON_ORE_PLACED_TRIANGLE = PLACED_FEATURES.register("zircon_ore_placed_triangle",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.ZIRCON_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(28)))));

    public static final RegistryObject<PlacedFeature> ZIRCON_ORE_PLACED_UNIFORM = PLACED_FEATURES.register("zircon_ore_placed_uniform",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.ZIRCON_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0)))));


    public static final RegistryObject<PlacedFeature> TANZANITE_ORE_PLACED_TRIANGLE = PLACED_FEATURES.register("tanzanite_ore_placed_triangle",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.TANZANITE_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(32)))));

    public static final RegistryObject<PlacedFeature> TANZANITE_ORE_PLACED_TRIANGLE_MOUNTAIN = PLACED_FEATURES.register("tanzanite_ore_placed_triangle_mountains",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.TANZANITE_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(128), VerticalAnchor.absolute(300)))));


    public static final RegistryObject<PlacedFeature> BLACK_ORE_PLACED_LOW = PLACED_FEATURES.register("black_opal_ore_placed_low",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.BLACK_OPAL_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-32)))));
    public static final RegistryObject<PlacedFeature> END_BLACK_ORE_PLACED_TRIANGLE = PLACED_FEATURES.register("end_black_opal_ore_placed",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.END_BLACK_OPAL_ORE.getHolder().get(), ModOrePlacement.commonOrePlacement(
                    6,
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)))));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
