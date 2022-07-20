package net.kaupenjoe.resourceslimes;

import com.mojang.logging.LogUtils;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.block.entity.ModBlockEntities;
import net.kaupenjoe.resourceslimes.config.ResourceSlimesCommonConfigs;
import net.kaupenjoe.resourceslimes.effect.ModEffects;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.integration.ResourceSlimeIntegrations;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.particle.ModParticles;
import net.kaupenjoe.resourceslimes.potion.ModPotion;
import net.kaupenjoe.resourceslimes.recipe.ModRecipes;
import net.kaupenjoe.resourceslimes.screen.ModMenuTypes;
import net.kaupenjoe.resourceslimes.networking.ModMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ResourceSlimes.MOD_ID)
public class ResourceSlimes {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "resourceslimes";

    public ResourceSlimes() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEntityTypes.register(eventBus);
        ModFluids.register(eventBus);

        ModEffects.register(eventBus);
        ModPotion.register(eventBus);

        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);

        ModRecipes.register(eventBus);
        ModParticles.register(eventBus);


        eventBus.addListener(this::setup);
        eventBus.addListener(ResourceSlimeIntegrations::sendIMCs);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ResourceSlimesCommonConfigs.SPEC, "resourceslimes-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        ResourceSlimeIntegrations.commonSetup();
    }
}
