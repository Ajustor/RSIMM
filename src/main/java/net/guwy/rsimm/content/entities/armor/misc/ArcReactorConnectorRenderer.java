package net.guwy.rsimm.content.entities.armor.misc;

import net.guwy.rsimm.content.items.ArcReactorConnectorArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArcReactorConnectorRenderer extends GeoArmorRenderer<ArcReactorConnectorArmorItem> {
    public ArcReactorConnectorRenderer() {
        super(new ArcReactorConnectorModel());

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
