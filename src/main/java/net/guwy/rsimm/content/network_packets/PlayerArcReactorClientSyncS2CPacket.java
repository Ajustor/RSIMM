package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerBlockEntity;
import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerMenu;
import net.guwy.rsimm.content.data.ArcReactorClientData;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PlayerArcReactorClientSyncS2CPacket {
    /**
     * Used To sync the arc reactor of players
     *
     * basically each client holds a list of players with arc reactors to display
     */
    private final int itemId;
    private final UUID uuid;
    private final double energy;

    public PlayerArcReactorClientSyncS2CPacket(int itemId, UUID uuid, double energy) {
        this.itemId = itemId;
        this.uuid = uuid;
        this.energy = energy;
    }

    public PlayerArcReactorClientSyncS2CPacket(FriendlyByteBuf buf) {
        this.itemId = buf.readInt();
        this.uuid = buf.readUUID();
        this.energy = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(itemId);
        buf.writeUUID(uuid);
        buf.writeDouble(energy);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {


            if (this.itemId != 0){
                ArcReactorClientData.setReactorData(this.itemId, this.uuid, this.energy);
            } else {
                ArcReactorClientData.removeArcReactorData(this.uuid);
            }


        });
        return true;
    }
}
