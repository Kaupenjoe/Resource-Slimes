package net.kaupenjoe.resourceslimes.entity.client.models;

import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public abstract class EasterEggEntityModel<T extends Entity> extends EntityModel<T> {
    public abstract ResourceLocation getTextureLocation();
}
