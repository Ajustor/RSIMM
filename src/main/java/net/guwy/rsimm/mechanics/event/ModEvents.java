package net.guwy.rsimm.mechanics.event;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorData;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.guwy.rsimm.mechanics.event.player_tick.PlayerTickEventOrganizer;
import net.guwy.rsimm.mechanics.event.server_events.PotionRemoveInterceptor;
import net.guwy.rsimm.mechanics.event.server_events.RenderArmEventHandler;
import net.guwy.rsimm.mechanics.event.server_events.RenderPlayerEventPreHandler;
import net.guwy.rsimm.mechanics.event.server_events.RenderPlayerNameEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RsImm.MOD_ID)
public class ModEvents {
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
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).isPresent()) {
                event.addCapability(new ResourceLocation(RsImm.MOD_ID, "properties"), new ArcReactorSlotProvider());
            }
            if(!event.getObject().getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).isPresent()) {
                event.addCapability(new ResourceLocation(RsImm.MOD_ID, "armor_data"), new IronmanArmorDataProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(oldStore -> {
                event.getEntity().getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(newStore -> {

                    if(oldStore.getPlayerArcReactorPoisoning() > oldStore.getMaximumPoisoning() * 3/4){
                        oldStore.setPlayerArcReactorPoisoning(oldStore.getMaximumPoisoning() * 3/4);        //gives you 12 days if the poison factor is 14 (43 if its 4)
                    }
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().invalidateCaps();
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ArcReactorSlot.class);
        event.register(IronmanArmorData.class);
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
        RenderArmEventHandler.init(event);
    }

    @SubscribeEvent
    public static void renderPlayerNameEvent(RenderNameTagEvent event) {
        RenderPlayerNameEventHandler.init(event);
    }





}
