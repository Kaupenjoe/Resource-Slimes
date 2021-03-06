package net.kaupenjoe.resourceslimes.data.loot;

import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        oreDrops();
        blockEntityDrops();
    }

    private void blockEntityDrops() {
        this.dropSelf(ModBlocks.GEM_CUTTING_STATION.get());
        this.dropSelf(ModBlocks.GEM_INFUSING_STATION.get());
        this.dropSelf(ModBlocks.SLIME_EXTRACT_CLEANING_STATION.get());
        this.dropSelf(ModBlocks.SLIME_INCUBATION_STATION.get());
    }

    private void oreDrops() {
        this.add(ModBlocks.CITRINE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.CITRINE_ORE.get(), ModItems.RAW_CITRINE.get()));
        this.add(ModBlocks.DEEPSLATE_CITRINE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_CITRINE_ORE.get(), ModItems.RAW_CITRINE.get()));

        this.add(ModBlocks.ZIRCON_ORE.get(),
                (block) -> createOreDrop(ModBlocks.ZIRCON_ORE.get(), ModItems.RAW_ZIRCON.get()));
        this.add(ModBlocks.DEEPSLATE_ZIRCON_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_ZIRCON_ORE.get(), ModItems.RAW_ZIRCON.get()));

        this.add(ModBlocks.TANZANITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()));
        this.add(ModBlocks.DEEPSLATE_TANZANITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()));

        this.add(ModBlocks.BLACK_OPAL_ORE.get(),
                (block) -> createOreDrop(ModBlocks.BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
        this.add(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
        this.add(ModBlocks.END_BLACK_OPAL_ORE.get(),
                (block) -> createOreDrop(ModBlocks.END_BLACK_OPAL_ORE.get(), ModItems.RAW_BLACK_OPAL.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
