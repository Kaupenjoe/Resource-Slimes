package net.kaupenjoe.resourceslimes;

import com.mojang.logging.LogUtils;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.block.entity.ModBlockEntities;
import net.kaupenjoe.resourceslimes.effect.ModEffects;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.fluid.ModFluidTypes;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.integration.ResourceSlimeIntegrations;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.networking.ModMessages;
import net.kaupenjoe.resourceslimes.particle.ModParticles;
import net.kaupenjoe.resourceslimes.potion.ModPotion;
import net.kaupenjoe.resourceslimes.recipe.ModRecipes;
import net.kaupenjoe.resourceslimes.screen.ModMenuTypes;
import net.kaupenjoe.resourceslimes.screen.util.ResourceSlimesRegistries;
import net.kaupenjoe.resourceslimes.screen.util.resources.BuiltinSlimeResources;
import net.kaupenjoe.resourceslimes.world.feature.ModConfiguredFeatures;
import net.kaupenjoe.resourceslimes.world.feature.ModPlacedFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(ResourceSlimes.MOD_ID)
public class ResourceSlimes {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "resourceslimes";

    public ResourceSlimes() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntityTypes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModEffects.register(modEventBus);
        ModPotion.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);
        ModParticles.register(modEventBus);

        BuiltinSlimeResources.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        
        modEventBus.addListener(this::setup);
        modEventBus.addListener(ResourceSlimeIntegrations::sendIMCs);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            var resources = ResourceSlimesRegistries.SLIME_RESOURCES.get().getValues();

            resources.stream().filter(sr -> sr != BuiltinSlimeResources.EMPTY.get() && sr.isEnabled()).forEach(resource -> {
                Item extractItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ResourceSlimes.MOD_ID,resource.name() + "_extract"));
                resource.setExtractItem(extractItem);

                Item slimeyExtractItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ResourceSlimes.MOD_ID,"slimey_" + resource.name() + "_extract"));
                resource.setSlimeyExtractItem(slimeyExtractItem);
            });
        });

        ModMessages.register();
        ResourceSlimeIntegrations.commonSetup();
    }
}
