package net.guwy.rsimm.events.server_events;

import net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.custom.player.armor_data.IronmanArmorData;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class RegisterCapabilitiesHandler {

    public static void init(RegisterCapabilitiesEvent event){
        event.register(ArcReactorSlot.class);
        event.register(IronmanArmorData.class);
    }
}
