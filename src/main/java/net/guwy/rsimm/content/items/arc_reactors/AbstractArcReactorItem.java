package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.index.ModSounds;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractArcReactorItem extends Item {
    /**
     * you gotta override
     * - displayName()
     * - maxEnergy()
     * - energy()
     * - energyOutput()
     * - idleDrain()
     * - poisonFactor()
     * - depletedItem()
     */


    public AbstractArcReactorItem(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    public abstract ResourceLocation OverlayIcon();

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
            pPlayer.getCooldowns().addCooldown(itemStack.getItem(), 20);

            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                if (arcReactor.hasArcReactorSlot()) {
                    if (!arcReactor.hasArcReactor()) {

                        // if the arc reactor item has any buffered forge energy transfer that back into the energy storage first
                        itemStack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energy -> {
                            ItemTagUtils.putLong(itemStack, "energy", ItemTagUtils.getLong(itemStack, "energy") + energy.getEnergyStored());
                        });

                        // bake the arc reactor to the player as capability
                        arcReactor.setArcReactor(displayName(), Item.getId(itemStack.getItem()), maxEnergy(), energy(itemStack),
                                energyOutput(), idleDrain(), poisonFactor());
                        // remove the item
                        itemStack.setCount(0);

                        // Fake player for sounds
                        Player soundPlayer = new Player(pLevel, pPlayer.getOnPos(), 0, pPlayer.getGameProfile(), null) {
                            @Override
                            public boolean isSpectator() {
                                return false;
                            }

                            @Override
                            public boolean isCreative() {
                                return false;
                            }
                        };
                        soundPlayer.playSound(ModSounds.ARC_REACTOR_EQUIP.get());
                        soundPlayer.discard();
                    } else {
                        pPlayer.sendSystemMessage(Component.translatable("arc_reactor.rsimm.already_have"));
                    }
                } else {
                    pPlayer.sendSystemMessage(Component.translatable("arc_reactor.rsimm.dont_have_slot"));
                }
            });
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            Component text;

            text = Component.translatable("arc_reactor.rsimm.energy").append(energy(pStack) + "/" + maxEnergy()).
                    withStyle(ChatFormatting.GRAY);
            pTooltipComponents.add(text);

            text = Component.translatable("arc_reactor.rsimm.energy_output").append(Long.toString(energyOutput())).
                    withStyle(ChatFormatting.GRAY);
            pTooltipComponents.add(text);

            text = Component.translatable("arc_reactor.rsimm.idle_drain").append(Integer.toString(idleDrain())).
                    withStyle(ChatFormatting.GRAY);
            pTooltipComponents.add(text);

            text = Component.translatable("arc_reactor.rsimm.posion_factor").append(Integer.toString(poisonFactor())).
                    withStyle(ChatFormatting.GRAY);
            pTooltipComponents.add(text);

        }   else {
            Component text = Component.translatable("arc_reactor.rsimm.energy").append(getTooltipBar(maxEnergy(), energy(pStack)))
                    .withStyle(getDisplayColour(maxEnergy(), energy(pStack)));

            pTooltipComponents.add(text);
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
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

    private String getTooltipBar(long maxEnergy, long energy){
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

    public abstract String displayName();

    public abstract long maxEnergy();

    public long energy(ItemStack itemStack){
        if(itemStack.getTag() != null) {
            return itemStack.getTag().getLong("energy");
        }   else {
            return maxEnergy();
        }
    }

    public abstract long energyOutput();

    public abstract int idleDrain();

    public abstract int poisonFactor();

    @Nullable
    public abstract Item depletedItem();

    public boolean shouldFillReactorIfNBTNotPresent(){
        return true;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        // will set arc reactor energy to max if there is no data set for the energy value
        // Which should be only in the case of it being taken from the creative inventory

        if(shouldFillReactorIfNBTNotPresent()){
            if(pStack.getTag() == null){
                CompoundTag tag = new CompoundTag();
                tag.putLong("energy", maxEnergy());

                pStack.setTag(tag);
            }
        }

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }


}
