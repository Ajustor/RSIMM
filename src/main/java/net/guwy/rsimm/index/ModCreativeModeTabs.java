package net.guwy.rsimm.index;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeModeTabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab("rsimm_tab_main") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.PRISMARINE);
        }
    };
}
