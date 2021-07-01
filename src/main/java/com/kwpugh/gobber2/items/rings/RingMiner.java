package com.kwpugh.gobber2.items.rings;

import java.util.ArrayList;
import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.MinerBlockTest;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RingMiner extends Item 
{
	static int minerCooldown = Gobber2.CONFIG.GENERAL.ringMinerCooldown;
	static int minerRange = Gobber2.CONFIG.GENERAL.ringMinerRange;
	static int minerVertical = Gobber2.CONFIG.GENERAL.ringMinerVerticalRange;
	static boolean minerDelayBreak = Gobber2.CONFIG.GENERAL.ringMinerDelayedBreak;
	
	List<BlockPos> posList = new ArrayList<BlockPos>();
	Block block;
	BlockPos pos;
	BlockPos breakPos;
	boolean breakBlocks;
	boolean dropBlocks;
	boolean hasQuickUse;
	
	public RingMiner(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		// if break delay is set to true in config, blocks are added to a List and broken over time in inventory tick
		if (!world.isClient && minerDelayBreak)
		{
			if(entity instanceof PlayerEntity)
			{					
				if(!posList.isEmpty())
				{
					dropBlocks = false;
					((PlayerEntity) entity).sendMessage((new TranslatableText("Breaking blocks...")), true);  
			
					if(((PlayerEntity) entity).getOffHandStack().getItem() == Items.STICK) 
					{
						dropBlocks = true;
					}
					
					breakPos = posList.get(0);
					world.breakBlock(breakPos, dropBlocks);
					posList.remove(0);				
				}				
			}
		}
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{		
		ItemStack mainHand = player.getStackInHand(hand);		
		ItemStack offHand = player.getOffHandStack();
		
		// Sneak + right-click to scan the area around player and store in list
		if(!world.isClient && player.isSneaking() && posList.isEmpty()) 
		{
			player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_miner.tip7")), true);   //Starting scan
			for (int x = minerRange; x >= -minerRange; x--)
			{
				for (int y = minerVertical; y >= 0; y--)
				{
					for (int z = minerRange; z >= -minerRange; z--)
					{
						pos = player.getBlockPos().add(x, y, z);
						block = world.getBlockState(pos).getBlock();							
				
						if(MinerBlockTest.canBreak(block))
						{
							posList.add(pos);
						}
					}
				}	
			}

			if(!minerDelayBreak)
			{
				player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_miner.tip8")), true);  //Scan complete, right-click again to break blocks.
			}
		}
		
		// Right-click to break blocks (no drops) in list, if list is NOT empty
		if(!world.isClient && !player.isSneaking() && !posList.isEmpty()) 
		{
			// if break delay is false, interate thru the List breaking all blocks
			if(!minerDelayBreak)
			{
				dropBlocks = false;
				if(offHand.getItem() == Items.STICK) dropBlocks = true;
				
				for (int i = 0; i < posList.size(); i++)
				{
					breakPos = posList.get(i);
					world.breakBlock(breakPos, dropBlocks);
				}		
				
				posList.clear();		
			}			
			
			// if instant break is enabled, a cooldown applies, unless enchanted with QuickUse
			hasQuickUse = mainHand.getEnchantments().toString().contains("quickuse");		
			if(!hasQuickUse)
			{
				player.getItemCooldownManager().set(this, minerCooldown);
			}
		}	
		
        return TypedActionResult.success(mainHand);
	}
	      
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_miner.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_miner.tip2").formatted(Formatting.YELLOW));
		
		if(!minerDelayBreak)
		{
			tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_miner.tip3").formatted(Formatting.YELLOW));
		}
		
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_miner.tip10").formatted(Formatting.BLUE));
		
		if(!posList.isEmpty())
		{
			tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_miner.tip9").formatted(Formatting.RED));  // Break area store scanned!
		}
	} 
}
