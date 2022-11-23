package net.guwy.rsimm.content.effects;

import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.index.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ForcedSoundEffect extends MobEffect {

    /**
     * This Effect type is used when the conventional play sound method is not aggressive enough.
     * like calling the method inside a capability handler
     */

    SoundEvent sound;
    public ForcedSoundEffect(MobEffectCategory pCategory, int pColor, SoundEvent sound) {
        super(pCategory, pColor);
        this.sound = sound;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.playSound(sound, 100, 1);
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    public static void applyForcedSoundEffect(LivingEntity entity, MobEffect effect){
        entity.addEffect(new MobEffectInstance(effect, 1, 1, false, false,false));
    }
}
