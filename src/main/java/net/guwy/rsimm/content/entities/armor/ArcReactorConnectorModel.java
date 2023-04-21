package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.armors.ArcReactorConnectorArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArcReactorConnectorModel extends AnimatedGeoModel<ArcReactorConnectorArmorItem> {
    @Override
    public ResourceLocation getModelResource(ArcReactorConnectorArmorItem object) {

        return new ResourceLocation(RsImm.MOD_ID, "geo/arc_reactor_connector_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArcReactorConnectorArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/arc_reactor_connector_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArcReactorConnectorArmorItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/none.animation.json");
    }
}
