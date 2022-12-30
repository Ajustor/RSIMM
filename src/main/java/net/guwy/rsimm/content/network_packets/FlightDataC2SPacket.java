package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FlightDataC2SPacket {
    private final double totalSpeed;

    public FlightDataC2SPacket(double totalSpeed) {
        this.totalSpeed = totalSpeed;
    }

    public FlightDataC2SPacket(FriendlyByteBuf buf) {
        this.totalSpeed = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(totalSpeed);

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            Level level = player.getLevel();

            player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {
                armorData.setMoveSpeed(totalSpeed);
            });
        });
        return true;
    }
}
