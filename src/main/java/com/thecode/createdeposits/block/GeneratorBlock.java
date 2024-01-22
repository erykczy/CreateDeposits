package com.thecode.createdeposits.block;

import com.thecode.createdeposits.block.entity.ITickingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class GeneratorBlock extends BaseEntityBlock {
    protected GeneratorBlock() {
        super(Properties.copy(Blocks.OBSIDIAN).strength(-1.0F, 3600000.0F));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : (level, pos, state, blockEntity) -> ((ITickingBlockEntity)blockEntity).tick();
    }

    @Override
    public float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
        return CanPlayerDestroy(pPlayer) ? 10 : 0;
    }

    private boolean CanPlayerDestroy(Player player) {
        return player.getInventory().getSelected().getEnchantmentLevel(Enchantments.SILK_TOUCH) != 0;
    }
}