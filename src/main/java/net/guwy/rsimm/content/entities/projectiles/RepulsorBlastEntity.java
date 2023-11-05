package net.guwy.rsimm.content.entities.projectiles;

import net.guwy.rsimm.index.RsImmParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class RepulsorBlastEntity extends AbstractArrow {
    public RepulsorBlastEntity(EntityType<RepulsorBlastEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RepulsorBlastEntity(EntityType<RepulsorBlastEntity> pEntityType, double x, double y, double z, Level pLevel) {
        super(pEntityType, x, y, z, pLevel);
    }

    public RepulsorBlastEntity(EntityType<RepulsorBlastEntity> pEntityType, LivingEntity shooter, Level pLevel) {
        super(pEntityType, shooter, pLevel);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.WARDEN_ATTACK_IMPACT;
    }

    @Override
    public void tick() {
        if (this.tickCount > 60){
            explode();
        }


        super.tick();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        explode();
        super.onHitBlock(pResult);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private void explode(){
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
    public void onClientRemoval() {
        this.level.addParticle(ParticleTypes.FLASH, true,
                this.getX(), this.getY(), this.getZ(),
                0, 0, 0);
        this.level.addParticle(RsImmParticles.REPULSOR_BLAST_IMPACT_PARTICLE.get(), true,
                this.getX(), this.getY(), this.getZ(),
                0, 0, 0);

        for(int h = 0; h < 360; h += 18){
            for(int w = 0; w < 360; w += 45) {
                double y = Math.sin(Math.toRadians(h));
                double x = Math.cos(Math.toRadians(w)) * (1.0 - Math.abs(y));
                double z = Math.sin(Math.toRadians(w)) * (1.0 - Math.abs(y));
                double m = 3;

                this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, true,
                        this.getX(), this.getY(), this.getZ(),
                        x * m + (Math.random() - 0.5), y * m + (Math.random() - 0.5), z * m + (Math.random() - 0.5));
            }
        }

        super.onClientRemoval();
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
