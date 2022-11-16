package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
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



    // Dev Tools
    public static final RegistryObject<Item> DEV_WAND_1 = ITEMS.register("dev_wand_1",
            () -> new DevWand1Item(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> DEV_WAND_2 = ITEMS.register("dev_wand_2",
            () -> new DevWand2Item(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> DEV_WAND_3 = ITEMS.register("dev_wand_3",
            () -> new DevWand3Item(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> DEV_WAND_4 = ITEMS.register("dev_wand_4",
            () -> new DevWand4Item(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> DEV_WAND_5 = ITEMS.register("dev_wand_5",
            () -> new DevWand5Item(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> DEV_WAND_6 = ITEMS.register("dev_wand_6",
            () -> new DevWand6Item(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));


    public static final RegistryObject<Item> TEST_ARMOR_HELMET = ITEMS.register("test_armor_helmet",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> TEST_ARMOR_CHESTPLATE = ITEMS.register("test_armor_chestplate",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.CHEST, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> TEST_ARMOR_LEGGINGS = ITEMS.register("test_armor_leggings",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.LEGS, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> TEST_ARMOR_BOOTS = ITEMS.register("test_armor_boots",
            () -> new TestArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.FEET, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_1_HELMET = ITEMS.register("mark_1_helmet",
            () -> new Mark1ArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_1_CHESTPLATE = ITEMS.register("mark_1_chestplate",
            () -> new Mark1ArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.CHEST, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_1_LEGGINGS = ITEMS.register("mark_1_leggings",
            () -> new Mark1ArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.LEGS, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_1_BOOTS = ITEMS.register("mark_1_boots",
            () -> new Mark1ArmorItem(ModArmorMaterials.TEST_ARMOR, EquipmentSlot.FEET, new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));






    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
