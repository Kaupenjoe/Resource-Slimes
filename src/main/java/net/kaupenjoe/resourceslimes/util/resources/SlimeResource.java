package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.util.ModRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class SlimeResource {
    private Item extractItem;
    private Item slimeyExtractItem;
    private final ResourceTier tier;
    private final ResourceLocation innerCubeTextureRes;
    private boolean enabled;

    SlimeResource(ResourceTier tier, ResourceLocation textureRes, boolean enabled) {
        this.tier = tier;
        this.innerCubeTextureRes = textureRes;
        this.enabled = enabled;
    }
    
    public String name() {
    	return ModRegistries.SLIME_RESOURCES.get().getKey(this).getPath();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setExtractItem(Item stacks) {
        this.extractItem = stacks;
    }

    public Item getExtractItem() {
        return extractItem;
    }

    public void setSlimeyExtractItem(Item stacks) {
        this.slimeyExtractItem = stacks;
    }

    public Item getSlimeyExtractItem() {
        return slimeyExtractItem;
    }

    public ResourceTier getTier() {
        return tier;
    }

    public ResourceLocation getInnerCubeTextureRes() {
        return innerCubeTextureRes;
    }

    public static ResourceLocation getInnerCubeLocationByItem(Item item) {
        for (SlimeResource resource : ModRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource.getInnerCubeTextureRes();
            }
        }

        return new ResourceLocation(ResourceSlimes.MOD_ID,"textures/block/citrine_ore.png");
    }

    public static ResourceTier getTierByItem(Item item) {
        for (SlimeResource resource : ModRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource.getTier();
            }
        }

        return ResourceTier.CITRINE;
    }

    public static SlimeResource getResourceBySlimeyExtractItem(Item item) {
        for (SlimeResource resource : ModRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource;
            }
        }
        // TODO: possibly add a "dummy" resource
        return BuiltinSlimeResources.EMPTY.get();
    }

    public static SlimeResource getResourceByExtractItem(Item item) {
        for (SlimeResource resource : ModRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getExtractItem() == item) {
                return resource;
            }
        }
        return BuiltinSlimeResources.EMPTY.get();
    }

    @Override
    public String toString() {
        return "SlimeResource: " + ForgeRegistries.ITEMS.getKey(slimeyExtractItem) + " | "
                + tier.getTierId() + "(" +  tier.getTierName() + ")";
    }
}
