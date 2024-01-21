package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import com.thecode.createdeposits.block.ModBlocks;
import com.thecode.createdeposits.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, CreateDeposits.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_IRON_FERTILIZER = FLUIDS.register("iron_fertilizer", () -> new ForgeFlowingFluid.Source(ModFluids.IRON_FERTILIZER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_IRON_FERTILIZER = FLUIDS.register("flowing_iron_fertilizer", () -> new ForgeFlowingFluid.Flowing(ModFluids.IRON_FERTILIZER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties IRON_FERTILIZER_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.IRON_FERTILIZER, SOURCE_IRON_FERTILIZER, FLOWING_IRON_FERTILIZER
    ).slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.IRON_FERTILIZER_BLOCK).bucket(ModItems.IRON_FERTILIZER_BUCKET);

    public static void Register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
