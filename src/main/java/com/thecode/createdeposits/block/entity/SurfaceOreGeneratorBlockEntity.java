package com.thecode.createdeposits.block.entity;

import com.thecode.createdeposits.fluid.FluidRegistryContainer;
import com.thecode.createdeposits.fluid.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SurfaceOreGeneratorBlockEntity extends BlockEntity {
    public static final int CAPACITY = 1000;
    public static final int ORE_COST = 1000;
    public FluidTank fluidTank;
    public LazyOptional<FluidTank> fluidCapability;
    private Block oreBlock = Blocks.AIR;
    private ResourceLocation oreKey = null;
    private FluidRegistryContainer fertilizer = null;

    public SurfaceOreGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SURFACE_ORE_GENERATOR_BLOCK_ENTITY.get(), pPos, pBlockState);
        fluidTank = new FluidTank(CAPACITY, (stack) -> false) {
            @Override
            protected void onContentsChanged() {
                SurfaceOreGeneratorBlockEntity.this.sendUpdate();
                SurfaceOreGeneratorBlockEntity.this.setChanged();
            }
        };
        fluidCapability = LazyOptional.of(() -> fluidTank);
    }

    private void sendUpdate() {
        if(getLevel() != null)
            getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("FluidTank", fluidTank.writeToNBT(new CompoundTag()));
        pTag.putString("Ore", ForgeRegistries.BLOCKS.getKey(oreBlock).toString());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        fluidTank.readFromNBT(pTag.getCompound("FluidTank"));
        if(pTag.contains("Ore")) {
            setOre(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(pTag.getString("Ore"))));
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.FLUID_HANDLER)
            return fluidCapability.cast();

        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        fluidCapability.invalidate();
    }

    public void setOre(Block ore) {
        oreBlock = ore;
        oreKey = ForgeRegistries.BLOCKS.getKey(oreBlock);
        fertilizer = ModFluids.FERTILIZERS.getOrDefault(oreKey, null);
        if(fertilizer != null)
            fluidTank.setValidator((stack) -> stack.getFluid().isSame(fertilizer.source.get()));
    }

    public void tick() {
        if(getLevel() == null || getLevel().isClientSide()) return;

        if(canPlaceOre()) {
            if(fluidTank.getFluidAmount() >= ORE_COST) {
                fluidTank.drain(ORE_COST, IFluidHandler.FluidAction.EXECUTE);
                getLevel().setBlockAndUpdate(getBlockPos().above(), oreBlock.defaultBlockState());
            }
        }
    }

    public boolean canPlaceOre() {
        return getLevel().getBlockState(getBlockPos().above()).isAir();
    }
}