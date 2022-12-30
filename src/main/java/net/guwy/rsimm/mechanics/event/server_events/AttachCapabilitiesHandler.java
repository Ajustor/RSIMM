package net.guwy.rsimm.mechanics.event.server_events;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class AttachCapabilitiesHandler {

    public static void initEntity(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).isPresent()) {
                event.addCapability(new ResourceLocation(RsImm.MOD_ID, "properties"), new ArcReactorSlotProvider());
            }
            if (!event.getObject().getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).isPresent()) {
                event.addCapability(new ResourceLocation(RsImm.MOD_ID, "armor_data"), new IronmanArmorDataProvider());
            }
        }
    }
}
