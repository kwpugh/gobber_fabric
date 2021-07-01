package com.kwpugh.gobber2.init;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;
import com.kwpugh.gobber2.Gobber2;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class LootTableInit
{
	static boolean enable = Gobber2.CONFIG.GENERAL.lootEnable;
	static float lootChance = Gobber2.CONFIG.GENERAL.lootChance;
	
	private static final List<LootTableInsert> INSERTS = Lists.newArrayList();

	public static void registerLoot()
	{
		if(enable)
		{
			FabricLootPoolBuilder GOBBER_RING_RETURN = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootTableRange.create(1))
					.with(ItemEntry.builder(ItemInit.GOBBER2_RING_RETURN))
					.withCondition(RandomChanceLootCondition.builder(lootChance).build());

			insert(new LootTableInsert(GOBBER_RING_RETURN,
					new Identifier("minecraft", "chests/desert_pyramid"),
					new Identifier("minecraft", "chests/jungle_temple"),	
					new Identifier("minecraft", "chests/igloo_chest"),
					new Identifier("minecraft", "chests/pillager_outpost")
			));


			FabricLootPoolBuilder GOBBER_RING_TELEPORT = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootTableRange.create(1))
					.with(ItemEntry.builder(ItemInit.GOBBER2_RING_TELEPORT))
					.withCondition(RandomChanceLootCondition.builder(lootChance).build());

			insert(new LootTableInsert(GOBBER_RING_TELEPORT,									
					new Identifier("minecraft", "chests/stronghold_library"),
					new Identifier("minecraft", "chests/pillager_outpost"),
					new Identifier("minecraft", "chests/nether_bridge"),
					new Identifier("minecraft", "chests/shipwreck_treasure")
			));
			
			
			LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
				INSERTS.forEach(i->{
					if(ArrayUtils.contains(i.tables, identifier))
					{
						i.insert(supplier);
					}
				});
			}));
		}		
	}

	public static void insert(LootTableInsert insert)
	{
		INSERTS.add(insert);
	}

	public static class LootTableInsert
	{
		public final Identifier[] tables;
		public final FabricLootPoolBuilder lootPool;

		public LootTableInsert(FabricLootPoolBuilder lootPool, Identifier... tables)
		{
			this.tables = tables;
			this.lootPool = lootPool;
		}

		public void insert(FabricLootSupplierBuilder supplier)
		{
			supplier.pool(lootPool);
		}
	}
}