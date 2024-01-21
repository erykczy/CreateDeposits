package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation IRON_FERTILIZER_OVERLAY_RL = new ResourceLocation(CreateDeposits.MODID, "misc/in_iron_fertilizer");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CreateDeposits.MODID);

    public static final RegistryObject<FluidType> IRON_FERTILIZER = RegisterFluid("iron_fertilizer", FluidType.Properties.create().lightLevel(2).density(15).viscosity(5));

    private static RegistryObject<FluidType> RegisterFluid(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, IRON_FERTILIZER_OVERLAY_RL, 0xD90D0D0D, new Vector3f(13 / 255f, 13 / 255f, 13 / 255f), properties));
    }

    public static void Register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
