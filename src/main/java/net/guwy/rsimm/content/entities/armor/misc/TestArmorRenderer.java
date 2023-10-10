package net.guwy.rsimm.content.entities.armor.misc;

import net.guwy.rsimm.content.items.TestArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class TestArmorRenderer extends GeoArmorRenderer<TestArmorItem> {
    public TestArmorRenderer() {
        super(new TestArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
