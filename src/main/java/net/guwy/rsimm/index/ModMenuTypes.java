package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerMenu;
import net.guwy.rsimm.content.blocks.armor_equipping_station.ArmorEquippingStationMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RsImm.MOD_ID);



    public static final RegistryObject<MenuType<ArcReactorChargerMenu>> ARC_REACTOR_CHARGER_MENU =
            registerMenuType(ArcReactorChargerMenu::new, "arc_reactor_charger_menu");

    public static final RegistryObject<MenuType<ArmorEquippingStationMenu>> ARMOR_EQUIPPING_STATION_MENU =
            registerMenuType(ArmorEquippingStationMenu::new, "armor_equipping_station_menu");



    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
