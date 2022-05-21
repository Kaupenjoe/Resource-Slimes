package net.kaupenjoe.resourceslimes.effect;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.effect.custom.CleanEffect;
import net.kaupenjoe.resourceslimes.effect.custom.DirtyEffect;
import net.kaupenjoe.resourceslimes.effect.custom.SlimeyEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ResourceSlimes.MOD_ID);


    public static final RegistryObject<MobEffect> DIRTY = EFFECTS.register("dirty", () ->
            new DirtyEffect(MobEffectCategory.NEUTRAL, 0x3b2012));

    // TODO: Maybe have it have you jump "a little bit"
    public static final RegistryObject<MobEffect> SLIMEY = EFFECTS.register("slimey", () ->
            new SlimeyEffect(MobEffectCategory.HARMFUL, 0x36ebab).addAttributeModifier(Attributes.MOVEMENT_SPEED,
            "7107DE5E-7CE8-4030-940E-514C1F160890", -0.25F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    // TODO: With Mixin in future
    public static final RegistryObject<MobEffect> CLEAN = EFFECTS.register("clean", () ->
            new CleanEffect(MobEffectCategory.NEUTRAL, 0xe85fd6));


    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
