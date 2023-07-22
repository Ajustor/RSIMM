package net.guwy.rsimm.content.items.armors;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class FlightTestSuitArmorItem extends AbstractGen2IronmanArmorItem implements IAnimatable, ILoopType {
    public FlightTestSuitArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public boolean ShouldBreakWhenNotWearing() {
        return false;
    }

    @Override
    public List<ItemStack> BrokenLoot() {
        return null;
    }

    // @Override
    // public Item HelmetItem() {
    //     return null;
    // }
//
    // @Override
    // public Item HelmetOpenItem() {
    //     return null;
    // }
//
    // @Override
    // public Item ChestplateItem() {
    //     return null;
    // }
//
    // @Override
    // public Item LeggingsItem() {
    //     return null;
    // }
//
    // @Override
    // public Item BootsItem() {
    //     return null;
    // }
//
    // @Override
    // public Item AmmoKitItem() {
    //     return null;
    // }
//
    // @Override
    // public List<ItemStack> getBrokenLoot() {
    //     return null;
    // }
//
    // @Override
    // public double FlightStallSpeed() {
    //     return 0;
    // }
//
    // @Override
    // public double FlightOverSpeedThreshold() {
    //     return 0;
    // }
//
    // @Override
    // public double FlightDragCoefficientAtSeaLevel() {
    //     return 0;
    // }
//
    // @Override
    // public double FlightMaxAccelerationAtSeaLevel() {
    //     return 0;
    // }
//
    // @Override
    // public double FlightEnergyConsumptionAtMaxThrottle() {
    //     return 0;
    // }
//
    // @Override
    // public double FlightBootRequirement() {
    //     return 0;
    // }
//
    // @Override
    // public void flyCustomTickServer(Player player) {
//
    // }
//
    // @Override
    // public void flyCustomTickClient(Player player) {
//
    // }
//
    // @Override
    // public int FreezingHeight() {
    //     return 0;
    // }
//
    // @Override
    // public int FreezingCoefficient() {
    //     return 0;
    // }
//
    // @Override
    // public double Heating() {
    //     return 0;
    // }
//
    // @Override
    // public long HeatingEnergyCost() {
    //     return 0;
    // }
//
    // @Override
    // public double HeatingBootRequirement() {
    //     return 0;
    // }
//
    // @Override
    // public double bootRate() {
    //     return 0;
    // }
//
    // @Override
    // public double OverloadTextDisappearThreshold() {
    //     return 0;
    // }
//
    // @Override
    // public long MaxStableEnergy() {
    //     return 0;
    // }
//
    // @Override
    // public long MaxEnergyOutput() {
    //     return 0;
    // }
//
    // @Override
    // public int ConstantEnergyDraw() {
    //     return 0;
    // }
//
    // @Override
    // public double RunningExtraEnergyDraw() {
    //     return 0;
    // }
//
    // @Override
    // public double handBootRequirement() {
    //     return 0;
    // }
//
    // @Override
    // public double WeaponsBootRequirement() {
    //     return 0;
    // }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return null;
    }

    @Override
    public boolean isRepeatingAfterEnd() {
        return false;
    }
}
