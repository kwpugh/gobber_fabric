package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RingSunshine extends Item
{
	public RingSunshine(Item.Settings settings)
	{
		super(settings);
	}
		
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getStackInHand(hand);

		if (!world.isClient)
		{
			ServerWorld server = (ServerWorld) world;
			
			if(server.isRaining())
			{
				server.getLevelProperties().setRaining(false);
			}
			else if(!server.isRaining())
			{
				server.getLevelProperties().setRaining(true);
			}
		}	

		return TypedActionResult.success(itemStack);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_sunshine.tip1").formatted(Formatting.GREEN));
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_sunshine.tip2").formatted(Formatting.YELLOW));
	}   
}