package com.kwpugh.gobber2.enchantments;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.Vec3d;

public class RebuffingEnchantment extends Enchantment 
{ 
	public RebuffingEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes)
	{
		super(weight, target, slotTypes);
	}
   
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
        return 3;
    }
    
    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level)
    {
    	if(user.world.isClient) return;
	  	if (attacker != null && user.squaredDistanceTo(attacker) <= 4.0D) 
		{ 
	  		Vec3d vec = new Vec3d(attacker.getX() - user.getX(), attacker.getY() - user.getY(), attacker.getZ() - user.getZ());
	  	
  			Random random = user.getRandom();
        	attacker.addVelocity(vec.x * 2 * level, vec.y * 3 * level, vec.z * 2 * level);
        	attacker.damage(DamageSource.thorns(user), (float)getDamageAmount(level, random));			       		
		}    		   	    	
    	//super.onTargetDamaged(user, attacker, level);
    }
    
    public static int getDamageAmount(int level, Random random)
    {
    	return level > 10 ? level - 10 : 1 + random.nextInt(3);
    }
}