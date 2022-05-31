package net.kaupenjoe.resourceslimes.item.custom;

import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SlimeyExtractItem extends Item {
    public SlimeyExtractItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        return "item.resourceslimes.slimey_extract";
    }

    @Override
    public Component getName(ItemStack stack) {
        TranslatableComponent extractNameKey = new TranslatableComponent("extract.resourceslimes." +
                SlimeResource.getResourceBySlimeyExtractItem(stack.getItem()).name().toLowerCase());

        return new TranslatableComponent(this.getDescriptionId(stack), extractNameKey);
    }
}
