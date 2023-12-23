package net.guwy.rsimm.events.player_tick.content;

import net.guwy.rsimm.index.RsImmTags;
import net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;

public class ArmorNBTWriteHandler {
    public static void init(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        Level level = player.level;

        player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
            if(player.getItemBySlot(EquipmentSlot.CHEST).is(RsImmTags.Items.IRONMAN_CHESTPLATES)){

                ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
                CompoundTag nbtTag;

                if(itemStack.getTag() != null){
                    nbtTag = itemStack.getTag();
                }   else {
                    nbtTag = new CompoundTag();
                }

                nbtTag.putLong("PlayerReactorLoad", arcReactor.getEnergyLoad());
                nbtTag.putLong("PlayerReactorEnergy", arcReactor.getArcReactorEnergy());
                nbtTag.putLong("PlayerReactorEnergyCapacity", arcReactor.getArcReactorEnergyCapacity());
                nbtTag.putLong("PlayerReactorEnergyOutput", arcReactor.getArcReactorEnergyOutput());

                itemStack.setTag(nbtTag);
            }
        });


    }
}
