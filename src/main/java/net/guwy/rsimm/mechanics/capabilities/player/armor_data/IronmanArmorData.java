package net.guwy.rsimm.mechanics.capabilities.player.armor_data;

import net.minecraft.nbt.CompoundTag;

public class IronmanArmorData {
    /** Lots of variables to save **/
    private boolean hasArmor = false;
    private double systemStatus = 0;
    private double flightSystems = 0;
    private double weaponSystems = 0;
    private double boot = 0;
    private int armorEnergy = 0;
    private int armorMaxStableEnergy = 0;
    private ArmorEnergyType armorEnergyType = ArmorEnergyType.EMERGENCY;
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
    private ArmStatus rightArmStatus = ArmStatus.NOT_VISIBLE;
    private ArmStatus leftArmStatus = ArmStatus.NOT_VISIBLE;
    private boolean isFlying = false;
    private FlyMode flyMode = FlyMode.NOT_FLYING;
    private boolean helmetOpen = false;
    private double rightHandCharge = 0;
    private double leftHandCharge = 0;
    private double uniBeamCharge = 0;
    private double energyDraw = 0;
    private double flightSpeed = 0;
    private double minimumFlightSpeed = 0;
    private double maximumFlightSpeed = 0;

    public IronmanArmorData() {
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

    public double getFlightSystems() {
        return this.flightSystems;
    }
    public void setFlightSystems(double val) {
        this.flightSystems = val;
    }
    public void increaseFlightSystems(double add) { this.flightSystems = Math.max(this.flightSystems + add, 100); }
    public void decreaseFlightSystems(double decrease) { this.flightSystems = Math.min(this.flightSystems - decrease, 0); }

    public double getWeaponSystems() {
        return this.weaponSystems;
    }
    public void setWeaponSystems(double val) {
        this.weaponSystems = val;
    }
    public void increaseWeaponSystems(double add) { this.weaponSystems = Math.max(this.weaponSystems + add, 100); }
    public void decreaseWeaponSystems(double decrease) { this.weaponSystems = Math.min(this.weaponSystems - decrease, 0); }

    public double getBoot() {
        return this.boot;
    }
    public void setBoot(double val) {
        this.boot = val;
    }
    public void increaseBoot(double add) { this.boot = Math.max(this.boot + add, 100); }
    public void decreaseBoot(double decrease) { this.boot = Math.min(this.boot - decrease, 0); }


    //Energy
    public int getArmorEnergy() {
        return this.armorEnergy;
    }
    public void setArmorEnergy(int val) {
        this.armorEnergy = val;
    }
    public void increaseArmorEnergy(int add) { this.armorEnergy = Math.max(this.armorEnergy + add, this.armorMaxStableEnergy * 4); }
    public void decreaseArmorEnergy(int decrease) { this.armorEnergy = Math.min(this.armorEnergy - decrease, 0); }

    public int getArmorMaxStableEnergy() {
        return this.armorMaxStableEnergy;
    }
    public void setArmorMaxStableEnergy(int val) {
        this.armorMaxStableEnergy = val;
    }

    public ArmorEnergyType getArmorEnergyType() {
        return this.armorEnergyType;
    }
    public void setArmorEnergyType(ArmorEnergyType type) {
        this.armorEnergyType = type;
    }


    //Hull
    public double getArmorFreezing() {
        return this.armorFreezing;
    }
    public void setArmorFreezing(double val) {
        this.armorFreezing = val;
    }
    public void increaseArmorFreezing(double add) { this.armorFreezing = Math.max(this.armorFreezing + add, 100); }
    public void decreaseArmorFreezing(double decrease) { this.armorFreezing = Math.min(this.armorFreezing - decrease, 0); }


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
