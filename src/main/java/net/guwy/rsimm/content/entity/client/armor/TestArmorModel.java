package net.guwy.rsimm.content.entity.client.armor;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestArmorModel extends AnimatedGeoModel<TestArmorItem> {
    @Override
    public ResourceLocation getModelLocation(TestArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/test_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TestArmorItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/armors/test_armor_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TestArmorItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/test_armor.animation.json");
    }
}
