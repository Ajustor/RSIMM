package net.guwy.rsimm.content.entities.item;

import net.guwy.rsimm.content.items.GenericRepulsorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RepulsorItemRenderer extends GeoItemRenderer<GenericRepulsorItem> {
    public RepulsorItemRenderer() {
        super(new RepulsorItemModel());
    }

    // Texture change if the glasses are working
    @Override
    public ResourceLocation getTextureLocation(GenericRepulsorItem animatable) {
        return super.getTextureLocation(animatable);
    }
}
