package net.guwy.rsimm.content.items.armors;

import net.guwy.rsimm.content.items.armors.gen_2.Gen2IronManArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Mark2ArmorItem extends Gen2IronManArmorItem implements IAnimatable {
    public Mark2ArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }


    /** Gecko Lib Stuff */
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    @Override
    public void registerControllers(AnimationData data) {
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
