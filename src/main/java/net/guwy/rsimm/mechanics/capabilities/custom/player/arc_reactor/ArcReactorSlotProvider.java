package net.guwy.rsimm.mechanics.capabilities.custom.player.arc_reactor;

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

public class ArcReactorSlotProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ArcReactorSlot> PLAYER_REACTOR_SLOT = CapabilityManager.get(new CapabilityToken<ArcReactorSlot>() { });

    private ArcReactorSlot arcReactorSlot = null;
    private final LazyOptional<ArcReactorSlot> optional = LazyOptional.of(this::createPlayerArcReactorSlot);

    private ArcReactorSlot createPlayerArcReactorSlot() {
        if(this.arcReactorSlot == null){
            this.arcReactorSlot = new ArcReactorSlot();
        }
        return arcReactorSlot;
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_REACTOR_SLOT){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerArcReactorSlot().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerArcReactorSlot().loadNBTData(nbt);
    }
}
