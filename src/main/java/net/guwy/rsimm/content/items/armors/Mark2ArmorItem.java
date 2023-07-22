package net.guwy.rsimm.content.items.armors;

import net.guwy.rsimm.index.ModAmmoKitItems;
import net.guwy.rsimm.index.ModArmorItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;

public class Mark2ArmorItem extends AbstractGen2IronmanArmorItem implements IAnimatable {
    public Mark2ArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public boolean ShouldBreakWhenNotWearing() {
        return false;
    }

    @Override
    public List<ItemStack> BrokenLoot() {
        return null;
    }





    // Gecko Lib Stuff
    @Override
    public void registerControllers(AnimationData data) {

    }

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
