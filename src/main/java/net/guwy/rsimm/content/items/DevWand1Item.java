package net.guwy.rsimm.content.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DevWand1Item extends Item {
    public DevWand1Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            pPlayer.sendSystemMessage(Component.literal(Integer.toString(pPlayer.tickCount)));
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
        }
        if(pLevel.isClientSide){
            pPlayer.swing(pUsedHand);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
