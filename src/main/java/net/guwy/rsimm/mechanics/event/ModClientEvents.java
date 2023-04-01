package net.guwy.rsimm.mechanics.event;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.overlays.Mark1ArmorOverlay;
import net.guwy.rsimm.mechanics.event.client_events.*;
import net.guwy.rsimm.mechanics.event.server_events.RenderPlayerEventPreHandler;
import net.guwy.rsimm.mechanics.event.server_events.RenderPlayerNameEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModClientEvents {



    @Mod.EventBusSubscriber(modid = RsImm.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            OnKeyInputHandler.init(event);

        }

        @SubscribeEvent
        public static void onMouseScroll(InputEvent.MouseScrollingEvent event){
            MouseScrollHandler.init(event);

        }

        @SubscribeEvent
        public static void renderPlayerEvent(RenderPlayerEvent.Pre event) {
            RenderPlayerEventPreHandler.init(event);
        }

        @SubscribeEvent
        public static void renderArmEvent(RenderArmEvent event) {
            //RenderArmEventHandler.init(event);
        }

        @SubscribeEvent
        public static void renderPlayerNameEvent(RenderNameTagEvent event) {
            RenderPlayerNameEventHandler.init(event);
        }

    }



    @Mod.EventBusSubscriber(modid = RsImm.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents{

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            OnKeyRegisterHandler.init(event);
        }

        @SubscribeEvent
        public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event){
            RegisterArmorRenderersEventHandler.init(event);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            RegisterGuiOverlaysEventHandler.init(event);
        }
    }



}
