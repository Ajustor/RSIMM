package net.guwy.rsimm.mechanics.event.subclasses;

import net.guwy.rsimm.index.ModTags;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RenderPlayerEventPreHandler {
    public static void init(RenderPlayerEvent.Pre event){
        Player player = (Player) event.getEntity();
        if(player.getItemBySlot(EquipmentSlot.HEAD).is(ModTags.Items.IRONMAN_HELMETS)){

            player.getCapability(IronmanArmorDataProvider.PLAYER_IRONMAN_ARMOR_DATA).ifPresent(armor -> {
                if(armor.getHasArmor()){
                    event.getRenderer().getModel().head.visible = false;
                }
            });

            event.getRenderer().getModel().hat.visible = false;
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).is(ModTags.Items.IRONMAN_CHESTPLATES)){
            event.getRenderer().getModel().body.visible = false;
            event.getRenderer().getModel().jacket.visible = false;
            event.getRenderer().getModel().leftArm.visible = false;
            event.getRenderer().getModel().leftSleeve.visible = false;
            event.getRenderer().getModel().rightArm.visible = false;
            event.getRenderer().getModel().rightSleeve.visible = false;
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).is(ModTags.Items.IRONMAN_LEGGINGS)
        && player.getItemBySlot(EquipmentSlot.FEET).is(ModTags.Items.IRONMAN_BOOTS)){
            event.getRenderer().getModel().leftLeg.visible = false;
            event.getRenderer().getModel().leftPants.visible = false;
            event.getRenderer().getModel().rightLeg.visible = false;
            event.getRenderer().getModel().rightPants.visible = false;
        }
    }
}
