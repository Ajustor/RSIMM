package net.guwy.rsimm.content.effects;

import net.guwy.rsimm.index.RsImmEffects;
import net.guwy.rsimm.index.RsImmSounds;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MissingReactorEffect extends MobEffect {

    public MissingReactorEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Player player = (Player) pLivingEntity;
        if(player.tickCount % 20 == 0){
            float volume = 1 - ((float)player.getEffect(RsImmEffects.MISSING_REACTOR.get()).getDuration() / 6000);
            player.playSound(RsImmSounds.HEARTBEAT.get(), volume, 1);
        }

        if(pLivingEntity.getEffect(RsImmEffects.MISSING_REACTOR.get()).getDuration() <= 1){
            player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                if(arcReactor.hasArcReactorSlot()){
                    pLivingEntity.hurt(new DamageSource("missing_reactor"),Float.MAX_VALUE);
                }
            });

        }

        player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
            if (arcReactor.hasArcReactorSlot()) {
                if ((arcReactor.hasArcReactor()) && (arcReactor.getArcReactorEnergy() > 0)) {

                    player.removeEffect(RsImmEffects.MISSING_REACTOR.get());
                }
            }
        });
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
