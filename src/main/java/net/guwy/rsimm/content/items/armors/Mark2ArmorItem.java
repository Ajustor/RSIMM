package net.guwy.rsimm.content.items.armors;

import net.guwy.rsimm.index.ModAmmoKitItems;
import net.guwy.rsimm.index.ModArmorItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;

public class Mark2ArmorItem extends AbstractGen2IronmanArmorItem implements IAnimatable {
    public Mark2ArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public Item HelmetItem() {
        return ModArmorItems.MARK_2_HELMET.get();
    }

    @Override
    public Item HelmetOpenItem() {
        return ModArmorItems.MARK_2_OPEN_HELMET.get();
    }

    @Override
    public Item ChestplateItem() {
        return ModArmorItems.MARK_2_CHESTPLATE.get();
    }

    @Override
    public Item LeggingsItem() {
        return ModArmorItems.MARK_2_LEGGINGS.get();
    }

    @Override
    public Item BootsItem() {
        return ModArmorItems.MARK_2_BOOTS.get();
    }

    @Override
    public Item AmmoKitItem() {
        return ModAmmoKitItems.MARK_2_AMMO_KIT.get();
    }

    @Override
    public List<ItemStack> getBrokenLoot() {
        List<ItemStack> list = new ArrayList<>();
        return list;
    }

    @Override
    public double FlightStallSpeed() {
        return 5;
    }

    @Override
    public double FlightOverSpeedThreshold() {
        return 20;
    }

    @Override
    public double FlightDragCoefficientAtSeaLevel() {
        return 0.02;
    }

    @Override
    public double FlightMaxAccelerationAtSeaLevel() {
        return 0;
    }

    @Override
    public double FlightEnergyConsumptionAtMaxThrottle() {
        return 0;
    }

    @Override
    public double FlightBootRequirement() {
        return 0;
    }

    @Override
    public void flyCustomTickServer(Player player) {
    }

    @Override
    public void flyCustomTickClient(Player player) {
    }

    @Override
    public int FreezingHeight() {
        return 128;
    }

    @Override
    public int FreezingCoefficient() {
        return 0;
    }

    @Override
    public double Heating() {
        return 0;
    }

    @Override
    public long HeatingEnergyCost() {
        return 0;
    }

    @Override
    public double HeatingBootRequirement() {
        return 0;
    }

    @Override
    public double bootRate() {
        return 0.1;
    }

    @Override
    public double OverloadTextDisappearThreshold() {
        return 0.1;
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
        return 10;
    }

    @Override
    public double RunningExtraEnergyDraw() {
        return 0;
    }

    @Override
    public double handBootRequirement() {
        return 0;
    }

    @Override
    public double WeaponsBootRequirement() {
        return 0;
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