package net.guwy.rsimm.content.entities.armor;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.TestArmorItem;
import net.guwy.rsimm.content.items.armors.Mark1ArmorItem;
import net.guwy.rsimm.index.ModArmorItems;
import net.guwy.rsimm.index.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Items;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Mark1ArmorRenderer extends GeoArmorRenderer<Mark1ArmorItem> {
    public Mark1ArmorRenderer() {
        super(new Mark1ArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }

    @Override
    public ResourceLocation getTextureLocation(Mark1ArmorItem animatable) {
        //if(this.entityLiving.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(ModArmorItems.MARK_1_OPEN_HELMET.get())){
        //    ResourceLocation resourceLocation = new ResourceLocation(RsImm.MOD_ID, "textures/entity/mark_1_flame_texture.png");
        //    ResourceLocation resourceLocation2 = super.getTextureLocation(animatable);
        //    List<ResourceLocation> list = new ArrayList<>();
        //    list.add(resourceLocation);
        //    list.add(resourceLocation2);
        //    return [resourceLocation2, resourceLocation];
        //} else {
        //}

        return super.getTextureLocation(animatable);
    }
}
