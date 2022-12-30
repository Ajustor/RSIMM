package net.guwy.rsimm.content.entities.non_living.mark_1_flame;

import net.guwy.rsimm.RsImm;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Mark1FlameEntityModel extends AnimatedGeoModel<Mark1FlameEntity> {
    @Override
    public ResourceLocation getModelResource(Mark1FlameEntity object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/mark_1_flame.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mark1FlameEntity object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/entity/mark_1_flame_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mark1FlameEntity animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/none.animation.json");
    }
}
