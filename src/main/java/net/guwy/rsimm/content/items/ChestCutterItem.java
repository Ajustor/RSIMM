package net.guwy.rsimm.content.items;

import net.guwy.rsimm.content.effects.ForcedSoundEffect;
import net.guwy.rsimm.index.ModEffects;
import net.guwy.rsimm.index.ModItems;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChestCutterItem extends Item {
    public ChestCutterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            pPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                if(!arcReactor.hasArcReactorSlot()){
                    arcReactor.setHasArcReactorSlot(true);
                    ForcedSoundEffect.applyForcedSoundEffect(pPlayer, ModEffects.SOUND_EFFECT_CHEST_CUTTING.get());
                    pPlayer.addEffect(new MobEffectInstance(ModEffects.SOUND_EFFECT_CHEST_CUTTING.get(),
                            30, 1, false, false, false));
                }   else {
                    pPlayer.sendSystemMessage(Component.translatable("message.rsimm.chest_cutter.don't_have_slot"));
                }
            });

            pPlayer.getCooldowns().addCooldown(ModItems.CHEST_CUTTER.get(), 40);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
