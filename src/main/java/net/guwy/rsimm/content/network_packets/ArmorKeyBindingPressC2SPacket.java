package net.guwy.rsimm.content.network_packets;

import net.guwy.rsimm.content.items.armors.AbstractIronmanArmorItem;
import net.guwy.rsimm.index.ModSounds;
import net.guwy.rsimm.index.ModTags;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class ArmorKeyBindingPressC2SPacket {
    /**
     * Key Binding Transmitter to the server for keybinding handling
     *
     * Should probably be reformatted to be like the weapon key binding
     */


    private final UUID uuid;

    public ArmorKeyBindingPressC2SPacket(UUID senderUuid) {
        this.uuid = senderUuid;
    }

    public ArmorKeyBindingPressC2SPacket(FriendlyByteBuf buf) {
        this.uuid = buf.readUUID();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(uuid);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            Player player = context.getSender().level.getPlayerByUUID(this.uuid);
            Level level = player.getLevel();

            player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armorData -> {

                    // The part that does the armor stuff
                    if(armorData.getHasArmor()){
                        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                        AbstractIronmanArmorItem armor = (AbstractIronmanArmorItem) chestplate.getItem();
                        armor.armorKeyPressAction(player);

                    }
                    // The part that does the Arc Reactor Stuff
                    else {
                        if(arcReactor.hasArcReactor()){

                            Component titleText = Component.translatable("arc_reactor.rsimm.chat_display_title").withStyle(ChatFormatting.GOLD);
                            Component nameText = Component.translatable("arc_reactor.rsimm.chat_display_name").append(arcReactor.getArcReactorTypeName()).withStyle(ChatFormatting.AQUA);
                            Component energyText = Component.translatable("arc_reactor.rsimm.energy").withStyle(ChatFormatting.AQUA)
                                            .append(getEnergyBar(arcReactor.getArcReactorEnergyCapacity(), arcReactor.getArcReactorEnergy()))
                                                    .withStyle(getDisplayColour(arcReactor.getArcReactorEnergyCapacity(), arcReactor.getArcReactorEnergy()));

                            player.sendSystemMessage(titleText);
                            player.sendSystemMessage(nameText);
                            player.sendSystemMessage(energyText);

                            // Arc Reactor Check Sound
                            // Fake player for sounds
                            Player soundPlayer = new Player(level, player.getOnPos(), 0, player.getGameProfile(), null) {
                                @Override
                                public boolean isSpectator() {
                                    return false;
                                }

                                @Override
                                public boolean isCreative() {
                                    return false;
                                }
                            };
                            soundPlayer.playSound(ModSounds.ARC_REACTOR_CHECK.get());
                        }
                    }

                });
            });

        });
        return true;
    }

    private String getEnergyBar(long maxEnergy, long energy){
        StringBuilder str = new StringBuilder();
        long increments = maxEnergy / 20;
        for(int i=1; i <= 20; i++){
            if(energy >= increments * i){
                str.append("|");
            }   else {
                str.append(".");
            }
        }
        return str.toString();
    }

    private ChatFormatting getDisplayColour(long maxEnergy, long energy){
        long increments = maxEnergy / 10;
        if(energy == 0){
            return ChatFormatting.DARK_GRAY;
        } else if(energy <= increments){
            return ChatFormatting.DARK_RED;
        } else if (energy <= increments*2) {
            return ChatFormatting.RED;
        } else if (energy <= increments*4) {
            return ChatFormatting.YELLOW;
        }   else {
            return ChatFormatting.AQUA;
        }
    }
}
