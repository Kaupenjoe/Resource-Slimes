package net.kaupenjoe.resourceslimes.data;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.block.ModBlocks;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ResourceSlimes.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        customItemModels();
    }

    private void customItemModels() {
        simpleItem(ModItems.RAW_CITRINE.get());
        simpleItem(ModItems.UNCUT_CITRINE.get());
        simpleItem(ModItems.CUT_CITRINE.get());
        simpleItem(ModItems.INFUSED_CITRINE.get());

        simpleItem(ModItems.RAW_ZIRCON.get());
        simpleItem(ModItems.UNCUT_ZIRCON.get());
        simpleItem(ModItems.CUT_ZIRCON.get());
        simpleItem(ModItems.INFUSED_ZIRCON.get());

        simpleItem(ModItems.CUT_DIAMOND.get());
        simpleItem(ModItems.INFUSED_DIAMOND.get());

        simpleItem(ModItems.CUT_EMERALD.get());
        simpleItem(ModItems.INFUSED_EMERALD.get());

        simpleItem(ModItems.RAW_TANZANITE.get());
        simpleItem(ModItems.UNCUT_TANZANITE.get());
        simpleItem(ModItems.CUT_TANZANITE.get());
        simpleItem(ModItems.INFUSED_TANZANITE.get());

        simpleItem(ModItems.RAW_BLACK_OPAL.get());
        simpleItem(ModItems.UNCUT_BLACK_OPAL.get());
        simpleItem(ModItems.CUT_BLACK_OPAL.get());

        simpleItem(ModItems.RED_SLIME_PEARL.get());
        simpleItem(ModItems.INFUSED_RED_SLIME_PEARL.get());

        spawnEgg(ModItems.RESOURCE_SLIME_SPAWN_EGG.get());


        simpleItem(ModItems.CITRINE_SLIME_FLUID_BUCKET.get());
        simpleItem(ModItems.ZIRCON_SLIME_FLUID_BUCKET.get());
        simpleItem(ModItems.DIAMOND_SLIME_FLUID_BUCKET.get());
        simpleItem(ModItems.EMERALD_SLIME_FLUID_BUCKET.get());
        simpleItem(ModItems.TANZANITE_SLIME_FLUID_BUCKET.get());
        simpleItem(ModItems.BLACK_OPAL_SLIME_FLUID_BUCKET.get());
        simpleItem(ModItems.RED_SLIME_PEARL_SLIME_FLUID_BUCKET.get());


        simpleBlock(ModBlocks.CITRINE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_CITRINE_ORE.get());

        simpleBlock(ModBlocks.ZIRCON_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_ZIRCON_ORE.get());

        simpleBlock(ModBlocks.TANZANITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_TANZANITE_ORE.get());

        simpleBlock(ModBlocks.BLACK_OPAL_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get());
        simpleBlock(ModBlocks.END_BLACK_OPAL_ORE.get());
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ResourceSlimes.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder simpleBlock(Block block) {
        return cubeAll(block.getRegistryName().getPath(), new ResourceLocation(ResourceSlimes.MOD_ID,
                "block/" + block.getRegistryName().getPath()));
    }

    private ItemModelBuilder spawnEgg(Item item) {
        return withExistingParent(item.getRegistryName().getPath(), ITEM_FOLDER + "/template_spawn_egg");
    }
}
