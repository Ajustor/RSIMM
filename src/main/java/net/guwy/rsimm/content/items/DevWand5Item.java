package net.guwy.rsimm.content.items;

import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DevWand5Item extends Item {
    public DevWand5Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {

            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                boolean hasSlot = (arcReactor.hasArcReactorSlot());

                if(hasSlot){
                    if(arcReactor.getPlayerArcReactorPoisoning() == 0){
                        arcReactor.setPlayerArcReactorPoisoning(50000);
                        pPlayer.sendSystemMessage(Component.literal("Poisoning has been set to: 50000"));
                    }   else if(arcReactor.getPlayerArcReactorPoisoning() == 50000){
                        arcReactor.setPlayerArcReactorPoisoning(100000);
                        pPlayer.sendSystemMessage(Component.literal("Poisoning has been set to: 100000"));
                    }   else if(arcReactor.getPlayerArcReactorPoisoning() == 100000){
                        arcReactor.setPlayerArcReactorPoisoning(300000);
                        pPlayer.sendSystemMessage(Component.literal("Poisoning has been set to: 300000"));
                    }   else if(arcReactor.getPlayerArcReactorPoisoning() == 300000){
                        arcReactor.setPlayerArcReactorPoisoning(420000);
                        pPlayer.sendSystemMessage(Component.literal("Poisoning has been set to: 420000"));
                    }   else if(arcReactor.getPlayerArcReactorPoisoning() == 420000){
                        arcReactor.setPlayerArcReactorPoisoning(0);
                        pPlayer.sendSystemMessage(Component.literal("Poisoning has been set to: 0"));
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
