package com.thecode.createdeposits.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SurfaceOreBlock extends Block {
    public SurfaceOreBlock() {
        super(Properties.copy(Blocks.IRON_ORE).strength(5.0f, 6.0f).noOcclusion().pushReaction(PushReaction.BLOCK).requiresCorrectToolForDrops());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);
    }

    @Override
    public float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
        return pPlayer.isCreative() ? super.getDestroyProgress(pState, pPlayer, pLevel, pPos) : 0f;
    }
}
