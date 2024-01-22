package com.thecode.createdeposits.block;

import com.thecode.createdeposits.block.entity.SurfaceOreGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SurfaceOreGeneratorBlock extends GeneratorBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SurfaceOreGeneratorBlockEntity(pPos, pState);
    }
}