package com.kwpugh.gobber2.items.fuel;

import java.util.List;

import com.kwpugh.gobber2.init.TagInit;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FooEnd extends Item
{
	public FooEnd(Settings settings) 
	{
		super(settings);
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		
		if(!world.isClient && player.isSneaking())
		{
			String common = TagInit.COMMON_LOOT.values().toString();
			String uncommon = TagInit.UNCOOMMON_LOOT.values().toString();
			String rare = TagInit.RARE_LOOT.values().toString();
			String very_rare = TagInit.VERY_RARE_LOOT.values().toString();
	        
			System.out.println("\nCommon Loot: " + common);
			System.out.println("\nUncommon Loot: " + uncommon);
			System.out.println("\nRare Loot: " + rare);
			System.out.println("\nVery Rare Loot: " + very_rare);			
		}

		return TypedActionResult.success(stack, world.isClient);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_foo.tip1").formatted(Formatting.GREEN));
	}
}