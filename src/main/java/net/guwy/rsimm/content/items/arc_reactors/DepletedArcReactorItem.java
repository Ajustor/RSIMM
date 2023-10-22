package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.index.ModArcReactorItems;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DepletedArcReactorItem extends Item {
    public DepletedArcReactorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!pLevel.isClientSide){
            Item item = pStack.getItem();
            Player player = (Player) pEntity;
            ItemStack itemStack;

            if(item == ModArcReactorItems.MARK_1_ARC_REACTOR_DEPLETED.get()){
                itemStack = new ItemStack(ModArcReactorItems.MARK_1_ARC_REACTOR.get());
            } else if (item == ModArcReactorItems.MARK_2_ARC_REACTOR_DEPLETED.get()){
                itemStack = new ItemStack(ModArcReactorItems.MARK_2_ARC_REACTOR.get());
            } else {
                itemStack = new ItemStack(Items.DIRT);
            }

            // set energy to 0
            ItemTagUtils.putLong(itemStack, "energy", 0);

            // replace model data
            AbstractArcReactorItem arcReactorItem = (AbstractArcReactorItem) itemStack.getItem();
            arcReactorItem.checkAndTransformDepletion(itemStack);

            // put it in inventory and delete the old one
            pStack.setCount(0);
            player.getInventory().placeItemBackInInventory(itemStack,true);

            // send confirmation message
            player.sendSystemMessage(Component.literal("depleted arc reactor has successfully been transformed into the new system"));
        }

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }
}
