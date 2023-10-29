package net.guwy.rsimm.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class RsImmServerConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> ARC_REACTOR_DEATH_TIME;
    public static final ForgeConfigSpec.BooleanValue ARC_REACTOR_EXTRACT_INSERT_LIMITS;

    static {
        BUILDER.push("Server");



        BUILDER.push("Mechanics");

        BUILDER.push("Arc Reactor");
        BUILDER.comment(".");
        ARC_REACTOR_DEATH_TIME = BUILDER.comment("Max time(in seconds) the player has before dying without an arc reactor")
                .comment("Players can set a number lower than this in their client configs, but not higher")
                .defineInRange("Death time", 300, 180, Integer.MAX_VALUE);
        ARC_REACTOR_EXTRACT_INSERT_LIMITS = BUILDER.comment("Whether or not to prevent you from inserting/extracting the arc reactor when you have a chestplate equipped")
                .define("Insert/Extract Limits", true);
        BUILDER.pop();

        BUILDER.pop();



        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
