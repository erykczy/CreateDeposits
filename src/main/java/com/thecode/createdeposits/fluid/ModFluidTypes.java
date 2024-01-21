package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CreateDeposits.MODID);

    public static final RegistryObject<BaseFluidType> IRON_FERTILIZER_TYPE = FLUID_TYPES.register("iron_fertilizer", IronOreFertilizer::new);

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
