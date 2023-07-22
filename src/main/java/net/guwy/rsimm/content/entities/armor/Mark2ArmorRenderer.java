package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.guwy.rsimm.content.items.armors.Mark2ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class Mark2ArmorRenderer extends GeoArmorRenderer<Mark2ArmorItem> {
    public Mark2ArmorRenderer() {
        super(new Mark2ArmorModel());

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
