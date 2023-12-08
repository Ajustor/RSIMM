package net.guwy.rsimm.content.items.armors;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.guwy.rsimm.client.ArcReactorClientData;
import net.guwy.rsimm.client.ArmorClientData;
import net.guwy.rsimm.content.entities.item.RepulsorItemRenderer;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntity;
import net.guwy.rsimm.content.entities.non_living.rocket.RocketEntity;
import net.guwy.rsimm.content.entities.projectiles.RepulsorBeamEntity;
import net.guwy.rsimm.content.items.RepulsorItem;
import net.guwy.rsimm.content.items.arc_reactors.ArcReactorItem;
import net.guwy.rsimm.enums.RepulsorAttackType;
import net.guwy.rsimm.index.*;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.ArmorEnergyType;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.FlyMode;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.guwy.rsimm.utils.KeyCallType;
import net.guwy.sticky_foundations.index.SFMinerals;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class ArmorStructureItem extends AbstractGen2IronmanArmorItem implements IAnimatable, ILoopType {
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();

    public ArmorStructureItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public Item HelmetItem() {
        return null;
    }
    @Override
    public Item HelmetOpenItem() {
        return null;
    }
    @Override
    public Item ChestplateItem() {
        return RsImmArmorItems.ARMOR_STRUCTURE_CHESTPLATE.get();
    }
    @Override
    public Item LeggingsItem() {
        return RsImmArmorItems.ARMOR_STRUCTURE_LEGGINGS.get();
    }
    @Override
    public Item BootsItem() {
        return RsImmArmorItems.ARMOR_STRUCTURE_BOOTS.get();
    }

    @Override
    public int ConstantEnergyDraw() {
        return 15;
    }

    @Override
    public double RunningExtraEnergyDraw() {
        return 2;
    }

    @Override
    public double OverloadTextDisappearThreshold() {
        return 10;
    }

    @Override
    public long MaxStableEnergy() {
        return 0;
    }

    @Override
    public long MaxEnergyOutput() {
        return 0;
    }

    @Override
    public double bootRate() {
        return 100;
    }    // 0.05 for release (3.0 for testing)

    @Override
    public int FreezingHeight() {
        return 200;
    }
    @Override
    public int FreezingCoefficient() {
        return 1;
    }
    @Override
    public double Heating() {
        return 0;
    }
    @Override
    public long HeatingEnergyCost() {
        return  0;
    }
    @Override
    public double HeatingBootRequirement() {
        return 0;
    }

    @Override
    protected boolean playerHasFullSetWithoutData(Player player) {
        AtomicBoolean hasArmor = new AtomicBoolean(false);
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            hasArmor.set(!armorData.getHasArmor());
        });

        return player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ChestplateItem())
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem().equals(LeggingsItem())
                && player.getItemBySlot(EquipmentSlot.FEET).getItem().equals(BootsItem())
                && player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() < player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage() - 1
                && hasArmor.get();
    }

    @Override
    protected void armorCheck(Player player, ItemStack pStack) {
        if(!player.getLevel().isClientSide){
            if(playerHasFullSetWithoutData(player)) {
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    ArcReactorSlot arcReactorSlot = player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).resolve().get();
                    if(!armorData.getHasArmor()) {
                        AbstractGen2IronmanArmorItem armorItem = (AbstractGen2IronmanArmorItem) this.ChestplateItem();

                        armorData.compileArmor(arcReactorSlot.getArcReactorEnergy(), armorItem.MaxStableEnergy(), ArmorEnergyType.MAIN, armorItem.MaxEnergyOutput(), armorItem.FlightOverSpeedThreshold(), armorItem.FlightStallSpeed());
                        armorData.setBoot(100);
                    }

                });
            } else if(!playerHasFullSet(player) && !playerHasFullSetWithoutData(player)) {
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    armorData.decompileArmor();
                    armorData.setIsFlying(false);
                    armorData.setFlyMode(FlyMode.NOT_FLYING);
                });

            }
            if(super.playerHasFullSet(player)){
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    ArcReactorSlot arcReactorSlot = player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).resolve().get();
                });
            }
        }
    }

    @Override
    public boolean playerHasFullSet(Player player){
        AtomicBoolean hasArmor = new AtomicBoolean(false);
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            hasArmor.set(armorData.getHasArmor());
        });

        return player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ChestplateItem())
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem().equals(LeggingsItem())
                && player.getItemBySlot(EquipmentSlot.FEET).getItem().equals(BootsItem())
                && player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() < player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage() - 1
                && hasArmor.get();
    }


    // Animation
    @SuppressWarnings("unused")
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {

        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

        Player player = null;
        if(livingEntity.getType() == EntityType.PLAYER){
            player = (Player)livingEntity;
        }

        if (livingEntity instanceof ArmorStand) {
            return PlayState.CONTINUE;
        }

        boolean isWearingAll = playerHasFullSet(player);
        //return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
        return PlayState.CONTINUE;
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
        return true;
    }

    // Flight

    @Override
    public double FlightStallSpeed() {
        return 0;
    }
    @Override
    public double FlightOverSpeedThreshold() {
        return 150;
    }
    @Override
    public double FlightDragCoefficientAtSeaLevel() {
        return 0.02;
    }
    @Override
    public double FlightMaxAccelerationAtSeaLevel() {
        return 2.0;
    }
    @Override
    public double FlightEnergyConsumptionAtMaxThrottle() {
        return 100;
    }
    @Override
    public double FlightBootRequirement() {
        return 100;
    }

    // Hand Actions
    @Override
    public double handBootRequirement() {
        return 100;
    }

    // Weapons
    @Override
    public double WeaponsBootRequirement() {
        return 100;
    }

    @Override
    public void specialKeyAction(Player player, KeyCallType callType) {
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getHasArmor()){
                if(armorData.getBoot() >= 100){
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 2, false, false, false));
                    armorData.increaseArmorEnergyLoad(10000);

                    // Fake player for sounds
                    Player soundPlayer = new Player(player.getLevel(), player.getOnPos(), 0, player.getGameProfile(), null) {
                        @Override
                        public boolean isSpectator() {
                            return false;
                        }

                        @Override
                        public boolean isCreative() {
                            return false;
                        }
                    };
                    soundPlayer.playSound(SoundEvents.PISTON_EXTEND);
                }
            }
        });
    }

}
