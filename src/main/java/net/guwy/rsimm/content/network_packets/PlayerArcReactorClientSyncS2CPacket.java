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

    public PlayerArcReactorClientSyncS2CPacket(int itemId, UUID uuid) {
        this.itemId = itemId;
        this.uuid = uuid;
    }

    public PlayerArcReactorClientSyncS2CPacket(FriendlyByteBuf buf) {
        this.itemId = buf.readInt();
        this.uuid = buf.readUUID();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(itemId);
        buf.writeUUID(uuid);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {


            if (this.itemId != 0){
                ArcReactorClientData.setReactorData(this.itemId, this.uuid);
            } else {
                ArcReactorClientData.removeArcReactorData(this.uuid);
            }


        });
        return true;
    }
}
