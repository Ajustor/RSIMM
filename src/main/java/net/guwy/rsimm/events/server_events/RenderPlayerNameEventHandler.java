package net.guwy.rsimm.events.server_events;

import net.guwy.rsimm.index.ModTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.eventbus.api.Event;

public class RenderPlayerNameEventHandler {
    public static void init(RenderNameTagEvent event){
        if(event.getEntity().getType().equals(EntityType.PLAYER)){
            Player player = (Player)event.getEntity();
            if(player.getItemBySlot(EquipmentSlot.HEAD).is(ModTags.Items.IRONMAN_HELMETS)){
                if(!player.getItemBySlot(EquipmentSlot.HEAD).is(ModTags.Items.FORCED_NAME_TAG_HELMETS)){
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}
