package net.guwy.rsimm.events.server_events;

import net.guwy.rsimm.index.ModTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.client.event.RenderArmEvent;

public class RenderArmEventHandler {
    public static void init(RenderArmEvent event){
        if(event.getPlayer().getItemBySlot(EquipmentSlot.CHEST).is(ModTags.Items.IRONMAN_CHESTPLATES)){
            event.setCanceled(true);
        }
    }
}
