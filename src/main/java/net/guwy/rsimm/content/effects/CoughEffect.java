package net.guwy.rsimm.content.effects;

import net.guwy.rsimm.index.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.client.event.sound.SoundEvent;
import org.lwjgl.system.CallbackI;

public class CoughEffect extends MobEffect {

    public CoughEffect(MobEffectCategory p_19451_, int p_19452_) {
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
            player.playSound(ModSounds.PLAYER_COUGH.get(), 100, (float)Math.random() * 0.2f + 0.9f);
            for(int i=0; i<=5; i++){
                cough(player, pLivingEntity.level);
            }
        }
        if(player.tickCount % 20 == 7){
            for(int i=0; i<=5; i++){
                cough(player, pLivingEntity.level);
            }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    private void cough(Player player, Level level){
        double YLook = Math.sin(Math.toRadians(player.getViewXRot(1)));
        double XLook = Math.sin(Math.toRadians(player.getViewYRot(1)));
        double ZLook = Math.cos(Math.toRadians(player.getViewYRot(1)));

        double YSpeed =  (YLook * -0.1 / 3) + ((Math.random() - 0.5) * 0.1);
        double XSpeed = ((XLook * -0.1)) * (1 - Math.abs(YLook)) + ((Math.random() - 0.5) * 0.1);
        double ZSpeed = ((ZLook * 0.1)) * (1 - Math.abs(YLook)) + ((Math.random() - 0.5) * 0.1);

        //ParticleOptions particle = ParticleTypes.END_ROD;  // good but too large
        //ParticleOptions particle = ParticleTypes.CLOUD;  // good but too long
        //ParticleOptions particle = ParticleTypes.ENCHANTED_HIT;  // good but blue
        ParticleOptions particle = ParticleTypes.SNOWFLAKE;
        level.addParticle(particle, player.getX(), player.getY() + 1.55, player.getZ(), XSpeed, YSpeed, ZSpeed);
    }
}
