package net.guwy.rsimm.events.server_events;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.custom.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;

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
