package net.guwy.rsimm.content.items.ammo_kits;

import net.guwy.rsimm.index.RsImmArmorItems;
import net.minecraft.world.item.Item;

public class Mark2AmmoKit extends AbstractAmmoKit{
    public Mark2AmmoKit(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Item UnassembledHelmetItem() {
        return RsImmArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item UnassembledChestplateItem() {
        return RsImmArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item UnassembledLeggingsItem() {
        return RsImmArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item UnassembledBootsItem() {
        return RsImmArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item HelmetItem() {
        return RsImmArmorItems.MARK_1_BOOTS.get();
    }

    @Override
    public Item ChestplateItem() {
        return RsImmArmorItems.MARK_1_BOOTS.get();
    }

    @Override
    public Item LeggingsItem() {
        return RsImmArmorItems.MARK_1_BOOTS.get();
    }

    @Override
    public Item BootsItem() {
        return RsImmArmorItems.MARK_1_BOOTS.get();
    }
}
