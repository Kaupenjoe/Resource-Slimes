package net.kaupenjoe.resourceslimes.event;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.entity.client.ModModelLayers;
import net.kaupenjoe.resourceslimes.entity.client.ResourceSlimeModel;
import net.kaupenjoe.resourceslimes.entity.client.models.*;
import net.kaupenjoe.resourceslimes.util.ResourceSlimesRegistries;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;

@Mod.EventBusSubscriber(modid = ResourceSlimes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RESOURCE_SLIME.get(), Monster.createMonsterAttributes().build());
        event.put(ModEntityTypes.ENERGY_SLIME.get(), Monster.createMonsterAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.RES_SLIME_EYES, ResourceSlimeModel::createEyesAndMouthLayer);

        event.registerLayerDefinition(ModModelLayers.RES_SLIME_OUTER_CUBE, ResourceSlimeModel::createOuterBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RES_SLIME_INNER_CUBE, ResourceSlimeModel::createInnerBodyLayer);
        event.registerLayerDefinition(WizardHat.LAYER_LOCATION, WizardHat::createBodyLayer);
        event.registerLayerDefinition(FaceModel.LAYER_LOCATION, FaceModel::createBodyLayer);
        event.registerLayerDefinition(CatEars.LAYER_LOCATION, CatEars::createBodyLayer);
        event.registerLayerDefinition(AxolotlHat.LAYER_LOCATION, AxolotlHat::createBodyLayer);
        event.registerLayerDefinition(ThermalHat.LAYER_LOCATION, ThermalHat::createBodyLayer);
        event.registerLayerDefinition(HeadphoneHat.LAYER_LOCATION, HeadphoneHat::createBodyLayer);
    }

    @SubscribeEvent
    public static void onNewRegistryEvent(NewRegistryEvent event) {
        event.create(ResourceSlimesRegistries.getSlimeResourceBuilder());
    }
}
