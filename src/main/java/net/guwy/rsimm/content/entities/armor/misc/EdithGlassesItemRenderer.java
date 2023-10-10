package net.guwy.rsimm.content.entities.armor.misc;

import net.guwy.rsimm.content.items.EdithGlassesArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class EdithGlassesItemRenderer extends GeoItemRenderer<EdithGlassesArmorItem> {
    public EdithGlassesItemRenderer() {
        super(new EdithGlassesItemModel());
    }

    // Texture change if the glasses are working
    @Override
    public ResourceLocation getTextureLocation(EdithGlassesArmorItem animatable) {
        return super.getTextureLocation(animatable);
    }
}
