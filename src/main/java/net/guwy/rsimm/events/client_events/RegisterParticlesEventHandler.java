package net.guwy.rsimm.events.client_events;

import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerScreen;
import net.guwy.rsimm.content.blocks.armor_equipping_station.ArmorEquippingStationScreen;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntityRenderer;
import net.guwy.rsimm.content.entities.non_living.rocket.RocketEntityRenderer;
import net.guwy.rsimm.content.entities.projectiles.RepulsorBlastRenderer;
import net.guwy.rsimm.content.particles.RepulsorBlastImpactParticle;
import net.guwy.rsimm.content.particles.RepulsorBlastTrailParticle;
import net.guwy.rsimm.index.RsImmEntityTypes;
import net.guwy.rsimm.index.RsImmMenuTypes;
import net.guwy.rsimm.index.RsImmParticles;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RegisterParticlesEventHandler {
    public static void init(RegisterParticleProvidersEvent event){
        event.register(RsImmParticles.REPULSOR_BLAST_TRAIL_PARTICLE.get(), RepulsorBlastTrailParticle.Provider::new);
        event.register(RsImmParticles.REPULSOR_BLAST_IMPACT_PARTICLE.get(), RepulsorBlastImpactParticle.Provider::new);
    }
}
