package com.kwpugh.gobber2.effects;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

/*
 * Sample status effect from Fabric wiki
 */

public class Knowledge extends StatusEffect
{
	  public Knowledge()
	  {
	    super(
	      StatusEffectType.BENEFICIAL, // whether beneficial or harmful for entities
	      0x98D982); // color in RGB
	  }
	 
	  static int xpAmount = Gobber2.CONFIG.GENERAL.KnowledgeBoostXPPerTick;
	  
	  // This method is called every tick to check weather it should apply the status effect or not
	  @Override
	  public boolean canApplyUpdateEffect(int duration, int amplifier) 	  
	  {
	    // In our case, we just make it return true so that it applies the status effect every tick.
	    return true;
	  }
	 
	  // This method is called when it applies the status effect. We implement custom functionality here.
	  @Override
	  public void applyUpdateEffect(LivingEntity entity, int amplifier) 
	  {
	    if (entity instanceof PlayerEntity)
	    {
	      ((PlayerEntity) entity).addExperience(xpAmount << amplifier); // Higher amplifier gives you EXP faster
	    }
	  }
}