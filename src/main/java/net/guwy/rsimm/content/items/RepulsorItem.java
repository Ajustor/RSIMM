package net.guwy.rsimm.content.items;

import com.mojang.logging.LogUtils;
import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.entities.item.RepulsorItemRenderer;
import net.guwy.rsimm.content.entities.projectiles.RepulsorBeamEntity;
import net.guwy.rsimm.enums.RepulsorAttackType;
import net.guwy.rsimm.index.RsImmEffects;
import net.guwy.rsimm.index.RsImmEntityTypes;
import net.guwy.rsimm.index.RsImmSounds;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class RepulsorItem extends Item implements IAnimatable {
    int blastMaxCharge, blastMaxDamage, blastRange, blastMaxEnergyConsumption, blastKickBack;
    int beamChargeTime, beamMaxDamage, beamRange, beamEnergyConsumptionPerTick;
    int flightMaxThrust, flightMaxEnergyConsumption;
    private boolean isUsable;

    private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();

    String BEAM_CHARGE_TAG = "beam_charge", BLAST_CHARGE_TAG = "blast_charge", STARTED_CHARGING_TAG = "started_charging";

    public RepulsorItem(Properties pProperties, int blastMaxCharge, int blastMaxDamage, int blastRange, int blastMaxEnergyConsumption, int blastKickBack,
                        int beamChargeTime, int beamMaxDamage, int beamRange, int beamEnergyConsumptionPerTick,
                        int flightMaxThrust, int flightMaxEnergyConsumption) {
        super(pProperties);

        this.blastMaxCharge = blastMaxCharge;
        this.blastMaxDamage = blastMaxDamage;
        this.blastRange = blastRange;
        this.blastMaxEnergyConsumption = blastMaxEnergyConsumption;
        this.blastKickBack = blastKickBack;

        this.beamChargeTime = beamChargeTime;
        this.beamMaxDamage = beamMaxDamage;
        this.beamRange = beamRange;
        this.beamEnergyConsumptionPerTick = beamEnergyConsumptionPerTick;

        this.flightMaxThrust = flightMaxThrust;
        this.flightMaxEnergyConsumption = flightMaxEnergyConsumption;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    public RepulsorAttackType getAttackType(ItemStack itemStack, Level level, Player player){
        return RepulsorAttackType.BEAM;

    }

    private void fireRepulsor(Player player, Player soundPlayer) {
        Level level = player.getLevel();
        RepulsorBeamEntity repulsorBeam = new RepulsorBeamEntity(RsImmEntityTypes.REPULSOR_BEAM.get(), player, level, beamRange, beamMaxDamage);

        float speedMul = 3;
        repulsorBeam.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, speedMul, 0);

        level.addFreshEntity(repulsorBeam);
        soundPlayer.playSound(RsImmSounds.REPULSOR_SHOT.get());
    }

    @SubscribeEvent
    public void tryToFire(InputEvent.MouseButton.Pre event) {
        this.LOGGER.info("left click !");
    }

    public void useRepulsor(ItemStack itemStack, Level level, Player player){
        int charge = ItemTagUtils.getInt(itemStack, BEAM_CHARGE_TAG);

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

        // Beam Attack
        if(getAttackType(itemStack, level, player) == RepulsorAttackType.BEAM){
            if(charge >= beamChargeTime){

                this.isUsable = true;

                RepulsorBeamEntity repulsorBeam = new RepulsorBeamEntity(RsImmEntityTypes.REPULSOR_BEAM.get(), player, level, beamRange, beamMaxDamage);

                float speedMul = 3;
                repulsorBeam.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, speedMul, 0);

                level.addFreshEntity(repulsorBeam);
                soundPlayer.playSound(RsImmSounds.REPULSOR_SHOT.get());
            }
            // Increase charge if the charge hasn't reached sufficient level yet
            else {

                if(!ItemTagUtils.getBoolean(itemStack, STARTED_CHARGING_TAG)){

                    // player.playSound(RsImmSounds.REPULSOR_ON.get());

                    soundPlayer.playSound(RsImmSounds.REPULSOR_ON.get());
                    ItemTagUtils.putBoolean(itemStack, STARTED_CHARGING_TAG, true);
                }
                ItemTagUtils.putInt(itemStack, BEAM_CHARGE_TAG, charge + 1);
            }
        }
    }

    public void stopUsingRepulsor(ItemStack itemStack, Level level, Player player){
        ItemTagUtils.putBoolean(itemStack, STARTED_CHARGING_TAG, false);
        ItemTagUtils.putInt(itemStack, BEAM_CHARGE_TAG, 0);
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

        // Blast Attack
        int blastCharge = ItemTagUtils.getInt(itemStack, BLAST_CHARGE_TAG);
        if(blastCharge > 0){
            // soundPlayer.playSound(RsImmSounds.REPULSOR_SHOT.get());

            ItemTagUtils.putInt(itemStack, BLAST_CHARGE_TAG, 0);
        }

        //player.sendSystemMessage(Component.literal("! missing sound"));
        soundPlayer.playSound(RsImmSounds.REPULSOR_OFF.get());
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        boolean playerHasReactorSlot = player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).isPresent();

        if(!playerHasReactorSlot) {
            return;
        }

        ArcReactorSlot arcReactorSlot = player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).resolve().get();
        if (arcReactorSlot.hasArcReactorSlot()) {
            if (!arcReactorSlot.hasArcReactor() || arcReactorSlot.getArcReactorEnergy() <= 0) {
                player.stopUsingItem();

                return;
            }
        }

        if(!player.getLevel().isClientSide){
            useRepulsor(stack, player.getLevel(), (Player) player);
        }

        super.onUsingTick(stack, player, count);

    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 14000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity player, int pTimeCharged) {
        player.stopUsingItem();
        this.isUsable = false;

        /**boolean playerHasReactorSlot = player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).isPresent();

        if(!playerHasReactorSlot) {
            return;
        }
        ArcReactorSlot arcReactorSlot = player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).resolve().get();
        if (arcReactorSlot.hasArcReactorSlot()) {
            this.LOGGER.info("player has arc reactor slot");
            if (!arcReactorSlot.hasArcReactor() || arcReactorSlot.getArcReactorEnergy() <= 0) {
                return;
            }
        }**/

        if(!pLevel.isClientSide){
            stopUsingRepulsor(pStack, pLevel, (Player) player);
        }

        super.releaseUsing(pStack, pLevel, player, pTimeCharged);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack.getItem() != newStack.getItem() || slotChanged;
    }

    /** Animation Controllers */
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    // Empty since there are no animations
    @Override
    public void registerControllers(AnimationData data) {}
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);

        consumer.accept(new IClientItemExtensions() {

            // Add item rendering
            private RepulsorItemRenderer renderer = null;
            // Don't instantiate until ready. This prevents race conditions breaking things
            @Override public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new RepulsorItemRenderer();
                return renderer;
            }
        });
    }
}
