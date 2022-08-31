package net.kaupenjoe.resourceslimes.integration;

import mcjty.theoneprobe.TheOneProbe;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

public class ResourceSlimeIntegrations {
    public static boolean TOPLoaded;

    public static void commonSetup() {
        ModList modList = ModList.get();
        TOPLoaded = modList.isLoaded(TheOneProbe.MODID);
    }

    public static void sendIMCs(final InterModEnqueueEvent event) {
        if (TOPLoaded) {
            InterModComms.sendTo(TheOneProbe.MODID, "getTheOneProbe",
                    TOPResourceSlimesPlugin::new);
        }
    }
}
