package net.kaupenjoe.resourceslimes.item;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ResourceSlimes.MOD_ID);

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_CITRINE = ITEMS.register("cut_citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_ZIRCON = ITEMS.register("cut_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RAW_TANZANITE = ITEMS.register("raw_tanzanite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_TANZANITE = ITEMS.register("cut_tanzanite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RAW_BLACK_OPAL = ITEMS.register("raw_black_opal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
    public static final RegistryObject<Item> CUT_BLACK_OPAL = ITEMS.register("cut_black_opal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static final RegistryObject<Item> RED_SLIME_PEARL = ITEMS.register("red_slime_pearl",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
