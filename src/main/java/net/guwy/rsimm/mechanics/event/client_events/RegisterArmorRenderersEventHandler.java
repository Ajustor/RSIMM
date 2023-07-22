package net.guwy.rsimm.mechanics.event.client_events;

import net.guwy.rsimm.content.entities.armor.*;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.guwy.rsimm.content.items.armors.*;
import net.minecraftforge.client.event.EntityRenderersEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RegisterArmorRenderersEventHandler {
    public static void init(final EntityRenderersEvent.AddLayers event){
        GeoArmorRenderer.registerArmorRenderer(TestArmorItem.class, TestArmorRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(Mark1ArmorItem.class, Mark1ArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(Mark1OpenHelmetArmorItem.class, Mark1OpenHelmetArmorRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(ArcReactorConnectorArmorItem.class, ArcReactorConnectorRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(Mark2ArmorItem.class, Mark2ArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(Mark2OpenHelmetArmorItem.class, Mark2OpenHelmetArmorRenderer::new);

    }
}
