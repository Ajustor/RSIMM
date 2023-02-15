package net.guwy.rsimm.content.items.arc_reactors;

import net.minecraft.world.item.Item;

public class UnchargedArcReactorItem extends AbstractUnchargedArcReactorItem{
    Item chargedItem;
    long requiredCharge;
    long chargeConsume;

    public UnchargedArcReactorItem(Properties pProperties, Item chargedItem, long requiredCharge, long chargeConsume) {
        super(pProperties);
        this.chargedItem = chargedItem;
        this.requiredCharge = requiredCharge;
        this.chargeConsume = chargeConsume;
    }

    @Override
    public Item getChargedItem() {
        return chargedItem;
    }

    @Override
    public long getRequiredCharge() {
        return requiredCharge;
    }

    @Override
    public long getChargeConsume() {
        return chargeConsume;
    }
}
