package net.guwy.rsimm.content.items.arc_reactors;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ArcReactorItem extends AbstractArcReactorItem{
    String displayName;
    long maxEnergy;
    long energyOutput;
    int idleDrain;
    int poisonFactor;
    Item depletedItem;

    public ArcReactorItem(Properties pProperties, String displayName, long maxEnergy, long energyOutput, int idleDrain, int poisonFactor, Item depletedItem) {
        super(pProperties);
        this.displayName = displayName;
        this.maxEnergy = maxEnergy;
        this.energyOutput = energyOutput;
        this.idleDrain = idleDrain;
        this.poisonFactor = poisonFactor;
        this.depletedItem = depletedItem;
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
}
