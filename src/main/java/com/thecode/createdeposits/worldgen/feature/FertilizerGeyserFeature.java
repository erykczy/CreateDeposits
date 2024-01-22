package com.thecode.createdeposits.worldgen.feature;

import com.mojang.serialization.Codec;
import com.thecode.createdeposits.block.ModBlocks;
import com.thecode.createdeposits.block.entity.FertilizerGeneratorBlockEntity;
import com.thecode.createdeposits.fluid.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FertilizerGeyserFeature  extends Feature<NoneFeatureConfiguration> {
    private static final int MinRadius = 2;
    private static final int MaxRadius = 3;

    public FertilizerGeyserFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        var level = pContext.level();
        var origin = pContext.origin();
        var random = pContext.random();

        var fertilizer = ModFluids.GetRandomFertilizer(random);
        var radius = random.nextInt(MinRadius, MaxRadius + 1);


        for(int deltaX = -radius; deltaX <= radius; deltaX++) {
            for(int deltaZ = -radius; deltaZ <= radius; deltaZ++) {
                var distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                if(distance > radius) continue;

                var pos = new BlockPos(origin.getX() + deltaX, origin.getY(), origin.getZ() + deltaZ);

                trySetBlock(level, pos, fertilizer.defaultFluidState().createLegacyBlock());

                // sides
                if(distance + 1 > radius) {
                    trySetBlock(level, pos, ModBlocks.SURFACE_ORE_STONE.get().defaultBlockState());
                }
                // above
                if(!level.getBlockState(pos.above()).isAir())
                    trySetBlock(level, pos.above(), ModBlocks.SURFACE_ORE_STONE.get().defaultBlockState());
                // below
                for(int y = pos.getY() - 1; y >= 0; y--) {
                    var p = pos.atY(y);
                    if(!level.getBlockState(p).isAir()) break;
                    trySetBlock(level, p, ModBlocks.SURFACE_ORE_STONE.get().defaultBlockState());
                }
            }
        }

        for(int deltaX = -1; deltaX <= 1; deltaX++) {
            for (int deltaZ = -1; deltaZ <= 1; deltaZ++) {
                var pos = new BlockPos(origin.getX() + deltaX, origin.getY() + 1, origin.getZ() + deltaZ);
                trySetBlock(level, pos, Blocks.AIR.defaultBlockState());
            }
        }

        if(trySetBlock(level, origin, ModBlocks.FERTILIZER_GENERATOR.get().defaultBlockState())) {
            var blockEntity = (FertilizerGeneratorBlockEntity)level.getBlockEntity(origin);
            blockEntity.fertilizer = fertilizer;
        }

        return true;
    }


    private boolean trySetBlock(WorldGenLevel level, BlockPos pos, BlockState blockState) {
        setBlock(level, pos, blockState);
        return level.getBlockState(pos) == blockState;
    }
}
