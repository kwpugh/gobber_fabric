package com.kwpugh.gobber2.items.tools.areatools;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

public class ObsidianBreaking 
{
	public static boolean fastAtObsidian(BlockState state, ItemStack stack)
	{
		if(stack.getEnchantments() != null)
		{
			String enchantments = stack.getEnchantments().toString();
			
			if(enchantments.contains("faster_obsidian"))
			{
				if (state.isOf(Blocks.OBSIDIAN)  || state.isOf(Blocks.CRYING_OBSIDIAN))
				{
					return true;
				}
			}	
		}
	
		return false;
	}
	
	public static float fastObsidianSpeed()
	{
		return 100.0F;
	}
}
