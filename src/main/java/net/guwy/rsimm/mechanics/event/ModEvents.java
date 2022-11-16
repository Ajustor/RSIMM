package net.guwy.rsimm.mechanics.event;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlot;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorData;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.guwy.rsimm.mechanics.event.player_tick.PlayerTickEventOrganizer;
import net.guwy.rsimm.mechanics.event.subclasses.PotionRemoveInterceptor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
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
    public static void onRemoveEffect(PotionEvent.PotionRemoveEvent event) {
        PotionRemoveInterceptor.init(event);
    }




}
