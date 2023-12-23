package net.guwy.rsimm.mechanics.capabilities.forge;

import net.minecraft.world.item.ItemStack;

// From Simply jetpacks 2 by Tomson124
public interface IItemExtendedEnergyContainer {

    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    int getEnergy(ItemStack container);

    int getCapacity(ItemStack container);
}