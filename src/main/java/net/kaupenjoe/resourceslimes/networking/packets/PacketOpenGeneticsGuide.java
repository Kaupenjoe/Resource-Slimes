package net.kaupenjoe.resourceslimes.networking.packets;

import net.kaupenjoe.resourceslimes.screen.GeneticGuideScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public final class PacketOpenGeneticsGuide {
    public PacketOpenGeneticsGuide() {
    }

    public PacketOpenGeneticsGuide(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(GeneticGuideScreen::open);
        context.setPacketHandled(true);
    }
}
