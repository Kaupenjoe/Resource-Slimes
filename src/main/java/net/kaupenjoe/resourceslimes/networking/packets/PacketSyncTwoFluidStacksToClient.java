package net.kaupenjoe.resourceslimes.networking.packets;

import net.kaupenjoe.resourceslimes.block.entity.SlimeExtractCleaningStationBlockEntity;
import net.kaupenjoe.resourceslimes.screen.IFluidMenu;
import net.kaupenjoe.resourceslimes.screen.SlimeExtractCleaningStationMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Deprecated(forRemoval = true) // Not in use anymore
public class PacketSyncTwoFluidStacksToClient {
    private final FluidStack fluidStackMain;
    private final FluidStack fluidStackWaste;
    private final BlockPos pos;

    public PacketSyncTwoFluidStacksToClient(FluidStack stackMain, FluidStack stackWaste, BlockPos pos) {
        this.fluidStackMain = stackMain;
        this.fluidStackWaste = stackWaste;
        this.pos = pos;
    }

    public PacketSyncTwoFluidStacksToClient(FriendlyByteBuf buf) {
        this.fluidStackMain = buf.readFluidStack();
        this.fluidStackWaste = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStackMain);
        buf.writeFluidStack(fluidStackWaste);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT YES
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SlimeExtractCleaningStationBlockEntity blockEntity) {
                blockEntity.setFluid(this.fluidStackMain);
                blockEntity.setWasteFluid(this.fluidStackWaste);

                if(Minecraft.getInstance().player.containerMenu instanceof SlimeExtractCleaningStationMenu menu &&
                menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setMainFluid(this.fluidStackMain);
                    menu.setWasteFluid(this.fluidStackWaste);
                }
            }
        });
        return true;
    }
}
