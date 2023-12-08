package net.guwy.rsimm.content.items.armors;

import net.guwy.rsimm.utils.KeyCallType;
import net.minecraft.world.entity.player.Player;

public interface IronmanArmorInterface {
    void flightKeyPressAction(Player player);
    void flightKeyHoldAction(Player player);

    void handKeyPressAction(Player player);
    void handKeyHoldAction(Player player);

    void handKeyHoldActionHandler(Player player);
    void handKeyHoldReleaseAction(Player player);

    void specialKeyAction(Player player, KeyCallType callType);

    void armorKeyPressAction(Player player);
    void armorKeyHoldAction(Player player);

    void weaponFireKeyAction(Player player, KeyCallType keyCallType);
}
