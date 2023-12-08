package net.guwy.rsimm.content.items.armors;

import net.guwy.rsimm.index.RsImmArmorItems;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.ArmorEnergyType;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.FlyMode;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class Mark2ArmorItem extends AbstractGen2IronmanArmorItem implements IAnimatable {
    public Mark2ArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public Item HelmetItem() {
        return RsImmArmorItems.MARK_2_HELMET.get();
    }

    @Override
    public Item HelmetOpenItem() {
        return null;
    }

    @Override
    public Item ChestplateItem() {
        return RsImmArmorItems.MARK_2_CHESTPLATE.get();
    }

    @Override
    public Item LeggingsItem() {
        return RsImmArmorItems.MARK_2_LEGGINGS.get();
    }

    @Override
    public Item BootsItem() {
        return RsImmArmorItems.MARK_2_BOOTS.get();
    }

    @Override
    public double FlightStallSpeed() {
        return 0;
    }

    @Override
    public double FlightOverSpeedThreshold() {
        return 200;
    }

    @Override
    public double FlightDragCoefficientAtSeaLevel() {
        return 0.2;
    }

    @Override
    public double FlightMaxAccelerationAtSeaLevel() {
        return 3;
    }

    @Override
    public double FlightEnergyConsumptionAtMaxThrottle() {
        return 150;
    }

    @Override
    public double FlightBootRequirement() {
        return 75;
    }

    @Override
    public boolean ShouldBreakWhenNotWearing() {
        return false;
    }

    @Override
    public int FreezingHeight() {
        return 300;
    }

    @Override
    public int FreezingCoefficient() {
        return 1;
    }

    @Override
    public double Heating() {
        return 3;
    }

    @Override
    public long HeatingEnergyCost() {
        return 30;
    }

    @Override
    public double HeatingBootRequirement() {
        return 25;
    }

    @Override
    public double bootRate() {
        return 1;
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
    public int ConstantEnergyDraw() {
        return 30;
    }

    @Override
    public double RunningExtraEnergyDraw() {
        return 3;
    }

    @Override
    public double handBootRequirement() {
        return 50;
    }

    @Override
    public double WeaponsBootRequirement() {
        return 100;
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

    // Gecko Lib Stuff
    @Override
    public void registerControllers(AnimationData data) {

    }

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
