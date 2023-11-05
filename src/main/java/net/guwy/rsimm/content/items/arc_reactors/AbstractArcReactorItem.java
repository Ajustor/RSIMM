package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.config.RsImmServerConfigs;
import net.guwy.rsimm.index.RsImmSounds;
import net.guwy.rsimm.mechanics.IItemEnergyContainer;
import net.guwy.rsimm.mechanics.ItemEnergyStorageImpl;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class AbstractArcReactorItem extends Item implements IItemEnergyContainer {
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



    public abstract String displayName();

    public abstract long maxEnergy();

    public abstract long energyOutput();

    public abstract int idleDrain();

    public abstract int poisonFactor();

    @Nullable
    public abstract ResourceLocation depletedName();

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
                        if(pPlayer.getItemBySlot(EquipmentSlot.CHEST).isEmpty() || !RsImmServerConfigs.ARC_REACTOR_EXTRACT_INSERT_LIMITS.get()){
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
                            soundPlayer.playSound(RsImmSounds.ARC_REACTOR_EQUIP.get());
                            soundPlayer.discard();
                        }
                        // don't put it in and return a message when the players chestplate slot is full
                        else {
                            pPlayer.sendSystemMessage(Component.translatable("arc_reactor.rsimm.chest_blocked"));
                        }
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
            Component text;

            text = Component.translatable("arc_reactor.rsimm.energy").append(getTooltipBar(maxEnergy(), energy(pStack)))
                .withStyle(getDisplayColour(maxEnergy(), energy(pStack)));
            pTooltipComponents.add(text);

            if(energy(pStack) <= 0){
                text = Component.translatable("arc_reactor.rsimm.depleted")
                        .withStyle(ChatFormatting.DARK_GRAY);
                pTooltipComponents.add(text);
            }
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



    public long energy(ItemStack itemStack){
        if(itemStack.getTag() != null) {
            return itemStack.getTag().getLong("energy");
        }   else {
            return maxEnergy();
        }
    }

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



    /** Turns the reactor to its depleted counterpart if the energy is 0 */
    public void checkAndTransformDepletion(ItemStack stack){
        if(ItemTagUtils.getLong(stack, "energy") <= 0){
            ItemTagUtils.putInt(stack, "CustomModelData", 1);
            if(depletedName() != null)
                stack.setHoverName(Component.translatable(depletedName().getPath()));
        } else {
            ItemTagUtils.putInt(stack, "CustomModelData", 0);
            stack.resetHoverName();
        }

    }



    /** The part that makes the arc reactor act like a battery,
     * Made a reality by the stolen code from yours truly Tomson124, the creator of Simply jetpack 2.
     * Seriously this I've spent like months figuring out how to do this myself and didn't manage to figure out anything.
     * I'm too dumb to do anything myself :P*/
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        IItemEnergyContainer container = this;
        return new ICapabilityProvider() {
            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                if (cap == ForgeCapabilities.ENERGY)
                    return LazyOptional.of(() -> new ItemEnergyStorageImpl(stack, container)).cast();
                return LazyOptional.empty();
            }
        };
    }



    // public int getEnergyReceive() {
    //     return 500;
    // }

    public int getEnergyExtract() {
        return (int) Math.max(0, Math.min(Integer.MAX_VALUE, energyOutput()));
    }

    public int getEnergyCapacity(){
        return (int) Math.max(0, Math.min(Integer.MAX_VALUE, maxEnergy()));
    }

    private void setEnergyStored(ItemStack stack, long value) {
        ItemTagUtils.putLong(stack, "energy", value);

        checkAndTransformDepletion(stack);
    }

    /** Use this for getting the energy */
    public long getEnergyStored(ItemStack stack) {
        return ItemTagUtils.getLong(stack, "energy");
    }

    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate) {
        return 0;
        // if (getEnergyReceive() == 0) return 0;
        // int energyStored = getEnergy(stack);
        // int energyReceived = Math.min(getCapacity(stack) - energyStored, Math.min(getEnergyReceive(), maxReceive));
        // if (!simulate) setEnergyStored(stack, energyStored + energyReceived);
        // return energyReceived;
    }

    // Modified to handle long variables as well
    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate) {
        if (getEnergyExtract() == 0) return 0;
        int energyStored = (int) Math.max(0, Math.min(getEnergyCapacity(), getEnergyStored(stack)));
        int energyExtracted = Math.min(energyStored, Math.min(getEnergyExtract(), maxExtract));
        if (!simulate) setEnergyStored(stack, getEnergyStored(stack) - energyExtracted);
        return energyExtracted;
    }

    /** Don't use this as it doesn't support long variables. This is for reporting back to the forge energy capability,
     * use getEnergyStored() instead */
    @Override
    public int getEnergy(ItemStack stack) {
        return (int) Math.max(0, Math.min(Integer.MAX_VALUE, ItemTagUtils.getLong(stack, "energy")));
    }

    @Override
    public int getCapacity(ItemStack container) {
        return getEnergyCapacity();
    }


}
