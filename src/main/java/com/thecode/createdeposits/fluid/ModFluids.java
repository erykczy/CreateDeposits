package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, CreateDeposits.MODID);

    public static final FluidRegistryContainer IRON_FERTILIZER = new IronFertilizer("iron_fertilizer");

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
