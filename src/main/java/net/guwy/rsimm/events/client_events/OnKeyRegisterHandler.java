package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.index.RsImmKeyBindings;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

public class OnKeyRegisterHandler {
    public static void init(RegisterKeyMappingsEvent event){
        event.register(RsImmKeyBindings.ARMOR_KEY);
        event.register(RsImmKeyBindings.HAND_KEY);
        event.register(RsImmKeyBindings.SPECIAL_KEY);
        event.register(RsImmKeyBindings.FLIGHT_KEY);
        event.register(RsImmKeyBindings.WEAPON_KEY);
        event.register(RsImmKeyBindings.SWITCH_WEAPON_KEY);
    }
}
