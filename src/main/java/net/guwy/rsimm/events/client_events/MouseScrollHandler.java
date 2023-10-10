package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.client.ArmorClientData;
import net.minecraftforge.client.event.InputEvent;

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
