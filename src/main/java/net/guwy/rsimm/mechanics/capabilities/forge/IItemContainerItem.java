package net.guwy.rsimm.mechanics.capabilities.forge;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IItemContainerItem {

    boolean isItemValid(ItemStack container, int slot, @NotNull ItemStack stack);
}
