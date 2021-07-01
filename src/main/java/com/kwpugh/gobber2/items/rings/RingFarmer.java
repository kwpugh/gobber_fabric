package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.GrowingUtil;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class RingFarmer extends Item
{
	public RingFarmer(Settings settings)
	{
		super(settings);
	}

	static int interval = Gobber2.CONFIG.GENERAL.ringFarmerInterval;
	static int range = Gobber2.CONFIG.GENERAL.ringFarmerRange;

	public void inventoryTick(ItemStack stack, World world, Entity entity, int par4, boolean par5)
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
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_farmer.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.while_in_main hand").formatted(Formatting.YELLOW));
	} 
}