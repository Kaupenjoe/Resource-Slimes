package net.kaupenjoe.resourceslimes.world.feature;

import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.config.ResourceSlimesCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_CITRINE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.CITRINE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_CITRINE_ORE.get().defaultBlockState()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CITRINE_ORE = FeatureUtils.register("citrine_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_CITRINE_ORES, ResourceSlimesCommonConfigs.CITRINE_ORE_VEIN_SIZE.get()));


    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_ZIRCON_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ZIRCON_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ZIRCON_ORE.get().defaultBlockState()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ZIRCON_ORE = FeatureUtils.register("zircon_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_ZIRCON_ORES, ResourceSlimesCommonConfigs.ZIRCON_ORE_VEIN_SIZE.get()));


    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TANZANITE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TANZANITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TANZANITE_ORE.get().defaultBlockState()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TANZANITE_ORE = FeatureUtils.register("tanzanite_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TANZANITE_ORES, ResourceSlimesCommonConfigs.TANZANITE_ORE_VEIN_SIZE.get()));


    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_BLACK_OPAL_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.BLACK_OPAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> END_BLACK_OPAL_ORES = List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.END_BLACK_OPAL_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> BLACK_OPAL_ORE = FeatureUtils.register("black_opal_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_BLACK_OPAL_ORES, ResourceSlimesCommonConfigs.BLACK_OPAL_ORE_VEIN_SIZE.get()));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> END_BLACK_OPAL_ORE = FeatureUtils.register("end_black_opal_ore",
            Feature.ORE, new OreConfiguration(END_BLACK_OPAL_ORES, ResourceSlimesCommonConfigs.END_BLACK_OPAL_ORE_VEIN_SIZE.get()));
}