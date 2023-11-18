package net.guwy.rsimm.index;

import net.guwy.rsimm.content.entities.projectiles.RepulsorBeamEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;

import javax.annotation.Nullable;

public class RsImmDamageSources {
    public static DamageSource repulsor_beam(RepulsorBeamEntity repulsorBeam, Entity entity){
        return new IndirectEntityDamageSource("repulsor_beam", repulsorBeam, entity).bypassEnchantments().bypassMagic();
    }
}
