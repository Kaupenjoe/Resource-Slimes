package net.kaupenjoe.resourceslimes.block.entity;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.block.custom.GemInfusingStationBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ResourceSlimes.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemCuttingStationBlockEntity>> GEM_CUTTING_STATION_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("gem_cutting_station_block_entity", () ->
                    BlockEntityType.Builder.of(GemCuttingStationBlockEntity::new,
                            ModBlocks.GEM_CUTTING_STATION.get()).build(null));

    public static final RegistryObject<BlockEntityType<GemInfusingStationBlockEntity>> GEM_INFUSING_STATION_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("gem_infusing_station_block_entity", () ->
                    BlockEntityType.Builder.of(GemInfusingStationBlockEntity::new,
                            ModBlocks.GEM_INFUSING_STATION.get()).build(null));

    public static final RegistryObject<BlockEntityType<SlimeExtractCleaningStationBlockEntity>> SLIME_EXTRACT_CLEANING_STATION_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("slime_extract_cleaning_station_block_entity", () ->
                    BlockEntityType.Builder.of(SlimeExtractCleaningStationBlockEntity::new,
                            ModBlocks.GEM_INFUSING_STATION.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
