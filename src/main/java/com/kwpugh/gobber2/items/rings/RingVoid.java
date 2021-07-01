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
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class RingVoid extends Item
{
	public RingVoid(Item.Settings settings)
	{
		super(settings);
	}
		
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		
		if (!world.isClient && !player.isSneaking())
		{		
			   if (world instanceof ServerWorld)
			   {
			         RegistryKey<World> registryKey = world.getRegistryKey() == World.END ? World.OVERWORLD : World.END;
			         ServerWorld serverWorld = ((ServerWorld)world).getServer().getWorld(registryKey);
			
			         if (serverWorld == null) 
			         {
			        	 return TypedActionResult.success(stack);
			         }

			         player.moveToWorld(serverWorld);
			   }
			   	
			   return TypedActionResult.success(stack);			
		}
		
		return TypedActionResult.success(stack);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_void.tip1").formatted(Formatting.GREEN));
	    tooltip.add(new TranslatableText("item.gobber2.right_click_activate").formatted(Formatting.YELLOW));
	}   
}