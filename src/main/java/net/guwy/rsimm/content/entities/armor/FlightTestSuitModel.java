package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.armors.FlightTestSuitArmorItem;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FlightTestSuitModel extends AnimatedGeoModel<FlightTestSuitArmorItem> {

    @Override
    public ResourceLocation getModelResource(FlightTestSuitArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/mark_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlightTestSuitArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/armors/mark_1_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlightTestSuitArmorItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/mark_1.animation.json");
    }
}
