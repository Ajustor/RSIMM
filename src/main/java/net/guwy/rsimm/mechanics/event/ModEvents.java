package net.guwy.rsimm.mechanics.event;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntity;
import net.guwy.rsimm.content.entities.non_living.rocket.RocketEntity;
import net.guwy.rsimm.index.ModEntityTypes;
import net.guwy.rsimm.mechanics.event.player_tick.PlayerTickEventOrganizer;
import net.guwy.rsimm.mechanics.event.server_events.*;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;


public class ModEvents {

    @Mod.EventBusSubscriber(modid = RsImm.MOD_ID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side == LogicalSide.SERVER) {
                if(event.phase == TickEvent.Phase.END){
                    PlayerTickEventOrganizer.init(event);
                }

            }
        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
            AttachCapabilitiesHandler.initEntity(event);
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            CapabilityCarryOverDeathHandler.init(event);
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            RegisterCapabilitiesHandler.init(event);
        }

        @SubscribeEvent
        public static void onRemoveEffect(MobEffectEvent.Remove event) {
            PotionRemoveInterceptor.init(event);
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

        @SubscribeEvent
        public static void entityHurtEvent(LivingHurtEvent event) {
            EntityHurtHandler.init(event);
        }
    }

    @Mod.EventBusSubscriber(modid = RsImm.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.MARK_1_FLAME.get(), Mark1FlameEntity.setAttributes());
            event.put(ModEntityTypes.ROCKET.get(), RocketEntity.setAttributes());
        }
    }






}
