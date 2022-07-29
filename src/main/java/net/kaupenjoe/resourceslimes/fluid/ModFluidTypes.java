package net.kaupenjoe.resourceslimes.fluid;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister
			.create(ForgeRegistries.Keys.FLUID_TYPES, ResourceSlimes.MOD_ID);

	public static final RegistryObject<FluidType> CITRINE_SLIME_FLUID = register("citrine_slime_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> ZIRCON_SLIME_FLUID = register("zircon_slime_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));
	
	public static final RegistryObject<FluidType> DIRTY_WATER_FLUID = register("dirty_water_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> DIAMOND_SLIME_FLUID = register("diamond_slime_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> EMERALD_SLIME_FLUID = register("emerald_slime_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> TANZANITE_SLIME_FLUID = register("tanzanite_slime_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> BLACK_OPAL_SLIME_FLUID = register("black_opal_slime_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> PINK_SLIME_PEARL_SLIME_FLUID = register(
			"pink_slime_pearl_slime_fluid", FluidType.Properties.create().lightLevel(2).density(15).viscosity(5)
					.sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> SOAPY_WATER_FLUID_PROPERTIES = register("soapy_water_fluid",
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));
 
	private static RegistryObject<FluidType> register(String name, FluidType.Properties props) {
		return FLUID_TYPES.register(name, () -> new FluidType(props));
	}
	
	public static void register(IEventBus eventBus) {
		FLUID_TYPES.register(eventBus);
	}
}
