package net.guwy.rsimm.content.entities.non_living.rocket;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class RocketEntity extends Mob implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private LivingEntity owner;

    public RocketEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f).build();
    }

    @Override
    public void registerControllers(AnimationData data) {
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    public void tick() {
        double rad = 0.5;
        if(!this.level.isClientSide){
            blowEntities(rad);
            igniteBlocks(rad);
        }

        super.tick();
    }

    private void blowEntities(double rad){

        List<Entity> list = this.level.getEntities(this, new AABB(this.getX() - rad, this.getY() - rad, this.getZ() - rad,
                this.getX() + rad, this.getY() + rad, this.getZ() + rad));

        if(this.tickCount > 5){
            for(int i = 0; i < list.size(); i++){
                PrimedTnt primedtnt = new PrimedTnt(level, (double)this.getX() + 0.5, (double)this.getY(), (double)this.getZ() + 0.5, owner);
                primedtnt.setFuse(0);
                level.addFreshEntity(primedtnt);
                primedtnt.setFuse(1);
                level.addFreshEntity(primedtnt);
                primedtnt.setFuse(2);
                level.addFreshEntity(primedtnt);
                primedtnt.setFuse(3);
                level.addFreshEntity(primedtnt);
                this.discard();
            }
        }
    }

    private void igniteBlocks(double rad){
        double x = this.getX();
        double y = this.getY() + 0.2;
        double z = this.getZ();
        double checkScale = 0.21;
        boolean potentialHit = !level.getBlockState(new BlockPos(x, y + checkScale, this.getZ())).isAir()
                || !level.getBlockState(new BlockPos(x, y - checkScale, z)).isAir()
                || !level.getBlockState(new BlockPos(x + checkScale, y, z)).isAir()
                || !level.getBlockState(new BlockPos(x - checkScale, y, z)).isAir()
                || !level.getBlockState(new BlockPos(x, y, z + checkScale)).isAir()
                || !level.getBlockState(new BlockPos(x, y, z - checkScale)).isAir();
        if(potentialHit){
            PrimedTnt primedtnt = new PrimedTnt(level, (double)this.getX() + 0.5D, (double)this.getY(), (double)this.getZ() + 0.5D, owner);
            primedtnt.setFuse(0);
            level.addFreshEntity(primedtnt);
            primedtnt.setFuse(1);
            level.addFreshEntity(primedtnt);
            primedtnt.setFuse(2);
            level.addFreshEntity(primedtnt);
            primedtnt.setFuse(3);
            level.addFreshEntity(primedtnt);
            this.discard();
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public int getExperienceReward() {
        return 0;
    }

    @Override
    public boolean canHoldItem(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isNoAi() {
        return false;
    }

    @Override
    public boolean isColliding(BlockPos pPos, BlockState pState) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected float getBlockSpeedFactor() {
        return 1;
    }
}
