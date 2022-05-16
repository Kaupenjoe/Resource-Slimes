package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public enum SlimeResource {
    EMPTY(() -> Items.AIR, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/structure_block.png")),

    STONE(() -> Items.STONE, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/stone.png")),
    DIORITE(() -> Items.DIORITE, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/diorite.png")),
    ANDESITE(() -> Items.ANDESITE, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/andesite.png")),
    GRANITE(() -> Items.GRANITE, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/granite.png")),
    TUFF(() -> Items.TUFF, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/tuff.png")),
    CALCITE(() -> Items.CALCITE, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/calcite.png")),
    DRIPSTONE(() -> Items.DRIPSTONE_BLOCK, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/dripstone_block.png")),
    BLACKSTONE(() -> Items.BLACKSTONE, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/blackstone.png")),
    SAND(() -> Items.SAND, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/sand.png")),
    RED_SAND(() -> Items.RED_SAND, ResourceTier.CITRINE,
            new ResourceLocation("textures/block/red_sand.png")),


    IRON(() -> Items.IRON_INGOT, ResourceTier.ZIRCON,
            new ResourceLocation("textures/block/iron_ore.png")),
    CLAY(() -> Items.CLAY_BALL, ResourceTier.ZIRCON,
            new ResourceLocation("textures/block/clay.png")),
    CITRINE(ModItems.UNCUT_CITRINE, ResourceTier.ZIRCON, new ResourceLocation(ResourceSlimes.MOD_ID,
            "textures/block/citrine_ore.png")),


    GOLD(() -> Items.GOLD_INGOT, ResourceTier.EMERALD,
            new ResourceLocation("textures/block/gold_ore.png"));

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
        return SlimeResource.EMPTY;
    }

    @Override
    public String toString() {
        return "SlimeResource: " + item.get().getRegistryName() + " | "
                + tier.getTierId() + "(" +  tier.getTierName() + ")";
    }
}
