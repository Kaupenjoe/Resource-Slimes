package net.kaupenjoe.resourceslimes.event;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.util.ResourceSlimesRegistries;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = ResourceSlimes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RESOURCE_SLIME.get(), Monster.createMonsterAttributes().build());
        event.put(ModEntityTypes.ENERGY_SLIME.get(), Monster.createMonsterAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
        ModItems.onRegisterItems(event.getForgeRegistry());
    }

    @SubscribeEvent
    public static void onNewRegistryEvent(NewRegistryEvent event) {
        event.create(ResourceSlimesRegistries.getSlimeResourceBuilder());
    }
}
