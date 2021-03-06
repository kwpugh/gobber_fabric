package com.kwpugh.gobber2.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class SolidFootingEnchantment extends Enchantment 
{ 
	public SolidFootingEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slots)
	{
		super(weight, target, slots);
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
}