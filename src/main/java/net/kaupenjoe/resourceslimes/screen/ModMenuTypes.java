package net.kaupenjoe.resourceslimes.screen;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, ResourceSlimes.MOD_ID);

    public static final RegistryObject<MenuType<GemCuttingStationMenu>> GEM_CUTTING_STATION_MENU =
            registerMenuType(GemCuttingStationMenu::new, "gem_cutting_station_menu");

    public static final RegistryObject<MenuType<GemInfusingStationMenu>> GEM_INFUSING_STATION_MENU =
            registerMenuType(GemInfusingStationMenu::new, "gem_infusing_station_menu");

    public static final RegistryObject<MenuType<SlimeExtractCleaningStationMenu>> SLIME_EXTRACT_CLEANING_STATION_MENU =
            registerMenuType(SlimeExtractCleaningStationMenu::new, "slimes_extract_cleaning_station_menu");

    public static final RegistryObject<MenuType<SlimeIncubationStationMenu>> SLIME_INCUBATION_STATION_MENU =
            registerMenuType(SlimeIncubationStationMenu::new, "slimes_incubation_station_menu");


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
