package net.kaupenjoe.resourceslimes.networking.packets;

import net.kaupenjoe.resourceslimes.block.entity.SlimeIncubationStationBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Deprecated(forRemoval = true) // Not in use anymore
public class PacketSyncProgressTimerToClient {
    private final int progress;
    private final BlockPos pos;

    public PacketSyncProgressTimerToClient(int progress, BlockPos pos) {
        this.progress = progress;
        this.pos = pos;
    }

    public PacketSyncProgressTimerToClient(FriendlyByteBuf buf) {
        this.progress = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(progress);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT YES
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SlimeIncubationStationBlockEntity blockEntity) {
                blockEntity.setProgress(progress);
            }
        });
        return true;
    }
}
