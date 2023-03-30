package net.guwy.rsimm.mechanics.event.player_tick.content;

import net.guwy.rsimm.content.items.arc_reactors.ArcReactorItem;
import net.guwy.rsimm.content.network_packets.ArcReactorChargerClientSyncS2CPacket;
import net.guwy.rsimm.content.network_packets.PlayerArcReactorClientSyncS2CPacket;
import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.index.ModNetworking;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TickEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class ChestSlotCheck {
    public static void init(TickEvent.PlayerTickEvent event){
        event.player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
            if(arcReactor.hasArcReactorSlot()){

                // checks if the player has an arc reactor with energy
                // if not adds the required effect to handle the situation
                if(!(arcReactor.hasArcReactor()) || !(arcReactor.getArcReactorEnergy() > 0)){
                    if(!event.player.hasEffect(ModEffects.MISSING_REACTOR.get())){
                        event.player.addEffect(new MobEffectInstance(ModEffects.MISSING_REACTOR.get(), 12000, 0 ,false, false, true));
                    }
                }


                if(arcReactor.hasArcReactor()){
                    int id, workingId;
                    workingId = arcReactor.getArcReactorTypeId();
                    ArcReactorItem item = (ArcReactorItem) Item.byId(workingId);


                    // if the energy is above 10% set id for the working reactor
                    if(arcReactor.getArcReactorEnergy() >= (arcReactor.getArcReactorEnergyCapacity() * 0.1)){
                        id = workingId;
                    }

                    // if between 10% and 0% select a random reactor between the depleted and working one
                    else if (arcReactor.getArcReactorEnergy() > 0){
                        if(Math.random() > 0.5){
                            id = workingId;
                        } else {
                            id = Item.getId(item.depletedItem());
                        }
                    }

                    //else just use the depleted one
                    else {
                        id = Item.getId(item.depletedItem());
                    }

                    ModNetworking.sendToClients(new PlayerArcReactorClientSyncS2CPacket(id, event.player.getUUID()));
                } else {
                    ModNetworking.sendToClients(new PlayerArcReactorClientSyncS2CPacket(0, event.player.getUUID()));
                }

            }
        });
    }

    public static boolean hasArcReactor(Player player){
        AtomicBoolean condition = new AtomicBoolean(false);

        player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
            if(arcReactor.hasArcReactorSlot()){
                if((arcReactor.hasArcReactor())){
                    condition.set(true);
                }
            }
        });

        return condition.get();
    }
}
