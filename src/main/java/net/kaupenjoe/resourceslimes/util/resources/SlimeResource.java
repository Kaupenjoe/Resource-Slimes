package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.util.ResourceSlimesRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SlimeResource extends ForgeRegistryEntry<SlimeResource> {
    private Item extractItem;
    private Item slimeyExtractItem;
    private Supplier<Item> craftingItem;
    private final ResourceTier tier;
    private final ResourceLocation innerCubeTextureRes;
    private boolean enabled;

    SlimeResource(ResourceTier tier, ResourceLocation textureRes, Supplier<Item> craftingItem, boolean enabled) {
        this.tier = tier;
        this.innerCubeTextureRes = textureRes;
        this.enabled = enabled;
        this.craftingItem = craftingItem;
    }

    public String name() {
        return ResourceSlimesRegistries.SLIME_RESOURCES.get().getKey(this).getPath();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setCraftingItem(Supplier<Item> craftingItem) {
        this.craftingItem = craftingItem;
    }

    public Supplier<Item> getCraftingItem() {
        return craftingItem;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setExtractItem(Item stacks) {
        this.extractItem = stacks;
    }

    @Nullable
    public Item getExtractItem() {
        return extractItem;
    }

    public void setSlimeyExtractItem(Item stacks) {
        this.slimeyExtractItem = stacks;
    }

    @Nullable
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
        for (SlimeResource resource : ResourceSlimesRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource.getInnerCubeTextureRes();
            }
        }

        return new ResourceLocation(ResourceSlimes.MOD_ID,"textures/block/citrine_ore.png");
    }

    public static ResourceTier getTierByItem(Item item) {
        for (SlimeResource resource : ResourceSlimesRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource.getTier();
            }
        }

        return ResourceTier.CITRINE;
    }

    public static SlimeResource getResourceBySlimeyExtractItem(Item item) {
        for (SlimeResource resource : ResourceSlimesRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource;
            }
        }
        return BuiltinSlimeResources.EMPTY.get();
    }

    public static SlimeResource getResourceByExtractItem(Item item) {
        for (SlimeResource resource : ResourceSlimesRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getExtractItem() == null) {
                continue;
            }

            if(resource.getExtractItem() == item) {
                return resource;
            }
        }
        return BuiltinSlimeResources.EMPTY.get();
    }

    public static SlimeResource getResourceByCraftingItem(Item result) {
        for (SlimeResource resource : ResourceSlimesRegistries.SLIME_RESOURCES.get().getValues()) {
            if(resource.getCraftingItem().get() == result) {
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