package net.guwy.rsimm.events.server_events;

import net.guwy.rsimm.content.items.ChestCutterItem;
import net.guwy.rsimm.index.RsImmEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class EntityAttackedHandler {
    public static void init(LivingAttackEvent event){
        if(!event.getEntity().getLevel().isClientSide){
            LivingEntity entity = event.getEntity();

            // When there's a attacker (e.g. the player who fired an arrow, the shulker who fired the bullet, etc.)
            if(event.getSource().getEntity() != null){
                LivingEntity attacker = (LivingEntity) event.getSource().getEntity();

                // Revenge trigger for someone stealing another persons arc reactor
                if(entity.hasEffect(RsImmEffects.STOLE_REACTOR.get()) && attacker.hasEffect(RsImmEffects.REACTOR_STOLEN.get())){
                    ChestCutterItem.triggerRevenge((Player) entity, (Player) attacker);
                }
            }
        }
    }
}
