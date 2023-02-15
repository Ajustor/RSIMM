package net.guwy.rsimm.content.items.ammo_kits;

import net.guwy.rsimm.index.ModArmorItems;
import net.guwy.rsimm.index.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.Objects;

public class Mark1AmmoKit extends AbstractAmmoKit{
    public Mark1AmmoKit(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Item storage1() {
        return Blocks.MAGMA_BLOCK.asItem();
    }
    @Override
    public Integer storage1Capacity() {
        return 10000;
    }

    @Override
    public Item storage2() {
        return ModItems.ROCKET.get();
    }
    @Override
    public Integer storage2Capacity() {
        return 1;
    }

    @Override
    public CompoundTag storageInsertion(CompoundTag nbtTag, Player pPlayer, String nbtKey, Item storage, int storageCapacity) {
        if(storage.equals(storage1())){
            if(nbtTag.getInt(nbtKey) < storageCapacity){
                for(int i = 0; i < pPlayer.getInventory().getContainerSize(); i++){
                    if(nbtTag.getInt(nbtKey) < storageCapacity){
                        ItemStack inventoryItem = pPlayer.getInventory().getItem(i);
                        if(inventoryItem.getItem() == storage){
                            nbtTag.putInt(nbtKey, nbtTag.getInt(nbtKey) + 100);
                            if(!inventoryItem.hurt(1, RandomSource.create(), (ServerPlayer) pPlayer)){
                                inventoryItem.setCount(inventoryItem.getCount() - 1);
                            }
                            pPlayer.getLevel().playSound(null, pPlayer.getOnPos(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 100, 0.7f);
                        }
                    }
                }
            }
            return nbtTag;
        }else {
            return super.storageInsertion(nbtTag, pPlayer, nbtKey, storage, storageCapacity);
        }
    }

    @Override
    public CompoundTag storageExtraction(CompoundTag nbtTag, Player pPlayer, String nbtKey, Item itemToGive) {
        if(itemToGive.equals(storage1())){
            if(nbtTag.getInt(nbtKey) > 0){
                pPlayer.addItem(new ItemStack(itemToGive));
                nbtTag.putInt(nbtKey, nbtTag.getInt(nbtKey) - 100);

                pPlayer.getLevel().playSound(null, pPlayer.getOnPos(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 100, 1.4f);
            }
            return nbtTag;
        }else {

            return super.storageExtraction(nbtTag, pPlayer, nbtKey, itemToGive);
        }
    }

    @Override
    public Item UnassembledHelmetItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_HELMET.get();
    }

    @Override
    public Item UnassembledChestplateItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_CHESTPLATE.get();
    }

    @Override
    public Item UnassembledLeggingsItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_LEGGINGS.get();
    }

    @Override
    public Item UnassembledBootsItem() {
        return ModArmorItems.UNASSAMBLED_MARK_1_BOOTS.get();
    }

    @Override
    public Item HelmetItem() {
        return ModArmorItems.MARK_1_HELMET.get();
    }

    @Override
    public Item ChestplateItem() {
        return ModArmorItems.MARK_1_CHESTPLATE.get();
    }

    @Override
    public Item LeggingsItem() {
        return ModArmorItems.MARK_1_LEGGINGS.get();
    }

    @Override
    public Item BootsItem() {
        return ModArmorItems.MARK_1_BOOTS.get();
    }
}