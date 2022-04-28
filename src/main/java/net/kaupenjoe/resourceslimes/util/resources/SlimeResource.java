package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum SlimeResource {
    IRON(() -> Items.IRON_INGOT, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/iron_ore.png")),
    GOLD(() -> Items.GOLD_INGOT, ResourceTier.EMERALD,
            new ResourceLocation("textures/block/iron_ore.png")),
    CITRINE(ModItems.UNCUT_CITRINE, ResourceTier.ZIRCON, new ResourceLocation(ResourceSlimes.MOD_ID,
            "textures/block/citrine_ore.png"));

    private final Supplier<Item> item;
    private final ResourceTier tier;
    private final ResourceLocation innerCubeTextureRes;

    SlimeResource(Supplier<Item> stacks, ResourceTier tier, ResourceLocation textureRes) {
        this.item = stacks;
        this.tier = tier;
        this.innerCubeTextureRes = textureRes;
    }

    SlimeResource(Supplier<Item> stacks, int i, ResourceLocation textureRes) {
        this.item = stacks;
        this.tier = ResourceTier.getTierById(i);
        this.innerCubeTextureRes = textureRes;
    }

    public Supplier<Item> getItem() {
        return item;
    }

    public ResourceTier getTier() {
        return tier;
    }

    public ResourceLocation getInnerCubeTextureRes() {
        return innerCubeTextureRes;
    }

    public static ResourceLocation getInnerCubeLocationByItem(Item item) {
        for (SlimeResource resource : values()) {
            if(resource.getItem().get() == item) {
                return resource.getInnerCubeTextureRes();
            }
        }

        return new ResourceLocation(ResourceSlimes.MOD_ID,"textures/block/citrine_ore.png");
    }

    public static ResourceTier getTierByItem(Item item) {
        for (SlimeResource resource : values()) {
            if(resource.getItem().get() == item) {
                return resource.getTier();
            }
        }

        return ResourceTier.CITRINE;
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
