package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.arc_reactors.ArcReactorItem;
import net.guwy.rsimm.content.items.arc_reactors.DepletedArcReactorItem;
import net.guwy.rsimm.content.items.arc_reactors.UnchargedArcReactorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModArcReactorItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RsImm.MOD_ID);



    // Arc Reactors
    public static final RegistryObject<Item> MARK_1_ARC_REACTOR_DEPLETED = ITEMS.register("mark_1_arc_reactor_depleted",
            () -> new DepletedArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_1_ARC_REACTOR = ITEMS.register("mark_1_arc_reactor",
            () -> new ArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN),
                    "mark 1", 1000000, 100000, 5, 4, MARK_1_ARC_REACTOR_DEPLETED.get()));

    public static final RegistryObject<Item> MARK_1_ARC_REACTOR_UNCHARGED = ITEMS.register("mark_1_arc_reactor_uncharged",
            () -> new UnchargedArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN),
                    MARK_1_ARC_REACTOR.get(), 10000000, 200));



    public static final RegistryObject<Item> MARK_2_ARC_REACTOR_DEPLETED = ITEMS.register("mark_2_arc_reactor_depleted",
            () -> new DepletedArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN)));

    public static final RegistryObject<Item> MARK_2_ARC_REACTOR = ITEMS.register("mark_2_arc_reactor",
            () -> new ArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN),
                    "mark 2", 10000000, 1000000, 25, 8, MARK_2_ARC_REACTOR_DEPLETED.get()));

    public static final RegistryObject<Item> MARK_2_ARC_REACTOR_UNCHARGED = ITEMS.register("mark_2_arc_reactor_uncharged",
            () -> new UnchargedArcReactorItem(new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.MAIN),
                    MARK_2_ARC_REACTOR.get(), 50000000, 1000));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
