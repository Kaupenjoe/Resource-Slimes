package net.kaupenjoe.resourceslimes.util.resources;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.item.ModCreativeModeTab;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.item.custom.ExtractItem;
import net.kaupenjoe.resourceslimes.item.custom.SlimeyExtractItem;
import net.kaupenjoe.resourceslimes.util.ResourceSlimesRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BuiltinSlimeResources {
    public static final DeferredRegister<SlimeResource> SLIME_RESOURCES = DeferredRegister
            .create(ResourceSlimesRegistries.Keys.SLIME_RESOURCES, ResourceSlimes.MOD_ID);

    public static final RegistryObject<SlimeResource> EMPTY = register("empty", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/structure_block.png"), true));
    public static final RegistryObject<SlimeResource> STONE = register("stone", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/stone.png"), true));
    public static final RegistryObject<SlimeResource> DIRT = register("dirt", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/dirt.png"), true));
    public static final RegistryObject<SlimeResource> WATER = register("water", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/water_flow.png"), true));
    public static final RegistryObject<SlimeResource> SAND = register("sand", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/sand.png"), true));
    public static final RegistryObject<SlimeResource> GRAVEL = register("gravel", () -> new SlimeResource(ResourceTier.CITRINE, new ResourceLocation("textures/block/gravel.png"), true));
    public static final RegistryObject<SlimeResource> WOOD = register("wood", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/oak_log.png"), true));
    public static final RegistryObject<SlimeResource> GRASS = register("grass", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/grass.png"), true));
    public static final RegistryObject<SlimeResource> FLOWER = register("flower", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/dandelion.png"), true));
    public static final RegistryObject<SlimeResource> SHEEP = register("sheep", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/white_wool.png"), true));
    public static final RegistryObject<SlimeResource> COW = register("cow", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"), true));
    public static final RegistryObject<SlimeResource> PIG = register("pig", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coarse_dirt.png"), true));
    public static final RegistryObject<SlimeResource> CHICKEN = register("chicken", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/hay_block.png"), true));
    public static final RegistryObject<SlimeResource> COAL = register("coal", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/coal_ore.png"), true));
    public static final RegistryObject<SlimeResource> BERRY = register("berry", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/sweet_berry_bush_stage3.png"), true));
    public static final RegistryObject<SlimeResource> FISH = register("fish", () -> new SlimeResource(ResourceTier.ZIRCON, new ResourceLocation("textures/block/barrel_side.png"), true));
    public static final RegistryObject<SlimeResource> IRON = register("iron", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/iron_ore.png"), true));
    public static final RegistryObject<SlimeResource> LAPIS = register("lapis", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/lapis_ore.png"), true));
    public static final RegistryObject<SlimeResource> QUARTZ = register("quartz", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/quartz_block_side.png"), true));
    public static final RegistryObject<SlimeResource> AQUATIC = register("aquatic", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/conduit.png"), true));
    public static final RegistryObject<SlimeResource> NETHER = register("nether", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/netherrack.png"), true));
    public static final RegistryObject<SlimeResource> ZOMBIE = register("zombie", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ICE = register("ice", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/ice.png"), true));
    public static final RegistryObject<SlimeResource> COPPER = register("copper", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/copper_block.png"), true));
    public static final RegistryObject<SlimeResource> FIRE = register("fire", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/fire_0.png"), true));
    public static final RegistryObject<SlimeResource> DEEPSLATE = register("deepslate", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"), true));
    public static final RegistryObject<SlimeResource> CROP = register("crop", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/deepslate.png"), true));
    public static final RegistryObject<SlimeResource> LESSERGEM = register("lesser_gem", () -> new SlimeResource(ResourceTier.DIAMOND, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> GOLD = register("gold", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/gold_ore.png"), true));
    public static final RegistryObject<SlimeResource> REDSTONE = register("redstone", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/redstone_ore.png"), true));
    public static final RegistryObject<SlimeResource> GLOWSTONE = register("glowstone", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/glowstone.png"), true));
    public static final RegistryObject<SlimeResource> SKELETON = register("skeleton", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/bone_block_top.png"), true));
    public static final RegistryObject<SlimeResource> PRISMARINE = register("prismarine", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/prismarine.png"), true));
    public static final RegistryObject<SlimeResource> CREEPER = register("creeper", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> SPIDER = register("spider", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/cobweb.png"), true));
    public static final RegistryObject<SlimeResource> SLIME = register("slime", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/slime.png"), true));
    public static final RegistryObject<SlimeResource> PHANTOM = register("phantom", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ALCHEMY = register("alchemy", () -> new SlimeResource(ResourceTier.EMERALD, new ResourceLocation("textures/block/brewing_stand_base.png"), true));
    public static final RegistryObject<SlimeResource> ENDERPEARL = register("ender_pearl", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/end_portal_frame_eye.png"), true));
    public static final RegistryObject<SlimeResource> DIAMOND = register("diamond", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/diamond_ore.png"), true));
    public static final RegistryObject<SlimeResource> HONEY = register("honey", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/honeycomb_block.png"), true));
    public static final RegistryObject<SlimeResource> EXPERIENCE = register("experience", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/bookshelf.png"), true));
    public static final RegistryObject<SlimeResource> MUSIC = register("music", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/note_block.png"), true));
    public static final RegistryObject<SlimeResource> OBSIDIAN = register("obsidian", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/obsidian.png"), true));
    public static final RegistryObject<SlimeResource> BLAZE = register("blaze", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> WITHERSKELETONS = register("wither_skeletons", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ILLAGERS = register("illagers", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> GHAST = register("ghast", () -> new SlimeResource(ResourceTier.TANZANITE, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> NETHERITE = register("netherite", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/netherite_block.png"), true));
    public static final RegistryObject<SlimeResource> MAGIC = register("magic", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> EMERALD = register("emerald", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/emerald_ore.png"), true));
    public static final RegistryObject<SlimeResource> SHULKERS = register("shulkers", () -> new SlimeResource(ResourceTier.BLACK_OPAL, new ResourceLocation("textures/block/shulker_box.png"), true));
    public static final RegistryObject<SlimeResource> ENDERDRAGON = register("ender_dragon", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/dragon_egg.png"), true));
    public static final RegistryObject<SlimeResource> NETHERSTAR = register("nether_star", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/beacon.png"), true));
    public static final RegistryObject<SlimeResource> GREATERGEM = register("greater_gem", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/carved_pumpkin.png"), true));
    public static final RegistryObject<SlimeResource> ENDCRYSTAL = register("end_crystal", () -> new SlimeResource(ResourceTier.PINK_SLIME_PEARL, new ResourceLocation("textures/block/crying_obsidian.png"), true));

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