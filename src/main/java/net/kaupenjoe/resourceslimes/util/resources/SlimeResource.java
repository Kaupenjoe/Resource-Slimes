package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum SlimeResource {
    IRON(() -> Items.IRON_INGOT, ResourceTier.CITRINE),
    GOLD(() -> Items.GOLD_INGOT, ResourceTier.EMERALD),
    CITRINE(ModItems.UNCUT_CITRINE, ResourceTier.ZIRCON);

    private final Supplier<Item> item;
    private final ResourceTier tier;

    SlimeResource(Supplier<Item> stacks, ResourceTier tier) {
        this.item = stacks;
        this.tier = tier;
    }

    SlimeResource(Supplier<Item> stacks, int i) {
        this.item = stacks;
        this.tier = ResourceTier.getTierById(i);
    }

    public Supplier<Item> getItem() {
        return item;
    }

    public ResourceTier getTier() {
        return tier;
    }

    @Nullable
    public static ResourceTier getTierByItem(Item item) {
        for (SlimeResource resource : values()) {
            if(resource.getItem().get() == item) {
                return resource.getTier();
            }
        }

        return null;
    }

    public static SlimeResource getResourceByItem(Item item) {
        for (SlimeResource resource : values()) {
            if(resource.getItem().get() == item) {
                return resource;
            }
        }
        // TODO: possibly add a "dummy" resource
        return SlimeResource.IRON;
    }

    @Override
    public String toString() {
        return "SlimeResource: " + item.get().getRegistryName() + " | "
                + tier.getTierId() + "(" +  tier.getTierName() + ")";
    }
}
