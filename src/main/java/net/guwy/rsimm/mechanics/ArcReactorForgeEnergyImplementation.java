package net.guwy.rsimm.mechanics;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.energy.EnergyStorage;

public abstract class ArcReactorForgeEnergyImplementation extends EnergyStorage {

    public ArcReactorForgeEnergyImplementation(int capacity)
    {
        super(capacity);
    }

    public ArcReactorForgeEnergyImplementation(int capacity, int maxTransfer)
    {
        super(capacity, maxTransfer);
    }

    public ArcReactorForgeEnergyImplementation(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract);
    }

    public ArcReactorForgeEnergyImplementation(int capacity, int maxReceive, int maxExtract, int energy)
    {
        super(capacity, maxReceive, maxExtract, energy);
    }


    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - getEnergyStored(), Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            setEnergy(getEnergyStored() + energyReceived);
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(getEnergyStored(), Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            setEnergy(getEnergyStored() - energyExtracted);
        return energyExtracted;
    }


    /** Override These 2 to work on item tags */
    @Override
    public int getEnergyStored()
    {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public Tag serializeNBT()
    {
        return IntTag.valueOf(0);
    }

    @Override
    public void deserializeNBT(Tag nbt)
    {
    }
}
