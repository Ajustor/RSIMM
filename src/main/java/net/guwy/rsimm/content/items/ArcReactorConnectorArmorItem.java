package net.guwy.rsimm.content.items;

import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.compat.curios.Curios;
import net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor.ArcReactorSlotProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

public class ArcReactorConnectorArmorItem extends GeoArmorItem implements IAnimatable, ILoopType {


    public ArcReactorConnectorArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        transferEnergy(level, player);
        super.onArmorTick(stack, level, player);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.rsimm.arc_reactor_connector_armor.t.1"));
        pTooltipComponents.add(Component.translatable("tooltip.rsimm.arc_reactor_connector_armor.t.2"));
        pTooltipComponents.add(Component.translatable("tooltip.rsimm.arc_reactor_connector_armor.t.3"));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("tooltip.rsimm.arc_reactor_connector_armor.t.4"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public static void transferEnergy(Level level, Player player){
        if(!level.isClientSide){
            // gets the capabilities
            player.getCapability(ArcReactorSlotProvider.PLAYER_REACTOR_SLOT).ifPresent(arcReactor -> {
                // gets each equipment slot 1 by 1 (includes hands)
                for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {

                    ItemStack itemStack = player.getItemBySlot(equipmentSlot);
                    itemStack.getCapability(ForgeCapabilities.ENERGY).ifPresent(itemEnergy -> {

                        // gets energy to send
                        int neededEnergy = itemEnergy.receiveEnergy((int) arcReactor.getArcReactorEnergyOutput(), true);
                        int energyToSend = (int) Math.min(arcReactor.getArcReactorEnergyOutput() - arcReactor.getEnergyLoad(), neededEnergy);

                        // checks if the energy to send can be supplied
                        if(arcReactor.testAddEnergyLoad(energyToSend)){

                            // sends energy to the item
                            itemEnergy.receiveEnergy(energyToSend, false);

                            // adds the energy sent to the arc reactor's load which will handle the overload and drain
                            arcReactor.addEnergyLoad(energyToSend);
                        }
                    });
                }
            });
        }
    }

    /**
     * Animation Controllers
     */
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    // Empty since there are no animations
    @Override
    public void registerControllers(AnimationData data) {}
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    @Override
    public boolean isRepeatingAfterEnd() {
        return false;
    }


    /**
     * Curio Stuff
     */
    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        if(RsImm.isCuriosLoaded()){
            return Curios.createArcReactorConnectorProvider(stack);
        } else {
            return super.initCapabilities(stack, nbt);
        }
    }
}
