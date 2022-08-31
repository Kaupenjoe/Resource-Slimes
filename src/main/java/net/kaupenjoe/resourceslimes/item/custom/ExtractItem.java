package net.kaupenjoe.resourceslimes.item.custom;

import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ExtractItem extends Item {
    public ExtractItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        return "item.resourceslimes.extract";
    }

    public TranslatableComponent getExtractNameKey(ItemStack stack) {
        return new TranslatableComponent("extract.resourceslimes." +
                SlimeResource.getResourceByExtractItem(stack.getItem()).name().toLowerCase());
    }


    @Override
    public Component getName(ItemStack stack) {
        TranslatableComponent extractNameKey = getExtractNameKey(stack);

        return new TranslatableComponent(this.getDescriptionId(stack), extractNameKey);
    }
}
