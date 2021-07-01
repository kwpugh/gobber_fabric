package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StaffStars extends Item 
{
	public StaffStars(Settings settings)
	{
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context)
	{
		World world = context.getWorld();
		
		if(!world.isClient)
		{			
		  	BlockPos torchPos;
	    	BlockPos pos = context.getBlockPos();
	    	BlockState state = context.getWorld().getBlockState(pos);
	    	
			if(context.getWorld().getBlockState(pos).getBlock() == Blocks.TORCH
					|| context.getWorld().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
			{
				return ActionResult.FAIL;
			}
	    	
	    	Boolean isWallTorch = false;
	    		    	
	    	switch(context.getSide())
	    	{
	    	case DOWN:
	    		return ActionResult.FAIL;
	    	case UP:
	    		torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
	    		break;
	    	case NORTH:
	    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
	    		isWallTorch = true;
	    		break;
	    	case SOUTH:
	    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
	    		isWallTorch = true;
	    		break;
	    	case WEST:
	    		torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
	    		isWallTorch = true;
	    		break;
	    	case EAST:
	    		torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
	    		isWallTorch = true;
	    		break;
	    	default:
	    		return ActionResult.FAIL;
	    	}
	    	
	    	if(context.getWorld().getBlockState(torchPos).isAir() || context.getWorld().getBlockState(torchPos).getFluidState().isStill())
	    	{		    		
	    		if(state.isSolidBlock(world, pos))
	    		{
	    			if(isWallTorch)
		    		{
	    				context.getWorld().setBlockState(torchPos, Blocks.WALL_TORCH.getDefaultState().with(HorizontalFacingBlock.FACING, context.getSide()));
		    			context.getWorld().playSound(null, context.getPlayer().getBlockPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));	    			
		    		}
		    		else
		    		{
		    			context.getWorld().setBlockState(torchPos, Blocks.TORCH.getDefaultState());
		    			context.getWorld().playSound(null, context.getPlayer().getBlockPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
		    		}		
	    		}
	    		
	    		return ActionResult.SUCCESS;
	    	}
	    	
	    	return ActionResult.PASS;
		}
		
        return ActionResult.SUCCESS;
	}
	   
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_stars.tip1").formatted(Formatting.GREEN));
	} 
}