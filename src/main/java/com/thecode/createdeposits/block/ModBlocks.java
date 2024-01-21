package com.thecode.createdeposits.block;

import com.thecode.createdeposits.CreateDeposits;
import com.thecode.createdeposits.fluid.ModFluids;
import com.thecode.createdeposits.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateDeposits.MODID);

    public static final RegistryObject<Block> COAL_SURFACE_ORE = BLOCKS.register("coal_surface_ore", SurfaceOreBlock::new);
    public static final RegistryObject<Block> COPPER_SURFACE_ORE = BLOCKS.register("copper_surface_ore", SurfaceOreBlock::new);
    public static final RegistryObject<Block> GOLD_SURFACE_ORE = BLOCKS.register("gold_surface_ore", SurfaceOreBlock::new);
    public static final RegistryObject<Block> REDSTONE_SURFACE_ORE = BLOCKS.register("redstone_surface_ore", SurfaceOreBlock::new);
    public static final RegistryObject<Block> ZINC_SURFACE_ORE = BLOCKS.register("zinc_surface_ore", SurfaceOreBlock::new);
    public static final RegistryObject<Block> IRON_SURFACE_ORE = BLOCKS.register("iron_surface_ore", SurfaceOreBlock::new);
    public static final RegistryObject<Block> ANDESITE_SURFACE_ORE = BLOCKS.register("andesite_surface_ore", SurfaceOreBlock::new);

    public static final RegistryObject<Block> SURFACE_ORE_STONE = registerBlockAndItem("surface_ore_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> SURFACE_ORE_GENERATOR = registerBlockAndItem("surface_ore_generator", SurfaceOreGeneratorBlock::new);

    public static ArrayList<RegistryObject<Block>> SURFACE_ORES = new ArrayList<>() {{
        add(COAL_SURFACE_ORE);
        add(COPPER_SURFACE_ORE);
        add(GOLD_SURFACE_ORE);
        add(REDSTONE_SURFACE_ORE);
        add(ZINC_SURFACE_ORE);
        add(IRON_SURFACE_ORE);
        add(ANDESITE_SURFACE_ORE);
    }};

    private static <T extends Block> RegistryObject<T> registerBlockAndItem(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject);
        return registryObject;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static Block GetRandomSurfaceOre(RandomSource random) {
        return SURFACE_ORES.get(random.nextInt(0, SURFACE_ORES.size())).get();
    }
}
