package com.thecode.createdeposits.block.entity;

import com.thecode.createdeposits.CreateDeposits;
import com.thecode.createdeposits.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CreateDeposits.MODID);

    public static final RegistryObject<BlockEntityType<SurfaceOreGeneratorBlockEntity>> SURFACE_ORE_GENERATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("surface_ore_generator_block_entity", () -> BlockEntityType.Builder.of(SurfaceOreGeneratorBlockEntity::new,
            ModBlocks.SURFACE_ORE_GENERATOR.get()
    ).build(null));

    public static void Register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
