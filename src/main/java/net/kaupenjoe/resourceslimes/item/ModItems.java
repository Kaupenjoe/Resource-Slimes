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
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			ResourceSlimes.MOD_ID);

	public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register("raw_citrine",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> UNCUT_CITRINE = ITEMS.register("uncut_citrine",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> CUT_CITRINE = ITEMS.register("cut_citrine",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> INFUSED_CITRINE = ITEMS.register("infused_citrine",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> UNCUT_ZIRCON = ITEMS.register("uncut_zircon",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> CUT_ZIRCON = ITEMS.register("cut_zircon",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> INFUSED_ZIRCON = ITEMS.register("infused_zircon",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> CUT_DIAMOND = ITEMS.register("cut_diamond",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> INFUSED_DIAMOND = ITEMS.register("infused_diamond",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> CUT_EMERALD = ITEMS.register("cut_emerald",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> INFUSED_EMERALD = ITEMS.register("infused_emerald",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> RAW_TANZANITE = ITEMS.register("raw_tanzanite",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> UNCUT_TANZANITE = ITEMS.register("uncut_tanzanite",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> CUT_TANZANITE = ITEMS.register("cut_tanzanite",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> INFUSED_TANZANITE = ITEMS.register("infused_tanzanite",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> RAW_BLACK_OPAL = ITEMS.register("raw_black_opal",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> UNCUT_BLACK_OPAL = ITEMS.register("uncut_black_opal",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> CUT_BLACK_OPAL = ITEMS.register("cut_black_opal",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> INFUSED_BLACK_OPAL = ITEMS.register("infused_black_opal",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> PINK_SLIME_PEARL = ITEMS.register("pink_slime_pearl",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));
	public static final RegistryObject<Item> INFUSED_PINK_SLIME_PEARL = ITEMS.register("infused_pink_slime_pearl",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> CITRINE_SLIME_FLUID_BUCKET = ITEMS.register("citrine_slime_fluid_bucket",
			() -> new BucketItem(ModFluids.CITRINE_SLIME_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> ZIRCON_SLIME_FLUID_BUCKET = ITEMS.register("zircon_slime_fluid_bucket",
			() -> new BucketItem(ModFluids.ZIRCON_SLIME_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> DIAMOND_SLIME_FLUID_BUCKET = ITEMS.register("diamond_slime_fluid_bucket",
			() -> new BucketItem(ModFluids.DIAMOND_SLIME_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> EMERALD_SLIME_FLUID_BUCKET = ITEMS.register("emerald_slime_fluid_bucket",
			() -> new BucketItem(ModFluids.EMERALD_SLIME_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> TANZANITE_SLIME_FLUID_BUCKET = ITEMS
			.register("tanzanite_slime_fluid_bucket", () -> new BucketItem(ModFluids.TANZANITE_SLIME_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> BLACK_OPAL_SLIME_FLUID_BUCKET = ITEMS
			.register("black_opal_slime_fluid_bucket", () -> new BucketItem(ModFluids.BLACK_OPAL_SLIME_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> PINK_SLIME_PEARL_SLIME_FLUID_BUCKET = ITEMS.register(
			"pink_slime_pearl_slime_fluid_bucket", () -> new BucketItem(ModFluids.PINK_SLIME_PEARL_SLIME_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> SOAPY_WATER_FLUID_BUCKET = ITEMS.register("soapy_water_fluid_bucket",
			() -> new BucketItem(ModFluids.SOAPY_WATER_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));
	public static final RegistryObject<Item> DIRTY_WATER_FLUID_BUCKET = ITEMS.register("dirty_water_fluid_bucket",
			() -> new BucketItem(ModFluids.DIRTY_WATER_FLUID,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).stacksTo(1)));

	public static final RegistryObject<Item> RESOURCE_SLIME_SPAWN_EGG = ITEMS.register("resource_slime_spawn_egg",
			() -> new ForgeSpawnEggItem(ModEntityTypes.RESOURCE_SLIME, 0x948e8d, 0x3b3635,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> ENERGY_SLIME_SPAWN_EGG = ITEMS.register("energy_slime_spawn_egg",
			() -> new ForgeSpawnEggItem(ModEntityTypes.ENERGY_SLIME, 0xdbdb40, 0x3b140c,
					new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	public static final RegistryObject<Item> GEM_CUTTER_TOOL = ITEMS.register("gem_cutter_tool",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES).durability(32)));

	public static final RegistryObject<Item> SOAP = ITEMS.register("soap",
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIMES)));

	/* SLIME RESOURCES */
	public static void onRegisterItems(IForgeRegistry<Item> registry) {
		//var resources = ModRegistries.SLIME_RESOURCES.get().getValues();
//
		//resources.stream().filter(sr -> sr != BuiltinSlimeResources.EMPTY.get() && sr.isEnabled()).forEach(resource -> {
		//	Item extractItem = ;
		//	Item slimeyExtractItem = new SlimeyExtractItem(
		//			new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIME_EXTRACTS));
//
		//	ITEMS.register(resource.name().toLowerCase() + "_extract", () ->  extractItem);
		//	// resource.setExtractItem(extractItem);
//
		//	ITEMS.register("slimey_" + resource.name().toLowerCase() + "_extract", () -> slimeyExtractItem);
		//	// resource.setSlimeyExtractItem(slimeyExtractItem);
		//});
	}

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
