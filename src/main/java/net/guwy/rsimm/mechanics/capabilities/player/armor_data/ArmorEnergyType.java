package net.guwy.rsimm.mechanics.capabilities.player.armor_data;

public enum ArmorEnergyType {
    EMERGENCY (0),
    BACKUP (1),
    MAIN (2);

    private final int type;

    ArmorEnergyType(int type){
        this.type = type;
    }

    public int getIndex(){
        return this.type;
    }
}
