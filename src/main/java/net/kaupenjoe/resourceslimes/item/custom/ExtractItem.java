package net.kaupenjoe.resourceslimes.item.custom;

import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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

    public Component getExtractNameKey(ItemStack stack) {
        return Component.translatable("extract.resourceslimes." + SlimeResource.getResourceByExtractItem(stack.getItem()).name().toLowerCase());
    }


    @Override
    public Component getName(ItemStack stack) {
        Component extractNameKey = getExtractNameKey(stack);

        return Component.translatable(this.getDescriptionId(stack), extractNameKey);
    }
}
