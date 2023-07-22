package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.content.items.armors.Mark2ArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class Mark2OpenHelmetArmorRenderer extends GeoArmorRenderer<Mark2ArmorItem> {
    public Mark2OpenHelmetArmorRenderer() {
        super(new Mark2OpenHelmetArmorModel());

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
