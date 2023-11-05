package net.guwy.rsimm.events.server_events;

import net.guwy.rsimm.index.RsImmEffects;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class PotionRemoveInterceptor {
    public static void init(MobEffectEvent.Remove event){



        if(event.getEffect() == RsImmEffects.MISSING_REACTOR.get()) {
            if (event.getEntity().getType() == EntityType.PLAYER) {

                Player player = (Player) event.getEntity();
                player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {

                    if (arcReactor.hasArcReactorSlot()) {
                        if (!(arcReactor.hasArcReactor()) || !(arcReactor.getArcReactorEnergy() > 0)) {
                            event.setCanceled(true);
                        }
                    }

                });

            }
        }



    }
}