package net.guwy.rsimm.content.items.armors;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class Mark2ArmorItem extends AbstractIronmanArmorItem{
    public Mark2ArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
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
        return null;
    }

    @Override
    public Item LeggingsItem() {
        return null;
    }

    @Override
    public Item BootsItem() {
        return null;
    }

    @Override
    public Item AmmoKitItem() {
        return null;
    }

    @Override
    public List<ItemStack> getBrokenLoot() {
        return null;
    }

    @Override
    public double MinimumFlightSpeed() {
        return 0;
    }

    @Override
    public double MaximumFlightSpeed() {
        return 0;
    }

    @Override
    public double FlightDragCoefficient() {
        return 0;
    }

    @Override
    public double FlightMaxAcceleration() {
        return 0;
    }

    @Override
    public double FlightAccelerationEfficinecy() {
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
        return 0;
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
        return 0;
    }

    @Override
    public double OverloadTextDisappearThreshold() {
        return 0;
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
        return 0;
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
}
