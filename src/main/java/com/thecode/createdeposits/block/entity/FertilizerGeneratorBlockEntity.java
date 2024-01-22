package com.thecode.createdeposits.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;

public class FertilizerGeneratorBlockEntity extends BlockEntity implements ITickingBlockEntity {
    // in seconds
    public static final float RenewInterval = 5f;
    public Fluid fertilizer = Fluids.EMPTY;

    public FertilizerGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FERTILIZER_GENERATOR_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putString("Fertilizer", ForgeRegistries.FLUIDS.getKey(fertilizer).toString());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if(pTag.contains("Fertilizer")) {
            fertilizer = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(pTag.getString("Fertilizer")));
        }
    }

    private int ticksLeft = 0;
    @Override
    public void tick() {
        if(getLevel() == null || getLevel().isClientSide()) return;

        ticksLeft--;
        if(ticksLeft <= 0) {
            tryRenewFertilizer();
            ticksLeft = (int)RenewInterval * 20;
        }
    }

    public boolean canRenewFertilizer() {
        var pos = getBlockPos().above();
        var state = getLevel().getBlockState(pos);
        if(state.isAir()) return true;
        if(state.canBeReplaced(fertilizer)) return true;

        return false;
    }

    public void tryRenewFertilizer() {
        if(!canRenewFertilizer()) return;
        var pos = getBlockPos().above();
        var state = getLevel().getBlockState(pos);
        if(!state.liquid()) {
            getLevel().destroyBlock(pos, true);
        }
        getLevel().setBlockAndUpdate(getBlockPos().above(), fertilizer.defaultFluidState().createLegacyBlock());
    }
}
