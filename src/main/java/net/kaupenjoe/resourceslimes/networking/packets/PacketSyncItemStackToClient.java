package net.kaupenjoe.resourceslimes.networking.packets;

import net.kaupenjoe.resourceslimes.block.entity.IFluidHandlingBlockEntity;
import net.kaupenjoe.resourceslimes.block.entity.IInventoryHandlingBlockEntity;
import net.kaupenjoe.resourceslimes.screen.IFluidMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Deprecated(forRemoval = true) // Not in use anymore
public class PacketSyncItemStackToClient {
    private final ItemStackHandler itemStackHandler;
    private final BlockPos pos;

    public PacketSyncItemStackToClient(ItemStackHandler stack, BlockPos pos) {
        this.itemStackHandler = stack;
        this.pos = pos;
    }

    public PacketSyncItemStackToClient(FriendlyByteBuf buf) {
        List<ItemStack> collection = buf.readCollection(ArrayList::new, FriendlyByteBuf::readItem);
        itemStackHandler = new ItemStackHandler(collection.size());
        for (int i = 0; i < collection.size(); i++) {
            itemStackHandler.insertItem(i, collection.get(i), false);
        }

        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        Collection<ItemStack> list = new ArrayList<>();
        for(int i = 0; i < itemStackHandler.getSlots(); i++) {
            list.add(itemStackHandler.getStackInSlot(i));
        }

        buf.writeCollection(list, FriendlyByteBuf::writeItem);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT YES
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof IInventoryHandlingBlockEntity blockEntity) {
                blockEntity.setHandler(this.itemStackHandler);
            }
        });
        return true;
    }
}
