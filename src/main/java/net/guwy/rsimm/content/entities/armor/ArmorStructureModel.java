package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.armors.ArmorStructureItem;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmorStructureModel extends AnimatedGeoModel<ArmorStructureItem> {

    @Override
    public ResourceLocation getModelLocation(ArmorStructureItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "geo/armor_structure.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ArmorStructureItem object) {
        return new ResourceLocation(RsImm.MOD_ID, "textures/models/armors/armor_structure.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ArmorStructureItem animatable) {
        return new ResourceLocation(RsImm.MOD_ID, "animations/structure_armor.animation.json");
    }
}
