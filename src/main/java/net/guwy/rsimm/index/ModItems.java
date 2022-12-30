package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.*;
import net.guwy.rsimm.content.items.arc_reactors.DepletedArcReactorItem;
import net.guwy.rsimm.content.items.arc_reactors.Mark1ArcReactorItem;
import net.guwy.rsimm.content.items.arc_reactors.UnchargedMark1ArcReactorItem;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.guwy.rsimm.content.items.armors.Mark1OpenHelmetArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);

    /* Example
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModTabs.MATERIALS)));
     */



    // Tools
    public static final RegistryObject<Item> CHEST_CUTTER = ITEMS.register("chest_cutter",
            () -> new ChestCutterItem(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));



    // Materials
    public static final RegistryObject<Item> PALLADIUM_NUGGET = ITEMS.register("palladium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_RAW = ITEMS.register("titanium_raw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_WIRE = ITEMS.register("steel_wire",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MOLDING_BOWL = ITEMS.register("molding_bowl",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_RING_MOLD = ITEMS.register("palladium_ring_mold",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> PALLADIUM_RING = ITEMS.register("palladium_ring",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY = ITEMS.register("circuitry",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> CIRCUITRY_ADVANCED = ITEMS.register("circuitry_advanced",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MOTOR = ITEMS.register("motor",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> STEEL_ARMOR_PLATE = ITEMS.register("steel_armor_plate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> TINY_PILE_OF_GUNPOWDER = ITEMS.register("tiny_pile_of_gunpowder",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> ROCKET = ITEMS.register("rocket",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));






    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
