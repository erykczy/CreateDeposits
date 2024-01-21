package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, CreateDeposits.MODID);

    public static final FluidRegistryContainer ANDESITE_FERTILIZER = new Fertilizer("andesite_fertilizer", 116, 135, 127);
    public static final FluidRegistryContainer COAL_FERTILIZER = new Fertilizer("coal_fertilizer", 21, 21, 21);
    public static final FluidRegistryContainer COPPER_FERTILIZER = new Fertilizer("copper_fertilizer", 188, 114, 91);
    public static final FluidRegistryContainer GOLD_FERTILIZER = new Fertilizer("gold_fertilizer", 229, 151, 0);
    public static final FluidRegistryContainer IRON_FERTILIZER = new Fertilizer("iron_fertilizer", 199, 154, 96);
    public static final FluidRegistryContainer REDSTONE_FERTILIZER = new Fertilizer("redstone_fertilizer", 199, 9, 41);
    public static final FluidRegistryContainer ZINC_FERTILIZER = new Fertilizer("zinc_fertilizer", 160, 199, 142);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
