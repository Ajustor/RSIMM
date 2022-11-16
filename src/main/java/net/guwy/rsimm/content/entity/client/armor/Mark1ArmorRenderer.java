package net.guwy.rsimm.content.entity.client.armor;

import net.guwy.rsimm.content.items.Mark1ArmorItem;
import net.guwy.rsimm.content.items.TestArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class Mark1ArmorRenderer extends GeoArmorRenderer<Mark1ArmorItem> {
    public Mark1ArmorRenderer() {
        super(new Mark1ArmorModel());

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
