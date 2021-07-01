package com.kwpugh.gobber2.init;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagInit 
{
	// Fabric c tags used by Lucky Block extra loot feature
	public static final Tag<Item> COMMON_LOOT = TagRegistry.item(new Identifier("c", "common_loot"));
	public static final Tag<Item> UNCOOMMON_LOOT = TagRegistry.item(new Identifier("c", "uncommon_loot"));
	public static final Tag<Item> RARE_LOOT = TagRegistry.item(new Identifier("c", "rare_loot"));
	public static final Tag<Item> VERY_RARE_LOOT = TagRegistry.item(new Identifier("c", "very_rare_loot"));	
	
	// Fabric c tags used by the Ring of Miner
	public static final Tag<Block> COBBLESTONE = TagRegistry.block(new Identifier("c", "cobblestone"));
	public static final Tag<Block> DIRT = TagRegistry.block(new Identifier("c", "dirt"));
	public static final Tag<Block> NETHERRACK = TagRegistry.block(new Identifier("c", "netherrack"));
	public static final Tag<Block> SAND = TagRegistry.block(new Identifier("c", "sand"));
	public static final Tag<Block> SANDSTONE = TagRegistry.block(new Identifier("c", "sandstone"));
	public static final Tag<Block> SOUL_GROUND = TagRegistry.block(new Identifier("c", "soul_ground"));
	public static final Tag<Block> STONE = TagRegistry.block(new Identifier("c", "stone"));
	public static final Tag<Block> END_STONES = TagRegistry.block(new Identifier("c", "end_stones"));

	// Fabric c tags used by the Staff of Clearing
	public static final Tag<Block> FLOWERS = TagRegistry.block(new Identifier("c", "flowers"));
	public static final Tag<Block> GRASS = TagRegistry.block(new Identifier("c", "grass"));
	public static final Tag<Block> BUSHES = TagRegistry.block(new Identifier("c", "bushes"));

	// Gobber tag for adding more blocks to Ring of the Miner breaking
	public static final Tag<Block> RING_MINER_ADDITIONS = TagRegistry.block(new Identifier("gobber2", "ring_miner_additions"));
	
	// Gobber tags for adding additional blocks to hammer and excavator breaking
	public static final Tag<Block> HAMMER_ADDITIONS = TagRegistry.block(new Identifier("gobber2", "hammer_additions"));
	public static final Tag<Block> EXCAVATOR_ADDITIONS = TagRegistry.block(new Identifier("gobber2", "excavator_additions"));
	
	// Gobber tag for adding items to Ring of Repair blacklist
	public static final Tag<Item> RING_REPAIR_BLACKLIST = TagRegistry.item(new Identifier("gobber2", "ring_repair_blacklist"));
		
	public static void registerTags()
	{
		// Register tags
	}
}
