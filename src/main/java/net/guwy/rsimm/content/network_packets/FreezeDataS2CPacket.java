package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.content.data.ArmorClientData;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.FlyMode;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FreezeDataS2CPacket {
    /**
     * sends the freeze amount of the armor to the client side to handle freeze particles
     */

    private final double freezing;

    public FreezeDataS2CPacket(double freezing) {
        this.freezing = freezing;
    }

    public FreezeDataS2CPacket(FriendlyByteBuf buf) {
        this.freezing = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(freezing);

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!

            ArmorClientData.setArmorFreezing(freezing);
        });
        return true;
    }
}
