package net.kaupenjoe.resourceslimes.block.entity;

import net.minecraftforge.energy.IEnergyStorage;

public interface IEnergyHandlingBlockEntity {
    void setEnergyLevel(int energyLevel);
    IEnergyStorage getEnergyStorage();
}
