package net.kaupenjoe.resourceslimes.fluid;

import com.mojang.math.Vector3f;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister
            .create(ForgeRegistries.Keys.FLUID_TYPES, ResourceSlimes.MOD_ID);

    public static final RegistryObject<FluidType> CITRINE_SLIME_FLUID = register("citrine_slime_fluid",
            () -> new RSFluidType(0xbff39517, new Vector3f(243f / 255f, 149f / 255f, 23f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> ZIRCON_SLIME_FLUID = register("zircon_slime_fluid",
            () -> new RSFluidType(0xbfc65b60, new Vector3f(193f / 255f, 91f / 255f, 96f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> DIRTY_WATER_FLUID = register("dirty_water_fluid",
            () -> new RSFluidType(0xbf99503d, new Vector3f(153f / 255f, 80f / 255f, 61f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> DIAMOND_SLIME_FLUID = register("diamond_slime_fluid",
            () -> new RSFluidType(0xbf2cd1c1, new Vector3f(44f / 255f, 209f / 255f, 193f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> EMERALD_SLIME_FLUID = register("emerald_slime_fluid",
            () -> new RSFluidType(0xbf2bd557, new Vector3f(43f / 255f, 213f / 255f, 87f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> TANZANITE_SLIME_FLUID = register("tanzanite_slime_fluid",
            () -> new RSFluidType(0xbf213de5, new Vector3f(33f / 255f, 61f / 255f, 229f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> BLACK_OPAL_SLIME_FLUID = register("black_opal_slime_fluid",
            () -> new RSFluidType(0xbf1c4766, new Vector3f(28f / 255f, 71f / 255f, 102f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType>
            PINK_SLIME_PEARL_SLIME_FLUID =
            register("pink_slime_pearl_slime_fluid",
                    () -> new RSFluidType(0xbfbb23cf, new Vector3f(187f / 255f, 35f / 255f, 207f / 255f),
                            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5)
                                    .sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK)));

    public static final RegistryObject<FluidType> SOAPY_WATER_FLUID_PROPERTIES = register("soapy_water_fluid",
            () -> new RSFluidType(0xbffcb6ea, new Vector3f(252f / 255f, 182f / 255f, 234f / 255f),
                    FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                            SoundEvents.HONEY_DRINK)));

    private static RegistryObject<FluidType> register(String name, Supplier<FluidType> fluidType) {
        return FLUID_TYPES.register(name, fluidType);
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
