package net.guwy.rsimm.content.items.arc_reactors;

import net.guwy.rsimm.mechanics.IItemEnergyContainer;
import net.guwy.rsimm.mechanics.ItemEnergyStorageImpl;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class ArcReactorItem extends AbstractArcReactorItem {
    String displayName;
    long maxEnergy;
    long energyOutput;
    int idleDrain;
    int poisonFactor;
    ResourceLocation depletedName;
    ResourceLocation overlayTexture;

    public ArcReactorItem(Properties pProperties, String displayName, long maxEnergy, long energyOutput, int idleDrain, int poisonFactor, ResourceLocation depletedName, @Nullable ResourceLocation overlayTexture) {
        super(pProperties);
        this.displayName = displayName;
        this.maxEnergy = maxEnergy;
        this.energyOutput = energyOutput;
        this.idleDrain = idleDrain;
        this.poisonFactor = poisonFactor;
        this.depletedName = depletedName;
        this.overlayTexture = overlayTexture;
    }

    @Override
    public @Nullable ResourceLocation OverlayIcon() {
        return overlayTexture;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public long maxEnergy() {
        return maxEnergy;
    }

    @Override
    public long energyOutput() {
        return energyOutput;
    }

    @Override
    public int idleDrain() {
        return idleDrain;
    }

    @Override
    public int poisonFactor() {
        return poisonFactor;
    }

    @Override
    public ResourceLocation depletedName() {
        return depletedName;
    }
}
