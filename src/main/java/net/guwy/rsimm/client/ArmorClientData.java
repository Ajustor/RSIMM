package net.guwy.rsimm.client;

import net.guwy.rsimm.mechanics.capabilities.custom.player.armor_data.FlyMode;

public class ArmorClientData {
    private static double playerRotation = 0;
    private static boolean isFlying = false;
    private static FlyMode flyMode = FlyMode.NOT_FLYING;
    private static double armorFreezing = 0;
    private static double accelerationAmount = 0.5;

    public static void setPlayerRotation(double rotation){
        ArmorClientData.playerRotation = rotation;
    }
    public static double getPlayerRotation(){
        return playerRotation;
    }

    public static void setIsFlying(boolean isFlying){
        ArmorClientData.isFlying = isFlying;
    }
    public static boolean isIsFlying(){
        return isFlying;
    }

    public static void setFlyMode(FlyMode flyMode){
        ArmorClientData.flyMode = flyMode;
    }
    public static FlyMode getFlyMode(){
        return flyMode;
    }

    public static void setArmorFreezing(double armorFreezing){ ArmorClientData.armorFreezing = armorFreezing; }
    public static double getArmorFreezing() {
        return armorFreezing;
    }

    public static void setAccelerationAmount(double accelerationAmount){ ArmorClientData.accelerationAmount = accelerationAmount; }
    public static double getAccelerationAmount() {
        return accelerationAmount;
    }
}
