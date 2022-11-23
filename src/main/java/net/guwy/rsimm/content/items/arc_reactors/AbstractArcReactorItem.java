package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.content.effects.ForcedSoundEffect;
import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.index.ModSounds;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide) {
            ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                if (arcReactor.hasArcReactorSlot()) {
                    if (!arcReactor.hasArcReactor()) {
                        arcReactor.setArcReactor(displayName(), Item.getId(itemStack.getItem()), maxEnergy(), energy(itemStack),
                                energyOutput(), idleDrain(), poisonFactor());
                        itemStack.setCount(0);
                        ForcedSoundEffect.applyForcedSoundEffect(pPlayer, ModEffects.SOUND_EFFECT_ARC_REACTOR_EQUIP.get());
                        pPlayer.getCooldowns().addCooldown(itemStack.getItem(), 20);
                    } else {
                        pPlayer.sendSystemMessage(Component.translatable("arc_reactor.rsimm.already_have"));
                    }
                } else {
                    pPlayer.sendSystemMessage(Component.translatable("arc_reactor.rsimm.already_have_slot"));
                }
            });

            pLevel.playSound(pPlayer, pPlayer.blockPosition(), ModSounds.ARC_REACTOR_EQUIP.get(), SoundSource.PLAYERS, 100f, 1f);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        Component text = Component.translatable("arc_reactor.rsimm.energy");
        text = Component.literal(text + getTooltipBar(maxEnergy(), energy(pStack)))
                .withStyle(getDisplayColour(maxEnergy(), energy(pStack)));

        pTooltipComponents.add(text);
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
            return 0;
        }
    }

    public abstract long energyOutput();

    public abstract int idleDrain();

    public abstract int poisonFactor();

    public abstract Item depletedItem();
}
