package net.guwy.rsimm.mechanics.capabilities.player.armor_data;

public enum ArmStatus {
    NOT_VISIBLE (0),
    NORMAL (1),
    RAISED (2),
    BLOCK (3),
    UTILITY_1 (4),
    UTILITY_2 (5),
    UTILITY_3 (6),
    UTILITY_4 (7),
    UTILITY_5 (8);

    private final int status;

    ArmStatus(int status){
        this.status = status;
    }

    public int getIndex(){
        return this.status;
    }
}
