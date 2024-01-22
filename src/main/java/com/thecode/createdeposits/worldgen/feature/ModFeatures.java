package com.thecode.createdeposits.worldgen.feature;

import com.thecode.createdeposits.CreateDeposits;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, CreateDeposits.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SURFACE_ORE = register("surface_ore", () -> new SurfaceOreFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FERTILIZER_GEYSER = register("fertilizer_geyser", () -> new FertilizerGeyserFeature(NoneFeatureConfiguration.CODEC));

    public static <T extends FeatureConfiguration> RegistryObject<Feature<T>> register(String name, Supplier<Feature<T>> featureSupplier) {
        return FEATURES.register(name, featureSupplier);
    }

    public static void registerFeatures(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

}
