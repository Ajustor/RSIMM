package net.guwy.rsimm.content.blocks.armor_equipping_station;

import net.guwy.rsimm.index.RsImmBlockEntities;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmorEquippingStationBlock extends BaseEntityBlock {
    public ArmorEquippingStationBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("tooltip.rsimm.armor_equipping_station.t.1"));
        if(!Screen.hasShiftDown()){
            pTooltip.add(Component.translatable("tooltip.rsimm.armor_equipping_station.t.2"));
        } else {
            pTooltip.add(Component.translatable("tooltip.rsimm.armor_equipping_station.s.1"));
            pTooltip.add(Component.translatable("tooltip.rsimm.armor_equipping_station.s.2"));
            pTooltip.add(Component.translatable("tooltip.rsimm.armor_equipping_station.s.3"));
            pTooltip.add(Component.translatable("tooltip.rsimm.armor_equipping_station.s.4"));
            pTooltip.add(Component.translatable("tooltip.rsimm.armor_equipping_station.s.5"));
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    /* BLOCK ENTITY */

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof ArmorEquippingStationBlockEntity) {
                ((ArmorEquippingStationBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof ArmorEquippingStationBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (ArmorEquippingStationBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ArmorEquippingStationBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, RsImmBlockEntities.ARMOR_EQUIPPING_STATION.get(),
                ArmorEquippingStationBlockEntity::tick);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity.getType() == EntityType.PLAYER){
            ArmorEquippingStationBlockEntity.stepOn(pLevel, pPos, pState, pEntity);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
