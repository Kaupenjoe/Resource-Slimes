package net.kaupenjoe.resourceslimes.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

// TODO: Possibly add Mixin for the Slippery Effect:
// https://github.com/samedifferent/Ecologics/blob/master/src/main/java/samebutdifferent/ecologics/mixin/MixinLivingEntity.java
public class CleanEffect extends MobEffect {
    public CleanEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
