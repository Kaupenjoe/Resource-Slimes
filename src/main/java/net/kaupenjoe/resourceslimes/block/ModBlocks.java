package net.kaupenjoe.resourceslimes.block;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.custom.GemCuttingStationBlock;
import net.kaupenjoe.resourceslimes.block.custom.GemInfusingStationBlock;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.item.ModCreativeModeTab;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ResourceSlimes.MOD_ID);

    public static final RegistryObject<Block> CITRINE_ORE = registerBlock("citrine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);
    public static final RegistryObject<Block> DEEPSLATE_CITRINE_ORE = registerBlock("deepslate_citrine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5.5f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);

    public static final RegistryObject<Block> ZIRCON_ORE = registerBlock("zircon_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);
    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = registerBlock("deepslate_zircon_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(7.5f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);

    public static final RegistryObject<Block> TANZANITE_ORE = registerBlock("tanzanite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);
    public static final RegistryObject<Block> DEEPSLATE_TANZANITE_ORE = registerBlock("deepslate_tanzanite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(10.5f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);

    public static final RegistryObject<Block> BLACK_OPAL_ORE = registerBlock("black_opal_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(11f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);
    public static final RegistryObject<Block> DEEPSLATE_BLACK_OPAL_ORE = registerBlock("deepslate_black_opal_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(12.5f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);
    public static final RegistryObject<Block> END_BLACK_OPAL_ORE = registerBlock("end_black_opal_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(12.5f).requiresCorrectToolForDrops()), ModCreativeModeTab.RESOURCE_SLIMES);


    public static final RegistryObject<LiquidBlock> CITRINE_SLIME_FLUID_BLOCK = registerBlockWithoutBlockItem("citrine_slime_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.CITRINE_SLIME_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    public static final RegistryObject<LiquidBlock> ZIRCON_SLIME_FLUID_BLOCK = registerBlockWithoutBlockItem("zircon_slime_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.ZIRCON_SLIME_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    public static final RegistryObject<LiquidBlock> DIAMOND_SLIME_FLUID_BLOCK = registerBlockWithoutBlockItem("diamond_slime_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.DIAMOND_SLIME_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    public static final RegistryObject<LiquidBlock> EMERALD_SLIME_FLUID_BLOCK = registerBlockWithoutBlockItem("emerald_slime_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.EMERALD_SLIME_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    public static final RegistryObject<LiquidBlock> TANZANITE_SLIME_FLUID_BLOCK = registerBlockWithoutBlockItem("tanzanite_slime_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.TANZANITE_SLIME_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    public static final RegistryObject<LiquidBlock> BLACK_OPAL_SLIME_FLUID_BLOCK = registerBlockWithoutBlockItem("black_opal_slime_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.BLACK_OPAL_SLIME_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    public static final RegistryObject<LiquidBlock> PINK_SLIME_PEARL_SLIME_FLUID_BLOCK = registerBlockWithoutBlockItem("pink_slime_pearl_slime_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.PINK_SLIME_PEARL_SLIME_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    public static final RegistryObject<LiquidBlock> SOAPY_WATER_BLOCK = registerBlockWithoutBlockItem("soapy_fluid_block",
            () -> new LiquidBlock(() -> ModFluids.SOAPY_WATER_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));


    public static final RegistryObject<Block> GEM_CUTTING_STATION = registerBlock("gem_cutting_station",
            () -> new GemCuttingStationBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.5f).noOcclusion()),
            ModCreativeModeTab.RESOURCE_SLIMES);

    public static final RegistryObject<Block> GEM_INFUSING_STATION = registerBlock("gem_infusing_station",
            () -> new GemInfusingStationBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.5f).noOcclusion()),
            ModCreativeModeTab.RESOURCE_SLIMES);


    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltipKey) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(new TranslatableComponent(tooltipKey));
            }
        });
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
