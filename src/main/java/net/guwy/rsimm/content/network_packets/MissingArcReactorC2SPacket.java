package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.index.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MissingArcReactorC2SPacket {
    private final int time;

    public MissingArcReactorC2SPacket(int time) {
        this.time = time;
    }

    public MissingArcReactorC2SPacket(FriendlyByteBuf buf) {
        this.time = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(time);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            // On the Server
            context.getSender().addEffect(new MobEffectInstance(ModEffects.MISSING_REACTOR.get(), time * 20, 0 ,false, false, true));

        });
        return true;
    }
}
