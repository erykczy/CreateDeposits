package com.thecode.createdeposits.block;

import com.simibubi.create.content.fluids.transfer.FluidManipulationBehaviour;
import com.thecode.createdeposits.block.entity.SurfaceOreGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

// TODO make it indestructible by drill
public class SurfaceOreGeneratorBlock extends BaseEntityBlock {
    protected SurfaceOreGeneratorBlock() {
        super(Properties.copy(Blocks.OBSIDIAN).strength(-1.0F, 3600000.0F));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SurfaceOreGeneratorBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : (level, pos, state, blockEntity) -> ((SurfaceOreGeneratorBlockEntity)blockEntity).tick();
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        if(pLevel.isClientSide()) return;
        var blockEntity = (SurfaceOreGeneratorBlockEntity)pLevel.getBlockEntity(pPos);
        if(pLevel.getBlockState(pPos.above()).isAir()) {
            blockEntity.trySpawnOre();
        }
    }

    @Override
    public float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
        return CanPlayerDestroy(pPlayer) ? 10 : 0;
    }

    private boolean CanPlayerDestroy(Player player) {
        return player.getInventory().getSelected().getEnchantmentLevel(Enchantments.SILK_TOUCH) != 0;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        if(pLevel.isClientSide()) return;
        var blockEntity = (SurfaceOreGeneratorBlockEntity)pLevel.getBlockEntity(pPos);
        blockEntity.trySpawnOre();
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(!pLevel.isClientSide()) {
            var blockEntity = (SurfaceOreGeneratorBlockEntity)pLevel.getBlockEntity(pPos);
            blockEntity.tryRemoveOre();
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }
}