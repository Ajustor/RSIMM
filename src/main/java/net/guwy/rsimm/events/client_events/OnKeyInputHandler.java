package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.config.RsImmClientConfigs;
import net.guwy.rsimm.content.network_packets.KeyBindingGenericC2SPacket;
import net.guwy.rsimm.enums.KeyActionTypes;
import net.guwy.rsimm.enums.KeyBinds;
import net.guwy.rsimm.index.RsImmKeyBindings;
import net.guwy.rsimm.index.RsImmNetworking;
import net.minecraftforge.client.event.InputEvent;

public class OnKeyInputHandler {
    private static int armorKeyHoldDuration;
    private static int flightKeyHoldDuration;
    private static int handKeyHoldDuration;
    private static int specialKeyHoldDuration;
    private static int weaponKeyHoldDuration;
    private static int switchWeaponKeyHoldDuration;

    private static final int holdTreshold = RsImmClientConfigs.KEY_BIND_HOLD_THRESHOLD.get();     // 15: too much, 7: a bit much, 5: fine but still not fast enough

    public static void init(InputEvent.Key event){

        if(RsImmKeyBindings.ARMOR_KEY.isDown()){
            armorKeyHoldDuration ++;
            if(armorKeyHoldDuration == holdTreshold){
                // Hold Start Action
                RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_START, KeyBinds.ARMOR_KEY));
            }
        } else if (!RsImmKeyBindings.ARMOR_KEY.isDown()) {
            if(armorKeyHoldDuration > 0){
                if(armorKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_RELEASE, KeyBinds.ARMOR_KEY));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.PRESS, KeyBinds.ARMOR_KEY));
                }
                armorKeyHoldDuration = 0;
            }
        }


        if(RsImmKeyBindings.FLIGHT_KEY.isDown()){
            flightKeyHoldDuration ++;
            if(flightKeyHoldDuration == holdTreshold){
                // Hold Start Action
                RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_START, KeyBinds.FLIGHT_KEY));
            }
        } else if (!RsImmKeyBindings.ARMOR_KEY.isDown()) {
            if(flightKeyHoldDuration > 0){
                if(flightKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_RELEASE, KeyBinds.FLIGHT_KEY));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.PRESS, KeyBinds.FLIGHT_KEY));
                }
                flightKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.HAND_KEY.isDown()){
            handKeyHoldDuration ++;
            if(handKeyHoldDuration == holdTreshold){
                // Hold Start Action
                RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_START, KeyBinds.HAND_KEY));
            }
        } else if (!RsImmKeyBindings.ARMOR_KEY.isDown()) {
            if(handKeyHoldDuration > 0){
                if(handKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_RELEASE, KeyBinds.HAND_KEY));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.PRESS, KeyBinds.HAND_KEY));
                }
                handKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.SPECIAL_KEY.isDown()){
            specialKeyHoldDuration ++;
            if(specialKeyHoldDuration == holdTreshold){
                // Hold Start Action
                RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_START, KeyBinds.SPECIAL_KEY));
            }
        } else if (!RsImmKeyBindings.ARMOR_KEY.isDown()) {
            if(specialKeyHoldDuration > 0){
                if(specialKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_RELEASE, KeyBinds.SPECIAL_KEY));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.PRESS, KeyBinds.SPECIAL_KEY));
                }
                specialKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.WEAPON_KEY.isDown()){
            weaponKeyHoldDuration ++;
            if(weaponKeyHoldDuration == holdTreshold){
                // Hold Start Action
                RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_START, KeyBinds.WEAPON_KEY));
            }
        } else if (!RsImmKeyBindings.ARMOR_KEY.isDown()) {
            if(weaponKeyHoldDuration > 0){
                if(weaponKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_RELEASE, KeyBinds.WEAPON_KEY));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.PRESS, KeyBinds.WEAPON_KEY));
                }
                weaponKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.SWITCH_WEAPON_KEY.isDown()){
            switchWeaponKeyHoldDuration ++;
            if(switchWeaponKeyHoldDuration == holdTreshold){
                // Hold Start Action
                RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_START, KeyBinds.WEAPON_SWITCH_KEY));
            }
        } else if (!RsImmKeyBindings.ARMOR_KEY.isDown()) {
            if(switchWeaponKeyHoldDuration > 0){
                if(switchWeaponKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.HOLD_RELEASE, KeyBinds.WEAPON_SWITCH_KEY));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new KeyBindingGenericC2SPacket(KeyActionTypes.PRESS, KeyBinds.WEAPON_SWITCH_KEY));
                }
                switchWeaponKeyHoldDuration = 0;
            }
        }
    }
}
