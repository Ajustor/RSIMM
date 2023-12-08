package net.guwy.rsimm.utils;

public enum KeyCallType {
    PRESS (0),
    START_HOLD (1),
    HOLDING (2),
    HOLD_RELEASE (3),
    CLICK (4);

    private final int weaponCall;

    KeyCallType(int weaponCall){
        this.weaponCall = weaponCall;
    }

    public int getIndex(){
        return this.weaponCall;
    }
}
