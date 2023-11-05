package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerBlockEntity;
import net.guwy.rsimm.content.blocks.armor_equipping_station.ArmorEquippingStationBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RsImmBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RsImm.MOD_ID);

    public static final RegistryObject<BlockEntityType<ArcReactorChargerBlockEntity>> ARC_REACTOR_CHARGER =
            BLOCK_ENTITIES.register("arc_reactor_charger", () ->
                    BlockEntityType.Builder.of(ArcReactorChargerBlockEntity::new,
                            RsImmBlocks.ARC_REACTOR_CHARGER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ArmorEquippingStationBlockEntity>> ARMOR_EQUIPPING_STATION =
            BLOCK_ENTITIES.register("armor_equipping_station", () ->
                    BlockEntityType.Builder.of(ArmorEquippingStationBlockEntity::new,
                            RsImmBlocks.ARMOR_EQUIPPING_STATION.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
