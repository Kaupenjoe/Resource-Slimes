package net.kaupenjoe.resourceslimes.event;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.effect.ModEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ResourceSlimes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void movementInputUpdateEvent(MovementInputUpdateEvent event) {
        if(event.getPlayer().hasEffect(ModEffects.SLIMEY.get())) {
            event.getInput().jumping = false;
        }
    }
}
