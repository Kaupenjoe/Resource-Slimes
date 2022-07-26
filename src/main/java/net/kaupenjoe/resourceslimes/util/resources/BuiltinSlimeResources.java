package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.util.ModRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BuiltinSlimeResources {
	public static final DeferredRegister<SlimeResource> SLIME_RESOURCES = DeferredRegister
			.create(ModRegistries.Keys.SLIME_RESOURCES, ResourceSlimes.MOD_ID);
	
    public static final RegistryObject<SlimeResource> EMPTY = SLIME_RESOURCES.register("empty", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/structure_block.png"), true));
    public static final RegistryObject<SlimeResource> STONE = SLIME_RESOURCES.register("stone", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/stone.png"), true));
    public static final RegistryObject<SlimeResource> DIRT = SLIME_RESOURCES.register("dirt", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/dirt.png"), true));
    public static final RegistryObject<SlimeResource> WATER = SLIME_RESOURCES.register("water", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/water_flow.png"), true));
    public static final RegistryObject<SlimeResource> SAND = SLIME_RESOURCES.register("sand", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/sand.png"), true));
    public static final RegistryObject<SlimeResource> GRAVEL = SLIME_RESOURCES.register("gravel", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/gravel.png"), true));
    public static final RegistryObject<SlimeResource> WOOD = SLIME_RESOURCES.register("wood", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/oak_log.png"), true));
    public static final RegistryObject<SlimeResource> GRASS = SLIME_RESOURCES.register("grass", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/grass.png"), true));
    public static final RegistryObject<SlimeResource> FLOWER = SLIME_RESOURCES.register("flower", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/dandelion.png"), true));
    public static final RegistryObject<SlimeResource> SHEEP = SLIME_RESOURCES.register("sheep", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/white_wool.png"), true));
    public static final RegistryObject<SlimeResource> COW = SLIME_RESOURCES.register("cow", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"), true));
    public static final RegistryObject<SlimeResource> PIG = SLIME_RESOURCES.register("pig", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coarse_dirt.png"), true));
    public static final RegistryObject<SlimeResource> CHICKEN = SLIME_RESOURCES.register("chicken", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"), true));
    public static final RegistryObject<SlimeResource> COAL = SLIME_RESOURCES.register("coal", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coal_ore.png"), true));
    public static final RegistryObject<SlimeResource> BERRY = SLIME_RESOURCES.register("berry", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/sweet_berry_bush_stage3.png"), true));
    public static final RegistryObject<SlimeResource> FISH = SLIME_RESOURCES.register("fish", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/barrel_side.png"), true));
    public static final RegistryObject<SlimeResource> IRON = SLIME_RESOURCES.register("iron", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/iron_ore.png"), true));
    public static final RegistryObject<SlimeResource> LAPIS = SLIME_RESOURCES.register("lapis", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/lapis_ore.png"), true));
    public static final RegistryObject<SlimeResource> QUARTZ = SLIME_RESOURCES.register("quartz", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/quartz_block_side.png"), true));
    public static final RegistryObject<SlimeResource> AQUATIC = SLIME_RESOURCES.register("aquatic", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/conduit.png"), true));
    public static final RegistryObject<SlimeResource> NETHER = SLIME_RESOURCES.register("nether", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/netherrack.png"), true));
    public static final RegistryObject<SlimeResource> ZOMBIE = SLIME_RESOURCES.register("zombie", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ICE = SLIME_RESOURCES.register("ice", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/ice.png"), true));
    public static final RegistryObject<SlimeResource> COPPER = SLIME_RESOURCES.register("copper", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/copper_block.png"), true));
    public static final RegistryObject<SlimeResource> FIRE = SLIME_RESOURCES.register("fire", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/fire_0.png"), true));
    public static final RegistryObject<SlimeResource> DEEPSLATE = SLIME_RESOURCES.register("deepslate", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"), true));
    public static final RegistryObject<SlimeResource> CROP = SLIME_RESOURCES.register("crop", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"), true));
    public static final RegistryObject<SlimeResource> LESSERGEM = SLIME_RESOURCES.register("lesser_gem", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> GOLD = SLIME_RESOURCES.register("gold", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/gold_ore.png"), true));
    public static final RegistryObject<SlimeResource> REDSTONE = SLIME_RESOURCES.register("redstone", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/redstone_ore.png"), true));
    public static final RegistryObject<SlimeResource> GLOWSTONE = SLIME_RESOURCES.register("glowstone", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/glowstone.png"), true));
    public static final RegistryObject<SlimeResource> SKELETON = SLIME_RESOURCES.register("skeleton", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/bone_block_top.png"), true));
    public static final RegistryObject<SlimeResource> PRISMARINE = SLIME_RESOURCES.register("prismarine", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/prismarine.png"), true));
    public static final RegistryObject<SlimeResource> CREEPER = SLIME_RESOURCES.register("creeper", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> SPIDER = SLIME_RESOURCES.register("spider", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/cobweb.png"), true));
    public static final RegistryObject<SlimeResource> SLIME = SLIME_RESOURCES.register("slime", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/slime.png"), true));
    public static final RegistryObject<SlimeResource> PHANTOM = SLIME_RESOURCES.register("phantom", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ALCHEMY = SLIME_RESOURCES.register("alchemy", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/brewing_stand_base.png"), true));
    public static final RegistryObject<SlimeResource> ENDERPEARL = SLIME_RESOURCES.register("ender_pearl", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/end_portal_frame_eye.png"), true));
    public static final RegistryObject<SlimeResource> DIAMOND = SLIME_RESOURCES.register("diamond", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/diamond_ore.png"), true));
    public static final RegistryObject<SlimeResource> HONEY = SLIME_RESOURCES.register("honey", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/honeycomb_block.png"), true));
    public static final RegistryObject<SlimeResource> EXPERIENCE = SLIME_RESOURCES.register("experience", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/bookshelf.png"), true));
    public static final RegistryObject<SlimeResource> MUSIC = SLIME_RESOURCES.register("music", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/note_block.png"), true));
    public static final RegistryObject<SlimeResource> OBSIDIAN = SLIME_RESOURCES.register("obsidian", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/obsidian.png"), true));
    public static final RegistryObject<SlimeResource> BLAZE = SLIME_RESOURCES.register("blaze", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> WITHERSKELETONS = SLIME_RESOURCES.register("wither_skeletons", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ILLAGERS = SLIME_RESOURCES.register("illagers", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> GHAST = SLIME_RESOURCES.register("ghast", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> NETHERITE = SLIME_RESOURCES.register("netherite", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/netherite_block.png"), true));
    public static final RegistryObject<SlimeResource> MAGIC = SLIME_RESOURCES.register("magic", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> EMERALD = SLIME_RESOURCES.register("emerald", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/emerald_ore.png"), true));
    public static final RegistryObject<SlimeResource> SHULKERS = SLIME_RESOURCES.register("shulkers", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/shulker_box.png"), true));
    public static final RegistryObject<SlimeResource> ENDERDRAGON = SLIME_RESOURCES.register("ender_dragon", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/dragon_egg.png"), true));
    public static final RegistryObject<SlimeResource> NETHERSTAR = SLIME_RESOURCES.register("nether_star", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/beacon.png"), true));
    public static final RegistryObject<SlimeResource> GREATERGEM = SLIME_RESOURCES.register("greater_gem", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ENDCRYSTAL = SLIME_RESOURCES.register("end_crystal", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/crying_obsidian.png"), true));

    public static void register(IEventBus eventBus) {
    	SLIME_RESOURCES.register(eventBus);
    }
}
