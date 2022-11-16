package net.guwy.rsimm.mechanics.event.player_tick.content;

import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class ChestSlotCheck {
    public static void init(TickEvent.PlayerTickEvent event){
        event.player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
            if(arcReactor.hasArcReactorSlot()){

                if(!(arcReactor.hasArcReactor()) || !(arcReactor.getArcReactorEnergy() > 0)){
                    if(!event.player.hasEffect(ModEffects.MISSING_REACTOR.get())){
                        event.player.addEffect(new MobEffectInstance(ModEffects.MISSING_REACTOR.get(), 12000, 0 ,false, false, true));
                    }
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
