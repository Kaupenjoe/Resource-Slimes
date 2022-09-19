package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public enum ResourceTier {
    CITRINE(0, "citrine", ModItems.CUT_CITRINE.get(), ModItems.CITRINE_SLIME_FLUID_BUCKET.get()),
    ZIRCON(1, "zircon", ModItems.CUT_ZIRCON.get(), ModItems.ZIRCON_SLIME_FLUID_BUCKET.get()),
    DIAMOND(2, "diamond", ModItems.CUT_DIAMOND.get(), ModItems.DIAMOND_SLIME_FLUID_BUCKET.get()),
    EMERALD(3, "emerald", ModItems.CUT_EMERALD.get(), ModItems.EMERALD_SLIME_FLUID_BUCKET.get()),
    TANZANITE(4, "tanzanite", ModItems.CUT_TANZANITE.get(), ModItems.TANZANITE_SLIME_FLUID_BUCKET.get()),
    BLACK_OPAL(5, "black_opal", ModItems.CUT_BLACK_OPAL.get(), ModItems.BLACK_OPAL_SLIME_FLUID_BUCKET.get()),
    PINK_SLIME_PEARL(6, "pink_slime_pearl", ModItems.PINK_SLIME_PEARL.get(), ModItems.PINK_SLIME_PEARL_SLIME_FLUID_BUCKET.get());

    private final int tierId;
    private final String tierName;
    private final ItemLike item;
    private final Item fluidItem;

    ResourceTier(int id, String name, ItemLike item, Item fluidItem) {
        this.tierId = id;
        this.tierName = name;
        this.item = item;
        this.fluidItem = fluidItem;
    }

    public int getTierId() {
        return tierId;
    }

    public String getTierName() {
        return tierName;
    }

    public ResourceLocation getTextureLocation() {
        return new ResourceLocation(ResourceSlimes.MOD_ID,"textures/entity/resource_slime/tiers/tier_"
                + this.tierId + "_" + this.getTierName() + ".png");
    }

    public static Item getFluidBucketItem(ResourceTier tier) {
        return tier.fluidItem;
    }

    public static ResourceTier getTierById(int i) {
        for (ResourceTier tier : values()) {
            if(tier.getTierId() == i) {
                return tier;
            }
        }

        return null;
    }

    public ItemLike getTierItem() {
        return item;
    }
}
