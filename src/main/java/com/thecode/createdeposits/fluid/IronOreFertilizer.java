package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import com.thecode.createdeposits.block.ModBlocks;
import com.thecode.createdeposits.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.joml.Vector3f;

public class IronOreFertilizer extends BaseFluidType {
    public static final ResourceLocation IRON_FERTILIZER_OVERLAY_RL = new ResourceLocation(CreateDeposits.MODID, "misc/in_iron_fertilizer");
    public static final int SLOPE_FIND_DISTANCE = 2;
    public static final int LEVEL_DECREASE_PER_BLOCK = 2;
    public static final ForgeFlowingFluid.Properties FLOWING_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.IRON_FERTILIZER_TYPE, ModFluids.IRON_FERTILIZER.Source, ModFluids.IRON_FERTILIZER.Flowing
    ).slopeFindDistance(SLOPE_FIND_DISTANCE).levelDecreasePerBlock(LEVEL_DECREASE_PER_BLOCK).block(ModBlocks.IRON_FERTILIZER_BLOCK).bucket(ModItems.IRON_FERTILIZER_BUCKET);

    public IronOreFertilizer() {
        super(
            WATER_STILL_RL,
            WATER_FLOWING_RL,
            IRON_FERTILIZER_OVERLAY_RL,
            0xD90D0D0D,
            new Vector3f(13 / 255f, 13 / 255f, 13 / 255f),
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5)
        );
    }

}
