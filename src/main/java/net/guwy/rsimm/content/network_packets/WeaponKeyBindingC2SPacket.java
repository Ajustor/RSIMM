package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.content.items.armors.AbstractIronmanArmorItem;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.guwy.rsimm.utils.KeyCallType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WeaponKeyBindingC2SPacket {
    /**
     * Sends a request to the server side with the key's call type for handling of this event
     *
     * Used to fire and cycle between selected weapons
     */
    private final KeyCallType keyCallType;
    public WeaponKeyBindingC2SPacket(KeyCallType fireCall) {
        this.keyCallType = fireCall;
    }

    public WeaponKeyBindingC2SPacket(FriendlyByteBuf buf) {
        this.keyCallType = buf.readEnum(KeyCallType.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(keyCallType);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {

                    if(armorData.getHasArmor()){
                        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                        AbstractIronmanArmorItem armor = (AbstractIronmanArmorItem) chestplate.getItem();
                        armor.weaponFireKeyAction(player, keyCallType);

                    }

                });
            });

        });
        return true;
    }
}
