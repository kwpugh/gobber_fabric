package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.blocks.BlockDefender;
import com.kwpugh.gobber2.blocks.BlockHealer;
import com.kwpugh.gobber2.blocks.BlockMaturator;
import com.kwpugh.gobber2.blocks.BlockProtector;
import com.kwpugh.gobber2.blocks.LuckyBlock;
import com.kwpugh.gobber2.blocks.ModGlass;
import com.kwpugh.gobber2.blocks.OreEnd;
import com.kwpugh.gobber2.blocks.OreGobber;
import com.kwpugh.gobber2.blocks.OreNether;
import com.kwpugh.gobber2.blocks.entities.BlockDefenderEntity;
import com.kwpugh.gobber2.blocks.entities.BlockHealerEntity;
import com.kwpugh.gobber2.blocks.entities.BlockMaturatorEntity;
import com.kwpugh.gobber2.blocks.entities.BlockProtectorEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit
{
	static boolean enableHealer = Gobber2.CONFIG.GENERAL.enableHealer;
	static boolean enableDefender = Gobber2.CONFIG.GENERAL.enableDefender;
	static boolean enableProtector = Gobber2.CONFIG.GENERAL.enableProtector;
	static boolean enableMaturator = Gobber2.CONFIG.GENERAL.enableMaturator;

	public static BlockEntityType<BlockHealerEntity> BLOCK_HEALER_ENTITY;
	public static BlockEntityType<BlockProtectorEntity> BLOCK_PROTECTOR_ENTITY;
	public static BlockEntityType<BlockDefenderEntity> BLOCK_DEFENDER_ENTITY;
	public static BlockEntityType<BlockMaturatorEntity> BLOCK_MATURATOR_ENTITY;
	
	public static final Block GOBBER2_LUCKY_BLOCK = new LuckyBlock(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	
	public static final Block GOBBER2_ORE = new OreGobber(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().strength(2.0F, 2.0F));	
	public static final Block GOBBER2_ORE_NETHER = new OreNether(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 4).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_ORE_END = new OreEnd(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 5).requiresTool().strength(2.0F, 2.0F));
	
	public static final Block GOBBER2_BLOCK = new Block(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_BLOCK_NETHER = new Block(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_BLOCK_END = new Block(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));

	public static final Block GOBBER2_GLASS = new ModGlass(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_GLASS_NETHER = new ModGlass(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block GOBBER2_GLASS_END = new ModGlass(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block CLEAR_GLASS = new ModGlass(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).luminance(15).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	
	
	public static final Block BLOCK_HEALER = new BlockHealer(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block BLOCK_MATURATOR = new BlockMaturator(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));	
	public static final Block BLOCK_DEFENDER = new BlockDefender(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	public static final Block BLOCK_PROTECTOR = new BlockProtector(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.STONE).build()).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().strength(2.0F, 2.0F));
	
	public static void registerBlocks()
	{
		{
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_lucky_block"), GOBBER2_LUCKY_BLOCK);
			
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_ore"), GOBBER2_ORE);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_ore_nether"), GOBBER2_ORE_NETHER);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_ore_end"), GOBBER2_ORE_END);
			
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_block"), GOBBER2_BLOCK);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_block_nether"), GOBBER2_BLOCK_NETHER);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_block_end"), GOBBER2_BLOCK_END);
			
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_glass"), GOBBER2_GLASS);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_glass_nether"), GOBBER2_GLASS_NETHER);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "gobber2_glass_end"), GOBBER2_GLASS_END);
			Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "clear_glass"), CLEAR_GLASS);	

			if(enableHealer)
			{
				Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "block_healer"), BLOCK_HEALER);
			}

			if(enableMaturator)
			{
				Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "block_maturator"), BLOCK_MATURATOR);
			}

			if(enableDefender)
			{
				Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "block_defender"), BLOCK_DEFENDER);
			}

			if(enableProtector)
			{
				Registry.register(Registry.BLOCK, new Identifier(Gobber2.MOD_ID, "block_protector"), BLOCK_PROTECTOR);
			}
		}
	}
	
	public static void registerBlockItems()
	{
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_lucky_block"), new BlockItem(GOBBER2_LUCKY_BLOCK, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ore"), new BlockItem(GOBBER2_ORE, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ore_nether"), new BlockItem(GOBBER2_ORE_NETHER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ore_end"), new BlockItem(GOBBER2_ORE_END, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_block"), new BlockItem(GOBBER2_BLOCK, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_block_nether"), new BlockItem(GOBBER2_BLOCK_NETHER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_block_end"), new BlockItem(GOBBER2_BLOCK_END, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glass"), new BlockItem(GOBBER2_GLASS, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glass_nether"), new BlockItem(GOBBER2_GLASS_NETHER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glass_end"), new BlockItem(GOBBER2_GLASS_END, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "clear_glass"), new BlockItem(CLEAR_GLASS, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));

			if(enableHealer)
			{
				Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "block_healer"), new BlockItem(BLOCK_HEALER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			}

			if(enableMaturator)
			{
				Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "block_maturator"), new BlockItem(BLOCK_MATURATOR, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			}

			if(enableDefender)
			{
				Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "block_defender"), new BlockItem(BLOCK_DEFENDER, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			}

			if(enableProtector)
			{
				Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "block_protector"), new BlockItem(BLOCK_PROTECTOR, new Item.Settings().group(Gobber2.GOBBER2_GROUP)));
			}
		}
	}
	
	public static void registerBlockEntities()
	{
		// Register block entities here
		BLOCK_HEALER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "gobber2:block_healer", BlockEntityType.Builder.create(BlockHealerEntity::new, BLOCK_HEALER).build(null));
		BLOCK_MATURATOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "gobber2:block_maturator", BlockEntityType.Builder.create(BlockMaturatorEntity::new, BLOCK_MATURATOR).build(null));
		BLOCK_DEFENDER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "gobber2:block_defender", BlockEntityType.Builder.create(BlockDefenderEntity::new, BLOCK_DEFENDER).build(null));
		BLOCK_PROTECTOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "gobber2:block_protector", BlockEntityType.Builder.create(BlockProtectorEntity::new, BLOCK_PROTECTOR).build(null));
	}
}
