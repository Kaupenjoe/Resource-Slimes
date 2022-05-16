package net.kaupenjoe.resourceslimes.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.kaupenjoe.resourceslimes.ResourceSlimes;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.util.function.Function;

public class ModRenderTypes extends RenderType {
    public ModRenderTypes(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_,
                          int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    // Accessor functon, ensures that you don't use the raw methods below unintentionally.
    public static RenderType slimeyness(ResourceLocation texture)
    {
        return ModRenderTypes.SLIMEYNESS_FUNC.apply(texture);
    }

    private static ShaderInstance slimeynessSolidShader;
    private static final ShaderStateShard RENDERTYPE_SLIMEYNESS_SHADER = new ShaderStateShard(() -> slimeynessSolidShader);

    // The memoize caches the output value for each input, meaning the expensive registration process doesn't have to rerun
    public static Function<ResourceLocation, RenderType> SLIMEYNESS_FUNC = Util.memoize(ModRenderTypes::createSlimeyness);

    public static RenderType createSlimeyness(ResourceLocation locationIn) {
        RenderType.CompositeState rendertypeState = RenderType.CompositeState.builder()
                .setShaderState(RENDERTYPE_SLIMEYNESS_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(locationIn,
                        true, false))
                //.setWriteMaskState(COLOR_WRITE)
                //.setCullState(NO_CULL)
                //.setDepthTestState(EQUAL_DEPTH_TEST)
                //.setTransparencyState(GLINT_TRANSPARENCY)
                //.setOutputState(ITEM_ENTITY_TARGET)
                // .setTexturingState(ENTITY_GLINT_TEXTURING)
                .createCompositeState(false);

        return create("rendertype_slimeyness", DefaultVertexFormat.POSITION_TEX,
                VertexFormat.Mode.QUADS, 256, false, false, rendertypeState);

    }


    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ResourceSlimes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModClientEvents {
        @SubscribeEvent
        public static void shaderRegistry(RegisterShadersEvent event) throws IOException {
            // Adds a shader to the list, the callback runs when loading is complete.
            event.registerShader(new ShaderInstance(event.getResourceManager(),
                    new ResourceLocation(ResourceSlimes.MOD_ID, "rendertype_slimeyness"), DefaultVertexFormat.NEW_ENTITY),
                    shaderInstance -> {
                ModRenderTypes.slimeynessSolidShader = shaderInstance;
            });
        }
    }

}
