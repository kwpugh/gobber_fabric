package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;

public class SmithBladeEnchantment extends Enchantment 
{ 
	public SmithBladeEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slots)
	{
		super(weight, target, slots);
	}
    
    static int attackAmount = Gobber2.CONFIG.GENERAL.smithbladeAttackAmount;
    static int maxLevel = Gobber2.CONFIG.GENERAL.smithbladeMaxLevel;

    public int getMinPower(int level) 
    {
    	return 1 + 10 * (level - 1);
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
	public float getAttackDamage(int level, EntityGroup group)
	{
		return attackAmount * level;
   }
}