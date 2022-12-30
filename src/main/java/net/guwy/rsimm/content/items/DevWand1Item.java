package net.guwy.rsimm.content.items;

import net.guwy.rsimm.index.ModArmorItems;
import net.guwy.rsimm.index.ModItems;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.ArmorEnergyType;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
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
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
            if(hasArmorSlotsFree(pPlayer)){
                addArmor(pPlayer);
                pPlayer.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    armorData.compileArmor(0, 0, ArmorEnergyType.EMERGENCY, 0
                             , 10, 0);
                    armorData.setArmorStorage(1, 20000);
                    armorData.setArmorStorage(2, 100);
                });

            }

        }
        if(pLevel.isClientSide){
            pPlayer.swing(pUsedHand);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private boolean hasArmorSlotsFree(Player player){
        return player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() &&
                player.getItemBySlot(EquipmentSlot.CHEST).isEmpty() &&
                player.getItemBySlot(EquipmentSlot.LEGS).isEmpty() &&
                player.getItemBySlot(EquipmentSlot.FEET).isEmpty();
    }

    private void addArmor(Player pPlayer){
        ItemStack itemStack;

        itemStack = new ItemStack(ModArmorItems.MARK_1_HELMET.get());
        pPlayer.setItemSlot(EquipmentSlot.HEAD, itemStack);

        itemStack = new ItemStack(ModArmorItems.MARK_1_CHESTPLATE.get());
        pPlayer.setItemSlot(EquipmentSlot.CHEST, itemStack);

        itemStack = new ItemStack(ModArmorItems.MARK_1_LEGGINGS.get());
        pPlayer.setItemSlot(EquipmentSlot.LEGS, itemStack);

        itemStack = new ItemStack(ModArmorItems.MARK_1_BOOTS.get());
        pPlayer.setItemSlot(EquipmentSlot.FEET, itemStack);
    }
}
