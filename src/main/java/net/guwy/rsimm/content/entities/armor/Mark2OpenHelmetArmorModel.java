package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.guwy.rsimm.content.items.armors.Mark2ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Mark2OpenHelmetArmorModel extends AnimatedGeoModel<Mark2ArmorItem> {
    @Override
    public ResourceLocation getModelResource(Mark2ArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/mark_1_open_helmet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mark2ArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/armors/mark_1_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mark2ArmorItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/mark_1.animation.json");
    }
}
