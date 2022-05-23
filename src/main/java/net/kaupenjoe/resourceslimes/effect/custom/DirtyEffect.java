package net.kaupenjoe.resourceslimes.effect.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class DirtyEffect extends MobEffect {
    public DirtyEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        AABB boundingBox = pLivingEntity.getBoundingBox().inflate(pAmplifier + 1);
        List<Entity> entities = pLivingEntity.level.getEntities(pLivingEntity, boundingBox);

        for (Entity entity : entities) {
            if(entity instanceof LivingEntity livingEntity) {
                livingEntity.hurt(DamageSource.GENERIC,0.25f * (pAmplifier + 1));
            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
