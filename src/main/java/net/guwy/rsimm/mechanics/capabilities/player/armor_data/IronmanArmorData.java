package net.guwy.rsimm.mechanics.capabilities.player.armor_data;

import net.guwy.rsimm.content.items.armors.AbstractIronmanArmorItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class IronmanArmorData {
    /** Lots of variables to save **/
    private boolean hasArmor = false;
    private double systemStatus = 0;
    private double boot = 0;
    private long armorEnergy = 0;
    private long armorMaxStableEnergy = 0;
    private ArmorEnergyType armorEnergyType = ArmorEnergyType.EMERGENCY;
    private long armorEnergyLoad = 0;
    private long armorEnergyOutput = 0;
    private boolean armorEnergyOverload = false;
    private double armorFreezing = 0;
    private int armorStorage1 = 0;
    private int armorStorage2 = 0;
    private int armorStorage3 = 0;
    private int armorStorage4 = 0;
    private int armorStorage5 = 0;
    private int armorStorage6 = 0;
    private int armorStorage7 = 0;
    private int armorStorage8 = 0;
    private int armorStorage9 = 0;
    private int armorStorage10 = 0;
    private int selectedStorage = 1;
    private boolean storageUse = false;
    private boolean isFlying = false;
    private FlyMode flyMode = FlyMode.NOT_FLYING;
    private boolean helmetOpen = false;
    private double rightHandCharge = 0;
    private double leftHandCharge = 0;
    private boolean handActTogether = false;
    private double uniBeamCharge = 0;
    private double flightSpeed = 0;
    private double maximumFlightSpeed = 0;
    private double minimumFlightSpeed = 0;
    private double moveSpeed = 0;
    private boolean handKeyHolding = false;
    //private ArmStatus rightArmStatus = ArmStatus.NOT_VISIBLE;
    //private ArmStatus leftArmStatus = ArmStatus.NOT_VISIBLE;

    public IronmanArmorData() {
    }


    // QOL
    public void compileArmor(long armorEnergy, long armorMaxStableEnergy, ArmorEnergyType armorEnergyType, long armorEnergyOutput,
                             double maximumFlightSpeed, double minimumFlightSpeed){
        this.hasArmor = true;
        this.systemStatus = 100;
        this.boot = 0;
        this.armorEnergy = armorEnergy;
        this.armorMaxStableEnergy = armorMaxStableEnergy;
        this.armorEnergyType = armorEnergyType;
        this.armorEnergyOutput = armorEnergyOutput;
        this.armorEnergyOverload = false;
        this.armorFreezing = 0;
        this.armorStorage1 = 0;
        this.armorStorage2 = 0;
        this.armorStorage3 = 0;
        this.armorStorage4 = 0;
        this.armorStorage5 = 0;
        this.armorStorage6 = 0;
        this.armorStorage7 = 0;
        this.armorStorage8 = 0;
        this.armorStorage9 = 0;
        this.armorStorage10 = 0;
        this.selectedStorage = 1;
        this.storageUse = false;
        this.isFlying = false;
        this.flyMode = FlyMode.NOT_FLYING;
        this.helmetOpen = false;
        this.rightHandCharge = 0;
        this.leftHandCharge = 0;
        this.uniBeamCharge = 0;
        this.flightSpeed = minimumFlightSpeed;
        this.maximumFlightSpeed = maximumFlightSpeed;
        this.minimumFlightSpeed = minimumFlightSpeed;
        this.handKeyHolding = false;
    }
    public void decompileArmor(){
        this.hasArmor = false;
        this.systemStatus = 0;
        this.boot = 0;
        this.armorEnergy = 0;
        this.armorMaxStableEnergy = 0;
        this.armorEnergyType = ArmorEnergyType.EMERGENCY;
        this.armorEnergyLoad = 0;
        this.armorEnergyOutput = 0;
        this.armorEnergyOverload = false;
        this.armorFreezing = 0;
        this.armorStorage1 = 0;
        this.armorStorage2 = 0;
        this.armorStorage3 = 0;
        this.armorStorage4 = 0;
        this.armorStorage5 = 0;
        this.armorStorage6 = 0;
        this.armorStorage7 = 0;
        this.armorStorage8 = 0;
        this.armorStorage9 = 0;
        this.armorStorage10 = 0;
        this.selectedStorage = 1;
        this.storageUse = false;
        this.isFlying = false;
        this.flyMode = FlyMode.NOT_FLYING;
        this.helmetOpen = false;
        this.rightHandCharge = 0;
        this.leftHandCharge = 0;
        this.uniBeamCharge = 0;
        this.flightSpeed = 0;
        this.maximumFlightSpeed = 0;
        this.minimumFlightSpeed = 0;
        this.handKeyHolding = false;
    }


    //Core
    public boolean getHasArmor() {
        return this.hasArmor;
    }
    public void setHasArmor(boolean bool) {
        this.hasArmor = bool;
    }


    //Systems
    public double getSystemStatus() {
        return this.systemStatus;
    }
    public void setSystemStatus(double val) {
        this.systemStatus = val;
    }
    public void increaseSystemStatus(double add) { this.systemStatus = Math.max(this.systemStatus + add, 100); }
    public void decreaseSystemStatus(double decrease) { this.systemStatus = Math.min(this.systemStatus - decrease, 0); }

    public double getBoot() {
        return this.boot;
    }
    public void setBoot(double val) {
        this.boot = val;
    }
    public void increaseBoot(double add) { this.boot = Math.min(this.boot + add, 100); }
    public void decreaseBoot(double decrease) { this.boot = Math.max(this.boot - decrease, 0); }


    //Energy
    public long getArmorEnergy() {
        return this.armorEnergy;
    }
    public void setArmorEnergy(long val) {
        this.armorEnergy = val;
    }
    public void increaseArmorEnergy(long add) { this.armorEnergy = Math.min(this.armorEnergy + add, this.armorMaxStableEnergy * 4); }
    public void decreaseArmorEnergy(long decrease) { this.armorEnergy = Math.max(this.armorEnergy - decrease, 0); }

    public long getArmorMaxStableEnergy() {
        return this.armorMaxStableEnergy;
    }
    public void setArmorMaxStableEnergy(long val) {
        this.armorMaxStableEnergy = val;
    }

    public ArmorEnergyType getArmorEnergyType() {
        return this.armorEnergyType;
    }
    public void setArmorEnergyType(ArmorEnergyType type) {
        this.armorEnergyType = type;
    }

    public long getArmorEnergyLoad() {
        return this.armorEnergyLoad;
    }
    public void setArmorEnergyLoad(long val) {
        this.armorEnergyLoad = val;
    }
    public void increaseArmorEnergyLoad(long add) { this.armorEnergyLoad = this.armorEnergyLoad + add; }
    public void decreaseArmorEnergyLoad(long decrease) { this.armorEnergyLoad = Math.max(this.armorEnergyLoad - decrease, 0); }

    public boolean isArmorEnergyOverload(){ return  this.armorEnergyOverload; }
    public void  setArmorEnergyOverload(boolean set){ this.armorEnergyOverload = set; }

    public long getArmorEnergyOutput(){return this.armorEnergyOutput;}
    public void  setArmorEnergyOutput(long set){ this.armorEnergyOutput = set;}


    //Hull
    public double getArmorFreezing() {
        return this.armorFreezing;
    }
    public void setArmorFreezing(double val) {
        this.armorFreezing = val;
    }
    public void increaseArmorFreezing(double add) { this.armorFreezing = Math.min(this.armorFreezing + add, 100); }
    public void decreaseArmorFreezing(double decrease) { this.armorFreezing = Math.max(this.armorFreezing - decrease, 0); }

    public boolean getHelmetOpen(){
        return this.helmetOpen;
    }
    public void setHelmetOpen(boolean set, Player player){
        this.helmetOpen = set;

        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        AbstractIronmanArmorItem armor = (AbstractIronmanArmorItem) helmet.getItem();
        ItemStack itemStack;
        if(set){
            itemStack = new ItemStack(armor.HelmetOpenItem());
        }   else {
            itemStack = new ItemStack(armor.HelmetItem());
        }
        itemStack.setDamageValue(helmet.getDamageValue());
        player.setItemSlot(EquipmentSlot.HEAD, itemStack);
    }


    // Arsenal
    public int getArmorStorage(int slot) {
        return switch (slot) {
            case 1 -> this.armorStorage1;
            case 2 -> this.armorStorage2;
            case 3 -> this.armorStorage3;
            case 4 -> this.armorStorage4;
            case 5 -> this.armorStorage5;
            case 6 -> this.armorStorage6;
            case 7 -> this.armorStorage7;
            case 8 -> this.armorStorage8;
            case 9 -> this.armorStorage9;
            case 10 -> this.armorStorage10;
            default -> 0;
        };
    }

    public void setArmorStorage(int slot, int val) {
        switch (slot) {
            case 1 -> this.armorStorage1 = val;
            case 2 -> this.armorStorage2 = val;
            case 3 -> this.armorStorage3 = val;
            case 4 -> this.armorStorage4 = val;
            case 5 -> this.armorStorage5 = val;
            case 6 -> this.armorStorage6 = val;
            case 7 -> this.armorStorage7 = val;
            case 8 -> this.armorStorage8 = val;
            case 9 -> this.armorStorage9 = val;
            case 10 -> this.armorStorage10 = val;
        };
    }

    public void decreaseArmorStorage(int slot, int decrease) {
        switch (slot) {
            case 1 -> this.armorStorage1 = Math.max(0, this.armorStorage1 - decrease);
            case 2 -> this.armorStorage2 = Math.max(0, this.armorStorage2 - decrease);
            case 3 -> this.armorStorage3 = Math.max(0, this.armorStorage3 - decrease);
            case 4 -> this.armorStorage4 = Math.max(0, this.armorStorage4 - decrease);
            case 5 -> this.armorStorage5 = Math.max(0, this.armorStorage5 - decrease);
            case 6 -> this.armorStorage6 = Math.max(0, this.armorStorage6 - decrease);
            case 7 -> this.armorStorage7 = Math.max(0, this.armorStorage7 - decrease);
            case 8 -> this.armorStorage8 = Math.max(0, this.armorStorage8 - decrease);
            case 9 -> this.armorStorage9 = Math.max(0, this.armorStorage9 - decrease);
            case 10 -> this.armorStorage10 = Math.max(0, this.armorStorage10 - decrease);
        };
    }

    public int getSelectedStorage(){
        return this.selectedStorage;
    }

    public void setSelectedStorage(int slot){
        this.selectedStorage = slot;
    }

    public void cycleSelectedStorage(AbstractIronmanArmorItem armorItem){
        switch (this.selectedStorage){
            case 1 -> this.selectedStorage = armorItem.FireWeapon2(null, null, true, null) ? 2 : 1;
            case 2 -> this.selectedStorage = armorItem.FireWeapon3(null, null, true, null) ? 3 : 1;
            case 3 -> this.selectedStorage = armorItem.FireWeapon4(null, null, true, null) ? 4 : 1;
            case 4 -> this.selectedStorage = armorItem.FireWeapon5(null, null, true, null) ? 5 : 1;
            case 5 -> this.selectedStorage = armorItem.FireWeapon6(null, null, true, null) ? 6 : 1;
            case 6 -> this.selectedStorage = armorItem.FireWeapon7(null, null, true, null) ? 7 : 1;
            case 7 -> this.selectedStorage = armorItem.FireWeapon8(null, null, true, null) ? 8 : 1;
            case 8 -> this.selectedStorage = armorItem.FireWeapon9(null, null, true, null) ? 9 : 1;
            case 9 -> this.selectedStorage = armorItem.FireWeapon10(null, null, true, null) ? 10 : 1;
            case 10 -> this.selectedStorage = 1;
        }
    }


    // Flight
    public boolean getIsFlying(){
        return this.isFlying;
    }
    public void setIsFlying(boolean isFlying){
        this.isFlying = isFlying;
    }

    public FlyMode getFlyMode(){
        return this.flyMode;
    }
    public void setFlyMode(FlyMode flyMode){
        this.flyMode = flyMode;
    }

    public double getFlightSpeed(){
        return this.flightSpeed;
    }
    public void setFlightSpeed(double setSpeed){
        this.flightSpeed = Math.max(this.minimumFlightSpeed, Math.min(this.maximumFlightSpeed, setSpeed));
    }
    public void increaseFlightSpeed(double increase){
        this.flightSpeed = Math.max(this.minimumFlightSpeed, Math.min(this.maximumFlightSpeed, this.flightSpeed + increase));
    }
    public void decreaseFlightSpeed(double decrease){
        this.flightSpeed = Math.max(this.minimumFlightSpeed, Math.min(this.maximumFlightSpeed, this.flightSpeed + decrease));
    }

    public double getMaxFlightSpeed(){
        return this.maximumFlightSpeed;
    }
    public void setMaxFlightSpeed(double setSpeed){
        this.maximumFlightSpeed = setSpeed;
    }

    public double getMinFlightSpeed(){
        return this.minimumFlightSpeed;
    }
    public void setMinFlightSpeed(double setSpeed){
        this.minimumFlightSpeed = setSpeed;
    }

    public double getMoveSpeed(){
        return this.moveSpeed;
    }
    public void setMoveSpeed(double setSpeed){
        this.moveSpeed = setSpeed;
    }


    // Energy Diversion
    public double getRightHandCharge(){
        return this.rightHandCharge;
    }
    public void setRightHandCharge(double charge){
        this.rightHandCharge = Math.min(100, charge);
    }
    public void addRightHandCharge(double add){
        this.rightHandCharge = Math.max(0, Math.min(100, this.rightHandCharge + add));
    }

    public double getLeftHandCharge(){
        return this.leftHandCharge;
    }
    public void setLeftHandCharge(double charge){
        this.leftHandCharge = Math.min(100, charge);
    }
    public void addLeftHandCharge(double add){
        this.leftHandCharge = Math.max(0, Math.min(100, this.leftHandCharge + add));
    }

    public boolean getHandActTogether(){
        return this.handActTogether;
    }
    public void setHandActTogether(boolean set){
        this.handActTogether = set;
    }

    public double getUniBeamCharge(){
        return this.uniBeamCharge;
    }
    public void setUniBeamCharge(double charge){
        this.uniBeamCharge = Math.min(100, charge);
    }
    public void addUniBeamCharge(double add){
        this.uniBeamCharge = Math.max(0, Math.min(100, this.uniBeamCharge + add));
    }



    // Keybinding Helpers
    public boolean isHandKeyHolding(){
        return this.handKeyHolding;
    }
    public void setHandKeyHolding(boolean set){
        this.handKeyHolding = set;
    }



    /** The part after this point handles the saving and loading of data **/
    public void copyFrom(IronmanArmorData source){
        this.hasArmor = source.hasArmor;
        this.systemStatus = source.systemStatus;
        this.boot = source.boot;
        this.armorEnergy = source.armorEnergy;
        this.armorMaxStableEnergy = source.armorMaxStableEnergy;
        this.armorEnergyType = source.armorEnergyType;
        this.armorEnergyLoad = source.armorEnergyLoad;
        this.armorEnergyOutput = source.armorEnergyOutput;
        this.armorFreezing = source.armorFreezing;
        this.armorStorage1 = source.armorStorage1;
        this.armorStorage2 = source.armorStorage2;
        this.armorStorage3 = source.armorStorage3;
        this.armorStorage4 = source.armorStorage4;
        this.armorStorage5 = source.armorStorage5;
        this.armorStorage6 = source.armorStorage6;
        this.armorStorage7 = source.armorStorage7;
        this.armorStorage8 = source.armorStorage8;
        this.armorStorage9 = source.armorStorage9;
        this.armorStorage10 = source.armorStorage10;
        this.selectedStorage = source.selectedStorage;
        this.storageUse = source.storageUse;
        this.isFlying = source.isFlying;
        this.flyMode = source.flyMode;
        this.helmetOpen = source.helmetOpen;
        this.rightHandCharge = source.rightHandCharge;
        this.leftHandCharge = source.leftHandCharge;
        this.handActTogether = source.handActTogether;
        this.uniBeamCharge = source.uniBeamCharge;
        this.flightSpeed = source.flightSpeed;
        this.maximumFlightSpeed = source.maximumFlightSpeed;
        this.minimumFlightSpeed = source.minimumFlightSpeed;
        this.moveSpeed = source.moveSpeed;
        this.handKeyHolding = source.handKeyHolding;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putBoolean("hasArmor", hasArmor);
        nbt.putDouble("systemStatus", systemStatus);
        nbt.putDouble("boot", boot);
        nbt.putLong("armorEnergy", armorEnergy);
        nbt.putLong("armorMaxStableEnergy", armorMaxStableEnergy);
        nbt.putInt("armorEnergyType", armorEnergyType.getIndex());
        nbt.putLong("armorEnergyLoad", armorEnergyLoad);
        nbt.putLong("armorEnergyOutput", armorEnergyOutput);
        nbt.putDouble("armorFreezing", armorFreezing);
        nbt.putInt("armorStorage1", armorStorage1);
        nbt.putInt("armorStorage2", armorStorage2);
        nbt.putInt("armorStorage3", armorStorage3);
        nbt.putInt("armorStorage4", armorStorage4);
        nbt.putInt("armorStorage5", armorStorage5);
        nbt.putInt("armorStorage6", armorStorage6);
        nbt.putInt("armorStorage7", armorStorage7);
        nbt.putInt("armorStorage8", armorStorage8);
        nbt.putInt("armorStorage9", armorStorage9);
        nbt.putInt("armorStorage10", armorStorage10);
        nbt.putInt("selectedStorage", selectedStorage);
        nbt.putBoolean("storageUse", storageUse);
        nbt.putBoolean("isFlying", isFlying);
        nbt.putInt("flyMode", flyMode.getIndex());
        nbt.putBoolean("helmetOpen", helmetOpen);
        nbt.putDouble("rightHandCharge", rightHandCharge);
        nbt.putDouble("leftHandCharge", leftHandCharge);
        nbt.putBoolean("handActTogether", handActTogether);
        nbt.putDouble("uniBeamCharge", uniBeamCharge);
        nbt.putDouble("flightSpeed", flightSpeed);
        nbt.putDouble("maximumFlightSpeed", maximumFlightSpeed);
        nbt.putDouble("minimumFlightSpeed", minimumFlightSpeed);
        nbt.putDouble("moveSpeed", moveSpeed);
        nbt.putBoolean("handKeyHolding", handKeyHolding);
    }

    public void loadNBTData(CompoundTag nbt){
        hasArmor = nbt.getBoolean("hasArmor");
        systemStatus = nbt.getDouble("systemStatus");
        boot = nbt.getDouble("boot");
        armorEnergy = nbt.getLong("armorEnergy");
        armorMaxStableEnergy = nbt.getLong("armorMaxStableEnergy");
        armorEnergyType = ArmorEnergyType.values()[nbt.getInt("armorEnergyType")];
        armorEnergyLoad = nbt.getLong("armorEnergyLoad");
        armorEnergyOutput = nbt.getLong("armorEnergyOutput");
        armorFreezing = nbt.getInt("armorFreezing");
        armorStorage1 = nbt.getInt("armorStorage1");
        armorStorage2 = nbt.getInt("armorStorage2");
        armorStorage3 = nbt.getInt("armorStorage3");
        armorStorage4 = nbt.getInt("armorStorage4");
        armorStorage5 = nbt.getInt("armorStorage5");
        armorStorage6 = nbt.getInt("armorStorage6");
        armorStorage7 = nbt.getInt("armorStorage7");
        armorStorage8 = nbt.getInt("armorStorage8");
        armorStorage9 = nbt.getInt("armorStorage9");
        armorStorage10 = nbt.getInt("armorStorage10");
        selectedStorage = nbt.getInt("selectedStorage");
        storageUse = nbt.getBoolean("storageUse");
        isFlying = nbt.getBoolean("isFlying");
        flyMode = FlyMode.values()[nbt.getInt("flyMode")];
        helmetOpen = nbt.getBoolean("helmetOpen");
        rightHandCharge = nbt.getDouble("rightHandCharge");
        leftHandCharge = nbt.getDouble("leftHandCharge");
        handActTogether = nbt.getBoolean("handActTogether");
        uniBeamCharge = nbt.getDouble("uniBeamCharge");
        flightSpeed = nbt.getDouble("flightSpeed");
        maximumFlightSpeed = nbt.getDouble("maximumFlightSpeed");
        minimumFlightSpeed = nbt.getDouble("minimumFlightSpeed");
        moveSpeed = nbt.getDouble("moveSpeed");
        handKeyHolding = nbt.getBoolean("handKeyHolding");
    }
}
