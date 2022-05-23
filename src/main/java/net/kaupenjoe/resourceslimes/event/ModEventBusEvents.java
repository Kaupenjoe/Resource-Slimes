package net.kaupenjoe.resourceslimes.event;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.entity.client.ModModelLayers;
import net.kaupenjoe.resourceslimes.entity.client.ResourceSlimeModel;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ResourceSlimes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RESOURCE_SLIME.get(), Monster.createMonsterAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.RES_SLIME_EYES, ResourceSlimeModel::createEyesAndMouthLayer);

        event.registerLayerDefinition(ModModelLayers.RES_SLIME_OUTER_CUBE, ResourceSlimeModel::createOuterBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RES_SLIME_INNER_CUBE, ResourceSlimeModel::createInnerBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        ModItems.onRegisterItems(event.getRegistry());
    }
}
