package net.guwy.rsimm.mechanics.capabilities.player.armor_data;

public enum FlyMode {
    NOT_FLYING (0),
    HOVERING_ON_GROUND (1),     // for alternate Running
    HOVERING (2),   // Classic hover
    FLYING (3),     // Elytra flight
    CRUISE (4);     // wasd+shift+space flight

    private final int flyMode;

    FlyMode(int flyMode){
        this.flyMode = flyMode;
    }

    public int getIndex(){
        return this.flyMode;
    }
}
