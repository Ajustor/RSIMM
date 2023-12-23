package net.guwy.rsimm.content.items;

import net.guwy.rsimm.content.items.armors.gen_2.Gen2IronManArmorItem;
import net.guwy.rsimm.index.RsImmArmorItems;
import net.guwy.rsimm.index.RsImmItems;
import net.guwy.rsimm.index.RsImmSuitComponentItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class DevWand4Item extends Item {
    public DevWand4Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){

            ItemStack helmetItem = new ItemStack(RsImmArmorItems.MARK_2_HELMET.get());
            pPlayer.setItemSlot(EquipmentSlot.HEAD, helmetItem);



            ItemStack chestplateItem = new ItemStack(RsImmArmorItems.MARK_2_CHESTPLATE.get());
            chestplateItem.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
                itemHandler.insertItem(Gen2IronManArmorItem.HULL_SLOT, new ItemStack(RsImmSuitComponentItems.MARK_2_HULL.get()), false);

                //itemHandler.insertItem(Gen2IronManArmorItem.REACTOR_CONNECTOR_SLOT, new ItemStack(Blocks.COPPER_BLOCK.asItem()), false);

                itemHandler.insertItem(Gen2IronManArmorItem.REPULSOR_RIGHT_ARM_SLOT, new ItemStack(RsImmSuitComponentItems.REPULSOR.get()), false);
                itemHandler.insertItem(Gen2IronManArmorItem.REPULSOR_LEFT_ARM_SLOT, new ItemStack(RsImmSuitComponentItems.REPULSOR.get()), false);
                itemHandler.insertItem(Gen2IronManArmorItem.REPULSOR_RIGHT_LEG_SLOT, new ItemStack(RsImmSuitComponentItems.REPULSOR.get()), false);
                itemHandler.insertItem(Gen2IronManArmorItem.REPULSOR_LEFT_LEG_SLOT, new ItemStack(RsImmSuitComponentItems.REPULSOR.get()), false);
            });
            pPlayer.setItemSlot(EquipmentSlot.CHEST, chestplateItem);



            ItemStack leggingsItem = new ItemStack(RsImmArmorItems.MARK_2_LEGGINGS.get());
            pPlayer.setItemSlot(EquipmentSlot.LEGS, leggingsItem);



            ItemStack bootsItem = new ItemStack(RsImmArmorItems.MARK_2_BOOTS.get());
            pPlayer.setItemSlot(EquipmentSlot.FEET, bootsItem);



            pPlayer.sendSystemMessage(Component.literal("Armor assembled successfully"));
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
