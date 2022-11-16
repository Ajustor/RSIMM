package net.guwy.rsimm.mechanics.event;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.entity.client.armor.Mark1ArmorRenderer;
import net.guwy.rsimm.content.entity.client.armor.TestArmorRenderer;
import net.guwy.rsimm.content.items.Mark1ArmorItem;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = RsImm.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    public static int damage = 0;
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event){
        if(damage > 1){
            GeoArmorRenderer.registerArmorRenderer(TestArmorItem.class, TestArmorRenderer::new);
            GeoArmorRenderer.registerArmorRenderer(Mark1ArmorItem.class, Mark1ArmorRenderer::new);
        }   else {
            GeoArmorRenderer.registerArmorRenderer(TestArmorItem.class, TestArmorRenderer::new);
            GeoArmorRenderer.registerArmorRenderer(Mark1ArmorItem.class, Mark1ArmorRenderer::new);
        }
    }

}
