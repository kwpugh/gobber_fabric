package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerSpecialAbilities
{
	static boolean waterCheck = Gobber2.CONFIG.GENERAL.medallionBreathingWaterCheck;

	//Increases the player's health as long as it is less than max health
	public static void giveHealing(World world, PlayerEntity player, ItemStack itemstack, int amount)
	{
		if(player.age % 180 == 0)
		{
			if(player.getHealth() < player.getMaxHealth())
			{
				player.heal(amount);
			}		
		}
		
    	return;
	}
		
	//Set player saturation level to max on tick update
	public static void giveSaturationEffect(World world, PlayerEntity player, ItemStack itemstack)
	{
		if(player.age % 180 == 0)
		{
			player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() + 10);
		}
		
    	return;
	}
	
	//Lesser yellow hearts on tick update
	public static void giveLesserAbsorption(World world, PlayerEntity player, ItemStack itemstack)
	{
		float current = player.getAbsorptionAmount();
		
		if(player.getHealth() < 20 || current >= 10)
		{
			return;
		}
		
		if(current >= 9)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(10);
			} 
			
			
			return;
		}
		if(current < 9)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(current + 0.33F);
			} 
			
			return;
		}
		
    	return;
	}
	
	//Greater yellow hearts on tick update
	public static void giveGreaterAbsorption(World world, PlayerEntity player, ItemStack itemstack)
	{
		float current = player.getAbsorptionAmount();
		
		if(player.getHealth() < 20 || current >= 20)
		{
			return;
		}
		
		if(current >= 19)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(20);
			} 
			
			return;
		}
		if(current < 19)
		{
			if (player.age % 180 == 0)
			{
				player.setAbsorptionAmount(current + 1.0F);
			} 
			
			return;
		}
		
    	return;
	}
	
	public static void givePhoenixEffect(World world, PlayerEntity player)
	{
		BlockPos pos = player.getBlockPos();
		
		if(    world.getBlockState(pos).getBlock() == Blocks.MAGMA_BLOCK           )
		{
			player.setNoGravity(true);			
		}
		
		if(player.isOnFire() ||
				player.isInLava())
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 8, 0, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 8, 0, false, false);
			StatusEffectInstance effect3 = new StatusEffectInstance(StatusEffects.SATURATION, 8, 0, false, false);
			
			{
				player.addStatusEffect(effect);
				player.addStatusEffect(effect2);
				player.addStatusEffect(effect3);
			}	
			
			return;
		}
		
		return;
	}

	public static void giveCuringEffect(World world, PlayerEntity player)
	{
		if (!world.isClient)
		{
			if(player.hasStatusEffect(StatusEffects.BLINDNESS) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.BLINDNESS);
			}

			if(player.hasStatusEffect(StatusEffects.HUNGER) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.HUNGER);
			}

			if(player.hasStatusEffect(StatusEffects.MINING_FATIGUE) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.MINING_FATIGUE);
			}

			if(player.hasStatusEffect(StatusEffects.NAUSEA) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.NAUSEA);
			}

			if(player.hasStatusEffect(StatusEffects.POISON) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.POISON);
			}

			if(player.hasStatusEffect(StatusEffects.SLOWNESS) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.SLOWNESS);
			}

			if(player.hasStatusEffect(StatusEffects.UNLUCK) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.UNLUCK);
			}

			if(player.hasStatusEffect(StatusEffects.WEAKNESS) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.WEAKNESS);
			}

			if(player.hasStatusEffect(StatusEffects.WITHER) && player.isSneaking());
			{
				player.removeStatusEffect(StatusEffects.WITHER);
			}
		}
	}

	public static void giveWaterBreathing(World world, PlayerEntity player)
	{
		if(player.isSubmergedInWater() || !waterCheck)
		{
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.WATER_BREATHING, 8, 0, false, false);
			
			{
				player.addStatusEffect(effect);
			}
			
			return;
		}
		
		return;
	}
}