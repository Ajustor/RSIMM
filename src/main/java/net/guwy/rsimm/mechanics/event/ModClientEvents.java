package net.guwy.rsimm.mechanics.event;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.Mark1ArmorOverlay;
import net.guwy.rsimm.content.entity.client.armor.Mark1ArmorRenderer;
import net.guwy.rsimm.content.entity.client.armor.TestArmorRenderer;
import net.guwy.rsimm.content.items.Mark1ArmorItem;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.guwy.rsimm.content.network_packets.ExampleC2SPacket;
import net.guwy.rsimm.index.ModKeyBindings;
import net.guwy.rsimm.index.ModNetworking;
import net.guwy.rsimm.mechanics.event.client_events.RegisterArmorRenderersEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ModClientEvents {



    @Mod.EventBusSubscriber(modid = RsImm.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(ModKeyBindings.IRONMAN_MENU_KEY.consumeClick()){
                ModNetworking.sendToServer(new ExampleC2SPacket());
            }
        }

    }



    @Mod.EventBusSubscriber(modid = RsImm.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents{

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(ModKeyBindings.IRONMAN_MENU_KEY);
        }

        @SubscribeEvent
        public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event){
            RegisterArmorRenderersEventHandler.init(event);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerBelowAll("mark_1_helmet", Mark1ArmorOverlay.HELMET_OVERLAY);
        }
    }



}
