package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, CreateDeposits.MODID);

    public static final RegistryFluid IRON_FERTILIZER = registerFluid("iron_fertilizer", IronOreFertilizer.FLOWING_FLUID_PROPERTIES);

    private static RegistryFluid registerFluid(String name, ForgeFlowingFluid.Properties flowingFluidProperties) {
        var fluid = new RegistryFluid();
        fluid.Source = FLUIDS.register(name+"_source", () -> new ForgeFlowingFluid.Source(flowingFluidProperties));
        fluid.Flowing = FLUIDS.register(name+"_flowing", () -> new ForgeFlowingFluid.Flowing(flowingFluidProperties));
        return fluid;
    }

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

    public static class RegistryFluid {
        public RegistryObject<FlowingFluid> Source;
        public RegistryObject<FlowingFluid> Flowing;

    }
}
