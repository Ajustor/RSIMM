package net.guwy.rsimm.content.effects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ReactorStolenEffect extends MobEffect {

    public ReactorStolenEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if(pLivingEntity.getType() == EntityType.PLAYER){
            Player player = (Player) pLivingEntity;
            player.displayClientMessage(Component.translatable("message.rsimm.chest_cutter.steal.success.victim.action_bar"), true);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
