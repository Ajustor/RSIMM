package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.index.ModArcReactorItems;
import net.guwy.rsimm.index.ModItems;
import net.minecraft.world.item.Item;

public class UnchargedMark1ArcReactorItem extends AbstractUnchargedArcReactorItem{
    public UnchargedMark1ArcReactorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Item getChargedItem() {
        return ModArcReactorItems.MARK_1_ARC_REACTOR.get();
    }

    @Override
    public long getRequiredCharge() {
        return 10000000;
    }

    @Override
    public long getChargeConsume() {
        return 200;
    }
}
