package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.mechanics.IItemEnergyContainer;
import net.guwy.rsimm.mechanics.ItemEnergyStorageImpl;
import net.guwy.rsimm.mechanics.ModEnergyStorage;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class ArcReactorItem extends AbstractArcReactorItem implements IItemEnergyContainer {
    String displayName;
    long maxEnergy;
    long energyOutput;
    int idleDrain;
    int poisonFactor;
    Item depletedItem;
    ResourceLocation overlayTexture;

    public ArcReactorItem(Properties pProperties, String displayName, long maxEnergy, long energyOutput, int idleDrain, int poisonFactor, @Nullable Item depletedItem, @Nullable ResourceLocation overlayTexture) {
        super(pProperties);
        this.displayName = displayName;
        this.maxEnergy = maxEnergy;
        this.energyOutput = energyOutput;
        this.idleDrain = idleDrain;
        this.poisonFactor = poisonFactor;
        this.depletedItem = depletedItem;
        this.overlayTexture = overlayTexture;
    }

    @Override
    public @Nullable ResourceLocation OverlayIcon() {
        return overlayTexture;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public long maxEnergy() {
        return maxEnergy;
    }

    @Override
    public long energyOutput() {
        return energyOutput;
    }

    @Override
    public int idleDrain() {
        return idleDrain;
    }

    @Override
    public int poisonFactor() {
        return poisonFactor;
    }

    @Override
    public Item depletedItem() {
        return depletedItem;
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        CompoundTag nbtTag = new CompoundTag();
        nbtTag.putLong("energy", maxEnergy);
        pStack.setTag(nbtTag);
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }



    /** The part that makes the arc reactor act like a battery,
     * Made a reality by the stolen code from yours truly Tomson124, the creator of Simply jetpack 2.
     * Seriously this I've spent like months figuring out how to do this myself and didn't manage to figure out anything.
     * I'm too dumb to do anything myself :P*/
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        IItemEnergyContainer container = this;
        return new ICapabilityProvider() {
            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                if (cap == ForgeCapabilities.ENERGY)
                    return LazyOptional.of(() -> new ItemEnergyStorageImpl(stack, container)).cast();
                return LazyOptional.empty();
            }
        };
    }



    // public int getEnergyReceive() {
    //     return 500;
    // }

    public int getEnergyExtract() {
        return (int) Math.max(0, Math.min(Integer.MAX_VALUE, energyOutput));
    }

    public int getEnergyCapacity(){
        return (int) Math.max(0, Math.min(Integer.MAX_VALUE, maxEnergy));
    }

    private void setEnergyStored(ItemStack stack, long value) {
        ItemTagUtils.putLong(stack, "energy", value);
    }

    /** Use this for getting the energy */
    public long getEnergyStored(ItemStack stack) {
        return ItemTagUtils.getLong(stack, "energy");
    }

    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        return 0;
        // if (getEnergyReceive() == 0) return 0;
        // int energyStored = getEnergy(stack);
        // int energyReceived = Math.min(getCapacity(stack) - energyStored, Math.min(getEnergyReceive(), maxReceive));
        // if (!simulate) setEnergyStored(stack, energyStored + energyReceived);
        // return energyReceived;
    }

    // Modified to handle long variables as well
    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        if (getEnergyExtract() == 0) return 0;
        int energyStored = (int) Math.max(0, Math.min(getEnergyCapacity(), getEnergyStored(stack)));
        int energyExtracted = Math.min(energyStored, Math.min(getEnergyExtract(), maxExtract));
        if (!simulate) setEnergyStored(stack, getEnergyStored(stack) - energyExtracted);
        return energyExtracted;
    }

    /** Don't use this as it doesn't support long variables. This is for reporting back to the forge energy capability,
     * use getEnergyStored() instead */
    @Override
    public int getEnergy(ItemStack stack) {
        return (int) Math.max(0, Math.min(Integer.MAX_VALUE, ItemTagUtils.getLong(stack, "energy")));
    }

    @Override
    public int getCapacity(ItemStack container) {
        return getEnergyCapacity();
    }
}
