package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.resources.ResourceLocation;

public enum ResourceTier {
    CITRINE(0, "citrine"),
    ZIRCON(1, "zircon"),
    DIAMOND(2, "diamond"),
    EMERALD(3, "emerald"),
    TANZANITE(4, "tanzanite"),
    BLACK_OPAL(5, "black_opal"),
    RED_SLIME_PEARL(6, "red_slime_pearl");

    private final int tierId;
    private final String tierName;

    ResourceTier(int id, String name) {
        this.tierId = id;
        this.tierName = name;
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

    public static ResourceTier getTierById(int i) {
        for (ResourceTier tier : values()) {
            if(tier.getTierId() == i) {
                return tier;
            }
        }

        return null;
    }
}