package net.kaupenjoe.resourceslimes.data.custom;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BiomeModifierProvider implements DataProvider
{
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator generator;
    private final String modid;
    private final Map<String, JsonElement> toSerialize = Maps.newHashMap();

    public BiomeModifierProvider(DataGenerator generator, String modid)
    {
        this.generator = generator;
        this.modid = modid;
    }

    @Override
    public void run(CachedOutput cachedOutput) throws IOException
    {
        addBiomeModifiers();

        Path forgePath = generator.getOutputFolder().resolve("data/forge/biome_modifiers/biome_modifiers.json");
        String modPath = "data/" + modid + "/forge/biome_modifiers";
        List<ResourceLocation> entries = new ArrayList<>();

        toSerialize.forEach(LamdbaExceptionUtils.rethrowBiConsumer((s, jsonElement) ->
        {
            entries.add(new ResourceLocation(modid, s));
            Path modifierPath = generator.getOutputFolder().resolve(modPath + s + ".json");
            DataProvider.saveStable(cachedOutput, jsonElement, modifierPath);
        }));

        JsonObject forgeJson = new JsonObject();
        forgeJson.add("entries", GSON.toJsonTree(entries.stream().map(ResourceLocation::toString).toList()));
        DataProvider.saveStable(cachedOutput, forgeJson, forgePath);
    }

    public abstract void addBiomeModifiers();

    @Override
    public @NotNull String getName()
    {
        return "Biome Modifiers";
    }

    public <T extends BiomeModifier> void add(String id, T instance)
    {
        JsonElement jsonElement = BiomeModifier.DIRECT_CODEC.encodeStart(JsonOps.INSTANCE, instance).getOrThrow(false, ResourceSlimes.LOGGER::error);
        this.toSerialize.put(id, jsonElement);
    }
}
