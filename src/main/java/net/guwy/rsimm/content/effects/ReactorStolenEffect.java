package net.guwy.rsimm.content.effects;

import net.guwy.rsimm.index.RsImmEffects;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

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

            LivingEntity entity = null;
            double r = 5;

            // Get entities within radius
            List<Entity> list = player.getLevel().getEntities(null, new AABB(
                    player.getX()-r, player.getY()-r, player.getZ()-r,
                    player.getX()+r, player.getY()+r, player.getZ()+r));

            // Go through each entity in radius and check if they are a player with "stole reactor" effect
            for (Entity e : list) {
                if (e.getType() == EntityType.PLAYER && ((LivingEntity) e).hasEffect(RsImmEffects.STOLE_REACTOR.get())) {
                    entity = (LivingEntity) e;
                }
            }

            // Look at the player if there is an entity that matches the specifications
            if(entity != null)
                player.lookAt(EntityAnchorArgument.Anchor.FEET, entity.position());
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}
