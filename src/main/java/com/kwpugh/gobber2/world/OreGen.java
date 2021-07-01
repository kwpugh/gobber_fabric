package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;
import com.kwpugh.gobber2.init.BlockInit;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OreGen
{	
	public static final Gobber2Config.Ores CONFIG = Gobber2.CONFIG.ORES;
	
	public static ConfiguredFeature<?, ?> ORE_GOBBER_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
			  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			  BlockInit.GOBBER2_ORE.getDefaultState(),
			  CONFIG.gobberVeinSize)) // vein size
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
			  0, // bottom offset
			  CONFIG.gobberMinLevel, // min y level
			  CONFIG.gobberMaxLevel))) // max y level
			.spreadHorizontally()
			.repeat(CONFIG.gobberPerChunk); // number of veins per chunk	
	
	public static ConfiguredFeature<?, ?> ORE_GOBBER_NETHER = Feature.ORE
			.configure(new OreFeatureConfig(
			  OreFeatureConfig.Rules.BASE_STONE_NETHER,
			  BlockInit.GOBBER2_ORE_NETHER.getDefaultState(),
			  CONFIG.netherGobberVeinSize)) // vein size
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
			  0, // bottom offset
			  CONFIG.netherGobberMinLevel, // min y level
			  CONFIG.netherGobberMaxLevel))) // max y level
			.spreadHorizontally()
			.repeat(CONFIG.netherGobberPerChunk); // number of veins per chunk		
	
	public static ConfiguredFeature<?, ?> ORE_GOBBER_THEEND = Feature.ORE
		    .configure(new OreFeatureConfig(
		      new BlockMatchRuleTest(Blocks.END_STONE), // base block is endstone in the end biomes
		      BlockInit.GOBBER2_ORE_END.getDefaultState(),
		      CONFIG.endGobberVeinSize))
		    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
		      0,
		      CONFIG.endGobberMinLevel,
		      CONFIG.endGobberMaxLevel)))
		    .spreadHorizontally()
		    .repeat(CONFIG.endGobberPerChunk);
	
	public static ConfiguredFeature<?, ?> ORE_LUCKY_BLOCK_OVERWORLD = Feature.ORE
			.configure(new OreFeatureConfig(
			  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
			  BlockInit.GOBBER2_LUCKY_BLOCK.getDefaultState(),
			  CONFIG.luckyVeinSize)) // vein size
			.decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
			  0, // bottom offset
			  CONFIG.luckyMinLevel, // min y level
			  CONFIG.luckyMaxLevel))) // max y level
			.spreadHorizontally()
			.repeat(CONFIG.luckyPerChunk); // number of veins per chunk	
}
