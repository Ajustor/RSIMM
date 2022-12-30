package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.ChestCutterItem;
import net.guwy.rsimm.content.items.TestArmorItem;
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

public class ModArcReactorItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);



    // Arc Reactors
    public static final RegistryObject<Item> MARK_1_ARC_REACTOR = ITEMS.register("mark_1_arc_reactor",
            () -> new Mark1ArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_1_ARC_REACTOR_DEPLETED = ITEMS.register("mark_1_arc_reactor_depleted",
            () -> new DepletedArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_1_ARC_REACTOR_UNCHARGED = ITEMS.register("mark_1_arc_reactor_uncharged",
            () -> new UnchargedMark1ArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
