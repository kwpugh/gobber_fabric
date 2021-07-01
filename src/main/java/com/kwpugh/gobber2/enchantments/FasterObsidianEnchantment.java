package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FasterObsidianEnchantment extends Enchantment
{
	public FasterObsidianEnchantment(Enchantment.Rarity weight, EnchantmentTarget digger, EquipmentSlot... slotTypes) 
	{
		super(weight, EnchantmentTarget.DIGGER, slotTypes);
	}

	public int getMinPower(int level) 
	{
		return 20;
	}

	public int getMaxPower(int level)
	{
		return 50;
	}

	@Override
	public int getMaxLevel()
	{
	    return 1;
	}

	 /* 
     * Makes the enchant only available
     *  from Village librarian
     */
	@Override
	public boolean isAvailableForEnchantedBookOffer() 
	{
		return true;
	}
	   
    /* 
     * This excludes enchant from enchanting table 
     * and loot
     */
    @Override
    public boolean isAvailableForRandomSelection()
    {
    	return false;
    }
    
	/*
	 *  So basically this does nothing since the enchantment
	 *  table screen handler pulls enchantments by type
	 *   in EnchantmentTarget
	 */
	@Override
	public boolean isAcceptableItem(ItemStack stack)
	{
		Item pickaxe = stack.getItem();

		if(pickaxe == ItemInit.GOBBER2_PICKAXE ||
				pickaxe == ItemInit.GOBBER2_PICKAXE_NETHER ||
				pickaxe == ItemInit.GOBBER2_PICKAXE_END ||
				pickaxe == ItemInit.GOBBER2_PAXEL ||
				pickaxe == ItemInit.GOBBER2_PAXEL_NETHER ||
				pickaxe == ItemInit.GOBBER2_PAXEL_END ||
				pickaxe == ItemInit.GOBBER2_PAXEL_STARS)
		{
			return this.type.isAcceptableItem(stack.getItem());
		}
		else
		{
			return false;
		}

	}
}