package net.kaupenjoe.resourceslimes.util;

import java.util.function.Supplier;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

public final class ResourceSlimesRegistries {
    static final DeferredRegister<SlimeResource> DEFERRED_SLIME_RESOURCES = DeferredRegister
            .create(Keys.SLIME_RESOURCES, Keys.SLIME_RESOURCES.location().getNamespace());

    public static final Supplier<IForgeRegistry<SlimeResource>> SLIME_RESOURCES = DEFERRED_SLIME_RESOURCES
            .makeRegistry(SlimeResource.class, ResourceSlimesRegistries::getSlimeResourceBuilder);

    public static RegistryBuilder<SlimeResource> getSlimeResourceBuilder() {
        return makeRegistry(Keys.SLIME_RESOURCES, SlimeResource.class);
    }

    private static <T extends IForgeRegistryEntry<T>> RegistryBuilder<T> makeRegistry(ResourceKey<? extends Registry<T>> key, Class<T> type) {
        return new RegistryBuilder<T>().setName(key.location()).setType(type);
    }

    public static class Keys {
        public static final ResourceKey<Registry<SlimeResource>> SLIME_RESOURCES = ResourceKey
                .createRegistryKey(new ResourceLocation(ResourceSlimes.MOD_ID, "slime_resources"));
    }
}