package com.kwpugh.gobber2.enchantments;

import java.util.Random;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class UntouchableEnchantment extends Enchantment
{	
	public UntouchableEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes)
	{
		super(weight, target, slotTypes);
	}

	static int damage = Gobber2.CONFIG.GENERAL.untouchableDamage;
	static int maxLevel = Gobber2.CONFIG.GENERAL.untouchableMaxLevel;
	
	public int getMinPower(int level) 
	{
		return 10 + 20 * (level - 1);
	}

	public int getMaxPower(int level)
	{
		return super.getMinPower(level) + 50;
	}

	@Override
	public int getMaxLevel()
	{
	    return maxLevel;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack)
	{
		return stack.getItem() instanceof ArmorItem ? true : super.isAcceptableItem(stack);
	}

	@Override
	public void onUserDamaged(LivingEntity user, Entity attacker, int level)
	{
		if(user.world.isClient) return;
		Random random = user.getRandom();
		if (shouldDamageAttacker(level, random))
		{
			if (attacker != null)
			{
				attacker.damage(DamageSource.thorns(user), (float)getDamageAmount(level, random));
			}
		}
	}

	public static boolean shouldDamageAttacker(int level, Random random)
	{
		return true;
	}

	public static int getDamageAmount(int level, Random random)
	{
		return damage * level;
	}
}