package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.content.overlays.EdithGlassesOverlay;
import net.guwy.rsimm.content.overlays.Mark1ArmorOverlay;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;

public class RegisterGuiOverlaysEventHandler {
    public static void init(final RegisterGuiOverlaysEvent event){
        event.registerBelowAll("mark_1_helmet", Mark1ArmorOverlay.HELMET_OVERLAY);
        event.registerBelowAll("edith_glasses", EdithGlassesOverlay.OVERLAY);
    }
}
