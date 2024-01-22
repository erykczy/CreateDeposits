package com.thecode.createdeposits.fluid;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;

public class Fertilizer extends FluidRegistryContainer {
    public Fertilizer(String name, int fogRed, int fogGreen, int fogBlue) {
        super(
            name,
            FluidType.Properties.create().canConvertToSource(false).motionScale(0.02d).density(100000).viscosity(100000).canSwim(false).fallDistanceModifier(0f),
            () -> FluidRegistryContainer.createExtension(
                    new ClientExtensions(
                            new ResourceLocation(CreateDeposits.MODID, "block/fluid/still/"+name),
                            new ResourceLocation(CreateDeposits.MODID, "block/fluid/flow/"+name),
                            new ResourceLocation(CreateDeposits.MODID, "fluid/misc/fluid/in_"+name),
                            new ResourceLocation(CreateDeposits.MODID, "textures/misc/fluid/in_"+name+".png")
                    )
                    //.tint(0xD90D0D0D)
                    .fogColor(fogRed / 255f, fogGreen / 255f, fogBlue / 255f)
                    .fogRange(0f, 0.1f)
            ),
            new AdditionalProperties().tickRate(10),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
        );
    }
}
