package net.guwy.rsimm.index;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class RsImmKeyBindings {
    public static final String KEY_CATEGORY_IRONMAN = "key.category.rsimm.ironman";
    public static final String KEY_ARMOR_KEY = "key.rsimm.armor_key";
    public static final String KEY_HAND_KEY = "key.rsimm.hand_key";
    public static final String KEY_SPECIAL_KEY = "key.rsimm.special_key";
    public static final String KEY_FLIGHT_KEY = "key.rsimm.flight_key";
    public static final String KEY_WEAPON_KEY = "key.rsimm.weapon_key";
    public static final String KEY_SWITCH_WEAPON_KEY = "key.rsimm.switch_weapon_key";

    public static final KeyMapping ARMOR_KEY = new KeyMapping(KEY_ARMOR_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, KEY_CATEGORY_IRONMAN);

    public static final KeyMapping HAND_KEY = new KeyMapping(KEY_HAND_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_IRONMAN);

    public static final KeyMapping SPECIAL_KEY = new KeyMapping(KEY_SPECIAL_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORY_IRONMAN);

    public static final KeyMapping FLIGHT_KEY = new KeyMapping(KEY_FLIGHT_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_IRONMAN);

    public static final KeyMapping WEAPON_KEY = new KeyMapping(KEY_WEAPON_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, KEY_CATEGORY_IRONMAN);

    public static final KeyMapping SWITCH_WEAPON_KEY = new KeyMapping(KEY_SWITCH_WEAPON_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY_IRONMAN);
}
