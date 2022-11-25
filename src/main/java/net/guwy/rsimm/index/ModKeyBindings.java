package net.guwy.rsimm.index;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static final String KEY_CATEGORY_IRONMAN = "key.category.rsimm.ironman";
    public static final String KEY_IRONMAN_MENU = "key.rsimm.ironman_menu";

    public static final KeyMapping IRONMAN_MENU_KEY = new KeyMapping(KEY_IRONMAN_MENU, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, KEY_CATEGORY_IRONMAN);
}
