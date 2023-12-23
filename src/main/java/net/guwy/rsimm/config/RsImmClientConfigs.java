package net.guwy.rsimm.config;

import net.guwy.rsimm.config.enums.GuiScreenAnchor;
import net.minecraftforge.common.ForgeConfigSpec;

public class RsImmClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> EDITH_GLASSES_OVERLAY_X_OFFSET;
    public static final ForgeConfigSpec.ConfigValue<Integer> EDITH_GLASSES_OVERLAY_Y_OFFSET;
    public static final ForgeConfigSpec.EnumValue<GuiScreenAnchor> EDITH_GLASSES_OVERLAY_ANCHOR;
    public static final ForgeConfigSpec.ConfigValue<Double> EDITH_GLASSES_OVERLAY_SCALE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ARC_REACTOR_DEATH_TIME;
    public static final ForgeConfigSpec.ConfigValue<Integer> KEY_BIND_HOLD_THRESHOLD;

    static {
        BUILDER.push("Client");



        BUILDER.push("Overlays");

        BUILDER.push("Edith Glasses");
        BUILDER.comment(".");
        EDITH_GLASSES_OVERLAY_X_OFFSET = BUILDER.comment("T offset for the overlay").comment(" +: right").comment(" -: left")
                .define("X Offset", 20);
        BUILDER.comment(".");
        EDITH_GLASSES_OVERLAY_Y_OFFSET = BUILDER.comment("Y offset for the overlay").comment(" +: down").comment(" -: up")
                .define("Y Offset", -80);
        BUILDER.comment(".");
        EDITH_GLASSES_OVERLAY_ANCHOR = BUILDER.comment("Screen corner to originate the overlay from")
                .defineEnum("Anchor Side", GuiScreenAnchor.BOTTOM_LEFT, GuiScreenAnchor.values());
        BUILDER.comment(".");
        EDITH_GLASSES_OVERLAY_SCALE = BUILDER.comment("Scale of the overlay")
                .comment("!(Keep in mind, when scaling with decimal points some values look weird. Choose one that looks ok)")
                .define("Scale", 1.0);
        BUILDER.pop();

        BUILDER.pop();



        BUILDER.push("Preferences");

        BUILDER.push("Arc Reactor");
        BUILDER.comment(".");
        ARC_REACTOR_DEATH_TIME = BUILDER.comment("The time (in seconds) you have before dying without a reactor")
                .comment(" min: 10")
                .comment(" values lower than 10: uses the server time (5 mins by default)")
                .comment(" !This value will not go above the server limit. Look into the server configs to make it higher")
                .define("Death time", 0);
        BUILDER.pop();

        BUILDER.push("Key Binds");
        BUILDER.comment(".");
        KEY_BIND_HOLD_THRESHOLD = BUILDER.comment("The time you have before a keybinding switches from triggering the PRESS action to HOLD action")
                .defineInRange("Hold Time", 2, 0, Integer.MAX_VALUE);


        BUILDER.pop();


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
