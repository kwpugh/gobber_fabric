package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class EndToolMaterial implements ToolMaterial
{
	private static int durability = Gobber2.CONFIG.GENERAL.gobberEndDurability;
	private static float miningSpeed = Gobber2.CONFIG.GENERAL.gobberEndMiningSpeed;
	private static float attackDamage = Gobber2.CONFIG.GENERAL.gobberEndAttackDamage;
	private static int miningLevel = Gobber2.CONFIG.GENERAL.gobberEndMiningLevel;
	private static int enchantability = Gobber2.CONFIG.GENERAL.gobberEndEnchantability;
	
	@Override
	public int getDurability() 
	{
		return durability;
	}

	@Override
	public float getMiningSpeedMultiplier()
	{
		return miningSpeed;
	}

	@Override
	public float getAttackDamage() 
	{
		return attackDamage;
	}

	@Override
	public int getMiningLevel()
	{
		return miningLevel;
	}

	@Override
	public int getEnchantability()
	{
		return enchantability;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return Ingredient.ofItems(ItemInit.GOBBER2_INGOT_END);
	}
}