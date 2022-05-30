package net.kaupenjoe.resourceslimes.item.custom;

import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SlimeyExtractItem extends Item {
    public SlimeyExtractItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getName(ItemStack stack) {
        String name = SlimeResource.getResourceBySlimeyExtractItem(stack.getItem()).name();
        return new TextComponent("Slimey " + name.charAt(0) + name.substring(1).toLowerCase() + " Extract");
    }
}
