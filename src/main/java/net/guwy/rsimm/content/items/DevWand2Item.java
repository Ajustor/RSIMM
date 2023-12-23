package net.guwy.rsimm.content.items;

import net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DevWand2Item extends Item {
    public DevWand2Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide)
            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                ArcReactorSlot.removeArcReactor(pPlayer);
                arcReactor.setHasArcReactorSlot(false);
            });
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
