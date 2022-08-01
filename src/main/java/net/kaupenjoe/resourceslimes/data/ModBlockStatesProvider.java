package net.kaupenjoe.resourceslimes.data;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ResourceSlimes.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.CITRINE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_CITRINE_ORE.get());

        simpleBlock(ModBlocks.ZIRCON_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_ZIRCON_ORE.get());

        simpleBlock(ModBlocks.TANZANITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_TANZANITE_ORE.get());

        simpleBlock(ModBlocks.BLACK_OPAL_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get());
        simpleBlock(ModBlocks.END_BLACK_OPAL_ORE.get());

        simpleBlock(ModBlocks.SLIMEY_DIRT.get());

        horizontalBlock(ModBlocks.GEM_CUTTING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_cutting_station")));
        horizontalBlock(ModBlocks.GEM_INFUSING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/gem_infusing_station")));
        horizontalBlock(ModBlocks.SLIME_EXTRACT_CLEANING_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/slime_extract_cleaning_station")));
        horizontalBlock(ModBlocks.SLIME_INCUBATION_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/slime_incubation_station")));
    }
}
