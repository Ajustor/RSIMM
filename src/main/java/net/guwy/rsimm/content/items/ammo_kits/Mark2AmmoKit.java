package net.guwy.rsimm.content.items.ammo_kits;

import net.guwy.rsimm.index.ModArmorItems;
import net.minecraft.world.item.Item;

public class Mark2AmmoKit extends AbstractAmmoKit{
    public Mark2AmmoKit(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Item UnassembledHelmetItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item UnassembledChestplateItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item UnassembledLeggingsItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item UnassembledBootsItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item HelmetItem() {
        return ModArmorItems.MARK_1_BOOTS.get();
    }

    @Override
    public Item ChestplateItem() {
        return ModArmorItems.MARK_1_BOOTS.get();
    }

    @Override
    public Item LeggingsItem() {
        return ModArmorItems.MARK_1_BOOTS.get();
    }

    @Override
    public Item BootsItem() {
        return ModArmorItems.MARK_1_BOOTS.get();
    }
}
