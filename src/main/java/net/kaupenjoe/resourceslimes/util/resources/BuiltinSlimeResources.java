package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.item.ModCreativeModeTab;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.item.custom.ExtractItem;
import net.kaupenjoe.resourceslimes.item.custom.SlimeyExtractItem;
import net.kaupenjoe.resourceslimes.util.ResourceSlimesRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BuiltinSlimeResources {
    public static final DeferredRegister<SlimeResource> SLIME_RESOURCES = DeferredRegister
            .create(ResourceSlimesRegistries.Keys.SLIME_RESOURCES, ResourceSlimes.MOD_ID);

    public static final RegistryObject<SlimeResource> EMPTY = register("empty", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/structure_block.png"), () -> Items.AIR,true));
    public static final RegistryObject<SlimeResource> STONE = register("stone", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/stone.png"), () -> Items.STONE,true));
    public static final RegistryObject<SlimeResource> DIRT = register("dirt", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/dirt.png"), () -> Items.DIRT,true));
    public static final RegistryObject<SlimeResource> WATER = register("water", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/water_flow.png"), () -> Items.WATER_BUCKET,true));
    public static final RegistryObject<SlimeResource> SAND = register("sand", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/sand.png"), () -> Items.SAND, true));
    public static final RegistryObject<SlimeResource> GRAVEL = register("gravel", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/gravel.png"), () -> Items.GRAVEL, true));
    public static final RegistryObject<SlimeResource> WOOD = register("wood", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/oak_log.png"), () -> Items.OAK_LOG, true)); // TODO multiple?
    public static final RegistryObject<SlimeResource> GRASS = register("grass", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/grass.png"), () -> Items.GRASS,true));
    public static final RegistryObject<SlimeResource> FLOWER = register("flower", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/dandelion.png"), () -> Items.DANDELION,true));
    public static final RegistryObject<SlimeResource> SHEEP = register("sheep", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/white_wool.png"), () -> Items.WHITE_WOOL, true));
    public static final RegistryObject<SlimeResource> COW = register("cow", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"), () -> Items.BEEF,true));
    public static final RegistryObject<SlimeResource> PIG = register("pig", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coarse_dirt.png"),() -> Items.PORKCHOP,true));
    public static final RegistryObject<SlimeResource> CHICKEN = register("chicken", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"),() -> Items.COOKED_CHICKEN, true));
    public static final RegistryObject<SlimeResource> COAL = register("coal", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coal_ore.png"), () -> Items.COAL,true));
    public static final RegistryObject<SlimeResource> BERRY = register("berry", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/sweet_berry_bush_stage3.png"), () -> Items.SWEET_BERRIES, true));
    public static final RegistryObject<SlimeResource> FISH = register("fish", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/barrel_side.png"), () -> Items.TROPICAL_FISH,true));
    public static final RegistryObject<SlimeResource> IRON = register("iron", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/iron_ore.png"), () -> Items.IRON_INGOT,true));
    public static final RegistryObject<SlimeResource> LAPIS = register("lapis", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/lapis_ore.png"), () -> Items.LAPIS_LAZULI,true));
    public static final RegistryObject<SlimeResource> QUARTZ = register("quartz", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/quartz_block_side.png"), () -> Items.QUARTZ,true));
    public static final RegistryObject<SlimeResource> AQUATIC = register("aquatic", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/conduit.png"), () -> Items.HEART_OF_THE_SEA,true));
    public static final RegistryObject<SlimeResource> NETHER = register("nether", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/netherrack.png"), () -> Items.NETHERRACK,true));
    public static final RegistryObject<SlimeResource> ZOMBIE = register("zombie", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.ROTTEN_FLESH,true));
    public static final RegistryObject<SlimeResource> ICE = register("ice", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/ice.png"), () -> Items.ICE,true));
    public static final RegistryObject<SlimeResource> COPPER = register("copper", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/copper_block.png"), () -> Items.COPPER_INGOT,true));
    public static final RegistryObject<SlimeResource> FIRE = register("fire", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/fire_0.png"),() -> Items.FIRE_CHARGE, true));
    public static final RegistryObject<SlimeResource> DEEPSLATE = register("deepslate", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"), () -> Items.DEEPSLATE,true));
    public static final RegistryObject<SlimeResource> CROP = register("crop", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"),() -> Items.WHEAT, true));
    public static final RegistryObject<SlimeResource> LESSERGEM = register("lesser_gem", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.BOW,true)); // TODO
    public static final RegistryObject<SlimeResource> GOLD = register("gold", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/gold_ore.png"), () -> Items.GOLD_INGOT,true));
    public static final RegistryObject<SlimeResource> REDSTONE = register("redstone", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/redstone_ore.png"), () -> Items.REDSTONE,true));
    public static final RegistryObject<SlimeResource> GLOWSTONE = register("glowstone", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/glowstone.png"), () -> Items.GLOWSTONE,true));
    public static final RegistryObject<SlimeResource> SKELETON = register("skeleton", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/bone_block_top.png"), () -> Items.BONE,true));
    public static final RegistryObject<SlimeResource> PRISMARINE = register("prismarine", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/prismarine.png"), () -> Items.PRISMARINE,true));
    public static final RegistryObject<SlimeResource> CREEPER = register("creeper", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.GUNPOWDER,true));
    public static final RegistryObject<SlimeResource> SPIDER = register("spider", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/cobweb.png"), () -> Items.SPIDER_EYE,true));
    public static final RegistryObject<SlimeResource> SLIME = register("slime", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/slime.png"), () -> Items.SLIME_BALL,true));
    public static final RegistryObject<SlimeResource> PHANTOM = register("phantom", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.PHANTOM_MEMBRANE,true));
    public static final RegistryObject<SlimeResource> ALCHEMY = register("alchemy", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/brewing_stand_base.png"), () -> Items.POTION,true));
    public static final RegistryObject<SlimeResource> ENDERPEARL = register("ender_pearl", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/end_portal_frame_eye.png"), () -> Items.ENDER_PEARL,true));
    public static final RegistryObject<SlimeResource> DIAMOND = register("diamond", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/diamond_ore.png"), () -> Items.DIAMOND,true));
    public static final RegistryObject<SlimeResource> HONEY = register("honey", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/honeycomb_block.png"),() -> Items.HONEY_BLOCK, true));
    public static final RegistryObject<SlimeResource> EXPERIENCE = register("experience", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/bookshelf.png"), () -> Items.BOOK,true));
    public static final RegistryObject<SlimeResource> MUSIC = register("music", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/note_block.png"), () -> Items.NOTE_BLOCK,true));
    public static final RegistryObject<SlimeResource> OBSIDIAN = register("obsidian", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/obsidian.png"), () -> Items.OBSIDIAN,true));
    public static final RegistryObject<SlimeResource> BLAZE = register("blaze", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.BLAZE_POWDER,true));
    public static final RegistryObject<SlimeResource> WITHERSKELETONS = register("wither_skeletons", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.WITHER_SKELETON_SKULL,true));
    public static final RegistryObject<SlimeResource> ILLAGERS = register("illagers", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.CROSSBOW,true));
    public static final RegistryObject<SlimeResource> GHAST = register("ghast", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.GHAST_TEAR,true));
    public static final RegistryObject<SlimeResource> NETHERITE = register("netherite", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/netherite_block.png"), () -> Items.NETHERITE_BLOCK,true));
    public static final RegistryObject<SlimeResource> MAGIC = register("magic", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.ENCHANTED_BOOK,true));
    public static final RegistryObject<SlimeResource> EMERALD = register("emerald", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/emerald_ore.png"), () -> Items.EMERALD,true));
    public static final RegistryObject<SlimeResource> SHULKERS = register("shulkers", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/shulker_box.png"), () -> Items.SHULKER_SHELL,true));
    public static final RegistryObject<SlimeResource> ENDERDRAGON = register("ender_dragon", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/dragon_egg.png"), () -> Items.DRAGON_EGG,true));
    public static final RegistryObject<SlimeResource> NETHERSTAR = register("nether_star", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/beacon.png"),() -> Items.NETHER_STAR, true));
    public static final RegistryObject<SlimeResource> GREATERGEM = register("greater_gem", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/carved_pumpkin.png"), () -> Items.BIRCH_DOOR,true));
    public static final RegistryObject<SlimeResource> ENDCRYSTAL = register("end_crystal", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/crying_obsidian.png"), () -> Items.END_CRYSTAL,true));

    public static RegistryObject<SlimeResource> register(String name, Supplier<? extends SlimeResource> supplier) {
        final RegistryObject<SlimeResource> reg = SLIME_RESOURCES.register(name, supplier);

        if(!name.equals("empty")) {
            ModItems.ITEMS.register("slimey_" + name + "_extract", () -> new SlimeyExtractItem(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIME_EXTRACTS)));
            ModItems.ITEMS.register(name + "_extract", () -> new ExtractItem(new Item.Properties().tab(ModCreativeModeTab.RESOURCE_SLIME_EXTRACTS)));
        }

        return reg;
    }

    public static void register(IEventBus eventBus) {
        SLIME_RESOURCES.register(eventBus);
    }
}