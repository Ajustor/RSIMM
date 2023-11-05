package net.guwy.rsimm.content.items.arc_reactors;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractUnchargedArcReactorItem extends Item {
    public AbstractUnchargedArcReactorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        pTooltipComponents.add(Component.translatable("arc_reactor.rsimm.uncharged.charge").append(Long.toString(getCharge(pStack)))
                .append("/").append(Long.toString(getRequiredCharge())).withStyle(ChatFormatting.GRAY));

        pTooltipComponents.add(Component.translatable("arc_reactor.rsimm.uncharged.charge_loss").append(Long.toString(getChargeConsume()))
                .append("Rf/s").withStyle(ChatFormatting.GRAY));

        if(getCharge(pStack) <= 0){
            pTooltipComponents.add(Component.translatable("arc_reactor.rsimm.uncharged.instructions.1").withStyle(ChatFormatting.GRAY));
            pTooltipComponents.add(Component.translatable("arc_reactor.rsimm.uncharged.instructions.2").withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public abstract Item getChargedItem();

    public abstract long getRequiredCharge();

    public abstract long getChargeConsume();

    public long getCharge(ItemStack itemStack) {
        if (itemStack.getTag() == null) {
            CompoundTag nbtTag = new CompoundTag();
            nbtTag.putLong("charge", 0);
            itemStack.setTag(nbtTag);
        }
        return itemStack.getTag().getLong("charge");
    }

    public void setCharge(ItemStack itemStack, long charge){
        CompoundTag nbtTag = new CompoundTag();
        nbtTag.putLong("charge", Math.max(0, Math.min(getRequiredCharge(), charge)));
        itemStack.setTag(nbtTag);
    }

}
