package net.guwy.rsimm.mechanics.event.client_events;

import net.guwy.rsimm.content.data.ArmorClientData;
import net.guwy.rsimm.content.overlays.Mark1ArmorOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;

public class MouseScrollHandler {
    public static void init(final InputEvent.MouseScrollingEvent event){
        if(ArmorClientData.isIsFlying()){
            if(event.getScrollDelta() < 0){
                ArmorClientData.setAccelerationAmount(Math.max(0, ArmorClientData.getAccelerationAmount() - 0.05));
            }
            if(event.getScrollDelta() > 0){
                ArmorClientData.setAccelerationAmount(Math.min(1, ArmorClientData.getAccelerationAmount() + 0.05));
            }
        }

    }
}
