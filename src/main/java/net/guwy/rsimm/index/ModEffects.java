package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.effects.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, RsImm.MOD_ID);

    public static final RegistryObject<MobEffect> REACTOR_POISONING_SUPPRESSANT = MOB_EFFECTS.register("reactor_poisoning_suppressant",
            () -> new BlankEffect(MobEffectCategory.BENEFICIAL, 0xFEBDEF0));

    public static final RegistryObject<MobEffect> BETTER_REACTOR_POISONING_SUPPRESSANT = MOB_EFFECTS.register("better_reactor_poisoning_suppressant",
            () -> new BlankEffect(MobEffectCategory.BENEFICIAL, 0xFEBDEF0));

    public static final RegistryObject<MobEffect> COUGH = MOB_EFFECTS.register("cough",
            () -> new CoughEffect(MobEffectCategory.NEUTRAL, 0xFEBDEF0));

    public static final RegistryObject<MobEffect> MISSING_REACTOR = MOB_EFFECTS.register("missing_reactor",
            () -> new MissingReactorEffect(MobEffectCategory.HARMFUL, 0xFEBDEF0));

    public static final RegistryObject<MobEffect> CHEST_CUTTING_HURT = MOB_EFFECTS.register("chest_cutting_hurt",
            () -> new ChestCutterHurtEffect(MobEffectCategory.HARMFUL, 0xFEBDEF0));



    // Sound Effects
    public static final RegistryObject<MobEffect> SOUND_EFFECT_ARC_REACTOR_EQUIP = MOB_EFFECTS.register("sound_effect_arc_reactor_equip",
            () -> new ForcedSoundEffect(MobEffectCategory.NEUTRAL, 0xFEBDEF0, ModSounds.ARC_REACTOR_EQUIP.get()));

    public static final RegistryObject<MobEffect> SOUND_EFFECT_ARC_REACTOR_UNEQUIP = MOB_EFFECTS.register("sound_effect_arc_reactor_unequip",
            () -> new ForcedSoundEffect(MobEffectCategory.NEUTRAL, 0xFEBDEF0, ModSounds.ARC_REACTOR_UNEQUIP.get()));

    public static final RegistryObject<MobEffect> SOUND_EFFECT_CHEST_CUTTING = MOB_EFFECTS.register("sound_effect_chest_cutting",
            () -> new ForcedSoundEffect(MobEffectCategory.NEUTRAL, 0xFEBDEF0, ModSounds.CHEST_CUTTING.get()));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
