package net.guwy.rsimm.mechanics.event.server_events;

import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class EntityHurtHandler {
    public static void init(LivingHurtEvent event){
        if(event.getSource().equals(DamageSource.IN_FIRE)){
            if(event.getEntity().getType().equals(EntityType.PLAYER)){
                Player player = (Player) event.getEntity();

                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                    if(armorData.getHasArmor() && !armorData.getHelmetOpen()){
                        event.setCanceled(true);
                    }
                });
            }
        }
    }
}
