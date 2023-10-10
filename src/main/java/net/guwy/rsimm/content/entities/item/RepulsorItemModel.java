package net.guwy.rsimm.content.entities.item;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.EdithGlassesArmorItem;
import net.guwy.rsimm.content.items.RepulsorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RepulsorItemModel extends AnimatedGeoModel<RepulsorItem> {
    @Override
    public ResourceLocation getModelResource(RepulsorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/repulsor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RepulsorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/repulsor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RepulsorItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/none.animation.json");
    }
}
