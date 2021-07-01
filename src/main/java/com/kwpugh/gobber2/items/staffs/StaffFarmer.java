package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.GrowingUtil;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.MelonBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
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

public class StaffFarmer extends Item 
{
	public StaffFarmer(Settings settings)
	{
		super(settings);
	}

	static int interval = Gobber2.CONFIG.GENERAL.staffFarmerInterval;
	static int range = Gobber2.CONFIG.GENERAL.staffFarmerRange;
	static boolean replant = Gobber2.CONFIG.GENERAL.staffFarmerReplant;
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
    	if(!(entity instanceof PlayerEntity) || world.isClient)
        {
            return;
        }

    	PlayerEntity player = (PlayerEntity)entity;
        ItemStack equippedMain = player.getMainHandStack();
        
        if(stack == equippedMain)
        {
        	if (!world.isClient)
        	{  
        		GrowingUtil.growCrops(world, player, interval, range);
        	}	
        }
	}
	
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		boolean maxAge;
		
		if(!world.isClient)
		{
			BlockPos playerPos = new BlockPos(player.getPos());
    		
			for (BlockPos targetPos : BlockPos.iterateOutwards(playerPos, range, range, range))
    		{
				Block block = world.getBlockState(targetPos).getBlock();
				BlockState state = world.getBlockState(targetPos);
				BlockState defaultState = block.getDefaultState();
				
				//These plants are simply broken with drops
				if(block instanceof CocoaBlock ||
						block instanceof MelonBlock ||
						block instanceof PumpkinBlock ||
						block instanceof CactusBlock ||
						block instanceof SugarCaneBlock ||
						block instanceof NetherWartBlock ||
						block instanceof BambooBlock)
				{
					world.breakBlock(targetPos, true);
				}
				
				//Crops are harvested, if at max age, and re-planted
				if(block instanceof CropBlock)
				{
					maxAge = state.get(((CropBlock) block).getAgeProperty()) >= ((CropBlock) block).getMaxAge();
					
					if(maxAge)
					{
						world.breakBlock(targetPos, true);
						
						if(replant)
						{
							world.setBlockState(targetPos, defaultState);	
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
		tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_farmer.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_farmer.tip2").formatted(Formatting.GREEN));
	} 
}