package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import com.kwpugh.gobber2.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StaffNature extends Item 
{
	public StaffNature(Settings settings)
	{
		super(settings);
	}

	Block block;

	@Override
	public ActionResult useOnBlock(ItemUsageContext context)
	{
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if(!player.isSneaking())
		{
			if(block == Blocks.ACACIA_SAPLING)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.BIRCH_SAPLING.getDefaultState(), 3);			
	    	 
				return ActionResult.SUCCESS;
			}
			else if(block == Blocks.BIRCH_SAPLING)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.DARK_OAK_SAPLING.getDefaultState(), 3);		 
	    	 
				return ActionResult.SUCCESS;
			}
			else if(block == Blocks.DARK_OAK_SAPLING)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.JUNGLE_SAPLING.getDefaultState(), 3);
	    	 
				return ActionResult.SUCCESS;
			}
			else if(block == Blocks.JUNGLE_SAPLING)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.OAK_SAPLING.getDefaultState(), 3);

				return ActionResult.SUCCESS;
			}
			else if(block == Blocks.OAK_SAPLING)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.SPRUCE_SAPLING.getDefaultState(), 3);

				return ActionResult.SUCCESS;
			}
			else if(block == Blocks.SPRUCE_SAPLING)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.ACACIA_SAPLING.getDefaultState(), 3);

				return ActionResult.SUCCESS;
			}
			else if(block == Blocks.SUGAR_CANE)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.BAMBOO_SAPLING.getDefaultState(), 3);
				
				return ActionResult.SUCCESS;
			}
			else if(block == Blocks.BAMBOO_SAPLING)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, Blocks.SUGAR_CANE.getDefaultState(), 3);
	
				return ActionResult.SUCCESS;
			}			 
		}
	 
		if(player.isSneaking())
		{
			if(block == Blocks.GLASS)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(pos, BlockInit.CLEAR_GLASS.getDefaultState(), 3);

				return ActionResult.SUCCESS;
			}
		}
	 	    	     
		return ActionResult.PASS;
	}
				
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_nature.tip1").formatted(Formatting.YELLOW));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_nature.tip2").formatted(Formatting.YELLOW));
	} 
}