package net.guwy.rsimm.content.entities.item;

import net.guwy.rsimm.content.items.RepulsorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RepulsorItemRenderer extends GeoItemRenderer<RepulsorItem> {
    public RepulsorItemRenderer() {
        super(new RepulsorItemModel());
    }

    // Texture change if the glasses are working
    @Override
    public ResourceLocation getTextureLocation(RepulsorItem animatable) {
        return super.getTextureLocation(animatable);
    }
}
