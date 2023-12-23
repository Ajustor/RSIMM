package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.GenericRepulsorItem;
import net.guwy.rsimm.content.items.armors.gen_2.HullItem;
import net.guwy.rsimm.content.items.armors.gen_2.SuitPowerSupplyItem;
import net.guwy.rsimm.enums.SuitPowerSupplyTypes;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RsImmSuitComponentItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);



    // Repulsor
    public static final RegistryObject<Item> REPULSOR = ITEMS.register("repulsor",
            () -> new GenericRepulsorItem(new Item.Properties().tab(RsImmCreativeModeTabs.SUIT_COMPONENTS).durability(1000),
                    1, 1, 1, 1,
                    20, 4, 30, 10,
                    1, 1,
                    0, 0.05, 0.5,
                    5000));



    // Power Supply
    public static final RegistryObject<Item> BASIC_CONNECTOR_TIER_1 = ITEMS.register("basic_connector_tier_1",
            () -> new SuitPowerSupplyItem(new Item.Properties().tab(RsImmCreativeModeTabs.SUIT_COMPONENTS).durability(250),
                    SuitPowerSupplyTypes.EMERGENCY, 1000, 1000,
                    60, 30, 60, 30, 1));



    // Hull
    public static final RegistryObject<Item> MARK_2_HULL = ITEMS.register("mark_2_hull",
            () -> new HullItem(new Item.Properties().tab(RsImmCreativeModeTabs.SUIT_COMPONENTS).durability(750),
                    40, 0.02, 120, 0, 0.05, 0.05));



    // Circuit



    // Extra Thruster



    // Backpack



    // Weapon





    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
