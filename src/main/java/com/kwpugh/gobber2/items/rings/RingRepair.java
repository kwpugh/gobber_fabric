package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.TagInit;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class RingRepair extends Item
{	
	public RingRepair(Settings settings)
	{
		super(settings);
	}

	static int repairInterval = Gobber2.CONFIG.GENERAL.ringRepairInterval;
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(!world.isClient)
		{			
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
							
			if(serverPlayer.age % 90 == 0) // create an delay interval
			{					
			    for(int i = 0; i < serverPlayer.inventory.size(); i++) 
		        {					    
		    		ItemStack stack2 = serverPlayer.inventory.getStack(i);		
	    			if(!stack2.isEmpty())
	    			{
	    				if (!(stack2 == serverPlayer.getMainHandStack()))
	    				{
	    					if (stack2.isDamaged() && !stack2.getItem().isIn(TagInit.RING_REPAIR_BLACKLIST)) 
	    		    		{		    			   			
	    		    			stack2.setDamage(stack2.getDamage() - 1);	    		
	    		    			break;		                
	    		    		}
	    				}
	    			}    				    		
		        }						
			}	    		
		}	
	}	
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_repair.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_repair.tip2").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));
	} 
}