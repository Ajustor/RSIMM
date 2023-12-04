package net.guwy.rsimm.content.items;

import net.guwy.rsimm.index.RsImmArmorItems;
import net.guwy.rsimm.index.RsImmCreativeModeTabs;
import net.guwy.rsimm.index.RsImmItems;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
// import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mark1ArmorItem extends GeoArmorItem implements IAnimatable, ILoopType {
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public Mark1ArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder.tab(RsImmCreativeModeTabs.MAIN));
    }


    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(stack.getItem().equals(RsImmArmorItems.MARK_1_CHESTPLATE.get())){
            chestplateTickEvent(stack, level, player);
            player.sendSystemMessage(Component.literal("player tick: " + player.tickCount));
            // player.sendMessage(new TextComponent("player tick: " + player.tickCount), player.getUUID());
        }
        super.onArmorTick(stack, level, player);
    }

    private static void chestplateTickEvent(ItemStack pStack, Level pLevel, Player player){
        if(playerHasFullSet(player)){
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, false, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 1, false, false, false));
        }
    }



    @SuppressWarnings("unused")
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {

        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

        Player player = null;
        if(livingEntity.getType() == EntityType.PLAYER){
            player = (Player)livingEntity;
        }

        event.getController().setAnimation(new AnimationBuilder()
                .addAnimation("animation.model.spinny_boi", EDefaultLoopTypes.LOOP));

        if (livingEntity instanceof ArmorStand) {
            return PlayState.CONTINUE;
        }

        List<Item> armorList = new ArrayList<>(4);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                if (livingEntity.getItemBySlot(slot) != null) {
                    armorList.add(livingEntity.getItemBySlot(slot).getItem());
                }
            }
        }

        boolean isWearingAll = armorList
                .containsAll(Arrays.asList(RsImmArmorItems.MARK_1_BOOTS.get(), RsImmArmorItems.MARK_1_LEGGINGS.get(), RsImmArmorItems.MARK_1_CHESTPLATE.get(), RsImmArmorItems.MARK_1_HELMET.get()));
        return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean isRepeatingAfterEnd() {
        return false;
    }




    @Override
    public boolean isFoil(ItemStack pStack) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        Player player = (Player) pEntity;
        if(!player.isCreative()){
            if(pStack.getItem() == RsImmArmorItems.MARK_1_CHESTPLATE.get()){
                if(pSlotId != EquipmentSlot.CHEST.getIndex()){removeArmorAndGive(pStack, player, Items.AMETHYST_SHARD);}
            } else if (pStack.getItem() == RsImmArmorItems.MARK_1_HELMET.get()) {
                if(pSlotId != EquipmentSlot.HEAD.getIndex()){removeArmorAndGive(pStack, player, Items.AMETHYST_SHARD);}
            } else if (pStack.getItem() == RsImmArmorItems.MARK_1_LEGGINGS.get()) {
                if(pSlotId != EquipmentSlot.LEGS.getIndex()){removeArmorAndGive(pStack, player, Items.AMETHYST_SHARD);}
            } else if (pStack.getItem() == RsImmArmorItems.MARK_1_BOOTS.get()) {
                if(pSlotId != EquipmentSlot.FEET.getIndex()){removeArmorAndGive(pStack, player, Items.AMETHYST_SHARD);}
            }
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    private static void removeArmorAndGive(ItemStack pStack, Player player, Item itemToGive){
        player.playSound(SoundEvents.ITEM_BREAK, 100, 1);
        pStack.setCount(0);
        player.addItem(new ItemStack(itemToGive));
    }

    private static boolean playerHasFullSet(Player player){
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(RsImmArmorItems.MARK_1_HELMET.get())
        && player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(RsImmArmorItems.MARK_1_CHESTPLATE.get())
        && player.getItemBySlot(EquipmentSlot.LEGS).getItem().equals(RsImmArmorItems.MARK_1_LEGGINGS.get())
        && player.getItemBySlot(EquipmentSlot.FEET).getItem().equals(RsImmArmorItems.MARK_1_BOOTS.get())){
            return true;
        }   else {
            return false;
        }
    }


}
