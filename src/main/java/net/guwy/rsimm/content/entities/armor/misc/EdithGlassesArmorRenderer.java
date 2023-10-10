package net.guwy.rsimm.content.entities.armor.misc;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.EdithGlassesArmorItem;
import net.guwy.rsimm.content.items.armors.ArcReactorConnectorArmorItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class EdithGlassesArmorRenderer extends GeoArmorRenderer<EdithGlassesArmorItem> {
    public EdithGlassesArmorRenderer() {
        super(new EdithGlassesArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }

    // Texture change if the glasses are working
    @Override
    public ResourceLocation getTextureLocation(EdithGlassesArmorItem animatable) {
        return super.getTextureLocation(animatable);
    }
}
