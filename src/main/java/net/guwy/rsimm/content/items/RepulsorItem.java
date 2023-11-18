package net.guwy.rsimm.content.items;

import net.guwy.rsimm.content.entities.item.RepulsorItemRenderer;
import net.guwy.rsimm.enums.RepulsorAttackType;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class RepulsorItem extends AbstractRepulsorItem implements IAnimatable {

    public RepulsorItem(Properties pProperties, int blastMaxCharge, int blastMaxDamage, int blastRange, int blastMaxEnergyConsumption, int blastKickBack, int beamChargeTime, int beamMaxDamage, int beamRange, int beamEnergyConsumptionPerTick, int flightMaxThrust, int flightMaxEnergyConsumption, int overheatTemp, int blastHeat, int beamHeat, int flightMaxThrustHeat, double coolingMultiplier, int energyBuffer) {
        super(pProperties, blastMaxCharge, blastMaxDamage, blastRange, blastMaxEnergyConsumption, blastKickBack, beamChargeTime, beamMaxDamage, beamRange, beamEnergyConsumptionPerTick, flightMaxThrust, flightMaxEnergyConsumption, overheatTemp, blastHeat, beamHeat, flightMaxThrustHeat, coolingMultiplier, energyBuffer);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        if(!player.getLevel().isClientSide){
            useRepulsor(stack, player.getLevel(), (Player) player, player.isCrouching() ? RepulsorAttackType.BLAST : RepulsorAttackType.BEAM);
        }

        super.onUsingTick(stack, player, count);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        pLivingEntity.stopUsingItem();

        if(!pLevel.isClientSide){
            stopUsingRepulsor(pStack, pLevel, (Player) pLivingEntity);
        }

        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 14000;
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
