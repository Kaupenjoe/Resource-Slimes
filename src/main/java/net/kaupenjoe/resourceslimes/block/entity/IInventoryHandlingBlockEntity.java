package net.kaupenjoe.resourceslimes.block.entity;

import net.minecraftforge.items.ItemStackHandler;

public interface IInventoryHandlingBlockEntity {
    void setHandler(ItemStackHandler handler);
    ItemStackHandler getItemStackHandler();
}
