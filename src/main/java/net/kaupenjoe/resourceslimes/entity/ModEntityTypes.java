package net.kaupenjoe.resourceslimes.entity;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ResourceSlimes.MOD_ID);

    public static final RegistryObject<EntityType<ResourceSlime>> RESOURCE_SLIME =
            ENTITY_TYPES.register("resource_slime",
            () -> EntityType.Builder.of(ResourceSlime::new, MobCategory.CREATURE)
                    .sized(2.04F, 2.04F).setTrackingRange(6)
                    .build(new ResourceLocation(ResourceSlimes.MOD_ID, "resource_slime").toString()));

    public static final RegistryObject<EntityType<EnergySlimeEntity>> ENERGY_SLIME =
            ENTITY_TYPES.register("energy_slime",
            () -> EntityType.Builder.of(EnergySlimeEntity::new, MobCategory.CREATURE)
                    .sized(2.04F, 2.04F).setTrackingRange(6)
                    .build(new ResourceLocation(ResourceSlimes.MOD_ID, "energy_slime").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
