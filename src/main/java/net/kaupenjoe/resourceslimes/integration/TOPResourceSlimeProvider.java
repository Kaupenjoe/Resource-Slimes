package net.kaupenjoe.resourceslimes.integration;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TOPResourceSlimeProvider implements IProbeInfoEntityProvider {
    static final TOPResourceSlimeProvider INSTANCE = new TOPResourceSlimeProvider();

    @Override
    public String getID() {
        return ResourceSlimes.MOD_ID + "data";
    }

    @Override
    public void addProbeEntityInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, Entity entity, IProbeHitEntityData iProbeHitEntityData) {
        if(probeMode.equals(ProbeMode.NORMAL) && entity instanceof ResourceSlimeEntity slime) {
            iProbeInfo.item(slime.getResourceItem());
        }
    }
}
