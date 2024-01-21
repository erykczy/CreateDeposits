package com.thecode.createdeposits.tag;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> ORE_SPAWNABLE = tag("ore_spawnable");
    public static final TagKey<Block> SURFACE_ORE = tag("surface_ore");

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(CreateDeposits.MODID, name));
    }
}
