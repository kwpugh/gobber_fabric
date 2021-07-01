package com.kwpugh.gobber2.items.staffs;

import java.util.ArrayList;
import java.util.List;

import com.kwpugh.gobber2.Gobber2;

import com.kwpugh.gobber2.init.TagInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StaffClearing extends Item 
{
	public StaffClearing(Settings settings)
	{
		super(settings);
	}

	static int clearingRange = Gobber2.CONFIG.GENERAL.staffClearingRange;
	
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		
		if(!world.isClient)
		{			
			player.setCurrentHand(hand);
			
//			BlockPos playerPos = player.getBlockPos();
	
			Block block;
			List<BlockPos> poslist = new ArrayList<BlockPos>();
			
			for (int x = -clearingRange; x <= clearingRange; x++)
			{
				for (int y = -3; y <= 3; y++)
				{
					for (int z = -clearingRange; z <= clearingRange; z++)
					{
						BlockPos pos = player.getBlockPos().add(x, y, z);
						block = world.getBlockState(pos).getBlock();							
			
						if (block instanceof LeavesBlock && player.isSneaking())
						{
							world.breakBlock(pos, true);
						}
						
						if (block == Blocks.GRASS || 
								block == Blocks.DEAD_BUSH || 
								block == Blocks.TALL_GRASS || 
								block == Blocks.FERN || 
								block == Blocks.LARGE_FERN ||
								block.isIn(TagInit.FLOWERS) ||
								block.isIn(TagInit.BUSHES) ||
								block.isIn(TagInit.GRASS) ||
								block instanceof MushroomBlock || 
								block instanceof FlowerBlock ||
								block instanceof TallFlowerBlock)
						{		
							if(!player.isSneaking())
							{
								poslist.add(pos);
								world.breakBlock(pos, true);	
							}		
						}
					}
				}
			}
		}
		
        return TypedActionResult.success(stack);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_clearing.tip1").formatted(Formatting.GREEN));
	} 
}