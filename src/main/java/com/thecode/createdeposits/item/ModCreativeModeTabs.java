package com.thecode.createdeposits.item;

import com.thecode.createdeposits.CreateDeposits;
import com.thecode.createdeposits.block.ModBlocks;
import com.thecode.createdeposits.fluid.ModFluids;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateDeposits.MODID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TAB.register("createdeposits_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.COAL))
                    .title(Component.translatable("creativetab.createdeposits_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.SURFACE_ORE_GENERATOR.get());
                        pOutput.accept(ModBlocks.SURFACE_ORE_STONE.get());
                        pOutput.accept(ModFluids.IRON_FERTILIZER.bucket.get());
                    })
                    .build());

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TAB.register(modEventBus);
    }
}
