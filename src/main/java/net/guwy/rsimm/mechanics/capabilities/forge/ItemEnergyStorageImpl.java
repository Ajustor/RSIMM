package net.guwy.rsimm.mechanics.capabilities.forge;

import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.energy.IEnergyStorage;

// From Simply jetpacks 2 by Tomson124
public class ItemEnergyStorageImpl implements IEnergyStorage {

    public ItemStack stack;
    public IItemEnergyContainer container;

    public ItemEnergyStorageImpl(ItemStack stack, IItemEnergyContainer container) {
        this.stack = stack;
        this.container = container;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (container.getEnergyRecieve() == 0) return 0;
        int energyStored = getEnergy(stack);
        int energyReceived = Math.min(container.getEnergyCapacity() - energyStored, Math.min(container.getEnergyRecieve(), maxReceive));
        if (!simulate) setEnergyStored(stack, energyStored + energyReceived);
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {

        if (container.getEnergyExtract() == 0) return 0;
        int energyStored = Math.max(0, Math.min(container.getEnergyCapacity(), getEnergy(stack)));
        int energyExtracted = Math.min(energyStored, Math.min(container.getEnergyExtract(), maxExtract));
        if (!simulate) setEnergyStored(stack, getEnergy(stack) - energyExtracted);
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return getEnergy(stack);
    }

    @Override
    public int getMaxEnergyStored() {
        return container.getEnergyCapacity();
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    private void setEnergyStored(ItemStack stack, int value) {
        ItemTagUtils.putInt(stack, "energy", value);
    }

    private int getEnergy(ItemStack stack) {
        return Math.max(0, Math.min(Integer.MAX_VALUE, ItemTagUtils.getInt(stack, "energy")));
    }
}
