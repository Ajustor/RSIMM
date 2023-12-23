package net.guwy.rsimm.mechanics.keybind;

import net.guwy.rsimm.enums.KeyActionTypes;
import net.guwy.rsimm.enums.KeyBinds;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IIronmanKeybindCapableItem {

    /** Called with respective values when a key is pressed
     * @param player player that pressed the keybind
     * @param itemStack item stack the keybind is called for
     * @param keyActionType the key trigger type (ex. press, start hold, etc.)
     * @param keyBind the pressed key
     * @return if false, continues with the next keybind accessor (held item -> armor -> arc reactor)*/
    default boolean keybindInput(Player player, ItemStack itemStack, KeyActionTypes keyActionType, KeyBinds keyBind){
        return false;
    }
}
