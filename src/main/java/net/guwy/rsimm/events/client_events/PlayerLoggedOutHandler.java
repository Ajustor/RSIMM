package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.client.ArcReactorClientData;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerLoggedOutHandler {
    public static void clientInit(PlayerEvent.PlayerLoggedOutEvent event) {
        ArcReactorClientData.purgeArcReactorData();
    }
}
