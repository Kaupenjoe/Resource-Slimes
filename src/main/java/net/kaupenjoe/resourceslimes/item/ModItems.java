package net.kaupenjoe.resourceslimes.item;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ResourceSlimes.MOD_ID);

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> UNCUT_CITRINE = ITEMS.register("uncut_citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_CITRINE = ITEMS.register("cut_citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> UNCUT_ZIRCON = ITEMS.register("uncut_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_ZIRCON = ITEMS.register("cut_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RAW_TANZANITE = ITEMS.register("raw_tanzanite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> UNCUT_TANZANITE = ITEMS.register("uncut_tanzanite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_TANZANITE = ITEMS.register("cut_tanzanite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RAW_BLACK_OPAL = ITEMS.register("raw_black_opal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> UNCUT_BLACK_OPAL = ITEMS.register("uncut_black_opal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_BLACK_OPAL = ITEMS.register("cut_black_opal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RED_SLIME_PEARL = ITEMS.register("red_slime_pearl",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));


    public static final RegistryObject<Item> CITRINE_SLIME_FLUID_BUCKET = ITEMS.register("citrine_slime_fluid_bucket",
            () -> new BucketItem(ModFluids.CITRINE_SLIME_FLUID, new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
    public static final RegistryObject<Item> ZIRCON_SLIME_FLUID_BUCKET = ITEMS.register("zircon_slime_fluid_bucket",
            () -> new BucketItem(ModFluids.ZIRCON_SLIME_FLUID, new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_SLIME_FLUID_BUCKET = ITEMS.register("diamond_slime_fluid_bucket",
            () -> new BucketItem(ModFluids.DIAMOND_SLIME_FLUID, new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
    public static final RegistryObject<Item> EMERALD_SLIME_FLUID_BUCKET = ITEMS.register("emerald_slime_fluid_bucket",
            () -> new BucketItem(ModFluids.EMERALD_SLIME_FLUID, new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
    public static final RegistryObject<Item> TANZANITE_SLIME_FLUID_BUCKET = ITEMS.register("tanzanite_slime_fluid_bucket",
            () -> new BucketItem(ModFluids.TANZANITE_SLIME_FLUID, new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
    public static final RegistryObject<Item> BLACK_OPAL_SLIME_FLUID_BUCKET = ITEMS.register("black_opal_slime_fluid_bucket",
            () -> new BucketItem(ModFluids.BLACK_OPAL_SLIME_FLUID, new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
    public static final RegistryObject<Item> RED_SLIME_PEARL_SLIME_FLUID_BUCKET = ITEMS.register("red_slime_pearl_slime_fluid_bucket",
            () -> new BucketItem(ModFluids.RED_SLIME_PEARL_SLIME_FLUID, new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));


    public static final RegistryObject<Item> RESOURCE_SLIME_SPAWN_EGG = ITEMS.register("resource_slime_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.RESOURCE_SLIME,0x948e8d, 0x3b3635,
                    new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
