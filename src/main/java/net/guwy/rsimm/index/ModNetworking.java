package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.network_packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RsImm.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;


        net.messageBuilder(Mark1FlameThrowerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(Mark1FlameThrowerC2SPacket::new)
                .encoder(Mark1FlameThrowerC2SPacket::toBytes)
                .consumerMainThread(Mark1FlameThrowerC2SPacket::handle)
                .add();


        net.messageBuilder(FlightDataS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FlightDataS2CPacket::new)
                .encoder(FlightDataS2CPacket::toBytes)
                .consumerMainThread(FlightDataS2CPacket::handle)
                .add();

        net.messageBuilder(FlightDataC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FlightDataC2SPacket::new)
                .encoder(FlightDataC2SPacket::toBytes)
                .consumerMainThread(FlightDataC2SPacket::handle)
                .add();

        net.messageBuilder(FreezeDataS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FreezeDataS2CPacket::new)
                .encoder(FreezeDataS2CPacket::toBytes)
                .consumerMainThread(FreezeDataS2CPacket::handle)
                .add();


        net.messageBuilder(ArmorKeyBindingPressC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ArmorKeyBindingPressC2SPacket::new)
                .encoder(ArmorKeyBindingPressC2SPacket::toBytes)
                .consumerMainThread(ArmorKeyBindingPressC2SPacket::handle)
                .add();


        net.messageBuilder(ArmorKeyBindingHoldC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ArmorKeyBindingHoldC2SPacket::new)
                .encoder(ArmorKeyBindingHoldC2SPacket::toBytes)
                .consumerMainThread(ArmorKeyBindingHoldC2SPacket::handle)
                .add();

        net.messageBuilder(FlightKeyBindingPressC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FlightKeyBindingPressC2SPacket::new)
                .encoder(FlightKeyBindingPressC2SPacket::toBytes)
                .consumerMainThread(FlightKeyBindingPressC2SPacket::handle)
                .add();

        net.messageBuilder(FlightKeyBindingHoldC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FlightKeyBindingHoldC2SPacket::new)
                .encoder(FlightKeyBindingHoldC2SPacket::toBytes)
                .consumerMainThread(FlightKeyBindingHoldC2SPacket::handle)
                .add();

        net.messageBuilder(HandKeyBindingPressC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(HandKeyBindingPressC2SPacket::new)
                .encoder(HandKeyBindingPressC2SPacket::toBytes)
                .consumerMainThread(HandKeyBindingPressC2SPacket::handle)
                .add();

        net.messageBuilder(HandKeyBindingHoldingC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(HandKeyBindingHoldingC2SPacket::new)
                .encoder(HandKeyBindingHoldingC2SPacket::toBytes)
                .consumerMainThread(HandKeyBindingHoldingC2SPacket::handle)
                .add();

        net.messageBuilder(HandKeyBindingHoldReleaseC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(HandKeyBindingHoldReleaseC2SPacket::new)
                .encoder(HandKeyBindingHoldReleaseC2SPacket::toBytes)
                .consumerMainThread(HandKeyBindingHoldReleaseC2SPacket::handle)
                .add();

        net.messageBuilder(WeaponKeyBindingC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WeaponKeyBindingC2SPacket::new)
                .encoder(WeaponKeyBindingC2SPacket::toBytes)
                .consumerMainThread(WeaponKeyBindingC2SPacket::handle)
                .add();

        net.messageBuilder(SpecialKeyBindingC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SpecialKeyBindingC2SPacket::new)
                .encoder(SpecialKeyBindingC2SPacket::toBytes)
                .consumerMainThread(SpecialKeyBindingC2SPacket::handle)
                .add();

        net.messageBuilder(ArcReactorChargerClientSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ArcReactorChargerClientSyncS2CPacket::new)
                .encoder(ArcReactorChargerClientSyncS2CPacket::toBytes)
                .consumerMainThread(ArcReactorChargerClientSyncS2CPacket::handle)
                .add();


    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
