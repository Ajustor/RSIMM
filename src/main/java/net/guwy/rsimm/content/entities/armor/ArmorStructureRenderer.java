package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.content.items.armors.ArmorStructureItem;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmorStructureRenderer extends GeoArmorRenderer<ArmorStructureItem> {
    public ArmorStructureRenderer() {
        super(new ArmorStructureModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
