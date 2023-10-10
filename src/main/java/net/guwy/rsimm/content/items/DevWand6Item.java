package net.guwy.rsimm.content.items;

import net.guwy.rsimm.content.items.armors.AbstractIronmanArmorItem;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.guwy.rsimm.utils.KeyCallType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DevWand6Item extends Item {
    public DevWand6Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            pPlayer.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                if(armorData.getHasArmor()){
                    ItemStack itemStack = pPlayer.getItemBySlot(EquipmentSlot.CHEST);
                    AbstractIronmanArmorItem armorItem = (AbstractIronmanArmorItem) itemStack.getItem();

                    armorItem.FireWeapon1(pPlayer, pLevel, false, KeyCallType.START_HOLD);
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
