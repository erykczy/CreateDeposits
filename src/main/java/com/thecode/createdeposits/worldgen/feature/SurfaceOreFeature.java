package com.thecode.createdeposits.worldgen.feature;

import com.mojang.serialization.Codec;
import com.thecode.createdeposits.block.ModBlocks;
import com.thecode.createdeposits.block.entity.SurfaceOreGeneratorBlockEntity;
import com.thecode.createdeposits.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import javax.annotation.Nullable;

public class SurfaceOreFeature extends Feature<NoneFeatureConfiguration> {
    private static final int MinRadius = 2;
    private static final int MaxRadius = 6;
    private static final int MaxYDelta = 5;

    public SurfaceOreFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        if(pContext.level().getBlockState(pContext.origin().below()).is(Blocks.WATER)) return false;
        var origin = pContext.origin();
        var random = pContext.random();

        var ore = ModBlocks.GetRandomSurfaceOre(random);
        var radius = random.nextInt(MinRadius, MaxRadius + 1);

        for(int deltaX = -radius; deltaX <= radius; deltaX++) {
            for(int deltaZ = -radius; deltaZ <= radius; deltaZ++) {
                var distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                if(distance > radius) continue;

                var oreChance = Math.pow(1f - (distance / radius) - 0.25f, 0.6f);
                var rng = random.nextFloat();

                if(rng <= oreChance) {
                    // ore
                    var placePos = getOrePlacePos(pContext, origin.getX() + deltaX, origin.getY(), origin.getZ() + deltaZ);
                    if(placePos != null)
                        placeOreGenerator(pContext, ore, placePos.below());
                }
                else {
                    // stone
                    var stoneChance = Math.pow(1 - (distance / radius), 0.3f);
                    rng = random.nextFloat();
                    if(rng <= stoneChance) {
                        var placePos = getOrePlacePos(pContext, origin.getX() + deltaX, origin.getY(), origin.getZ() + deltaZ);
                        if(placePos != null)
                            setBlock(pContext.level(), placePos.below(), ModBlocks.SURFACE_ORE_STONE.get().defaultBlockState());
                    }
                }
            }
        }

        return true;
    }

    @Nullable
    private BlockPos getOrePlacePos(FeaturePlaceContext<NoneFeatureConfiguration> pContext, int x, int y, int z) {
        var level = pContext.level();

        BlockPos pos = null;
        for(int ty = y + MaxYDelta; ty >= y - MaxYDelta; ty--) {
            var tpos = new BlockPos(x, ty, z);
            if(level.getBlockState(tpos).isAir()) {
                if(ty == y - MaxYDelta && !level.getBlockState(tpos.below()).is(ModTags.ORE_SPAWNABLE)) {
                    pos = null;
                    break;
                }
                pos = tpos;
            }
            else if(level.getBlockState(tpos).is(ModTags.ORE_SPAWNABLE)) {
                break;
            }
        }

        return pos;
    }

    private void placeOreGenerator(FeaturePlaceContext<NoneFeatureConfiguration> pContext, Block ore, BlockPos pos) {
        var level = pContext.level();

        if(!trySetBlock(level, pos, ModBlocks.SURFACE_ORE_GENERATOR.get().defaultBlockState())) return;
        var generatorEntity = (SurfaceOreGeneratorBlockEntity)level.getBlockEntity(pos);
        generatorEntity.setOre(ore);
        trySetBlock(level, pos.above(), ore.defaultBlockState());
    }

    private boolean trySetBlock(WorldGenLevel level, BlockPos pos, BlockState blockState) {
        setBlock(level, pos, blockState);
        return level.getBlockState(pos) == blockState;
    }
}
