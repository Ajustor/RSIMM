package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.content.items.armors.FlightTestSuitArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FlightTestSuitRenderer extends GeoArmorRenderer<FlightTestSuitArmorItem> {
    public FlightTestSuitRenderer() {
        super(new FlightTestSuitModel());

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
