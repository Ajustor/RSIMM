package net.guwy.rsimm.content.items;

import net.guwy.rsimm.content.entities.projectiles.RepulsorBeamEntity;
import net.guwy.rsimm.enums.RepulsorAttackType;
import net.guwy.rsimm.index.RsImmEntityTypes;
import net.guwy.rsimm.mechanics.IItemEnergyContainer;
import net.guwy.rsimm.mechanics.ItemEnergyStorageImpl;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class AbstractRepulsorItem extends Item implements IItemEnergyContainer {
    int blastMaxCharge, blastMaxDamage, blastRange, blastMaxEnergyConsumption, blastKickBack;
    int beamChargeTime, beamMaxDamage, beamRange, beamEnergyConsumptionPerTick;
    int flightMaxThrust, flightMaxEnergyConsumption;
    int overheatTemp, blastHeat, beamHeat, flightMaxThrustHeat; double coolingMultiplier;
    int energyBuffer;

    String BEAM_CHARGE_TAG = "beam_charge", BLAST_CHARGE_TAG = "blast_charge", STARTED_CHARGING_TAG = "started_charging", HEAT_TAG = "heat";

    public AbstractRepulsorItem(Properties pProperties, int blastMaxCharge, int blastMaxDamage, int blastRange, int blastMaxEnergyConsumption, int blastKickBack,
                                int beamChargeTime, int beamMaxDamage, int beamRange, int beamEnergyConsumptionPerTick,
                                int flightMaxThrust, int flightMaxEnergyConsumption,
                                int overheatTemp, int blastHeat, int beamHeat, int flightMaxThrustHeat, double coolingMultiplier,
                                int energyBuffer) {
        super(pProperties);

        this.blastMaxCharge = blastMaxCharge;
        this.blastMaxDamage = blastMaxDamage;
        this.blastRange = blastRange;
        this.blastMaxEnergyConsumption = blastMaxEnergyConsumption;
        this.blastKickBack = blastKickBack;

        this.beamChargeTime = beamChargeTime;
        this.beamMaxDamage = beamMaxDamage;
        this.beamRange = beamRange;
        this.beamEnergyConsumptionPerTick = beamEnergyConsumptionPerTick;

        this.flightMaxThrust = flightMaxThrust;
        this.flightMaxEnergyConsumption = flightMaxEnergyConsumption;

        this.overheatTemp = overheatTemp;
        this.blastHeat = blastHeat;
        this.beamHeat = beamHeat;
        this.flightMaxThrustHeat = flightMaxThrustHeat;
        this.coolingMultiplier = coolingMultiplier;

        this.energyBuffer = energyBuffer;
    }



    public void useRepulsor(ItemStack itemStack, Level level, Player player, RepulsorAttackType attackType){
        double effectiveness = 1 - ((double)itemStack.getDamageValue() / itemStack.getMaxDamage());
        effectiveness = 0.4;

        // Offset for shooting from hand
        double playerY = player.getYRot();
        playerY += player.getItemInHand(InteractionHand.MAIN_HAND) == itemStack ? 180 : 0;      // Fires from the right side if the item is detected on the main hand
        playerY += player.getMainArm() == HumanoidArm.LEFT ? 180 : 0;      // Switches side if the player is left-handed
        double x = Math.cos(Math.toRadians(playerY)) * 0.35;
        double z = Math.sin(Math.toRadians(playerY)) * 0.35;


        // Beam Attack
        if(attackType == RepulsorAttackType.BEAM){
            beamAttack(itemStack, player, level, effectiveness, x, z);
        }
    }



    public void stopUsingRepulsor(ItemStack itemStack, Level level, Player player){
        if(ItemTagUtils.getBoolean(itemStack, STARTED_CHARGING_TAG)){
            ItemTagUtils.putBoolean(itemStack, STARTED_CHARGING_TAG, false);
            player.sendSystemMessage(Component.literal("! missing sound (Repulsor Powering Down)"));
        }

        // Beam Attack
        beamRelease(itemStack, player);

        // Blast Attack
        int blastCharge = ItemTagUtils.getInt(itemStack, BLAST_CHARGE_TAG);
        if(blastCharge > 0){
            player.sendSystemMessage(Component.literal("! missing sound"));

            ItemTagUtils.putInt(itemStack, BLAST_CHARGE_TAG, 0);
        }
    }



    private void beamAttack(ItemStack itemStack, Player player, Level level, double effectiveness, double xNudge, double yNudge){
        int charge = ItemTagUtils.getInt(itemStack, BEAM_CHARGE_TAG);
        AtomicInteger energy = new AtomicInteger(0);

        itemStack.getCapability(ForgeCapabilities.ENERGY).ifPresent(e -> {
            energy.set(e.getEnergyStored());
        });

        if(energy.get() > this.beamEnergyConsumptionPerTick){

            if(charge >= beamChargeTime){

                RepulsorBeamEntity repulsorBeam = new RepulsorBeamEntity(RsImmEntityTypes.REPULSOR_BEAM.get(), player, level,
                        beamRange, (int) (beamMaxDamage * Math.pow(effectiveness, 1.0/3.0 )));

                float speedMul = 3;
                repulsorBeam.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, speedMul, (float) (1 - effectiveness) * 3);

                repulsorBeam.setPos(repulsorBeam.getX() + xNudge, repulsorBeam.getY() -0.2, repulsorBeam.getZ() + yNudge);

                level.addFreshEntity(repulsorBeam);
                player.sendSystemMessage(Component.literal("! missing sound (Repulsor Beam Firing)"));

                // Decrease energy
                itemStack.getCapability(ForgeCapabilities.ENERGY).ifPresent(e -> {
                    e.extractEnergy(this.beamEnergyConsumptionPerTick, false);
                });
            }
            // Increase charge if the charge hasn't reached sufficient level yet
            else {

                if(!ItemTagUtils.getBoolean(itemStack, STARTED_CHARGING_TAG)){
                    player.sendSystemMessage(Component.literal("! missing sound (Repulsor Powering Up)"));
                    ItemTagUtils.putBoolean(itemStack, STARTED_CHARGING_TAG, true);
                }
                ItemTagUtils.putInt(itemStack, BEAM_CHARGE_TAG, charge + 1);
            }
        }
        // Play sound if there is no energy in the buffer
        else {
            player.sendSystemMessage(Component.literal("! missing sound (Repulsor Failed To Power Up)"));
        }
    }

    private void beamRelease(ItemStack itemStack, Player player){
        if(ItemTagUtils.getInt(itemStack, BEAM_CHARGE_TAG) > 0){
            ItemTagUtils.putInt(itemStack, BEAM_CHARGE_TAG, 0);
        }
    }



    /** The part that makes the arc reactor act like a battery,
     * Made a reality by the stolen code from yours truly Tomson124, the creator of Simply jetpack 2.
     * Seriously this I've spent like months figuring out how to do this myself and didn't manage to figure out anything.
     * I'm too dumb to do anything myself :P*/
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        IItemEnergyContainer container = this;
        return new ICapabilityProvider() {
            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                if (cap == ForgeCapabilities.ENERGY)
                    return LazyOptional.of(() -> new ItemEnergyStorageImpl(stack, container)).cast();
                return LazyOptional.empty();
            }
        };
    }



    public int getEnergyReceive() {
        return this.energyBuffer;
    }

    public int getEnergyExtract() {
        return this.energyBuffer;
    }

    public int getEnergyCapacity(){
        return Math.max(0, Math.min(Integer.MAX_VALUE, this.energyBuffer));
    }

    private void setEnergyStored(ItemStack stack, int value) {
        ItemTagUtils.putInt(stack, "energy", value);
    }

    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
         if (getEnergyReceive() == 0) return 0;
         int energyStored = getEnergy(stack);
         int energyReceived = Math.min(getCapacity(stack) - energyStored, Math.min(getEnergyReceive(), maxReceive));
         if (!simulate) setEnergyStored(stack, energyStored + energyReceived);
         return energyReceived;
    }

    // Modified to handle long variables as well
    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        if (getEnergyExtract() == 0) return 0;
        int energyStored = Math.max(0, Math.min(getEnergyCapacity(), getEnergy(stack)));
        int energyExtracted = Math.min(energyStored, Math.min(getEnergyExtract(), maxExtract));
        if (!simulate) setEnergyStored(stack, getEnergy(stack) - energyExtracted);
        return energyExtracted;
    }

    @Override
    public int getEnergy(ItemStack stack) {
        return Math.max(0, Math.min(Integer.MAX_VALUE, ItemTagUtils.getInt(stack, "energy")));
    }

    @Override
    public int getCapacity(ItemStack container) {
        return getEnergyCapacity();
    }
}
