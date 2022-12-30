package net.guwy.rsimm.mechanics.event.client_events;

import net.guwy.rsimm.index.ModKeyBindings;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

public class OnKeyRegisterHandler {
    public static void init(RegisterKeyMappingsEvent event){
        event.register(ModKeyBindings.ARMOR_KEY);
        event.register(ModKeyBindings.HAND_KEY);
        event.register(ModKeyBindings.SPECIAL_KEY);
        event.register(ModKeyBindings.FLIGHT_KEY);
        event.register(ModKeyBindings.WEAPON_KEY);
        event.register(ModKeyBindings.SWITCH_WEAPON_KEY);
    }
}
