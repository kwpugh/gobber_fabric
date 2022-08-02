package com.kwpugh.gobber2.blocks;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.init.TagInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class LuckyBlock extends BaseOreBlock
{
	public LuckyBlock(FabricBlockSettings settings) 
	{
		super(settings);
		this.settings.requiresTool();
	}

	static boolean enableExtraLoot = Gobber2.CONFIG.ORES.enableExtraLoot;
	static boolean enableFortune = Gobber2.CONFIG.ORES.enableFortune;

	// Get the default item string from config and cast to ItemStack
	Identifier id = Identifier.tryParse(Gobber2.CONFIG.ORES.defaultDrop);
	ItemStack stack = Registry.ITEM.get(id).getDefaultStack();

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
	{

		if(world.isClient) return;
		
		int tries = 1;  // Default value if Fortune not enabled
		
		if(enableFortune)
		{
			ItemStack stack2 = player.getMainHandStack();
			boolean enchanted = player.getMainHandStack().hasEnchantments();	
			boolean hasFortune = stack2.getEnchantments().toString().contains("fortune");
			int level = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack2);
						
			if(enchanted && hasFortune)
			{
				tries = level + 1;
			}
			else
			{
				tries = 1;
			}			
		}

		if(enableExtraLoot)
		{
			for(int i = 1; i < (tries + 1); i++)
			{
				Random random = Random.create(); // AbstractRandom ??
				double r = random.nextDouble();  //generate a random double between 0 and 1

				if(r >= Gobber2.CONFIG.ORES.cutoffThreshold)  // cutoff threshold
				{
					// no drops
				}
				else if(r >= Gobber2.CONFIG.ORES.commonThreshold) // Common
				{
					stack = Registry.ITEM.getEntryList(TagInit.COMMON_LOOT).flatMap((items) ->
							items.getRandom(random)).map((itemEntry) ->
							(itemEntry.value()).getDefaultStack()).orElse(stack);
				}
				else if(r >= Gobber2.CONFIG.ORES.uncommonThreshold)  //Uncommon
				{
					stack = Registry.ITEM.getEntryList(TagInit.UNCOOMMON_LOOT).flatMap((items) ->
							items.getRandom(random)).map((itemEntry) ->
							(itemEntry.value()).getDefaultStack()).orElse(stack);
				}
				else if(r >= Gobber2.CONFIG.ORES.rareThreshold)  //Rare
				{
					stack = Registry.ITEM.getEntryList(TagInit.RARE_LOOT).flatMap((items) ->
							items.getRandom(random)).map((itemEntry) ->
							(itemEntry.value()).getDefaultStack()).orElse(stack);
				}
				else
				{
					stack = Registry.ITEM.getEntryList(TagInit.VERY_RARE_LOOT).flatMap((items) ->
							items.getRandom(random)).map((itemEntry) ->
							(itemEntry.value()).getDefaultStack()).orElse(stack);
				}

				dropStack(world, pos, stack);
			}			
		}
	}
	
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
	{


		if(stack.isOf(BlockInit.GOBBER2_LUCKY_BLOCK_NETHER.asItem()))
		{

			tooltip.add(Text.translatable("item.gobber2.ore_nether.tip1").formatted(Formatting.GREEN));
		}
		else
		{
			tooltip.add(Text.translatable("item.gobber2.ore_overworld.tip1").formatted(Formatting.GREEN));
		}
		tooltip.add(Text.translatable("item.gobber2.gobber2_lucky_block.tip1").formatted(Formatting.YELLOW));
	}
}