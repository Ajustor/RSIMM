package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.ChestCutterItem;
import net.guwy.rsimm.content.items.RepulsorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RsImmItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);

    /* Example
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModTabs.MATERIALS)));
     */



    // Tools
    public static final RegistryObject<Item> CHEST_CUTTER = ITEMS.register("chest_cutter",
            () -> new ChestCutterItem(new Item.Properties().tab(RsImmCreativeModeTabs.MAIN).stacksTo(1)));

    public static final RegistryObject<Item> REPULSOR = ITEMS.register("repulsor",
            () -> new RepulsorItem(new Item.Properties().tab(RsImmCreativeModeTabs.MAIN).durability(1000),
                    1, 1, 1, 1, 1,
                    20, 4, 30, 10,
                    1, 1,
                    100, 0, 0, 0, 1.0,
                    5000));



    // Materials

    public static final RegistryObject<Item> PALLADIUM_RING_MOLD = ITEMS.register("palladium_ring_mold",
            () -> new Item(new Item.Properties().tab(RsImmCreativeModeTabs.MAIN).stacksTo(1)));

    public static final RegistryObject<Item> PALLADIUM_RING = ITEMS.register("palladium_ring",
            () -> new Item(new Item.Properties().tab(RsImmCreativeModeTabs.MAIN).stacksTo(16)));

    public static final RegistryObject<Item> STEEL_ARMOR_PLATE = ITEMS.register("steel_armor_plate",
            () -> new Item(new Item.Properties().tab(RsImmCreativeModeTabs.MAIN).stacksTo(16)));

    public static final RegistryObject<Item> ROCKET = ITEMS.register("rocket",
            () -> new Item(new Item.Properties().tab(RsImmCreativeModeTabs.MAIN).stacksTo(32)));






    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
