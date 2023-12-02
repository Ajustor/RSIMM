package net.guwy.rsimm.content.items.armors;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.guwy.rsimm.client.ArmorClientData;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntity;
import net.guwy.rsimm.content.entities.non_living.rocket.RocketEntity;
import net.guwy.rsimm.index.*;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.FlyMode;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.guwy.rsimm.utils.KeyCallType;
import net.guwy.sticky_foundations.index.SFMinerals;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArmorStructureItem extends AbstractIronmanArmorItem implements IAnimatable, ILoopType {
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

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
    public Item AmmoKitItem() {
        return null;
    }

    @Override
    public List<ItemStack> getBrokenLoot() {
        List<ItemStack> list = new ArrayList<>();
        ItemStack itemStack;

        itemStack = new ItemStack(SFMinerals.STEEL_PLATE.get());
        itemStack.setCount(18);
        list.add(itemStack);

        itemStack = new ItemStack(net.guwy.sticky_foundations.index.SFItems.CIRCUITRY_BASIC.get());
        itemStack.setCount(2);
        list.add(itemStack);

        itemStack = new ItemStack(net.guwy.sticky_foundations.index.SFItems.MOTOR.get());
        itemStack.setCount(1);
        list.add(itemStack);

        itemStack = new ItemStack(SFMinerals.COPPER_WIRE.get());
        itemStack.setCount(3);
        list.add(itemStack);

        itemStack = new ItemStack(AllBlocks.SPOUT.get());
        itemStack.setCount(2);
        list.add(itemStack);

        itemStack = new ItemStack(Items.COPPER_INGOT);
        itemStack.setCount(5);
        list.add(itemStack);

        itemStack = new ItemStack(AllItems.IRON_SHEET.get());
        itemStack.setCount(1);
        list.add(itemStack);

        return list;
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
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(stack.getItem().equals(RsImmArmorItems.ARMOR_STRUCTURE_CHESTPLATE.get())){
            chestplateTickEvent(stack, level, player);
        }
        super.onArmorTick(stack, level, player);
    }

    private void chestplateTickEvent(ItemStack pStack, Level pLevel, Player player){
        if(!pLevel.isClientSide){
            if(playerHasFullSet(player)){
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    if(armorData.getBoot() >= 100){
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 0, false, false, false));

                        if(player.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() != 2){
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 1, false, false, false));
                        }
                    }   else {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 9, false, false, false));
                    }
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
    public void flightKeyPressAction(Player player) {
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getHasArmor()){
                if(armorData.getIsFlying()){
                    armorData.setIsFlying(false);
                    armorData.setFlyMode(FlyMode.NOT_FLYING);
                }   else {
                    armorData.setIsFlying(true);
                    armorData.setFlyMode(FlyMode.CUSTOM);
                }
            }
        });
    }

    @Override
    public void flightKeyHoldAction(Player player) {}

    @Override
    public void flyCustomTickServer(Player player) {
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            player.resetFallDistance();
            player.setNoGravity(true);
        });
    }

    @Override
    public void flyCustomTickClient(Player player) {
        player.setDeltaMovement(player.getDeltaMovement().x()
                ,player.getDeltaMovement().y() + (FlightMaxAccelerationAtSeaLevel()/20)
                , player.getDeltaMovement().z());



        //Drag is applied here
        player.setDeltaMovement(player.getDeltaMovement().x() - (player.getDeltaMovement().x() * FlightDragCoefficientAtSeaLevel()),
                player.getDeltaMovement().y() - (player.getDeltaMovement().y() * FlightDragCoefficientAtSeaLevel()),
                player.getDeltaMovement().z() - (player.getDeltaMovement().z() * FlightDragCoefficientAtSeaLevel()));



        // Flight Particles are here
        //double playerRotation = ArmorClientData.getPlayerRotation();
        //Level level = player.getLevel();
        //double particleX;
        //double particleY;
        //double particleZ;
//
        //particleX = player.getX() + (-Math.sin(Math.toRadians(playerRotation + 90)) * 0.2) ;
        //particleY = player.getY();
        //particleZ = player.getZ() + (Math.cos(Math.toRadians(playerRotation + 90)) * 0.2);
        //level.addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ,
        //        (Math.random() - 0.5) * 0.5, (Math.random() - 0.5) * 0.5 - 1, (Math.random() - 0.5) * 0.5);
//
        //particleX = player.getX() + (-Math.sin(Math.toRadians(playerRotation - 90)) * 0.2) ;
        //particleY = player.getY();
        //particleZ = player.getZ() + (Math.cos(Math.toRadians(playerRotation - 90)) * 0.2);
        //level.addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ,
        //        (Math.random() - 0.5) * 0.5, (Math.random() - 0.5) * 0.5 - 1, (Math.random() - 0.5) * 0.5);
    }

    @Override
    public double FlightStallSpeed() {
        return 0;
    }
    @Override
    public double FlightOverSpeedThreshold() {
        return 15;
    }
    @Override
    public double FlightDragCoefficientAtSeaLevel() {
        return 0.002;
    }
    @Override
    public double FlightMaxAccelerationAtSeaLevel() {
        return 2.0;
    }
    @Override
    public double FlightEnergyConsumptionAtMaxThrottle() {
        return 1;
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

    @Override
    public void handKeyHoldAction(Player player) {
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getHasArmor()){
                if(armorData.getArmorStorage(1) > 0){

                    Mark1FlameEntity mark1FlameEntity = new Mark1FlameEntity(RsImmEntityTypes.MARK_1_FLAME.get(), player.getLevel());
                    mark1FlameEntity.setNoGravity(true);
                    mark1FlameEntity.setSilent(true);


                    double posX;
                    double posY;
                    double posZ;
                    double playerRotation = player.getYRot();

                    posX = player.getX() + (-Math.sin(Math.toRadians(playerRotation + 90)) * 0.4) ;
                    posY = player.getY() + 1.2f;
                    posZ = player.getZ() + (Math.cos(Math.toRadians(playerRotation + 90)) * 0.4);

                    mark1FlameEntity.setPos(posX, posY, posZ);


                    double YLook = Math.sin(Math.toRadians(player.getViewXRot(1)));
                    double XLook = Math.sin(Math.toRadians(player.getViewYRot(1)));
                    double ZLook = Math.cos(Math.toRadians(player.getViewYRot(1)));

                    double YSpeed =  (YLook * -1.5 / 3) + ((Math.random() - 0.5) * 0.1);
                    double XSpeed = ((XLook * -1.5)) * (1 - Math.abs(YLook)) + ((Math.random() - 0.5) * 0.1);
                    double ZSpeed = ((ZLook * 1.5)) * (1 - Math.abs(YLook)) + ((Math.random() - 0.5) * 0.1);

                    mark1FlameEntity.setDeltaMovement(XSpeed, YSpeed, ZSpeed);

                    player.getLevel().addFreshEntity(mark1FlameEntity);

                    armorData.decreaseArmorStorage(1, 1);

                    if(player.tickCount % 5 == 0){

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
                        soundPlayer.playSound(RsImmSounds.MARK_1_FLAME_THROWER.get(), 1, 1 + (float)((Math.random() - 0.5) * 0.2));
                    }

                    if(armorData.getHandActTogether()){

                        Mark1FlameEntity entity2 = new Mark1FlameEntity(RsImmEntityTypes.MARK_1_FLAME.get(), player.getLevel());
                        entity2.setNoGravity(true);
                        entity2.setSilent(true);

                        entity2.setPos(player.getX() + (-Math.sin(Math.toRadians(playerRotation - 90)) * 0.4)
                                , player.getY() + 1.2f
                                , player.getZ() + (Math.cos(Math.toRadians(playerRotation - 90)) * 0.4));

                        entity2.setDeltaMovement(XSpeed, YSpeed, ZSpeed);

                        player.getLevel().addFreshEntity(entity2);

                        armorData.decreaseArmorStorage(1, 1);

                        if(player.tickCount % 5 == 2){

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
                            soundPlayer.playSound(RsImmSounds.MARK_1_FLAME_THROWER.get(), 1, 1 + (float)((Math.random() - 0.5) * 0.2));
                        }
                    }


                }   else {
                    armorData.setHandKeyHolding(false);
                }
            }
        });
    }

    @Override
    public void handKeyHoldReleaseAction(Player player) {
    }


    // Weapons
    @Override
    public double WeaponsBootRequirement() {
        return 100;
    }

    @Override
    public boolean FireWeapon1(@Nullable Player player, @Nullable Level level, boolean simulate, KeyCallType fireCall) {
        if(!simulate){
            if(fireCall == KeyCallType.HOLD_RELEASE){
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    if(armorData.getArmorStorage(2) > 0){
                        RocketEntity rocketEntity = new RocketEntity(RsImmEntityTypes.ROCKET.get(), level);
                        rocketEntity.setSilent(true);
                        rocketEntity.setOwner(player);


                        double posX;
                        double posY;
                        double posZ;
                        double playerRotation = player.getYRot();

                        posX = player.getX() + (-Math.sin(Math.toRadians(playerRotation - 90)) * 0.4) ;
                        posY = player.getY() + 1.2f;
                        posZ = player.getZ() + (Math.cos(Math.toRadians(playerRotation - 90)) * 0.4);

                        rocketEntity.setPos(posX, posY, posZ);


                        double YLook = Math.sin(Math.toRadians(player.getViewXRot(1)));
                        double XLook = Math.sin(Math.toRadians(player.getViewYRot(1)));
                        double ZLook = Math.cos(Math.toRadians(player.getViewYRot(1)));

                        double YSpeed =  (YLook * -2.5) + ((Math.random() - 0.5) * 0.3);
                        double XSpeed = ((XLook * -4.5)) * Math.pow((1 - Math.abs(YLook)) * 1, 1) + ((Math.random() - 0.5) * 0.3);
                        double ZSpeed = ((ZLook * 4.5)) * Math.pow((1 - Math.abs(YLook)) * 1, 1) + ((Math.random() - 0.5) * 0.3);

                        rocketEntity.setDeltaMovement(XSpeed, YSpeed, ZSpeed);
                        rocketEntity.setYRot(player.getViewYRot(1));
                        rocketEntity.setXRot(player.getViewXRot(1));
                        rocketEntity.addEffect(new MobEffectInstance(RsImmEffects.ROCKET_PARTICLE_EFFECT.get(), 20, 0, false, false, false), rocketEntity);

                        level.addFreshEntity(rocketEntity);

                        // Fake player for sounds
                        Player soundPlayer = new Player(level, player.getOnPos(), 0, player.getGameProfile(), null) {
                            @Override
                            public boolean isSpectator() {
                                return false;
                            }

                            @Override
                            public boolean isCreative() {
                                return false;
                            }
                        };
                        soundPlayer.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH);

                        armorData.decreaseArmorStorage(2, 1);
                    }
                });

                // Fake player for sounds
                Player soundPlayer = new Player(level, player.getOnPos(), 0, player.getGameProfile(), null) {
                    @Override
                    public boolean isSpectator() {
                        return false;
                    }

                    @Override
                    public boolean isCreative() {
                        return false;
                    }
                };
                soundPlayer.playSound(SoundEvents.IRON_TRAPDOOR_CLOSE);
            }
            if(fireCall == KeyCallType.START_HOLD){
                // Fake player for sounds
                Player soundPlayer = new Player(level, player.getOnPos(), 0, player.getGameProfile(), null) {
                    @Override
                    public boolean isSpectator() {
                        return false;
                    }

                    @Override
                    public boolean isCreative() {
                        return false;
                    }
                };
                soundPlayer.playSound(SoundEvents.IRON_TRAPDOOR_OPEN);
            }
        }
        return true;
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