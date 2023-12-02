package net.guwy.rsimm.content.entities.projectiles;

import net.guwy.rsimm.index.RsImmParticles;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class RepulsorBeamEntity extends AbstractArrow {
    int lifetime, damage;

    public RepulsorBeamEntity(EntityType<RepulsorBeamEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RepulsorBeamEntity(EntityType<RepulsorBeamEntity> pEntityType, LivingEntity shooter, Level pLevel, int lifetime, int damage) {
        super(pEntityType, shooter, pLevel);

        this.lifetime = lifetime;
        this.damage = damage;
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return null;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    public void tick() {
        if (this.tickCount > 60){
            impact();
        }

        super.tick();
    }

    @Override
    protected void tickDespawn() {
        ++this.tickCount;
        if (this.tickCount >= lifetime) {
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        impact();
        super.onHitBlock(pResult);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private void impact(){
        if(this.level.isClientSide){
            //this.level.addParticle(ParticleTypes.FLASH, true,
            //        this.getX(), this.getY(), this.getZ(),
            //        0, 0, 0);
            //this.level.addParticle(ParticleTypes.SONIC_BOOM, true,
            //        this.getX(), this.getY(), this.getZ(),
            //        0, 0, 0);
        }
        //this.level.explode(this, this.getX(), this.getY(), this.getZ(), 0.5f, false, Explosion.BlockInteraction.BREAK);
        this.discard();
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void lerpMotion(double pX, double pY, double pZ) {
        super.lerpMotion(pX, pY, pZ);

        double tracikngMul = 0.98;
        this.level.addParticle(RsImmParticles.REPULSOR_BLAST_TRAIL_PARTICLE.get(),
                this.getX(), this.getY(), this.getZ(),
                this.getDeltaMovement().x() * tracikngMul, this.getDeltaMovement().y() * tracikngMul, this.getDeltaMovement().z() * tracikngMul);
    }
}
