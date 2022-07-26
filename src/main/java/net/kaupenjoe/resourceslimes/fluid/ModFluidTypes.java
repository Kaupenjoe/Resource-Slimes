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

	public static final RegistryObject<FluidType> CITRINE_SLIME_FLUID = register(ModFluids.CITRINE_SLIME_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> ZIRCON_SLIME_FLUID = register(ModFluids.ZIRCON_SLIME_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));
	
	public static final RegistryObject<FluidType> DIRTY_WATER_FLUID = register(ModFluids.DIRTY_WATER_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> DIAMOND_SLIME_FLUID = register(ModFluids.DIAMOND_SLIME_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> EMERALD_SLIME_FLUID = register(ModFluids.EMERALD_SLIME_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> TANZANITE_SLIME_FLUID = register(ModFluids.TANZANITE_SLIME_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> BLACK_OPAL_SLIME_FLUID = register(ModFluids.BLACK_OPAL_SLIME_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> PINK_SLIME_PEARL_SLIME_FLUID = register(
			ModFluids.PINK_SLIME_PEARL_SLIME_FLUID, FluidType.Properties.create().lightLevel(2).density(15).viscosity(5)
					.sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));

	public static final RegistryObject<FluidType> SOAPY_WATER_FLUID_PROPERTIES = register(ModFluids.SOAPY_WATER_FLUID,
			FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
					SoundEvents.HONEY_DRINK));

	private static RegistryObject<FluidType> register(RegistryObject<FlowingFluid> fluid, FluidType.Properties props) {
		return FLUID_TYPES.register(ForgeRegistries.FLUIDS.getKey(fluid.get()).getPath(), () -> new FluidType(props));
	}
	
	public static void register(IEventBus eventBus) {
		FLUID_TYPES.register(eventBus);
	}
}
