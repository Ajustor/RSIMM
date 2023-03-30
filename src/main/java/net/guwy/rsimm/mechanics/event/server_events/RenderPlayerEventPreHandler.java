package net.guwy.rsimm.mechanics.event.server_events;

import com.mojang.math.Vector3f;
import net.guwy.rsimm.index.ModArcReactorItems;
import net.guwy.rsimm.index.ModTags;
import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlotProvider;
import net.guwy.rsimm.mechanics.capabilities.player.armor_data.IronmanArmorDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.client.event.RenderPlayerEvent;

import java.util.concurrent.atomic.AtomicReference;

public class RenderPlayerEventPreHandler {
    public static void init(RenderPlayerEvent.Pre event){
        Player player = (Player) event.getEntity();

        /**
         * The Part That hides the player limbs when wearing ironman armor
         *
         * Due to complications with GeckoLib armors that have a very tight fit on the player
         * sometimes clip through the body thus making weird glitches
         **/
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


        /**
         * The Part That Renders The Arc Reactor on the player
         */

        event.getPoseStack().pushPose();

        event.getPoseStack().mulPose(Vector3f.YN.rotationDegrees(180 + event.getEntity().yBodyRot));

        double x = 0;
        double y = -0.13;

        event.getPoseStack().translate(x, 1.18, y);
        event.getPoseStack().scale(0.2f, 0.2f, 0.2f);

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack stack = new ItemStack(ModArcReactorItems.MARK_2_ARC_REACTOR.get());
        stack = getArcReactorItem(player);

        BakedModel bakedModel = itemRenderer.getModel(stack, event.getEntity().getLevel(), event.getEntity(), 1);

        itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, false, event.getPoseStack(),
                event.getMultiBufferSource(), getLightLevel(event.getEntity()), OverlayTexture.NO_OVERLAY,
                bakedModel);

        event.getPoseStack().popPose();

    }

    private static int getLightLevel(Player entity) {
        int bLight = entity.level.getBrightness(LightLayer.BLOCK, entity.getOnPos().offset(0, 1, 0));
        int sLight = entity.level.getBrightness(LightLayer.SKY, entity.getOnPos().offset(0, 1, 0));
        return LightTexture.pack(bLight, sLight);
    }

    private static ItemStack getArcReactorItem(Player player){
        AtomicReference<ItemStack> itemStack = new AtomicReference<ItemStack>(new ItemStack(Items.BUCKET));

        Player serverPlayer = player.getServer().getLevel(player.getLevel().dimension()).getPlayerByUUID(player.getUUID());
        serverPlayer.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(handler -> {
            itemStack.set(new ItemStack(Item.byId(handler.getArcReactorTypeId())));
        });

        player.getUUID();

        return itemStack.get();
    }
}
