package net.guwy.rsimm.mechanics.event.client_events;

import net.guwy.rsimm.content.data.ArcReactorClientData;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerLoggedOutHandler {
    public static void clientInit(PlayerEvent.PlayerLoggedOutEvent event) {
        ArcReactorClientData.purgeArcReactorData();
    }
}
