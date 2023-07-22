package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.guwy.rsimm.content.items.armors.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModArmorItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);



    // Utility
    public static final RegistryObject<Item> ARC_REACTOR_CONNECTOR_ARMOR = ITEMS.register("arc_reactor_connector_armor",
            () -> new ArcReactorConnectorArmorItem(ModArmorMaterials.ARC_REACTOR_CONNECTOR,
                    EquipmentSlot.CHEST, new Item.Properties().stacksTo(1)
                    .tab(ModCreativeModeTabs.MAIN)));

    // Armors
    public static final RegistryObject<Item> TEST_ARMOR_HELMET = ITEMS.register("test_armor_helmet",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEST_ARMOR_CHESTPLATE = ITEMS.register("test_armor_chestplate",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.CHEST, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEST_ARMOR_LEGGINGS = ITEMS.register("test_armor_leggings",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.LEGS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TEST_ARMOR_BOOTS = ITEMS.register("test_armor_boots",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.FEET, new Item.Properties().stacksTo(1)));


    // Mark 1
    public static final RegistryObject<Item> MARK_1_HELMET = ITEMS.register("mark_1_helmet",
            () -> new Mark1ArmorItem(ModArmorMaterials.MARK_1_ARMOR, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_1_OPEN_HELMET = ITEMS.register("mark_1_open_helmet",
            () -> new Mark1OpenHelmetArmorItem(ModArmorMaterials.MARK_1_OPEN_ARMOR, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_1_CHESTPLATE = ITEMS.register("mark_1_chestplate",
            () -> new Mark1ArmorItem(ModArmorMaterials.MARK_1_ARMOR, EquipmentSlot.CHEST, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_1_LEGGINGS = ITEMS.register("mark_1_leggings",
            () -> new Mark1ArmorItem(ModArmorMaterials.MARK_1_ARMOR, EquipmentSlot.LEGS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_1_BOOTS = ITEMS.register("mark_1_boots",
            () -> new Mark1ArmorItem(ModArmorMaterials.MARK_1_ARMOR, EquipmentSlot.FEET, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_1_HELMET = ITEMS.register("unassambled_mark_1_helmet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_1_CHESTPLATE = ITEMS.register("unassambled_mark_1_chestplate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_1_LEGGINGS = ITEMS.register("unassambled_mark_1_leggings",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_1_BOOTS = ITEMS.register("unassambled_mark_1_boots",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));


    // Mark 2
    public static final RegistryObject<Item> MARK_2_HELMET = ITEMS.register("mark_2_helmet",
            () -> new Mark2ArmorItem(ModArmorMaterials.MARK_2_ARMOR, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_2_OPEN_HELMET = ITEMS.register("mark_2_open_helmet",
            () -> new Mark2OpenHelmetArmorItem(ModArmorMaterials.MARK_2_OPEN_ARMOR, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_2_CHESTPLATE = ITEMS.register("mark_2_chestplate",
            () -> new Mark2ArmorItem(ModArmorMaterials.MARK_2_ARMOR, EquipmentSlot.CHEST, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_2_LEGGINGS = ITEMS.register("mark_2_leggings",
            () -> new Mark2ArmorItem(ModArmorMaterials.MARK_2_ARMOR, EquipmentSlot.LEGS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MARK_2_BOOTS = ITEMS.register("mark_2_boots",
            () -> new Mark2ArmorItem(ModArmorMaterials.MARK_2_ARMOR, EquipmentSlot.FEET, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_2_HELMET = ITEMS.register("unassambled_mark_2_helmet",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_2_CHESTPLATE = ITEMS.register("unassambled_mark_2_chestplate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_2_LEGGINGS = ITEMS.register("unassambled_mark_2_leggings",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));

    public static final RegistryObject<Item> UNASSAMBLED_MARK_2_BOOTS = ITEMS.register("unassambled_mark_2_boots",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.MAIN).stacksTo(1).durability(750)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
