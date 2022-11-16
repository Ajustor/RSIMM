package net.guwy.rsimm.content.items;

import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DevWand4Item extends Item {
    public DevWand4Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {

            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                boolean hasSlot = (arcReactor.hasArcReactorSlot());

                if(hasSlot){
                    if(arcReactor.getArcReactorTypeId() != 0){
                        arcReactor.deleteArcReactor();
                        pPlayer.sendMessage(new TextComponent("Arc Reactor Has Been Removed"), pPlayer.getUUID());
                    }   else {
                        arcReactor.setHasArcReactorSlot(false);
                        arcReactor.setPlayerArcReactorPoisoning(0);
                        pPlayer.sendMessage(new TextComponent("Arc Reactor Slot Has Been Removed"), pPlayer.getUUID());
                    }
                }
            });

            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
        }

        if(pLevel.isClientSide){
            pPlayer.swing(pUsedHand);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
