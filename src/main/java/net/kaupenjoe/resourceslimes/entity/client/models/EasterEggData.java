package net.kaupenjoe.resourceslimes.entity.client.models;

import com.mojang.math.Vector3f;
import net.minecraft.world.entity.LivingEntity;

public class EasterEggData<T extends LivingEntity> {
    private String name;
    private EasterEggEntityModel<T> model;
    private Vector3f translation;
    private Vector3f scale;
    private Vector3f rotation;

    public EasterEggData(String name, EasterEggEntityModel<T> model,
                         Vector3f translation, Vector3f scale, Vector3f rotation) {
        this.name = name;
        this.model = model;
        this.translation = translation;
        this.scale = scale;
        this.rotation = rotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EasterEggEntityModel<T> getModel() {
        return model;
    }

    public void setModel(EasterEggEntityModel<T> model) {
        this.model = model;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}
