package net.guwy.rsimm.content.effects;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ParticleSpawningEffect extends MobEffect {
    SimpleParticleType particleTypes;
    public ParticleSpawningEffect(MobEffectCategory pCategory, int pColor, SimpleParticleType particleTypes) {
        super(pCategory, pColor);
        this.particleTypes = particleTypes;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.getLevel().addParticle(this.particleTypes, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), 0, 0, 0);

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
