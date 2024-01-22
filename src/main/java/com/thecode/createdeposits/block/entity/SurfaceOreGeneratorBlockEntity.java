package com.thecode.createdeposits.block.entity;

import com.thecode.createdeposits.fluid.FluidRegistryContainer;
import com.thecode.createdeposits.fluid.ModFluids;
import com.thecode.createdeposits.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SurfaceOreGeneratorBlockEntity extends BlockEntity {
    public static final int CAPACITY = 1000;
    public static final int AMOUNT_FOR_REFUEL = 1000;
    public FluidTank fluidTank;
    public LazyOptional<FluidTank> fluidCapability;
    public int amount = 64;
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
        pTag.putInt("Amount", amount);
        pTag.putString("Ore", ForgeRegistries.BLOCKS.getKey(oreBlock).toString());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        fluidTank.readFromNBT(pTag.getCompound("FluidTank"));
        if(pTag.contains("Amount"))
            amount = pTag.getInt("Amount");
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

    public void tryRefuel() {
        if(fluidTank.getFluidAmount() >= AMOUNT_FOR_REFUEL) {
            fluidTank.drain(AMOUNT_FOR_REFUEL, IFluidHandler.FluidAction.EXECUTE);
            amount += 1;
            trySpawnOre();
        }
    }

    public void trySpawnOre() {
        if(amount <= 0) return;
        if(!getLevel().getBlockState(getBlockPos().above()).isAir()) return;
        getLevel().setBlockAndUpdate(getBlockPos().above(), oreBlock.defaultBlockState());
        amount--;
    }

    public void tryRemoveOre() {
        if(!getLevel().getBlockState(getBlockPos().above()).is(ModTags.SURFACE_ORE)) return;
        getLevel().setBlockAndUpdate(getBlockPos().above(), Blocks.AIR.defaultBlockState());
    }

    public void tick() {
        if(getLevel() == null || getLevel().isClientSide()) return;

        tryRefuel();
    }
}