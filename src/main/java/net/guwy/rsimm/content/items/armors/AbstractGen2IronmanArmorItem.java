package net.guwy.rsimm.content.items.armors;

import com.mojang.logging.LogUtils;
import net.guwy.rsimm.client.ArcReactorClientData;
import net.guwy.rsimm.client.ArmorClientData;
import net.guwy.rsimm.content.entities.item.RepulsorItemRenderer;
import net.guwy.rsimm.content.entities.projectiles.RepulsorBeamEntity;
import net.guwy.rsimm.content.items.arc_reactors.ArcReactorItem;
import net.guwy.rsimm.content.network_packets.FlightDataC2SPacket;
import net.guwy.rsimm.content.network_packets.FlightDataS2CPacket;
import net.guwy.rsimm.content.network_packets.FreezeDataS2CPacket;
import net.guwy.rsimm.content.network_packets.WeaponKeyBindingC2SPacket;
import net.guwy.rsimm.enums.RepulsorAttackType;
import net.guwy.rsimm.index.*;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.ArmorEnergyType;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.FlyMode;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.guwy.rsimm.utils.KeyCallType;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public abstract class AbstractGen2IronmanArmorItem extends GeoArmorItem implements IronmanArmorInterface {
    public AbstractGen2IronmanArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    protected static final org.slf4j.Logger LOGGER = LogUtils.getLogger();
    protected float flightAccel = 1f;
    private boolean isAlreadyFlying = false;
    protected boolean isUsingRepulsor = false;
    protected boolean isUsingRepulsorTimeout = false;

    public abstract Item HelmetItem();
    public abstract Item HelmetOpenItem();
    public abstract Item ChestplateItem();
    public abstract Item LeggingsItem();
    public abstract Item BootsItem();

    public abstract double FlightStallSpeed();
    public abstract double FlightOverSpeedThreshold();
    public abstract double FlightDragCoefficientAtSeaLevel();
    public abstract double FlightMaxAccelerationAtSeaLevel();
    public abstract double FlightEnergyConsumptionAtMaxThrottle();
    public abstract double FlightBootRequirement();

    /** When True: armor will break when forcefully taken off and drop the "BrokenLoot" list
     *  When False: armor can be taken off and act like any normal armor would
     */
    public boolean ShouldBreakWhenNotWearing() {
        return true;
    };

    /** This list of items will be given when
     *  1) When armor breaks
     *  2) When armor is forcefully taken off and "ShouldBreakWhenNotWearing" = true
     */
    // public abstract List<ItemStack> BrokenLoot();

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {

        if(!level.isClientSide){
            armorCheck(player, stack);
        }

        if(stack.is(RsImmTags.Items.IRONMAN_CHESTPLATES)){
            flightCheck(player);
            temperatureCheck(player);
            bootHandler(player);
            energyHandler(player);
            handKeyHoldActionHandler(player);
            fireproofHandler(player);
            checkUsingRepulsor(player);
        }

        super.onArmorTick(stack, level, player);
    }

    public boolean isLavaProof(){
        return false;
    }
    private void fireproofHandler(Player player) {
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(handler -> {
            if(( !player.isInLava() || isLavaProof() ) && !handler.getHelmetOpen()){
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2, 0, false, false, false));
            }
        });
    }

    //armor Checks
    protected void armorCheck(Player player, ItemStack pStack){
        if(!player.isCreative() && this.ShouldBreakWhenNotWearing()){
            int itemSlot = pStack.getEquipmentSlot().getIndex();
            if(pStack.getItem() == ChestplateItem()){
                if(itemSlot != EquipmentSlot.CHEST.getIndex() || !playerHasFullSet(player)){
                    removeArmor(pStack, player);
                }
            } else if (pStack.getItem() == HelmetItem()) {
                if(itemSlot != EquipmentSlot.HEAD.getIndex() || !playerHasFullSet(player)){
                    removeArmor(pStack, player);
                }
            } else if (pStack.getItem() == HelmetOpenItem()) {
                if(itemSlot != EquipmentSlot.HEAD.getIndex() || !playerHasFullSet(player)){
                    removeArmor(pStack, player);
                }
            } else if (pStack.getItem() == LeggingsItem()) {
                if(itemSlot != EquipmentSlot.LEGS.getIndex() || !playerHasFullSet(player)){
                    removeArmor(pStack, player);
                }
            } else if (pStack.getItem() == BootsItem()) {
                if(itemSlot != EquipmentSlot.FEET.getIndex() || !playerHasFullSet(player)){
                    removeArmor(pStack, player);
                }
            }
        }
    }

    public boolean playerHasFullSet(Player player){
        AtomicBoolean hasArmor = new AtomicBoolean(false);
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            hasArmor.set(armorData.getHasArmor());
        });

        boolean hasHelmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(HelmetItem());

        return hasHelmet && player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ChestplateItem())
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem().equals(LeggingsItem())
                && player.getItemBySlot(EquipmentSlot.FEET).getItem().equals(BootsItem())
                && player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() < player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage() - 1
                && hasArmor.get();
    }

    protected boolean playerHasFullSetWithoutData(Player player) {
        AtomicBoolean hasArmor = new AtomicBoolean(false);
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            hasArmor.set(!armorData.getHasArmor());
        });

        boolean hasHelmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(HelmetItem());

        return hasHelmet && player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ChestplateItem())
                && player.getItemBySlot(EquipmentSlot.LEGS).getItem().equals(LeggingsItem())
                && player.getItemBySlot(EquipmentSlot.FEET).getItem().equals(BootsItem())
                && player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue() < player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage() - 1
                && hasArmor.get();
    }

    protected void removeArmor(ItemStack pStack, Player player){
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
        soundPlayer.playSound(SoundEvents.ITEM_BREAK);
        pStack.setCount(0);

        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            armorData.decompileArmor();
        });
    }

    // Flying system

    public void flightSpeedArmorDamage(Player player){
        ItemStack itemStack;
        itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        itemStack.setDamageValue(itemStack.getDamageValue() + 1);

        itemStack = player.getItemBySlot(EquipmentSlot.LEGS);
        itemStack.setDamageValue(itemStack.getDamageValue() + 1);

        itemStack = player.getItemBySlot(EquipmentSlot.FEET);
        itemStack.setDamageValue(itemStack.getDamageValue() + 1);

        itemStack = player.getItemBySlot(EquipmentSlot.HEAD);
        itemStack.setDamageValue(itemStack.getDamageValue() + 1);
    }

    public void flightSpeedArmorDamageEffects(Player player){
        if(player.tickCount % 20 == 0){
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

            // Armor Break Sound
            soundPlayer.playSound(RsImmSounds.METAL_RATTLE_1.get(), 100,1 + (float)((Math.random() - 0.5) * 0.2) );

            if(Math.random() < 0.3) {
                soundPlayer.playSound(RsImmSounds.METAL_RATTLE_2.get(), 100,1 + (float)((Math.random() - 0.5) * 0.2) );
            }
            if(Math.random() < 0.1) {
                soundPlayer.playSound(RsImmSounds.METAL_RATTLE_3.get(), 100,1 + (float)((Math.random() - 0.5) * 0.2) );
            }
        }
        player.level.addParticle(ParticleTypes.CRIT, player.getX() + (Math.random() - 0.5) * 3, player.getY() + 0.9 + (Math.random() - 0.5) * 3,
                player.getZ() + (Math.random() - 0.5) * 3, 0, 0, 0);
        player.level.addParticle(ParticleTypes.ENCHANTED_HIT, player.getX() + (Math.random() - 0.5) * 3, player.getY() + 0.9 + (Math.random() - 0.5) * 3,
                player.getZ() + (Math.random() - 0.5) * 3, 0, 0, 0);
    }

    public void flyFlyingTickServer(Player player){}
    public void flyFlyingTickClient(Player player){}

    private void stopFlying(Player player) {
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            armorData.setIsFlying(false);
            armorData.setFlyMode(FlyMode.NOT_FLYING);
            player.setForcedPose(null);
            isAlreadyFlying = false;
        });
    }

    public void flyCustomTickServer(Player player) {
        player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactorSlot -> {
            player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                setLookingMotion(player, 1);
                if (player.horizontalCollision) {
                    double vel = player.getDeltaMovement().length();

                    if (player.getLevel().getDifficulty() == Difficulty.HARD) {
                        vel *= 2;
                    } else if (player.getLevel().getDifficulty() == Difficulty.NORMAL) {
                        vel *= 1.5;
                    }
                    if (vel > 2) {
                        player.playSound(vel > 2.5 ? SoundEvents.GENERIC_BIG_FALL : SoundEvents.GENERIC_SMALL_FALL, 1.0F, 1.0F);

                        player.hurt(RsImmDamageSources.FLY_INTO_WALL, (float) vel);
                    }
                    this.stopFlying(player);
                }
            });
        });
    }

    public void flyCustomTickClient(Player player) {
        ArcReactorItem arcReactorItem = ArcReactorClientData.getReactorArcItem(player.getUUID());

        if(arcReactorItem == null) {
            this.stopFlying(player);

            return;
        }

        setLookingMotion(player, 1);
        player.setForcedPose(isAlreadyFlying ? null : Pose.FALL_FLYING);
        isAlreadyFlying = true;
    }

    public void flyHoveringTickServer(Player player) {
        player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactorSlot -> {
            player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                if(arcReactorSlot.getArcReactorEnergy() < this.FlightEnergyConsumptionAtMaxThrottle()) {
                    this.stopFlying(player);
                    return;
                }
                // hover mode - rise vertically (or hover if sneaking and firing)
                double yMotion = KeyboardHelper.isHoldingShift() ? -0.15 + 0.15 * -2 : KeyboardHelper.isHoldingSpace() ? 0.15 + 0.15 * 2 : 0;
                setYMotion(player, yMotion);
            });
        });
    }

    public void flyHoveringTickClient(Player player) {
        double yMotion = KeyboardHelper.isHoldingShift() ? -0.15 + 0.15 * -2 : KeyboardHelper.isHoldingSpace() ? 0.15 + 0.15 * 2 : 0;
        setYMotion(player, yMotion);
    }

    private void setYMotion(Entity entity, double y) {
        Vec3 v = entity.getDeltaMovement();
        v = v.add(0, y - v.y, 0);
        entity.setDeltaMovement(v);
    }

    public void flightKeyPressAction(Player player) {
        player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactorSlot -> {
            if(arcReactorSlot.hasArcReactor() && arcReactorSlot.getArcReactorEnergy() > 0){
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    if(armorData.getIsFlying()){
                        stopFlying(player);
                        player.setNoGravity(false);
                    } else {
                        armorData.setIsFlying(true);

                        armorData.setFlyMode(FlyMode.HOVERING);
                    }
                });

            } else {
                stopFlying(player);
            }
        });
    }

    public void flightKeyHoldAction(Player player) {
        player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactorSlot -> {
            if(arcReactorSlot.hasArcReactor() && arcReactorSlot.getArcReactorEnergy() > 0){
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    armorData.setIsFlying(true);
                    player.resetFallDistance();
                    if(armorData.getFlyMode() != FlyMode.CUSTOM){
                        armorData.setFlyMode(FlyMode.CUSTOM);
                    } else {
                        armorData.setFlyMode(FlyMode.HOVERING);
                        player.setForcedPose(null);
                    }
                });
                arcReactorSlot.removeArcReactorEnergy((long) FlightEnergyConsumptionAtMaxThrottle());
            } else {
                stopFlying(player);
            }
        });
    }

    private void flightCheck(Player player){
        if(!player.getLevel().isClientSide){
            player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                if(armorData.getHasArmor()) {
                    if (armorData.getIsFlying()) {
                        if(armorData.getBoot() < FlightBootRequirement()){
                            this.stopFlying(player);
                        }

                        switch (armorData.getFlyMode()) {
                            case HOVERING -> flyHoveringTickServer(player);
                            case FLYING -> flyFlyingTickServer(player);
                            case CUSTOM -> flyCustomTickServer(player);
                            case NOT_FLYING -> armorData.setIsFlying(false);
                        }

                        RsImmNetworking.sendToPlayer(new FlightDataS2CPacket(player.getVisualRotationYInDegrees(),
                                true, armorData.getFlyMode(), flightAccel), (ServerPlayer) player);

                        if(armorData.getMoveSpeed() * 20 > FlightOverSpeedThreshold()){
                            flightSpeedArmorDamage(player);
                            flightSpeedArmorDamageEffects(player);
                        }
                    } else {
                        flightAccel = 1f;
                        RsImmNetworking.sendToPlayer(new FlightDataS2CPacket(0,
                                false, FlyMode.NOT_FLYING, flightAccel), (ServerPlayer) player);
                        armorData.setMoveSpeed(0);
                    }
                }
            });
        }
        if(player.getLevel().isClientSide){
            if(ArmorClientData.isIsFlying()){
                switch (ArmorClientData.getFlyMode()){
                    case FLYING -> flyFlyingTickClient(player);
                    case HOVERING -> flyHoveringTickClient(player);
                    case CUSTOM -> flyCustomTickClient(player);
                }
                double totalSpeed = Math.sqrt((Math.pow(player.getDeltaMovement().x, 2)) + Math.pow(player.getDeltaMovement().z, 2)
                        + Math.pow(player.getDeltaMovement().y, 2));

                RsImmNetworking.sendToServer(new FlightDataC2SPacket(totalSpeed));

                if(totalSpeed * 20 > FlightOverSpeedThreshold()){
                    flightSpeedArmorDamageEffects(player);
                }
            }
        }
    }

    private void setLookingMotion(Player player, long arcReactorEnergy) {
        Vec3 lookVec = player.getLookAngle().scale(0.3 * FlightMaxAccelerationAtSeaLevel());
        updateAccel(lookVec);
        lookVec = getEffectiveMotion(lookVec, player.isShiftKeyDown() ? 1 : player.isSprinting() ? 5 : 2.5);
        player.setDeltaMovement(lookVec.x, player.isOnGround() ? 0 : lookVec.y, lookVec.z);
    }

    public Vec3 getEffectiveMotion(Vec3 lookVec, double arcReactorEnergy) {
        return lookVec.scale(flightAccel * arcReactorEnergy);
    }

    public void updateAccel(Vec3 lookVec) {
        float div = lookVec.y > 0 ? -64f : -16f;
        flightAccel = Mth.clamp(flightAccel + (float)lookVec.y / div, 0.8F, 4.2F);
    }

    //Freezing Controller
    public abstract int FreezingHeight();
    public abstract int FreezingCoefficient();
    public abstract double Heating();
    public abstract long HeatingEnergyCost();
    public abstract double HeatingBootRequirement();
    public boolean CanOperateInHotEnvironments(){
        return false;
    }
    public boolean Fireproof(){
        return true;
    }
    public boolean LavaProof(){
        return false;
    }

    private void temperatureCheck(Player player) {
        Level level = player.getLevel();

        if(!level.isClientSide){
            player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                if(armorData.getHasArmor()){

                    // Heating
                    armorData.decreaseArmorFreezing(0.1); //Passive Heating
                    if(armorData.getBoot() >= HeatingBootRequirement()){     // Active Heating
                        if(armorData.getArmorFreezing() > 20){
                            armorData.decreaseArmorFreezing(Heating());
                            armorData.increaseArmorEnergyLoad(HeatingEnergyCost());
                        }
                    }

                    // Freezing from height
                    if(player.getOnPos().getY() > FreezingHeight()){
                        double freeze = (player.getOnPos().getY() - FreezingHeight()) * 0.025 * FreezingCoefficient();
                        armorData.increaseArmorFreezing(freeze);
                    }

                    // Freezing From Biome
                    if(level.getBiome(player.getOnPos()).get().getBaseTemperature() < 0.2f){
                        double freeze = Math.abs(level.getBiome(player.getOnPos()).get().getBaseTemperature() * 2 - 5) * 0.025 * FreezingCoefficient();
                        armorData.increaseArmorFreezing(freeze);
                    }

                    if(armorData.getArmorFreezing() >= 100 && armorData.getBoot() >= 1){
                        armorData.decreaseBoot(1);
                    }

                    if(level.dimension().equals(Level.NETHER) && !CanOperateInHotEnvironments()){
                        armorData.decreaseBoot(1);
                    }

                    for(double i = 0; i <= armorData.getArmorFreezing() / 10; i++){
                        level.addParticle(ParticleTypes.WHITE_ASH,
                                player.getX() + (Math.random() - 0.5) * 2,
                                player.getY() + (Math.random() - 0.5) * 2,
                                player.getZ() + (Math.random() - 0.5) * 2,
                                0, 0, 0);
                    }

                    RsImmNetworking.sendToPlayer(new FreezeDataS2CPacket(armorData.getArmorFreezing()), (ServerPlayer) player);

                }
            });
        }

        if(level.isClientSide){
            for(double i = 0; i < ArmorClientData.getArmorFreezing() / 10; i++){
                level.addParticle(ParticleTypes.SNOWFLAKE,
                        player.getX() + (Math.random() - 0.5) * 2,
                        player.getY() + 1 + (Math.random() - 0.5) * 2,
                        player.getZ() + (Math.random() - 0.5) * 2,
                        0, 0, 0);
            }
        }

        if(Fireproof()){
            player.clearFire();
        }
        if(LavaProof()){
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2, 0, false, false, false));
        }
    }

    //Boot Controller
    public abstract double bootRate();
    private void bootHandler(Player player){
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getHasArmor()){
                if(armorData.getBoot() < 100){
                    armorData.increaseBoot(bootRate());

                    if(armorData.getBoot() > OverloadTextDisappearThreshold() && armorData.isArmorEnergyOverload()){
                        armorData.setArmorEnergyOverload(false);
                    }
                }

                ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
                CompoundTag tag;
                if(itemStack.getTag() != null){
                    tag = itemStack.getTag();
                }   else {
                    tag = new CompoundTag();
                }
                tag.putDouble("boot", armorData.getBoot());
            }
        });
    }

    //Energy Handler
    public abstract double OverloadTextDisappearThreshold();
    public abstract long MaxStableEnergy();
    public abstract long MaxEnergyOutput();
    public abstract int ConstantEnergyDraw();
    public abstract double RunningExtraEnergyDraw();

    private void energyHandler(Player player){
        Level level = player.getLevel();
        if(!level.isClientSide){
            runningExtraEnergyDrawHandler(player);
            energyDrawHandler(player);
        }
    }

    private void runningExtraEnergyDrawHandler(Player player){
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            armorData.increaseArmorEnergyLoad((long) (ConstantEnergyDraw() * RunningExtraEnergyDraw()));
        });
    }
    private void energyDrawHandler(Player player){
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {

                /** For armors that have REALLY LOW energy source **/
                if(armorData.getArmorEnergyType().equals(ArmorEnergyType.EMERGENCY)){

                    if(arcReactor.hasArcReactor()){
                        if(arcReactor.getArcReactorEnergy() > arcReactor.getArcReactorEnergyCapacity() / 50){
                            //Draw from arc reactor if it's not in a critical state
                            long outputAbleEnergy = Math.min(arcReactor.getArcReactorEnergy(), arcReactor.getArcReactorEnergyOutput());
                            long outputtedEnergy = Math.min(outputAbleEnergy, armorData.getArmorEnergyLoad());
                            if(arcReactor.simulateAddEnergyLoad(outputtedEnergy)){
                                arcReactor.addEnergyLoad(outputtedEnergy);
                                armorData.decreaseArmorEnergyLoad(outputtedEnergy);
                            }
                        } else {
                            //Draw from armor if arc reactor is critical
                            long outputAbleEnergy = Math.min(armorData.getArmorEnergy(), armorData.getArmorEnergyOutput());
                            armorData.decreaseArmorEnergy(armorData.getArmorEnergyLoad());
                            armorData.decreaseArmorEnergyLoad(outputAbleEnergy);

                            //Draw from arc reactor if armor is insufficient
                            if(arcReactor.hasArcReactor()){
                                outputAbleEnergy = Math.min(arcReactor.getArcReactorEnergy(), arcReactor.getArcReactorEnergyOutput());
                                long outputtedEnergy = Math.min(outputAbleEnergy, armorData.getArmorEnergyLoad());
                                if(arcReactor.simulateAddEnergyLoad(outputtedEnergy)){
                                    arcReactor.addEnergyLoad(outputtedEnergy);
                                    armorData.decreaseArmorEnergyLoad(outputtedEnergy);
                                }
                            }
                        }
                    }
                }

                /** For armors that have OK energy source **/
                if(armorData.getArmorEnergyType().equals(ArmorEnergyType.BACKUP)){
                    if(arcReactor.hasArcReactor()){
                        if(arcReactor.getArcReactorEnergy() > arcReactor.getArcReactorEnergyCapacity() / 20){
                            //Draw from arc reactor if it's not too low
                            long outputAbleEnergy = Math.min(arcReactor.getArcReactorEnergy(), arcReactor.getArcReactorEnergyOutput());
                            long outputtedEnergy = Math.min(outputAbleEnergy, armorData.getArmorEnergyLoad());
                            if(arcReactor.simulateAddEnergyLoad(outputtedEnergy)){
                                arcReactor.addEnergyLoad(outputtedEnergy);
                                armorData.decreaseArmorEnergyLoad(outputtedEnergy);
                            }
                        }
                    }
                    //Draw from armor
                    long outputAbleEnergy = Math.min(armorData.getArmorEnergy(), armorData.getArmorEnergyOutput());
                    armorData.decreaseArmorEnergy(armorData.getArmorEnergyLoad());
                    armorData.decreaseArmorEnergyLoad(outputAbleEnergy);

                    //Draw from arc reactor if armor is not sufficient
                    if(arcReactor.hasArcReactor()){
                        outputAbleEnergy = Math.min(arcReactor.getArcReactorEnergy(), arcReactor.getArcReactorEnergyOutput());
                        long outputtedEnergy = Math.min(outputAbleEnergy, armorData.getArmorEnergyLoad());
                        if(arcReactor.simulateAddEnergyLoad(outputtedEnergy)){
                            arcReactor.addEnergyLoad(outputtedEnergy);
                            armorData.decreaseArmorEnergyLoad(outputtedEnergy);
                        }
                    }
                }

                /** For armors that have a GOOD energy source **/
                if(armorData.getArmorEnergyType().equals(ArmorEnergyType.MAIN)){

                    long outputAbleEnergy = Math.min(armorData.getArmorEnergy(), armorData.getArmorEnergyOutput());
                    armorData.decreaseArmorEnergy(armorData.getArmorEnergyLoad());
                    armorData.decreaseArmorEnergyLoad(outputAbleEnergy);

                    if(arcReactor.hasArcReactor()){
                        if(arcReactor.getArcReactorEnergy() > arcReactor.getArcReactorEnergyCapacity() / 20){

                            outputAbleEnergy = Math.min(arcReactor.getArcReactorEnergy(), arcReactor.getArcReactorEnergyOutput());
                            long outputtedEnergy = Math.min(outputAbleEnergy, armorData.getArmorEnergyLoad());
                            if(arcReactor.simulateAddEnergyLoad(outputtedEnergy)){
                                arcReactor.addEnergyLoad(outputtedEnergy);
                                armorData.decreaseArmorEnergyLoad(outputtedEnergy);
                            }
                        }
                    }
                }

                // Overload Handler
                if(armorData.getArmorEnergyLoad() > 0){
                    armorData.setBoot(0);
                    armorData.setArmorEnergyOverload(true);
                }

                // Constant Energy Drain Handler
                armorData.increaseArmorEnergyLoad(ConstantEnergyDraw());
            });
        });
    }

    // Hand Action Controller

    public abstract double handBootRequirement();

    public void handKeyPressAction(Player player){
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getHasArmor()){
                armorData.setHandActTogether(!armorData.getHandActTogether());

                // Fake player for sounds
                Repulsor.fireRepulsor(player);
            }
        });
    }

    public void handKeyHoldAction(Player player){
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getHasArmor()){
                if(!isUsingRepulsorTimeout) {
                    isUsingRepulsorTimeout = true;
                    isUsingRepulsor = !isUsingRepulsor;
                }
            }
        });
    }

    public void handKeyHoldReleaseAction(Player player){
        isUsingRepulsorTimeout = false;
    }

    public void checkUsingRepulsor(Player player) {
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getHasArmor()){
                if(isUsingRepulsor) {
                    Repulsor.useRepulsor(player);
                } else {
                    Repulsor.stopUsingRepulsor(player);
                }
            }
        });
    }

    public void handKeyHoldActionHandler(Player player){
        if(!player.getLevel().isClientSide){
            player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                if(armorData.getHasArmor()){
                    if(armorData.isHandKeyHolding()){
                        if(armorData.getBoot() >= handBootRequirement()){
                            handKeyHoldAction(player);
                            armorData.setHandKeyHolding(true);
                        } else {
                            armorData.setHandKeyHolding(false);
                        }
                    } else {
                        handKeyHoldReleaseAction(player);
                    }
                }
            });
        }
    }

    public abstract double WeaponsBootRequirement();

    public void weaponFireKeyAction(Player player, KeyCallType callType){
        LOGGER.info("fire weapon"+callType);
        player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
            if(armorData.getBoot() >= WeaponsBootRequirement()){
                Repulsor.fireRepulsor(player);
            }
        });
    }

    public void specialKeyAction(Player player, KeyCallType callType){

    }

    public void armorKeyPressAction(Player player) {

    }

    public void armorKeyHoldAction(Player player) {

    }

    protected static class Repulsor {
        static final int blastMaxCharge = 1;
        static final int blastMaxDamage = 1;
        static final int blastRange = 1;
        static final int blastMaxEnergyConsumption = 10;
        static final int blastKickBack = 1;
        public static int charge = 0;
        public static boolean isCharging = false;

        public static RepulsorAttackType getAttackType(ItemStack itemStack, Level level, Player player){
            return RepulsorAttackType.BEAM;
        }

        public static void fireRepulsor(Player player) {
            if(charge != 100) {
                return;
            }
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
            Level level = player.getLevel();
            RepulsorBeamEntity repulsorBeam = new RepulsorBeamEntity(RsImmEntityTypes.REPULSOR_BEAM.get(), player, level, blastRange, blastMaxDamage);

            float speedMul = 3;
            repulsorBeam.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, speedMul, 0);

            level.addFreshEntity(repulsorBeam);
            soundPlayer.playSound(RsImmSounds.REPULSOR_SHOT.get());
        }

        public static void useRepulsor(Player player){

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

            if(!isCharging){
                soundPlayer.playSound(RsImmSounds.REPULSOR_ON.get());
                isCharging=true;
            }

            if(isCharging && charge != 100) {
                charge += 10;
            }
        }

        public static void stopUsingRepulsor(Player player){
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
            if(charge == 100 || isCharging) {
                soundPlayer.playSound(RsImmSounds.REPULSOR_OFF.get());
                charge = 0;
                isCharging = false;
            }

        }

        public static int getUseDuration(ItemStack pStack) {
            return 14000;
        }

    }
}
