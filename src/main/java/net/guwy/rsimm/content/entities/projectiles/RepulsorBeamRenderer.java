package net.guwy.rsimm.content.entities.projectiles;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.guwy.rsimm.RsImm;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RepulsorBeamRenderer extends EntityRenderer<RepulsorBeamEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RsImm.MOD_ID, "textures/null.png");

    public RepulsorBeamRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(RepulsorBeamEntity pEntity) {
        return TEXTURE;
    }
}
