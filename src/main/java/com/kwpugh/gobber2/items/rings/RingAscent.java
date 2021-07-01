package com.kwpugh.gobber2.items.rings;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RingAscent extends Item
{
	public RingAscent(Settings settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if (!world.isClient)
		{
			if(entity instanceof PlayerEntity)
			{	
				PlayerEntity player = (PlayerEntity) entity;
				
				player.fallDistance = 0.0F;
				
				if(player.isSneaking())
				{
					if(player.hasStatusEffect(StatusEffects.LEVITATION) && player.isSneaking());
					{
						player.removeStatusEffect(StatusEffects.LEVITATION);
					}	
				}	
			}
		}
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);

		if (!world.isClient && player.isOnGround())
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.LEVITATION, 3200, 1, false, false);
		
			player.addStatusEffect(effect);	
		}

		return TypedActionResult.success(stack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_ascent.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_ascent.tip2").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_ascent.tip3").formatted(Formatting.YELLOW));
	}
}