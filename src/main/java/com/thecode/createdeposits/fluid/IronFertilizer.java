package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;

public class IronFertilizer extends FluidRegistryContainer {
    public IronFertilizer(String name) {
        super(
            name,
            FluidType.Properties.create().canConvertToSource(false).motionScale(0.02d).density(10000).viscosity(50000).canSwim(false).fallDistanceModifier(0f),
            () -> FluidRegistryContainer.createExtension(
                    new ClientExtensions(
                            //new ResourceLocation(CreateDeposits.MODID, "fluid/"+name+"_still"),
                            //new ResourceLocation(CreateDeposits.MODID, "fluid/"+name+"_flow"),
                            new ResourceLocation(CreateDeposits.MODID, "block/fluid/still/iron_fertilizer"),
                            new ResourceLocation(CreateDeposits.MODID, "block/fluid/flow/iron_fertilizer"),
                            new ResourceLocation(CreateDeposits.MODID, "fluid/misc/fluid/in_"+name),
                            new ResourceLocation(CreateDeposits.MODID, "textures/misc/fluid/in_"+name+".png")
                    )
                    //.tint(0xD90D0D0D)
                    .fogColor(216 / 255f, 175 / 255f, 147 / 255f)
                    .fogRange(0f, 0.1f)
            ),
            new AdditionalProperties().tickRate(10),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
        );
    }
}
