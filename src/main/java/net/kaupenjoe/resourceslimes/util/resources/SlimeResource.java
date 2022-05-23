package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public enum SlimeResource {
    EMPTY(ResourceTier.CITRINE,
            new ResourceLocation("textures/block/structure_block.png"), true),
    STONE(ResourceTier.CITRINE, new ResourceLocation("textures/block/stone.png"), true),
    DIRT(ResourceTier.CITRINE, new ResourceLocation("textures/block/dirt.png"), true),
    WATER(ResourceTier.CITRINE, new ResourceLocation("textures/block/water_flow.png"), true),
    SAND(ResourceTier.CITRINE, new ResourceLocation("textures/block/sand.png"), true),
    GRAVEL(ResourceTier.CITRINE, new ResourceLocation("textures/block/gravel.png"), true),


    WOOD(ResourceTier.ZIRCON, new ResourceLocation("textures/block/oak_log.png"), true),
    GRASS(ResourceTier.ZIRCON, new ResourceLocation("textures/block/grass.png"), true),
    FLOWER(ResourceTier.ZIRCON, new ResourceLocation("textures/block/dandelion.png"), true),
    SHEEP(ResourceTier.ZIRCON, new ResourceLocation("textures/block/white_wool.png"), true),
    COW(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"), true),
    PIG(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coarse_dirt.png"), true),
    CHICKEN(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"), true),
    COAL(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coal_ore.png"), true),
    BERRY(ResourceTier.ZIRCON, new ResourceLocation("textures/block/sweet_berry_bush_stage_3.png"), true),
    FISH(ResourceTier.ZIRCON, new ResourceLocation("textures/block/barrel_side.png"), true),


    IRON(ResourceTier.DIAMOND, new ResourceLocation("textures/block/iron_ore.png"), true),
    LAPIS(ResourceTier.DIAMOND, new ResourceLocation("textures/block/lapis_ore.png"), true),
    QUARTZ(ResourceTier.DIAMOND, new ResourceLocation("textures/block/quartz_block_side.png"), true),
    AQUATIC(ResourceTier.DIAMOND, new ResourceLocation("textures/block/conduit.png"), true),
    NETHER(ResourceTier.DIAMOND, new ResourceLocation("textures/block/netherrack.png"), true),
    ZOMBIE(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    ICE(ResourceTier.DIAMOND, new ResourceLocation("textures/block/ice.png"), true),
    COPPER(ResourceTier.DIAMOND, new ResourceLocation("textures/block/copper_block.png"), true),
    FIRE(ResourceTier.DIAMOND, new ResourceLocation("textures/block/fire_0.png"), true),
    DEEPSLATE(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"), true),
    CROP(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"), true),
    LESSERGEM(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), true),


    GOLD(ResourceTier.EMERALD, new ResourceLocation("textures/block/gold_ore.png"), true),
    REDSTONE(ResourceTier.EMERALD, new ResourceLocation("textures/block/redstone_ore.png"), true),
    GLOWSTONE(ResourceTier.EMERALD, new ResourceLocation("textures/block/glowstone.png"), true),
    SKELETON(ResourceTier.EMERALD, new ResourceLocation("textures/block/bone_block_top.png"), true),
    PRISMARINE(ResourceTier.EMERALD, new ResourceLocation("textures/block/prismarine.png"), true),
    CREEPER(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    SPIDER(ResourceTier.EMERALD, new ResourceLocation("textures/block/cobweb.png"), true),
    SLIME(ResourceTier.EMERALD, new ResourceLocation("textures/block/slime.png"), true),
    PHANTOM(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    ALCHEMY(ResourceTier.EMERALD, new ResourceLocation("textures/block/brewing_stand_base.png"), true),


    ENDERPEARL(ResourceTier.TANZANITE, new ResourceLocation("textures/block/end_portal_frame_eye.png"), true),
    DIAMOND(ResourceTier.TANZANITE, new ResourceLocation("textures/block/diamond_ore.png"), true),
    HONEY(ResourceTier.TANZANITE, new ResourceLocation("textures/block/honeycomb_block.png"), true),
    EXPERIENCE(ResourceTier.TANZANITE, new ResourceLocation("textures/block/bookshelf.png"), true),
    MUSIC(ResourceTier.TANZANITE, new ResourceLocation("textures/block/note_block.png"), true),
    OBSIDIAN(ResourceTier.TANZANITE, new ResourceLocation("textures/block/obsidian.png"), true),
    BLAZE(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    WITHERSKELETONS(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    ILLAGERS(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    GHAST(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true),


    NETHERITE(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/netherite_block.png"), true),
    MAGIC(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    EMERALD(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/emerald_ore.png"), true),
    SHULKERS(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/shulker_box.png"), true),


    ENDERDRAGON(ResourceTier.RED_SLIME_PEARL, new ResourceLocation("textures/block/dragon_egg.png"), true),
    NETHERSTAR(ResourceTier.RED_SLIME_PEARL, new ResourceLocation("textures/block/beacon.png"), true),
    GREATERGEM(ResourceTier.RED_SLIME_PEARL, new ResourceLocation("textures/block/carved_pumpkin.png"), true),
    ENDCRYSTAL(ResourceTier.RED_SLIME_PEARL, new ResourceLocation("textures/block/crying_obsidian.png"), true);



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
        for (SlimeResource resource : values()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource.getInnerCubeTextureRes();
            }
        }

        return new ResourceLocation(ResourceSlimes.MOD_ID,"textures/block/citrine_ore.png");
    }

    public static ResourceTier getTierByItem(Item item) {
        for (SlimeResource resource : values()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource.getTier();
            }
        }

        return ResourceTier.CITRINE;
    }

    public static SlimeResource getResourceByItem(Item item) {
        for (SlimeResource resource : values()) {
            if(resource.getSlimeyExtractItem() == item) {
                return resource;
            }
        }
        // TODO: possibly add a "dummy" resource
        return SlimeResource.EMPTY;
    }

    @Override
    public String toString() {
        return "SlimeResource: " + slimeyExtractItem.getRegistryName() + " | "
                + tier.getTierId() + "(" +  tier.getTierName() + ")";
    }
}
