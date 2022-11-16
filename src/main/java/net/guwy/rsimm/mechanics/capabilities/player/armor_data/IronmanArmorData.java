package net.guwy.rsimm.mechanics.capabilities.player.armor_data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;

public class IronmanArmorData {
    /** Lots of variables to save **/
    private boolean hasArmor = false;

    public IronmanArmorData() {
    }

    public boolean getHasArmor() {
        return this.hasArmor;
    }

    public void setHasArmor(boolean bool) {
        this.hasArmor = bool;
    }


    /** The part after this point handles the saving and loading of data **/
    public void copyFrom(IronmanArmorData source){
        this.hasArmor = source.hasArmor;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putBoolean("hasArmor", hasArmor);
    }

    public void loadNBTData(CompoundTag nbt){
        hasArmor = nbt.getBoolean("hasArmor");
    }
}
