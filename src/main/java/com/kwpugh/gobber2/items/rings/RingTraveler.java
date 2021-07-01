package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;

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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RingTraveler extends Item
{
	public RingTraveler(Settings settings)
	{
		super(settings);
	}

	static double launchVelocity = Gobber2.CONFIG.GENERAL.ringTravelerLaunch;
	static double cruiseVelocity = Gobber2.CONFIG.GENERAL.ringTravelerCruising;
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(world.isClient) return;
		
		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;
	
			if(!player.isOnGround())
			{
				player.fallDistance = 0;
			}
		}
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{	
		ItemStack stack = player.getStackInHand(hand);

		Vec3d look = player.getRotationVector().normalize();		
		double lookX = look.getX();
		double lookY = look.getY();
		double lookZ = look.getZ();
		
		//Get some vertical height to start
		if(player.isOnGround())	
		{
			player.addVelocity(lookX * 0.0, lookY * 4.0, lookZ * 0.0);
		}
		
		//Add more velocity while in air
		if(!player.isOnGround())
		{
			player.addVelocity(lookX * cruiseVelocity, lookY * cruiseVelocity, lookZ * cruiseVelocity);
		}
		
		
		return TypedActionResult.success(stack, world.isClient);
	}


	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_traveler.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_traveler.tip2").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_traveler.tip3").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.right_click_activate").formatted(Formatting.YELLOW));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_traveler.tip4").formatted(Formatting.LIGHT_PURPLE));
	}
}