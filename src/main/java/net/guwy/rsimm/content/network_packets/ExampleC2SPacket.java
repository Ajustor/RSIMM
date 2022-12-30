package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntity;
import net.guwy.rsimm.index.ModEntityTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExampleC2SPacket {
    public ExampleC2SPacket() {

    }

    public ExampleC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            Mark1FlameEntity mark1FlameEntity = new Mark1FlameEntity(ModEntityTypes.MARK_1_FLAME.get(), level);
            mark1FlameEntity.setPos(player.getX(), player.getY() + 1.2f, player.getZ());
            mark1FlameEntity.setNoGravity(true);
            mark1FlameEntity.setSilent(true);

            double YLook = Math.sin(Math.toRadians(player.getViewXRot(1)));
            double XLook = Math.sin(Math.toRadians(player.getViewYRot(1)));
            double ZLook = Math.cos(Math.toRadians(player.getViewYRot(1)));

            double YSpeed =  (YLook * -0.5 / 3) + ((Math.random() - 0.5) * 0.1);
            double XSpeed = ((XLook * -0.5)) * (1 - Math.abs(YLook)) + ((Math.random() - 0.5) * 0.1);
            double ZSpeed = ((ZLook * 0.5)) * (1 - Math.abs(YLook)) + ((Math.random() - 0.5) * 0.1);

            mark1FlameEntity.setDeltaMovement(XSpeed, YSpeed, ZSpeed);

            level.addFreshEntity(mark1FlameEntity);


        });
        return true;
    }
}
