package net.guwy.rsimm.mechanics.event.player_tick;

import net.guwy.rsimm.mechanics.event.player_tick.content.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;

public class PlayerTickEventOrganizer {
    public static void init(TickEvent.PlayerTickEvent event){
        int counter = event.player.tickCount;

        if((counter % 20) == 0){    //Every Second
            ChestSlotCheck.init(event);
            ReactorUtilization.init(event);
            ReactorPoisoningHandler.init(event);
            ArmorNBTWriteHandler.init(event);
            ReactorLoadHandler.init(event);

            //Player player = event.player;
            //ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
            //if(itemStack.getTag() != null){
            //    player.sendSystemMessage(Component.literal("Load: ").append(Long.toString(itemStack.getTag().getLong("PlayerReactorLoad"))));
            //    player.sendSystemMessage(Component.literal("Energy: ").append(Long.toString(itemStack.getTag().getLong("PlayerReactorEnergy"))));
            //    player.sendSystemMessage(Component.literal("EnergyCapacity: ").append(Long.toString(itemStack.getTag().getLong("PlayerReactorEnergyCapacity"))));
            //    player.sendSystemMessage(Component.literal("EnergyOverload: ").append(Long.toString(itemStack.getTag().getLong("PlayerReactorEnergyOutput"))));
            //}
        }



        //if((counter % 10) == 0){    //Every Half a Second
        //}



        //if((counter % 5) == 0){    //Every Quarter of a Second
        //}



        //if((counter % 1200) == 0){    //Every Minute (Not very reliable under some circumstances)
        //}
    }
}
