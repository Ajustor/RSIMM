package net.guwy.rsimm.mechanics;

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
        return container.receiveEnergy(stack, maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return container.extractEnergy(stack, maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return container.getEnergy(stack);
    }

    @Override
    public int getMaxEnergyStored() {
        return container.getCapacity(stack);
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
