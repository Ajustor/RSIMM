package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.ammo_kits.Mark1AmmoKit;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RsImmAmmoKitItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);



    // Ammo Kits
    public static final RegistryObject<Item> MARK_1_AMMO_KIT = ITEMS.register("mark_1_ammo_kit",
            () -> new Mark1AmmoKit(new Item.Properties().tab(RsImmCreativeModeTabs.MAIN)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
