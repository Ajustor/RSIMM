package net.guwy.rsimm.content.items;

import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class DevWand2Item extends Item {
    public DevWand2Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {

            AtomicBoolean hasSlot = new AtomicBoolean(false);
            AtomicBoolean hasReactor = new AtomicBoolean(false);
            AtomicReference<String> reactorTypeName = new AtomicReference<>("");
            AtomicInteger reactorTypeId = new AtomicInteger();
            AtomicLong reactorEnergyCapacity = new AtomicLong();
            AtomicLong reactorEnergy = new AtomicLong();
            AtomicLong reactorEnergyOutput = new AtomicLong();
            AtomicInteger reactorIdleDrain = new AtomicInteger();
            AtomicInteger reactorPoisonFactor = new AtomicInteger();
            AtomicInteger playerReactorPoisoning = new AtomicInteger();

            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                hasSlot.set(arcReactor.hasArcReactorSlot());
                hasReactor.set(arcReactor.hasArcReactor());
                reactorTypeName.set(arcReactor.getArcReactorTypeName());
                reactorTypeId.set(arcReactor.getArcReactorTypeId());
                reactorEnergyCapacity.set(arcReactor.getArcReactorEnergyCapacity());
                reactorEnergy.set(arcReactor.getArcReactorEnergy());
                reactorEnergyOutput.set(arcReactor.getArcReactorEnergyOutput());
                reactorIdleDrain.set(arcReactor.getArcReactorIdleDrain());
                reactorPoisonFactor.set(arcReactor.getArcReactorPoisonFactor());
                playerReactorPoisoning.set(arcReactor.getPlayerArcReactorPoisoning());
            });

            pPlayer.sendSystemMessage(Component.literal("hasSlot: " + Boolean.toString(hasSlot.get())));
            pPlayer.sendSystemMessage(Component.literal("hasReactor: " + Boolean.toString(hasReactor.get())));
            pPlayer.sendSystemMessage(Component.literal("reactorTypeName: " + reactorTypeName.get()));
            pPlayer.sendSystemMessage(Component.literal("reactorTypeId: " + Integer.toString(reactorTypeId.get())));
            pPlayer.sendSystemMessage(Component.literal("reactorEnergyCapacity: " + Long.toString(reactorEnergyCapacity.get())));
            pPlayer.sendSystemMessage(Component.literal("reactorEnergy: " + Long.toString(reactorEnergy.get())));
            pPlayer.sendSystemMessage(Component.literal("reactorEnergyOutput: " + Long.toString(reactorEnergyOutput.get())));
            pPlayer.sendSystemMessage(Component.literal("reactorIdleDrain: " + Integer.toString(reactorIdleDrain.get())));
            pPlayer.sendSystemMessage(Component.literal("reactorPoisonFactor: " + Integer.toString(reactorPoisonFactor.get())));
            pPlayer.sendSystemMessage(Component.literal("playerReactorPoisoning: " + Integer.toString(playerReactorPoisoning.get())));

            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
        }

        if(pLevel.isClientSide){
            pPlayer.swing(pUsedHand);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
