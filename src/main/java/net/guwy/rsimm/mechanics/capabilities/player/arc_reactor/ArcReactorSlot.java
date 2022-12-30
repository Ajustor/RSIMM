package net.guwy.rsimm.mechanics.capabilities.player.arc_reactor;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;

public class ArcReactorSlot {
    /** Lots of variables to save **/
    private boolean hasSlot = false;
    private boolean hasReactor = false;
    private String reactorTypeName = "";      //Display Name
    private int reactorTypeId = 0;
    private long reactorEnergyCapacity = 0;
    private long reactorEnergy = 0;
    private long reactorEnergyOutput = 0;       //Max Energy Output
    private long reactorLoad = 0;       //Current Drawn energy
    private int reactorIdleDrain = 0;
    private int reactorPoisonFactor = 0;
    private int playerReactorPoisoning = 0;

    private static final int MAX_PLAYER_POISON = 840000;       //Gives you 50 minecraft days if the poison factor is 14 (175 for 4)

    public ArcReactorSlot() {
    }


    /**    Used for easily saving and deleting arc reactor data in bulk     *
     *                                                                      *
     *     !WARNING! This action will not affect the item you wish to use   *
     *     make sure to delete the item in your hand after saving           *
     *     !FOR DEV! Make sure to manually give the arc reactor back        *
     *                      before deleting any data                        *
     */
    public void setArcReactor(String reactorTypeName, int reactorTypeId, long reactorEnergyCapacity,
                              long reactorEnergy, long reactorEnergyOutput, int reactorIdleDrain, int reactorPoisonFactor){
        this.hasReactor = true;
        this.reactorTypeName = reactorTypeName;
        this.reactorTypeId = reactorTypeId;
        this.reactorEnergyCapacity = reactorEnergyCapacity;
        this.reactorEnergy = reactorEnergy;
        this.reactorEnergyOutput = reactorEnergyOutput;
        this.reactorIdleDrain = reactorIdleDrain;
        this.reactorPoisonFactor = reactorPoisonFactor;
    }

    public void deleteArcReactor(){
        this.hasReactor = false;
        this.reactorTypeName = "";
        this.reactorTypeId = 0;
        this.reactorEnergyCapacity = 0;
        this.reactorEnergy = 0;
        this.reactorEnergyOutput = 0;
        this.reactorLoad = 0;
        this.reactorIdleDrain = 0;
        this.reactorPoisonFactor = 0;
    }



    /** This part with lots of messy code is for accessing and setting variables outside this method **/
    //hasSlot
    public boolean hasArcReactorSlot() {
        return this.hasSlot;
    }

    public void setHasArcReactorSlot(boolean bool) {
        this.hasSlot = bool;
    }


    //hasReactor
    public boolean hasArcReactor() {
        return this.hasReactor;
    }

    public void setHasArcReactor(boolean bool) {
        this.hasReactor = bool;
    }


    //reactorTypeName
    public String getArcReactorTypeName() {
        return reactorTypeName;
    }

    public void setArcReactorTypeName(String typeName) {
        this.reactorTypeName = typeName;
    }


    //reactorTypeId
    public int getArcReactorTypeId() {
        return reactorTypeId;
    }

    public void setArcReactorType(Item type) {
        this.reactorTypeId = Item.getId(type);
    }


    //reactorEnergyCapacity
    public long getArcReactorEnergyCapacity() {
        return reactorEnergyCapacity;
    }

    public void setArcReactorEnergyCapacity(long energyCapacity) {
        this.reactorEnergyCapacity = energyCapacity;
    }


    //reactorEnergy
    public long getArcReactorEnergy() {
        return reactorEnergy;
    }

    public void setArcReactorEnergy(long energy) {
        this.reactorEnergy = Math.min(energy, this.reactorEnergyCapacity);
    }

    public void addArcReactorEnergy(long add) {
        this.reactorEnergy = Math.min(this.reactorEnergy + add, this.reactorEnergyCapacity);
    }

    public void removeArcReactorEnergy(long remove) {
        this.reactorEnergy = Math.max(0, this.reactorEnergy - Math.min(this.reactorEnergyOutput, remove));
    }


    //reactorEnergyOutput
    public long getArcReactorEnergyOutput() {
        return reactorEnergyOutput;
    }

    public void setArcReactorEnergyOutput(long output) {
        this.reactorEnergyOutput = output;
    }

    public long getEnergyLoad() {
        return reactorLoad;
    }

    public void setEnergyLoad(long load) {
        this.reactorLoad = load;
    }

    public void addEnergyLoad(long add){
        this.reactorLoad = this.reactorLoad + add;
    }

    public boolean simulateAddEnergyLoad(long add){
        return this.reactorLoad + add <= this.reactorEnergy && this.reactorLoad + add <= this.reactorEnergyOutput;
    }


    //reactorIdleDrain
    public int getArcReactorIdleDrain() {
        return this.reactorIdleDrain;
    }

    public void setArcReactorIdleDrain(int reactorIdleDrain) {
        this.reactorIdleDrain = reactorIdleDrain;
    }


    //reactorPoisonFactor
    public int getArcReactorPoisonFactor() {
        return this.reactorPoisonFactor;
    }

    public void setArcReactorPoisonFactor(int reactorPoisonFactor) {
        this.reactorPoisonFactor = reactorPoisonFactor;
    }


    //playerReactorPoisoning
    public int getPlayerArcReactorPoisoning() {
        return this.playerReactorPoisoning;
    }

    public void setPlayerArcReactorPoisoning(int playerReactorPoisoning) {
        this.playerReactorPoisoning = playerReactorPoisoning;
    }

    public void addPlayerArcReactorPoisoning(int add) {
        this.playerReactorPoisoning = Math.min(this.playerReactorPoisoning + add, MAX_PLAYER_POISON);
    }

    public void removePlayerArcReactorPoisoning(int remove) {
        this.playerReactorPoisoning = Math.max(0, this.playerReactorPoisoning - remove);
    }

    public int getMaximumPoisoning(){
        return MAX_PLAYER_POISON;
    }



    /** The part after this point handles the saving and loading of data **/
    public void copyFrom(ArcReactorSlot source){
        this.hasSlot = source.hasSlot;
        this.hasReactor = source.hasReactor;
        this.reactorTypeName = source.reactorTypeName;
        this.reactorTypeId = source.reactorTypeId;
        this.reactorEnergyCapacity = source.reactorEnergyCapacity;
        this.reactorEnergy = source.reactorEnergy;
        this.reactorEnergyOutput = source.reactorEnergyOutput;
        this.reactorIdleDrain = source.reactorIdleDrain;
        this.reactorPoisonFactor = source.reactorPoisonFactor;
        this.playerReactorPoisoning = source.playerReactorPoisoning;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putBoolean("hasArcReactorSlot", hasSlot);
        nbt.putBoolean("hasArcReactor", hasReactor);
        nbt.putString("arcReactorTypeName", reactorTypeName);
        nbt.putInt("arcReactorTypeId", reactorTypeId);
        nbt.putLong("arcReactorEnergyCapacity", reactorEnergyCapacity);
        nbt.putLong("arcReactorEnergy", reactorEnergy);
        nbt.putLong("arcReactorEnergyOutput", reactorEnergyOutput);
        nbt.putInt("arcReactorIdleDrain", reactorIdleDrain);
        nbt.putInt("arcReactorPoisonFactor", reactorPoisonFactor);
        nbt.putInt("playerArcReactorPoisoning", playerReactorPoisoning);
    }

    public void loadNBTData(CompoundTag nbt){
        hasSlot = nbt.getBoolean("hasArcReactorSlot");
        hasReactor = nbt.getBoolean("hasArcReactor");
        reactorTypeName = nbt.getString("arcReactorTypeName");
        reactorTypeId = nbt.getInt("arcReactorTypeId");
        reactorEnergyCapacity = nbt.getLong("arcReactorEnergyCapacity");
        reactorEnergy = nbt.getLong("arcReactorEnergy");
        reactorEnergyOutput = nbt.getLong("arcReactorEnergyOutput");
        reactorIdleDrain = nbt.getInt("arcReactorIdleDrain");
        reactorPoisonFactor = nbt.getInt("arcReactorPoisonFactor");
        playerReactorPoisoning = nbt.getInt("playerArcReactorPoisoning");
    }
}
