package com.thecode.createdeposits.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class SurfaceOreGeneratorBlockEntity extends BlockEntity {
    public Block OreBlock = Blocks.AIR;
    public int Amount = 1;

    public SurfaceOreGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SURFACE_ORE_GENERATOR_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("Amount", Amount);
        if(OreBlock != null)
            pTag.putString("Ore", ForgeRegistries.BLOCKS.getKey(OreBlock).toString());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if(pTag.contains("Amount"))
            Amount = pTag.getInt("Amount");
        if(pTag.contains("Ore")) {
            OreBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(pTag.getString("Ore")));
        }
    }

    public void spawnOre() {
        getLevel().setBlockAndUpdate(getBlockPos().above(), OreBlock.defaultBlockState());
    }

    public void removeOre() {
        getLevel().setBlockAndUpdate(getBlockPos().above(), Blocks.AIR.defaultBlockState());
    }
}