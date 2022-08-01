package net.kaupenjoe.resourceslimes.data;

import org.jetbrains.annotations.Nullable;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.util.ResourceSlimesRegistries;
import net.kaupenjoe.resourceslimes.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, new ModBlockTagProvider(pGenerator, ResourceSlimes.MOD_ID, existingFileHelper),
                ResourceSlimes.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        var resources = ResourceSlimesRegistries.SLIME_RESOURCES.get().getValues();
        for (var resource : resources) {
            this.tag(ModTags.Items.SLIMEY_EXTRACTS)
                    .addOptional(new ResourceLocation(ResourceSlimes.MOD_ID,"slimey_" + resource.name().toLowerCase() + "_extract"));

            this.tag(ModTags.Items.EXTRACTS)
                    .addOptional(new ResourceLocation(ResourceSlimes.MOD_ID,resource.name().toLowerCase() + "_extract"));
        }
    }
}
