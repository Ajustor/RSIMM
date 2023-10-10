package net.guwy.rsimm.content.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.content.items.EdithGlassesArmorItem;
import net.guwy.rsimm.index.ModArmorItems;
import net.guwy.sticky_foundations.client.view_bobbing.ViewBobbing;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import top.theillusivec4.curios.Curios;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.common.capability.CurioInventoryCapability;
import top.theillusivec4.curios.common.inventory.CurioSlot;
import top.theillusivec4.curios.common.inventory.container.CuriosContainerProvider;

import java.util.Objects;

public class EdithGlassesOverlay {

    private static final double SIZE_MULTIPLIER = 1;

    public static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(RsImm.MOD_ID,
            "textures/overlay/armor/edith_glasses/edith_glasses_overlay.png");

    public static final IGuiOverlay OVERLAY = (((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int bobX = (int) (ViewBobbing.MouseBobbing.GetMouseX() * 10 / SIZE_MULTIPLIER);
        int bobY = (int) (ViewBobbing.MouseBobbing.GetMouseY() * 10 / SIZE_MULTIPLIER);
        final int OFFSET_X = screenWidth / 2 + bobX;
        final int OFFSET_Y = screenHeight / 2 + bobY;

        if(Minecraft.getInstance().options.getCameraType().equals(CameraType.FIRST_PERSON)){
            Player player = Minecraft.getInstance().player;
            ItemStack helmetItem = player.getItemBySlot(EquipmentSlot.HEAD); // Worn Helmet Item

            if(helmetItem.getItem().equals(ModArmorItems.EDITH_GLASSES.get())){

                // Read tags from the item and assign variables
                boolean hasReactorSlot = ItemTagUtils.getBoolean(helmetItem, EdithGlassesArmorItem.HAS_SLOT_TAG_KEY);
                boolean hasReactor = ItemTagUtils.getBoolean(helmetItem, EdithGlassesArmorItem.HAS_REACTOR_TAG_KEY);
                long reactorEnergy = ItemTagUtils.getLong(helmetItem, EdithGlassesArmorItem.ENERGY_TAG_KEY);
                long reactorEnergyCapacity = ItemTagUtils.getLong(helmetItem, EdithGlassesArmorItem.ENERGY_CAPACITY_TAG_KEY);
                long reactorLoad = ItemTagUtils.getLong(helmetItem, EdithGlassesArmorItem.LOAD_TAG_KEY);
                long reactorMaxOutput = ItemTagUtils.getLong(helmetItem, EdithGlassesArmorItem.MAX_OUTPUT_TAG_KEY);
                String reactorIconTexture = ItemTagUtils.getString(helmetItem, EdithGlassesArmorItem.REACTOR_ICON_TAG_KEY);

                if(hasReactorSlot){
                    // Define percent variables for rendering use
                    double energyPercent = (double) reactorEnergy / reactorEnergyCapacity;
                    double loadPercent = (double) reactorLoad / reactorMaxOutput;

                    // Render Start
                    RenderSystem.enableBlend();
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    RenderSystem.setShaderColor(1, 1, 1, 1);
                    RenderSystem.setShaderTexture(0, OVERLAY_TEXTURE);

                    // Render
                    RenderReactorIcon(poseStack, OFFSET_X, OFFSET_Y, energyPercent, hasReactor, reactorIconTexture);
                    // Reset texture in case the RenderReactorIcon function changes it
                    RenderSystem.setShaderTexture(0, OVERLAY_TEXTURE);
                    RenderBackGround(poseStack, OFFSET_X, OFFSET_Y);
                    RenderEnergyBar(poseStack, OFFSET_X, OFFSET_Y, energyPercent, hasReactor);
                    RenderLoadBar(poseStack, OFFSET_X, OFFSET_Y, loadPercent, hasReactor);

                    // Render End
                    RenderSystem.disableBlend();
                }
            }
        }
    }));

    private static void RenderReactorIcon(PoseStack poseStack, int x, int y, double energyPercent, boolean hasReactor, String texture){
        // Checks if there is a custom texture present otherwise continues with the default one
        if(!Objects.equals(texture, "")){
            RenderSystem.setShaderTexture(0, Objects.requireNonNull(ResourceLocation.tryParse(texture)));
        }

        // Blue Reactor By Default
        int uOff = 0;
        int vOff = 0;

        // Black Reactor if 0%
        if(energyPercent == 0){
            uOff = 33;
            vOff = 0;
        }
        // Red Reactor if below 5%
        else if(energyPercent <= 0.05){
            uOff = 66;
            vOff = 0;
        }
        // Yellow Reactor if below 10%
        else if (energyPercent <= 0.1){
            uOff = 0;
            vOff = 33;
        }

        // Display The empty Reactor if there is no reactor
        if(!hasReactor){
            uOff = 33;
            vOff = 33;
        }

        GuiComponent.blit(poseStack, x, y,uOff,vOff,
                (int) (32 * SIZE_MULTIPLIER), (int) (32 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER));
    }

    private static void RenderBackGround(PoseStack poseStack, int x, int y){
        GuiComponent.blit(poseStack, x + 36, y,0,105,
                (int) (128 * SIZE_MULTIPLIER), (int) (23 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER));

    }

    private static void RenderEnergyBar(PoseStack poseStack, int x, int y, double energyPercent, boolean hasReactor){
        // Display when there's a reactor
        if(hasReactor){

            // Display each notch seperately
            for(int i = 0; i < 25; i++){

                // Get U Offset depending on energy levels to fade out energy bar
                int uOff = 0;
                double percentForCurrentBar = (energyPercent * 25) - i; // Energy percent narrowed down to just take the current bar's limits
                percentForCurrentBar = Math.max(0, Math.min(1, percentForCurrentBar)); // Cap the Percent between 1 and 0

                if(percentForCurrentBar == 0) uOff = 24;
                else if(percentForCurrentBar <= 0.25) uOff = 18;
                else if(percentForCurrentBar <= 0.5) uOff = 12;
                else if(percentForCurrentBar <= 0.75) uOff = 6;

                GuiComponent.blit(poseStack, x + 38 + (i * 5), y + 9,100 + uOff,87,
                        (int) (4 * SIZE_MULTIPLIER), (int) (12 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER));
            }
        }
    }

    private static void RenderLoadBar(PoseStack poseStack, int x, int y, double loadPercent, boolean hasReactor){
        // Display when there's a reactor
        if(hasReactor){

            // Length of the bar rounded up to at least show if there is any energy consumption
            int bar = (int) Math.ceil(37 * loadPercent);

            GuiComponent.blit(poseStack, x + 124, y + 3,88,101,
                    (int) (bar * SIZE_MULTIPLIER), (int) (2 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER), (int) (128 * SIZE_MULTIPLIER));
        }
    }
}
