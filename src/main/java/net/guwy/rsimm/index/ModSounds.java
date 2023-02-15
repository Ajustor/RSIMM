package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RsImm.MOD_ID);



    public static final RegistryObject<SoundEvent> PLAYER_COUGH = registerSoundEvent("player_cough");
    public static final RegistryObject<SoundEvent> HEARTBEAT = registerSoundEvent("heartbeat");
    public static final RegistryObject<SoundEvent> ARC_REACTOR_EQUIP = registerSoundEvent("arc_reactor_equip");
    public static final RegistryObject<SoundEvent> ARC_REACTOR_UNEQUIP = registerSoundEvent("arc_reactor_unequip");
    public static final RegistryObject<SoundEvent> ARC_REACTOR_CHECK = registerSoundEvent("arc_reactor_check");
    public static final RegistryObject<SoundEvent> CHEST_CUTTING = registerSoundEvent("chest_cutting");
    public static final RegistryObject<SoundEvent> MARK_1_FLAME_THROWER = registerSoundEvent("mark_1_flame_thrower");
    public static final RegistryObject<SoundEvent> METAL_RATTLE_1 = registerSoundEvent("metal_rattle_1");
    public static final RegistryObject<SoundEvent> METAL_RATTLE_2 = registerSoundEvent("metal_rattle_2");
    public static final RegistryObject<SoundEvent> METAL_RATTLE_3 = registerSoundEvent("metal_rattle_3");
    public static final RegistryObject<SoundEvent> RAIN_IN_HELMET = registerSoundEvent("rain_in_helmet");



    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(RsImm.MOD_ID, name)));
    }

    private static RegistryObject<SoundEvent> registerSoundEvent(String name, float range){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(RsImm.MOD_ID, name), range));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
