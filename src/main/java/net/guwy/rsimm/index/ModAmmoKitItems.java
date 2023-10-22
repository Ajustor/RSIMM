package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.guwy.rsimm.content.items.ammo_kits.Mark1AmmoKit;
import net.guwy.rsimm.content.items.ammo_kits.Mark2AmmoKit;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.guwy.rsimm.content.items.armors.Mark1OpenHelmetArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAmmoKitItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);



    // Ammo Kits
    public static final RegistryObject<Item> MARK_1_AMMO_KIT = ITEMS.register("mark_1_ammo_kit",
            () -> new Mark1AmmoKit(new Item.Properties().tab(ModCreativeModeTabs.MAIN)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
