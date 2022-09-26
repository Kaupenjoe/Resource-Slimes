package net.kaupenjoe.resourceslimes.block.custom;

import net.kaupenjoe.resourceslimes.block.entity.EnergyReceiverBlockEntity;
import net.kaupenjoe.resourceslimes.block.entity.GemCuttingStationBlockEntity;
import net.kaupenjoe.resourceslimes.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EnergyReceiverBlock extends BaseEntityBlock {
    public EnergyReceiverBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new EnergyReceiverBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide) return null;
        return createTickerHelper(pBlockEntityType, ModBlockEntities.ENERGY_RECEIVER.get(),
                EnergyReceiverBlockEntity::serverTick);
    }
}
