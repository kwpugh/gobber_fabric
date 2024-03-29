package com.kwpugh.gobber2.enchantments;

import com.kwpugh.gobber2.Gobber2;

import com.kwpugh.gobber2.init.EffectsInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

public class SmithBladeEnchantment extends Enchantment
{ 
	public SmithBladeEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slots)
	{
		super(weight, target, slots);
	}
    
    static int attackAmount = Gobber2.CONFIG.GENERAL.smithbladeAttackAmount;
    static int maxLevel = Gobber2.CONFIG.GENERAL.smithbladeMaxLevel;

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level)
    {
        // no op
    }

    @Override
    public int getMinPower(int level) 
    {
    	return 1 + 10 * (level - 1);
    }

    @Override
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

    @Override
    public boolean isTreasure()
    {
        return Gobber2.CONFIG.GENERAL.enableSmithBlade;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer()
    {
        return Gobber2.CONFIG.GENERAL.enableSmithBlade;
    }

    @Override
    public boolean isAvailableForRandomSelection()
    {
        return Gobber2.CONFIG.GENERAL.enableSmithBlade;
    }
}