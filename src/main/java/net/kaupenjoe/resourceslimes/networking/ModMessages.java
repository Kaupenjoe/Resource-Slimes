package net.kaupenjoe.resourceslimes.networking;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.networking.packets.*;
import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.filters.VanillaPacketSplitter;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.ArrayList;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ResourceSlimes.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketSyncFluidStackToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncFluidStackToClient::new)
                .encoder(PacketSyncFluidStackToClient::toBytes)
                .consumerMainThread(PacketSyncFluidStackToClient::handle)
                .add();

        net.messageBuilder(PacketSyncEnergyToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncEnergyToClient::new)
                .encoder(PacketSyncEnergyToClient::toBytes)
                .consumerMainThread(PacketSyncEnergyToClient::handle)
                .add();

        net.messageBuilder(PacketSyncItemStackToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncItemStackToClient::new)
                .encoder(PacketSyncItemStackToClient::toBytes)
                .consumerMainThread(PacketSyncItemStackToClient::handle)
                .add();

        net.messageBuilder(PacketSyncTwoFluidStacksToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncTwoFluidStacksToClient::new)
                .encoder(PacketSyncTwoFluidStacksToClient::toBytes)
                .consumerMainThread(PacketSyncTwoFluidStacksToClient::handle)
                .add();

        net.messageBuilder(PacketOpenGeneticsGuide.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketOpenGeneticsGuide::new)
                .encoder(PacketOpenGeneticsGuide::toBytes)
                .consumerMainThread(PacketOpenGeneticsGuide::handle)
                .add();
    }

    public static <MSG> void sendTo(MSG msg, Connection connection, NetworkDirection direction) {
        INSTANCE.sendTo(msg, connection, direction);
    }

    public static <MSG> void sendToSplit(ServerPlayer player, MSG message) {
        Packet<?> vanillaPacket = INSTANCE.toVanillaPacket(message, NetworkDirection.PLAY_TO_CLIENT);
        var packets = new ArrayList<Packet<?>>();
        VanillaPacketSplitter.appendPackets(ConnectionProtocol.PLAY, PacketFlow.CLIENTBOUND, vanillaPacket, packets);
        packets.forEach(player.connection::send);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
