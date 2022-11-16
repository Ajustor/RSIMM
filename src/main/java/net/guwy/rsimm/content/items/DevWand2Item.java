package net.guwy.rsimm.content.items;

import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.network.chat.TextComponent;
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

            pPlayer.sendMessage(new TextComponent("hasSlot: " + Boolean.toString(hasSlot.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("hasReactor: " + Boolean.toString(hasReactor.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("reactorTypeName: " + reactorTypeName.get()), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("reactorTypeId: " + Integer.toString(reactorTypeId.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("reactorEnergyCapacity: " + Long.toString(reactorEnergyCapacity.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("reactorEnergy: " + Long.toString(reactorEnergy.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("reactorEnergyOutput: " + Long.toString(reactorEnergyOutput.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("reactorIdleDrain: " + Integer.toString(reactorIdleDrain.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("reactorPoisonFactor: " + Integer.toString(reactorPoisonFactor.get())), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("playerReactorPoisoning: " + Integer.toString(playerReactorPoisoning.get())), pPlayer.getUUID());

            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
        }

        if(pLevel.isClientSide){
            pPlayer.swing(pUsedHand);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
