package net.kaupenjoe.resourceslimes.world.gen;

import net.kaupenjoe.resourceslimes.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ModOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(ModPlacedFeatures.CITRINE_ORE_PLACED_TRIANGLE);
        base.add(ModPlacedFeatures.CITRINE_ORE_PLACED_UNIFORM);

        base.add(ModPlacedFeatures.ZIRCON_ORE_PLACED_TRIANGLE);
        base.add(ModPlacedFeatures.ZIRCON_ORE_PLACED_UNIFORM);

        if(event.getCategory() == Biome.BiomeCategory.SAVANNA || event.getCategory() == Biome.BiomeCategory.PLAINS) {
            base.add(ModPlacedFeatures.TANZANITE_ORE_PLACED_TRIANGLE);
        }
        if(event.getCategory() == Biome.BiomeCategory.MOUNTAIN) {
            base.add(ModPlacedFeatures.TANZANITE_ORE_PLACED_TRIANGLE_MOUNTAIN);
        }

        if(event.getCategory() == Biome.BiomeCategory.SAVANNA || event.getCategory() == Biome.BiomeCategory.DESERT) {
            base.add(ModPlacedFeatures.BLACK_ORE_PLACED_LOW);
        }
        if(event.getCategory() == Biome.BiomeCategory.THEEND) {
            base.add(ModPlacedFeatures.END_BLACK_ORE_PLACED_TRIANGLE);
        }
    }
}
