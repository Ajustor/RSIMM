package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.armors.ArcReactorConnectorArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArcReactorConnectorModel extends AnimatedGeoModel<ArcReactorConnectorArmorItem> {
    @Override
    public ResourceLocation getModelResource(ArcReactorConnectorArmorItem object) {

        return new ResourceLocation(RsImm.MOD_ID, "geo/mark_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArcReactorConnectorArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/armors/mark_1_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArcReactorConnectorArmorItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/none.animation.json");
    }
}
