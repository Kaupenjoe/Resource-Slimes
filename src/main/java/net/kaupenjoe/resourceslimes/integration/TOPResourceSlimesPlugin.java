package net.kaupenjoe.resourceslimes.integration;

import mcjty.theoneprobe.api.*;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public class TOPResourceSlimesPlugin implements IProbeInfoProvider, Function<ITheOneProbe, Void> {

    @Override
    public Void apply(ITheOneProbe probe) {
        probe.registerProvider(this);
        probe.registerEntityProvider(TOPResourceSlimeProvider.INSTANCE);

        return null;
    }

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation(ResourceSlimes.MOD_ID, "data");
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player player, Level level, BlockState blockState, IProbeHitData iProbeHitData) {

    }
}
