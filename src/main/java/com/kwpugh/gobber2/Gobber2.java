package com.kwpugh.gobber2;

import com.kwpugh.gobber2.config.Gobber2Config;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.EnchantmentInit;
import com.kwpugh.gobber2.init.FuelInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.init.LootTableInit;
import com.kwpugh.gobber2.init.PortalInit;
import com.kwpugh.gobber2.init.TagInit;
import com.kwpugh.gobber2.world.Gobber2Dimension;
import com.kwpugh.gobber2.world.ModConfiguredFeatures;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo;
import top.theillusivec4.curios.api.SlotTypePreset;

public class Gobber2 implements ModInitializer
{
	public static final String MOD_ID = "gobber2";
	public static final Identifier MOD_DIMENSION_ID = new Identifier(Gobber2.MOD_ID, "mining");	
	public static final Identifier MOD_DIMENSION2_ID = new Identifier(Gobber2.MOD_ID, "hunting");
	public static final Identifier MOD_DIMENSION3_ID = new Identifier(Gobber2.MOD_ID, "caving");
	public static final Identifier MOD_DIMENSION4_ID = new Identifier(Gobber2.MOD_ID, "nethering");
	public static final ItemGroup GOBBER2_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "gobber2_group"), () -> new ItemStack(ItemInit.GOBBER2_SWORD_NETHER));
	public static final Gobber2Config CONFIG = AutoConfig.register(Gobber2Config.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();


    @Override
    public void onInitialize()
    { 
    	TagInit.registerTags();
    	BlockInit.registerBlocks();
    	BlockInit.registerBlockItems();
    	BlockInit.registerBlockEntities();
    	ItemInit.registerItems();
    	ModConfiguredFeatures.Features();
    	FuelInit.registerFuels();
    	Gobber2Dimension.setupDimension();  	
    	EnchantmentInit.registerEnchantments();
    	EffectsInit.registerEffects();
    	LootTableInit.registerLoot();
    	PortalInit.registerPortal();

    	if(FabricLoader.getInstance().isModLoaded("curios"))
    	{
        	CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, SlotTypePreset.RING.getInfoBuilder().size(4).build());
        	CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, SlotTypePreset.BELT.getInfoBuilder().size(2).build());
    	}
    }
}