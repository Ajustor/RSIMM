package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.blocks.ArcReactorChargerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RsImm.MOD_ID);

    public static final RegistryObject<BlockEntityType<ArcReactorChargerBlockEntity>> ARC_REACTOR_CHARGER =
            BLOCK_ENTITIES.register("arc_reactor_charger", () ->
                    BlockEntityType.Builder.of(ArcReactorChargerBlockEntity::new,
                            ModBlocks.ARC_REACTOR_CHARGER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
