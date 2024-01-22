package com.thecode.createdeposits.block;

import com.thecode.createdeposits.block.entity.FertilizerGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FertilizerGeneratorBlock extends GeneratorBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new FertilizerGeneratorBlockEntity(pPos, pState);
    }
}
