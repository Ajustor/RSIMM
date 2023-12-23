package net.guwy.rsimm.content.entities.item;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.GenericRepulsorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RepulsorItemModel extends AnimatedGeoModel<GenericRepulsorItem> {
    @Override
    public ResourceLocation getModelResource(GenericRepulsorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/repulsor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GenericRepulsorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/flight_test_suit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GenericRepulsorItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/none.animation.json");
    }
}
