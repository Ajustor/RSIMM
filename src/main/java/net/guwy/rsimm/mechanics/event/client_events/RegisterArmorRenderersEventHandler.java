package net.guwy.rsimm.mechanics.event.client_events;

import net.guwy.rsimm.content.entities.armor.Mark1ArmorRenderer;
import net.guwy.rsimm.content.entities.armor.Mark1OpenHelmetArmorRenderer;
import net.guwy.rsimm.content.entities.armor.TestArmorRenderer;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.guwy.rsimm.content.items.armors.Mark1OpenHelmetArmorItem;
import net.minecraftforge.client.event.EntityRenderersEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RegisterArmorRenderersEventHandler {
    public static void init(final EntityRenderersEvent.AddLayers event){
        GeoArmorRenderer.registerArmorRenderer(TestArmorItem.class, TestArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(Mark1ArmorItem.class, Mark1ArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(Mark1OpenHelmetArmorItem.class, Mark1OpenHelmetArmorRenderer::new);

    }
}
