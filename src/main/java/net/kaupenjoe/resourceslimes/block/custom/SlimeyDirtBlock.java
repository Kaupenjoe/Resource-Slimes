package net.kaupenjoe.resourceslimes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class SlimeyDirtBlock extends Block {
    public static final BooleanProperty SLIMEY = BooleanProperty.create("slimey");
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public SlimeyDirtBlock(Properties pProperties) {
        super(pProperties);
    }

    public BlockState getStateForPlacement(BlockPlaceContext p_153131_) {
        return !this.defaultBlockState().canSurvive(p_153131_.getLevel(), p_153131_.getClickedPos()) ? Block.pushEntitiesUp(this.defaultBlockState(), Blocks.DIRT.defaultBlockState(), p_153131_.getLevel(), p_153131_.getClickedPos()) : super.getStateForPlacement(p_153131_);
    }

    public BlockState updateShape(BlockState p_153152_, Direction p_153153_, BlockState p_153154_, LevelAccessor p_153155_, BlockPos p_153156_, BlockPos p_153157_) {
        if (p_153153_ == Direction.UP && !p_153152_.canSurvive(p_153155_, p_153156_)) {
            p_153155_.scheduleTick(p_153156_, this, 1);
        }

        return super.updateShape(p_153152_, p_153153_, p_153154_, p_153155_, p_153156_, p_153157_);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
        FarmBlock.turnToDirt(p_222945_, p_222946_, p_222947_);
    }

    public boolean canSurvive(BlockState p_153148_, LevelReader p_153149_, BlockPos p_153150_) {
        BlockState blockstate = p_153149_.getBlockState(p_153150_.above());
        return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(SLIMEY);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
