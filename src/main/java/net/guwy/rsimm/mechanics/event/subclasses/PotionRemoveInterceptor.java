package net.guwy.rsimm.mechanics.event.subclasses;

import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.PotionEvent;

public class PotionRemoveInterceptor {
    public static void init(PotionEvent.PotionRemoveEvent event){



        if(event.getPotion() == ModEffects.MISSING_REACTOR.get()) {
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
