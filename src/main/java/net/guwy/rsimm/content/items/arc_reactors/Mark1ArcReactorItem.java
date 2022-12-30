package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.index.ModArcReactorItems;
import net.guwy.rsimm.index.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Mark1ArcReactorItem extends AbstractArcReactorItem {
    public Mark1ArcReactorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public String displayName() {
        return "mark 1";
    }

    @Override
    public long maxEnergy() {
        return 1000000;
    }

    @Override
    public long energyOutput() {
        return 100000;
    }

    @Override
    public int idleDrain() {
        return 5;
    }

    @Override
    public int poisonFactor() {
        return 4;
    }

    @Override
    public Item depletedItem() {
        return ModArcReactorItems.MARK_1_ARC_REACTOR_DEPLETED.get();
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        CompoundTag nbtTag = new CompoundTag();
        nbtTag.putLong("energy", 10000);
        pStack.setTag(nbtTag);
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }
}
