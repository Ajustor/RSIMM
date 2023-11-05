package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RsImmParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, RsImm.MOD_ID);

    public static final RegistryObject<SimpleParticleType> REPULSOR_BLAST_TRAIL_PARTICLE =
            PARTICLE_TYPES.register("repulsor_blast_trail_particle", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> REPULSOR_BLAST_IMPACT_PARTICLE =
            PARTICLE_TYPES.register("repulsor_blast_impact_particle", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
