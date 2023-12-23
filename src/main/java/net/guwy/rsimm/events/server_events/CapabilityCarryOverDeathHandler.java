package net.guwy.rsimm.events.server_events;

import net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.custom.player.armor_data.IronmanArmorDataProvider;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class CapabilityCarryOverDeathHandler {

    public static void init(PlayerEvent.Clone event){
        event.getOriginal().reviveCaps();



        // Copy Arc Reactor Data
        event.getOriginal().getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(oldStore -> {
            event.getEntity().getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(newStore -> {

                if(oldStore.getPlayerArcReactorPoisoning() > oldStore.getMaximumPoisoning() * 3/4){
                    oldStore.setPlayerArcReactorPoisoning(oldStore.getMaximumPoisoning() * 3/4);        //gives you 12 days if the poison factor is 14 (43 if its 4)
                }
                newStore.copyFrom(oldStore);
            });
        });



        // Copy Armor Data (only when you trave from end to the overworld)
        if(!event.isWasDeath()){
            event.getOriginal().getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(oldStore -> {
                event.getEntity().getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }



        event.getOriginal().invalidateCaps();

    }
}
