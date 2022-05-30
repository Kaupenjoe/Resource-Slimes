package net.kaupenjoe.resourceslimes.item.custom;

import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ExtractItem extends Item {
    public ExtractItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getName(ItemStack stack) {
        String name = SlimeResource.getResourceByExtractItem(stack.getItem()).name();
        return new TextComponent(name.charAt(0) + name.substring(1).toLowerCase() + " Extract");
    }
}
