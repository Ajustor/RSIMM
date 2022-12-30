package net.guwy.rsimm.content.items;

import net.guwy.rsimm.content.items.arc_reactors.AbstractArcReactorItem;
import net.guwy.rsimm.index.ModSounds;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DevWand4Item extends Item {
    public DevWand4Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {

            pPlayer.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                pPlayer.sendSystemMessage(Component.literal("hasArmor: ").append(Boolean.toString(armorData.getHasArmor())));
                pPlayer.sendSystemMessage(Component.literal("systemStatus: ").append(Double.toString(armorData.getSystemStatus())));
                pPlayer.sendSystemMessage(Component.literal("boot: ").append(Double.toString(armorData.getBoot())));
                pPlayer.sendSystemMessage(Component.literal("armorEnergy: ").append(Long.toString(armorData.getArmorEnergy())));
                pPlayer.sendSystemMessage(Component.literal("armorMaxStableEnergy: ").append(Long.toString(armorData.getArmorMaxStableEnergy())));
                pPlayer.sendSystemMessage(Component.literal("armorEnergyType: ").append(armorData.getArmorEnergyType().toString()));
                pPlayer.sendSystemMessage(Component.literal("armorFreezing: ").append(Double.toString(armorData.getArmorFreezing())));
                pPlayer.sendSystemMessage(Component.literal("armorStorage1: ").append(Integer.toString(armorData.getArmorStorage(1))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage2: ").append(Integer.toString(armorData.getArmorStorage(2))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage3: ").append(Integer.toString(armorData.getArmorStorage(3))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage4: ").append(Integer.toString(armorData.getArmorStorage(4))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage5: ").append(Integer.toString(armorData.getArmorStorage(5))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage6: ").append(Integer.toString(armorData.getArmorStorage(6))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage7: ").append(Integer.toString(armorData.getArmorStorage(7))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage8: ").append(Integer.toString(armorData.getArmorStorage(8))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage9: ").append(Integer.toString(armorData.getArmorStorage(9))));
                pPlayer.sendSystemMessage(Component.literal("armorStorage10: ").append(Integer.toString(armorData.getArmorStorage(10))));
                pPlayer.sendSystemMessage(Component.literal("isFlying: ").append(Boolean.toString(armorData.getIsFlying())));
                pPlayer.sendSystemMessage(Component.literal("flyMode: ").append(armorData.getFlyMode().toString()));
                pPlayer.sendSystemMessage(Component.literal("helmetOpen: ").append(Boolean.toString(armorData.getHelmetOpen())));
                pPlayer.sendSystemMessage(Component.literal("rightHandCharge: ").append(Double.toString(armorData.getRightHandCharge())));
                pPlayer.sendSystemMessage(Component.literal("leftHandCharge: ").append(Double.toString(armorData.getLeftHandCharge())));
                pPlayer.sendSystemMessage(Component.literal("HandsActTogether: ").append(Boolean.toString(armorData.getHandActTogether())));
                pPlayer.sendSystemMessage(Component.literal("uniBeamCharge: ").append(Double.toString(armorData.getUniBeamCharge())));
                pPlayer.sendSystemMessage(Component.literal("flightSpeed: ").append(Double.toString(armorData.getFlightSpeed())));
                pPlayer.sendSystemMessage(Component.literal("maxFlightSpeed: ").append(Double.toString(armorData.getMaxFlightSpeed())));
                pPlayer.sendSystemMessage(Component.literal("minFlightSpeed: ").append(Double.toString(armorData.getMinFlightSpeed())));

            });

            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
        }

        if(pLevel.isClientSide){
            pPlayer.swing(pUsedHand);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
