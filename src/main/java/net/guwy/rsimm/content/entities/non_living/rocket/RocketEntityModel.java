package net.guwy.rsimm.content.entities.non_living.rocket;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RocketEntityModel extends AnimatedGeoModel<RocketEntity> {
    @Override
    public ResourceLocation getModelResource(RocketEntity object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/rocket.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RocketEntity object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/armors/mark_1_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RocketEntity animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/none.animation.json");
    }
}
