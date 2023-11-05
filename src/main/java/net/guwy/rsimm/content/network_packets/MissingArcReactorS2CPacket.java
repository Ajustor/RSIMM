package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.config.RsImmClientConfigs;
import net.guwy.rsimm.index.RsImmNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MissingArcReactorS2CPacket {
    private final int time;

    public MissingArcReactorS2CPacket(int time) {
        this.time = time;
    }

    public MissingArcReactorS2CPacket(FriendlyByteBuf buf) {
        this.time = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(time);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            // On the Client
            if(RsImmClientConfigs.ARC_REACTOR_DEATH_TIME.get() < 10){
                RsImmNetworking.sendToServer(new MissingArcReactorC2SPacket(time));
            } else {
                RsImmNetworking.sendToServer(new MissingArcReactorC2SPacket(Math.min(time, RsImmClientConfigs.ARC_REACTOR_DEATH_TIME.get())));
            }


        });
        return true;
    }
}
