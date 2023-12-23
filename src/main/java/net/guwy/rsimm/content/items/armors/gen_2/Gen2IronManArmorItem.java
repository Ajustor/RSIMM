package net.guwy.rsimm.content.items.armors.gen_2;

import net.guwy.rsimm.enums.KeyActionTypes;
import net.guwy.rsimm.enums.KeyBinds;
import net.guwy.rsimm.mechanics.capabilities.forge.IItemContainerItem;
import net.guwy.rsimm.mechanics.capabilities.forge.ItemStorageItemImpl;
import net.guwy.rsimm.mechanics.keybind.IIronmanKeybindCapableItem;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.item.GeoArmorItem;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class Gen2IronManArmorItem extends GeoArmorItem implements IItemContainerItem, IIronmanKeybindCapableItem {
    public static int HULL_SLOT = 0;
    public static int POWER_SUPPLY_SLOT = 1;
    public static int CIRCUITRY_SLOT = 2;
    public static int REPULSOR_RIGHT_ARM_SLOT = 3, REPULSOR_LEFT_ARM_SLOT = 4, REPULSOR_RIGHT_LEG_SLOT = 5, REPULSOR_LEFT_LEG_SLOT = 6;
    public static int EXTRA_THRUSTER_SLOT = 7;
    public static int BACKPACK_SLOT = 10;
    public static int AMMO_SLOT_FIRST = 11;

    public Gen2IronManArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide && stack.getEquipmentSlot() == EquipmentSlot.CHEST){
            // tick events here
        }
        super.onArmorTick(stack, level, player);
    }

    @Override
    public boolean keybindInput(Player player, ItemStack itemStack, KeyActionTypes keyActionType, KeyBinds keyBind) {
        // Keybind events here (runs on server)

        return IIronmanKeybindCapableItem.super.keybindInput(player, itemStack, keyActionType, keyBind);
    }



    public static void processDamage(ItemStack itemStack, @Nullable ItemStack armorItem, int damage){
        AtomicInteger d = new AtomicInteger();
        d.set(damage);

        if(armorItem != null){
            armorItem.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(inv -> {
                ItemStack hullItem = inv.getStackInSlot(Gen2IronManArmorItem.HULL_SLOT);
                HullItem h = (HullItem) hullItem.getItem();

                // Extra damage from overheat
                if(h.isOverheating(hullItem)){
                    d.set( (int)(h.getTemp(hullItem) - h.getOverheatingTemp()) + d.get());
                }

                itemStack.setDamageValue(Math.max(itemStack.getMaxDamage(), itemStack.getDamageValue() + d.get()));
            });
        }
    }



    /** Item having forge Item Storage Capabilities Implementation */
    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        IItemContainerItem container = this;
        return new ICapabilityProvider() {
            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
                if (cap == ForgeCapabilities.ITEM_HANDLER)
                    return LazyOptional.of(() -> new ItemStorageItemImpl(stack, container, 40) {
                    }).cast();
                return LazyOptional.empty();
            }
        };
    }
    @Override
    public boolean isItemValid(ItemStack container, int slot, @NotNull ItemStack stack) {
        return true;
    }
}
