package net.guwy.rsimm;

import com.mojang.logging.LogUtils;
import net.guwy.rsimm.config.RsImmClientConfigs;
import net.guwy.rsimm.config.RsImmCommonConfigs;
import net.guwy.rsimm.config.RsImmServerConfigs;
import net.guwy.rsimm.content.blocks.arc_reactor_charger.ArcReactorChargerScreen;
import net.guwy.rsimm.content.blocks.armor_equipping_station.ArmorEquippingStationScreen;
import net.guwy.rsimm.content.entities.non_living.mark_1_flame.Mark1FlameEntityRenderer;
import net.guwy.rsimm.content.entities.non_living.rocket.RocketEntityRenderer;
import net.guwy.rsimm.index.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RsImm.MOD_ID)
public class RsImm {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "rsimm";

    private static boolean curiosLoaded = false;

    public RsImm() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        //ModDeveloperItems.register(eventBus);
        ModArcReactorItems.register(eventBus);
        ModArmorItems.register(eventBus);
        ModAmmoKitItems.register(eventBus);

        ModBlocks.register(eventBus);

        ModEntityTypes.register(eventBus);

        ModSounds.register(eventBus);
        ModEffects.register(eventBus);

        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);

        ModRecipes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);

        GeckoLib.initialize();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, RsImmClientConfigs.SPEC, "iron_man-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RsImmCommonConfigs.SPEC, "iron_man-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, RsImmServerConfigs.SPEC, "iron_man-server.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        curiosLoaded = ModList.get().isLoaded("curios");
    }

    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            ModNetworking.register();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(RSIMMPonder::register);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ARC_REACTOR_CHARGER_MENU.get(), ArcReactorChargerScreen::new);
            MenuScreens.register(ModMenuTypes.ARMOR_EQUIPPING_STATION_MENU.get(), ArmorEquippingStationScreen::new);

            EntityRenderers.register(ModEntityTypes.MARK_1_FLAME.get(), Mark1FlameEntityRenderer::new);
            EntityRenderers.register(ModEntityTypes.ROCKET.get(), RocketEntityRenderer::new);
        }
    }

    public static boolean isCuriosLoaded()
    {
        return curiosLoaded;
    }
}
