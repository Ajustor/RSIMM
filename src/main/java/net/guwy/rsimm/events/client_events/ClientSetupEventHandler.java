package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerScreen;
import net.guwy.rsimm.content.blocks.armor_equipping_station.ArmorEquippingStationScreen;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntityRenderer;
import net.guwy.rsimm.content.entities.non_living.rocket.RocketEntityRenderer;
import net.guwy.rsimm.content.entities.projectiles.RepulsorBeamRenderer;
import net.guwy.rsimm.content.entities.projectiles.RepulsorBlastRenderer;
import net.guwy.rsimm.index.RsImmEntityTypes;
import net.guwy.rsimm.index.RsImmMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetupEventHandler {
    public static void init(FMLClientSetupEvent event){
        MenuScreens.register(RsImmMenuTypes.ARC_REACTOR_CHARGER_MENU.get(), ArcReactorChargerScreen::new);
        MenuScreens.register(RsImmMenuTypes.ARMOR_EQUIPPING_STATION_MENU.get(), ArmorEquippingStationScreen::new);

        EntityRenderers.register(RsImmEntityTypes.MARK_1_FLAME.get(), Mark1FlameEntityRenderer::new);
        EntityRenderers.register(RsImmEntityTypes.ROCKET.get(), RocketEntityRenderer::new);

        EntityRenderers.register(RsImmEntityTypes.REPULSOR_BLAST.get(), RepulsorBlastRenderer::new);
        EntityRenderers.register(RsImmEntityTypes.REPULSOR_BEAM.get(), RepulsorBeamRenderer::new);
    }
}
