package net.guwy.rsimm.mechanics.event.player_tick;

import net.guwy.rsimm.mechanics.event.player_tick.content.ChestSlotCheck;
import net.guwy.rsimm.mechanics.event.player_tick.content.ReactorPoisoningHandler;
import net.guwy.rsimm.mechanics.event.player_tick.content.ReactorUtilization;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;

public class PlayerTickEventOrganizer {
    public static void init(TickEvent.PlayerTickEvent event){
        int counter = event.player.tickCount;

        if((counter % 20) == 0){    //Every Second
            ChestSlotCheck.init(event);
            ReactorUtilization.init(event);
            ReactorPoisoningHandler.init(event);
        }



        //if((counter % 10) == 0){    //Every Half a Second
        //}



        //if((counter % 5) == 0){    //Every Quarter of a Second
        //}



        //if((counter % 1200) == 0){    //Every Minute (Not very reliable under some circumstances)
        //}
    }
}
