package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CreateDeposits.MODID);

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
