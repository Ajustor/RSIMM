package net.guwy.rsimm.content.items;

import net.guwy.rsimm.content.items.arc_reactors.AbstractArcReactorItem;
import net.guwy.rsimm.index.ModSounds;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DevWand4Item extends Item {
    public DevWand4Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {

            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                boolean hasSlot = (arcReactor.hasArcReactorSlot());

                if(hasSlot){
                    if(arcReactor.getArcReactorTypeId() != 0){

                        ItemStack itemStack = new ItemStack(Item.byId(arcReactor.getArcReactorTypeId()));
                        if(arcReactor.getArcReactorEnergy() > 0){
                            CompoundTag nbtTag = new CompoundTag();
                            nbtTag.putLong("energy", arcReactor.getArcReactorEnergy());
                            itemStack.setTag(nbtTag);
                            pPlayer.addItem(itemStack);
                        }   else {
                            AbstractArcReactorItem arcReactorItem = (AbstractArcReactorItem) itemStack.getItem();
                            ItemStack depletedItem = new ItemStack(arcReactorItem.depletedItem());
                            pPlayer.addItem(depletedItem);
                        }
                        pPlayer.playSound(ModSounds.ARC_REACTOR_UNEQUIP.get(), 100, 1);

                        arcReactor.deleteArcReactor();

                        pPlayer.sendSystemMessage(Component.literal("Arc Reactor Has Been Removed"));
                    }   else {
                        arcReactor.deleteArcReactor();
                        arcReactor.setHasArcReactorSlot(false);
                        arcReactor.setPlayerArcReactorPoisoning(0);
                        pPlayer.sendSystemMessage(Component.literal("Arc Reactor Slot Has Been Removed"));
                    }
                }
            });

            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 20);
        }

        if(pLevel.isClientSide){
            pPlayer.swing(pUsedHand);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
