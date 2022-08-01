package net.kaupenjoe.resourceslimes.recipe;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ResourceSlimes.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GemCuttingStationRecipe>> GEM_CUTTING_SERIALIZER =
            SERIALIZERS.register("gem_cutting", () -> GemCuttingStationRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<GemInfusingStationRecipe>> GEM_INFUSING_SERIALIZER =
            SERIALIZERS.register("gem_infusing", () -> GemInfusingStationRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<SlimeExtractCleaningStationRecipe>> SLIME_EXTRACT_CLEANING_SERIALIZER =
            SERIALIZERS.register("extract_cleaning", () -> SlimeExtractCleaningStationRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<SlimeIncubationRecipe>> SLIME_INCUBATION_SERIALIZER =
            SERIALIZERS.register("slime_incubation", () -> SlimeIncubationRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
