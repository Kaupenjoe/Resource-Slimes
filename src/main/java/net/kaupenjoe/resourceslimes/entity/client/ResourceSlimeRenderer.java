package net.kaupenjoe.resourceslimes.entity.client;

import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ResourceSlimeRenderer extends SlimeRenderer {
    public ResourceSlimeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Slime pEntity) {
        if(pEntity instanceof ResourceSlimeEntity resourceSlimeEntity) {
            return getLocationByItem(resourceSlimeEntity.getResourceItem());
        }

        return super.getTextureLocation(pEntity);
    }

    public static ResourceLocation getLocationByItem(Item item) {
        if(item == null || item == Items.AIR) {
            return new ResourceLocation(ResourceSlimes.MOD_ID,
                    "textures/entity/resource_slime/resource_slime_default.png");
        }

        // this should work as long as the textures are named correctly :)
        return new ResourceLocation(ResourceSlimes.MOD_ID, "textures/entity/resource_slime/resource_slime_" +
                item.getRegistryName().getPath() + ".png");
    }
}
