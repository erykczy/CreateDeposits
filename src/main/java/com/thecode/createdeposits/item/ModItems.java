package com.thecode.createdeposits.item;

import com.thecode.createdeposits.CreateDeposits;
import com.thecode.createdeposits.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CreateDeposits.MODID);

    public static final RegistryObject<Item> IRON_FERTILIZER_BUCKET = ITEMS.register("iron_fertilizer_bucket", () -> new BucketItem(ModFluids.SOURCE_IRON_FERTILIZER, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static void Register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
