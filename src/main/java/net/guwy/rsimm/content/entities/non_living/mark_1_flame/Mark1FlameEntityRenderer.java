package net.guwy.rsimm.content.entities.non_living.mark_1_flame;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.guwy.rsimm.RsImm;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class Mark1FlameEntityRenderer extends GeoEntityRenderer<Mark1FlameEntity> {
    public Mark1FlameEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Mark1FlameEntityModel());
        this.shadowRadius = 0.1f;
    }

    @Override
    public ResourceLocation getTextureLocation(Mark1FlameEntity instance) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/entity/mark_1_flame_texture.png");
    }

    @Override
    public RenderType getRenderType(Mark1FlameEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(animatable.tickCount / 4f, animatable.tickCount / 4f, animatable.tickCount / 4f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
