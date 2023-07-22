package net.guwy.rsimm.content.items.armors;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.item.GeoArmorItem;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractGen2IronmanArmorItem extends GeoArmorItem {
    public AbstractGen2IronmanArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    /** When True: armor will break when forcefully taken off and drop the "BrokenLoot" list
     *  When False: armor can be taken off and act like any normal armor would
     */
    public abstract boolean ShouldBreakWhenNotWearing();

    /** This list of items will be given when
     *  1) When armor breaks
     *  2) When armor is forcefully taken off and "ShouldBreakWhenNotWearing" = true
     */
    public abstract List<ItemStack> BrokenLoot();
}
