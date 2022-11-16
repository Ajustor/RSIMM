package net.guwy.rsimm.mechanics.capabilities.player.armor_data;

import net.guwy.rsimm.mechanics.capabilities.player.arc_reactor.ArcReactorSlot;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IronmanArmorDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<IronmanArmorData> PLAYER_IRONMAN_ARMOR_DATA = CapabilityManager.get(new CapabilityToken<IronmanArmorData>() { });

    private IronmanArmorData ironmanArmorData = null;
    private final LazyOptional<IronmanArmorData> optional = LazyOptional.of(this::createPlayerIronmanArmorData);

    private IronmanArmorData createPlayerIronmanArmorData() {
        if(this.ironmanArmorData == null){
            this.ironmanArmorData = new IronmanArmorData();
        }
        return ironmanArmorData;
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_IRONMAN_ARMOR_DATA){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerIronmanArmorData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerIronmanArmorData().loadNBTData(nbt);
    }
}
