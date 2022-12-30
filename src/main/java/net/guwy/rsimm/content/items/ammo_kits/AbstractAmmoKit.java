package net.guwy.rsimm.content.items.ammo_kits;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractAmmoKit extends Item {
    public AbstractAmmoKit(Properties pProperties) {
        super(pProperties);
    }

    public Item storage1(){
        return Items.AIR;
    }
    public Integer storage1Capacity(){
        return 0;
    }

    public Item storage2(){
        return Items.AIR;
    }
    public Integer storage2Capacity(){
        return 0;
    }

    public Item storage3(){
        return Items.AIR;
    }
    public Integer storage3Capacity(){
        return 0;
    }

    public Item storage4(){
        return Items.AIR;
    }
    public Integer storage4Capacity(){
        return 0;
    }

    public Item storage5(){
        return Items.AIR;
    }
    public Integer storage5Capacity(){
        return 0;
    }

    public Item storage6(){
        return Items.AIR;
    }
    public Integer storage6Capacity(){
        return 0;
    }

    public Item storage7(){
        return Items.AIR;
    }
    public Integer storage7Capacity(){
        return 0;
    }

    public Item storage8(){
        return Items.AIR;
    }
    public Integer storage8Capacity(){
        return 0;
    }

    public Item storage9(){
        return Items.AIR;
    }
    public Integer storage9Capacity(){
        return 0;
    }

    public Item storage10(){
        return Items.AIR;
    }
    public Integer storage10Capacity(){
        return 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            if(pPlayer.isCrouching()){
                ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
                generateTags(itemStack);
                CompoundTag nbtTag = itemStack.getTag();
                assert nbtTag != null;

                nbtTag = storageExtraction(nbtTag, pPlayer, "1", storage1());
                nbtTag = storageExtraction(nbtTag, pPlayer, "2", storage2());
                nbtTag = storageExtraction(nbtTag, pPlayer, "3", storage3());
                nbtTag = storageExtraction(nbtTag, pPlayer, "4", storage4());
                nbtTag = storageExtraction(nbtTag, pPlayer, "5", storage5());
                nbtTag = storageExtraction(nbtTag, pPlayer, "6", storage6());
                nbtTag = storageExtraction(nbtTag, pPlayer, "7", storage7());
                nbtTag = storageExtraction(nbtTag, pPlayer, "8", storage8());
                nbtTag = storageExtraction(nbtTag, pPlayer, "9", storage9());
                nbtTag = storageExtraction(nbtTag, pPlayer, "10", storage10());

                itemStack.setTag(nbtTag);
            }else {
                ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
                generateTags(itemStack);
                CompoundTag nbtTag = itemStack.getTag();
                assert nbtTag != null;

                nbtTag = storageInsertion(nbtTag, pPlayer, "1", storage1(), storage1Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "2", storage2(), storage2Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "3", storage3(), storage3Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "4", storage4(), storage4Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "5", storage5(), storage5Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "6", storage6(), storage6Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "7", storage7(), storage7Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "8", storage8(), storage8Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "9", storage9(), storage9Capacity());
                nbtTag = storageInsertion(nbtTag, pPlayer, "10", storage10(), storage10Capacity());

                itemStack.setTag(nbtTag);
            }

        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }



    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        generateTags(pStack);
        CompoundTag nbtTag = pStack.getTag();

        if(nbtTag != null){
            if(storage1Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage1(), storage1Capacity(), nbtTag.getInt("1")));
            }
            if(storage2Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage2(), storage2Capacity(), nbtTag.getInt("2")));
            }
            if(storage3Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage3(), storage3Capacity(), nbtTag.getInt("3")));
            }
            if(storage4Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage4(), storage4Capacity(), nbtTag.getInt("4")));
            }
            if(storage5Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage5(), storage5Capacity(), nbtTag.getInt("5")));
            }
            if(storage6Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage6(), storage6Capacity(), nbtTag.getInt("6")));
            }
            if(storage7Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage7(), storage7Capacity(), nbtTag.getInt("7")));
            }
            if(storage8Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage8(), storage8Capacity(), nbtTag.getInt("8")));
            }
            if(storage9Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage9(), storage9Capacity(), nbtTag.getInt("9")));
            }
            if(storage10Capacity() > 0){
                pTooltipComponents.add(tooltipText(storage10(), storage10Capacity(), nbtTag.getInt("10")));
            }
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private Component tooltipText(Item storageItem, int storageCapacity, int storedAmount){
        Component text = Component.literal(
                        new ItemStack(storageItem).getDisplayName().getString())
                .append(": ")
                .append(Integer.toString(storedAmount))
                .append(" / ")
                .append(Integer.toString(storageCapacity))
                .withStyle(ChatFormatting.GOLD);
        return text;
    }



    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }



    private void generateTags(ItemStack itemStack){
        if(itemStack.getTag() == null){
            CompoundTag nbtTag = new CompoundTag();
            nbtTag.putInt("1", 0);
            nbtTag.putInt("2", 0);
            nbtTag.putInt("3", 0);
            nbtTag.putInt("4", 0);
            nbtTag.putInt("5", 0);
            nbtTag.putInt("6", 0);
            nbtTag.putInt("7", 0);
            nbtTag.putInt("8", 0);
            nbtTag.putInt("9", 0);
            nbtTag.putInt("10", 0);
            itemStack.setTag(nbtTag);
        }
    }

    public CompoundTag storageInsertion(CompoundTag nbtTag, Player pPlayer, String nbtKey, Item storage, int storageCapacity){
        if(nbtTag.getInt(nbtKey) < storageCapacity){
            for(int i = 0; i < pPlayer.getInventory().getContainerSize(); i++){
                if(nbtTag.getInt(nbtKey) < storageCapacity){
                    ItemStack inventoryItem = pPlayer.getInventory().getItem(i);
                    if(inventoryItem.getItem() == storage){
                        nbtTag.putInt(nbtKey, nbtTag.getInt(nbtKey) + 1);
                        if(!inventoryItem.hurt(1, RandomSource.create(), (ServerPlayer) pPlayer)){
                            inventoryItem.setCount(inventoryItem.getCount() - 1);
                        }
                        pPlayer.getLevel().playSound(null, pPlayer.getOnPos(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 100, 0.7f);
                    }
                }
            }
        }
        return nbtTag;
    }

    public CompoundTag storageExtraction(CompoundTag nbtTag, Player pPlayer, String nbtKey, Item itemToGive){
        if(nbtTag.getInt(nbtKey) > 0){
            pPlayer.addItem(new ItemStack(itemToGive));
            nbtTag.putInt(nbtKey, nbtTag.getInt(nbtKey) - 1);
            pPlayer.getLevel().playSound(null, pPlayer.getOnPos(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 100, 1.4f);
        }
        return nbtTag;
    }

    public abstract Item UnassembledHelmetItem();
    public abstract Item UnassembledChestplateItem();
    public abstract Item UnassembledLeggingsItem();
    public abstract Item UnassembledBootsItem();

    public abstract Item HelmetItem();
    public abstract Item ChestplateItem();
    public abstract Item LeggingsItem();
    public abstract Item BootsItem();


}
