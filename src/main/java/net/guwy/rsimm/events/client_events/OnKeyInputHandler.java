package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.content.network_packets.*;
import net.guwy.rsimm.index.RsImmKeyBindings;
import net.guwy.rsimm.index.RsImmNetworking;
import net.guwy.rsimm.utils.KeyCallType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;

import java.util.Objects;

public class OnKeyInputHandler {
    private static int armorKeyHoldDuration;
    private static int flightKeyHoldDuration;
    private static int handKeyHoldDuration;
    private static int specialKeyHoldDuration;
    private static int switchWeaponKeyHoldDuration;
    private static int weaponKeyHoldDuration;

    private static final int holdTreshold = 2;     // 15: too much, 7: a bit much, 5: fine but still not fast enough

    public static void init(InputEvent.Key event){
        Player player = Minecraft.getInstance().player;



        if(RsImmKeyBindings.ARMOR_KEY.isDown()){
            armorKeyHoldDuration = armorKeyHoldDuration + 1;
            if(armorKeyHoldDuration == holdTreshold){
                // Hold Action
                RsImmNetworking.sendToServer(new ArmorKeyBindingHoldC2SPacket());
            }
            if(armorKeyHoldDuration > holdTreshold){
                // Triggered when holding and will preform hold release action
            }
        } else if (!RsImmKeyBindings.ARMOR_KEY.isDown()) {
            if(armorKeyHoldDuration > 0){
                if(armorKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new ArmorKeyBindingPressC2SPacket(player.getUUID()));
                }
                armorKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.FLIGHT_KEY.isDown()){
            flightKeyHoldDuration = flightKeyHoldDuration + 1;
            if(flightKeyHoldDuration == holdTreshold){
                // Hold Action
                RsImmNetworking.sendToServer(new FlightKeyBindingHoldC2SPacket());
            }
            if(flightKeyHoldDuration > holdTreshold){
                // Triggered when holding and will preform hold release action
            }
        } else if (!RsImmKeyBindings.FLIGHT_KEY.isDown()) {
            if(flightKeyHoldDuration > 0){
                if(flightKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new FlightKeyBindingPressC2SPacket());
                }
                flightKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.HAND_KEY.isDown()){
            handKeyHoldDuration = handKeyHoldDuration + 1;
            if(handKeyHoldDuration == holdTreshold){
                // Hold Action
            }
            if(handKeyHoldDuration > holdTreshold){
                // Triggered when holding and will preform hold release action
                if (Minecraft.getInstance().player.tickCount % 5 == 0) {
                    RsImmNetworking.sendToServer(new HandKeyBindingHoldingC2SPacket());
                }
            }
        } else if (!RsImmKeyBindings.HAND_KEY.isDown()) {
            if(handKeyHoldDuration > 0){
                if(handKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new HandKeyBindingHoldReleaseC2SPacket());
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new HandKeyBindingPressC2SPacket());
                }
                handKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.SPECIAL_KEY.isDown()){
            specialKeyHoldDuration = specialKeyHoldDuration + 1;
            if(specialKeyHoldDuration == holdTreshold){
                // Hold Action
                RsImmNetworking.sendToServer(new SpecialKeyBindingC2SPacket(KeyCallType.START_HOLD));
            }
            if(specialKeyHoldDuration > holdTreshold){
                // Triggered when holding and will preform hold release action
                // ModNetworking.sendToServer(new SpecialKeyBindingC2SPacket(KeyCallType.HOLDING));
            }
        } else if (!RsImmKeyBindings.SPECIAL_KEY.isDown()) {
            if(specialKeyHoldDuration > 0){
                if(specialKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new SpecialKeyBindingC2SPacket(KeyCallType.HOLD_RELEASE));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new SpecialKeyBindingC2SPacket(KeyCallType.PRESS));
                }
                specialKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.SWITCH_WEAPON_KEY.isDown()){
            switchWeaponKeyHoldDuration = switchWeaponKeyHoldDuration + 1;
            if(switchWeaponKeyHoldDuration == holdTreshold){
                // Hold Action
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Triggered Hold Action"));
            }
            if(switchWeaponKeyHoldDuration > holdTreshold){
                // Triggered when holding and will preform hold release action
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Holding"));
            }
        } else if (!RsImmKeyBindings.SWITCH_WEAPON_KEY.isDown()) {
            if(switchWeaponKeyHoldDuration > 0){
                if(switchWeaponKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Triggered Hold Release Action"));
                }   else {
                    // Press Action
                    //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Triggered Press Action"));
                }
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Key hold Duration: " + switchWeaponKeyHoldDuration));
                switchWeaponKeyHoldDuration = 0;
            }
        }



        if(RsImmKeyBindings.WEAPON_KEY.isDown()){
            weaponKeyHoldDuration = weaponKeyHoldDuration + 1;
            if(weaponKeyHoldDuration == holdTreshold){
                // Hold Action
                RsImmNetworking.sendToServer(new WeaponKeyBindingC2SPacket(KeyCallType.START_HOLD));
            }
            if(weaponKeyHoldDuration > holdTreshold){
                // Triggered when holding and will preform hold release action
                /** While holding packet is unreliable and inconsistent **/
                // ModNetworking.sendToServer(new WeaponKeyBindingC2SPacket(WeaponFireCall.HOLDING));
            }
        } else if (!RsImmKeyBindings.WEAPON_KEY.isDown()) {
            if(weaponKeyHoldDuration > 0){
                if(weaponKeyHoldDuration > holdTreshold){
                    // Hold Release Action
                    RsImmNetworking.sendToServer(new WeaponKeyBindingC2SPacket(KeyCallType.HOLD_RELEASE));
                }   else {
                    // Press Action
                    RsImmNetworking.sendToServer(new WeaponKeyBindingC2SPacket(KeyCallType.PRESS));
                }
                weaponKeyHoldDuration = 0;
            }
        }

    }
}
