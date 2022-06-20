package net.kaupenjoe.resourceslimes.util.networking.packets;

import net.kaupenjoe.resourceslimes.block.entity.GemCuttingStationBlockEntity;
import net.kaupenjoe.resourceslimes.screen.GemCuttingStationMenu;
import net.kaupenjoe.resourceslimes.screen.GemCuttingStationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSyncFluidStackToClient {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public PacketSyncFluidStackToClient(FluidStack stack, BlockPos pos) {
        this.fluidStack = stack;
        this.pos = pos;
    }

    public PacketSyncFluidStackToClient(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT YES
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof GemCuttingStationBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof GemCuttingStationMenu menu &&
                menu.blockEntity.getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }
}
