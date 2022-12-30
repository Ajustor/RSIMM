package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntity;
import net.guwy.rsimm.content.entities.non_living.rocket.RocketEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RsImm.MOD_ID);

    public static final RegistryObject<EntityType<Mark1FlameEntity>> MARK_1_FLAME =
            ENTITY_TYPES.register("mark_1_flame",
                    () -> EntityType.Builder.of(Mark1FlameEntity::new, MobCategory.MISC)
                            .sized(0.4f, 0.4f)
                            .build(new ResourceLocation(RsImm.MOD_ID, "mark_1_flame").toString()));

    public static final RegistryObject<EntityType<RocketEntity>> ROCKET =
            ENTITY_TYPES.register("rocket_entity",
                    () -> EntityType.Builder.of(RocketEntity::new, MobCategory.MISC)
                            .sized(0.4f, 0.4f)
                            .build(new ResourceLocation(RsImm.MOD_ID, "rocket_entity").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
