package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin extends Entity 
{
	@Shadow private int amount;
	
	public ExperienceOrbEntityMixin(EntityType<? extends Entity> entityType, World world) 
	{
		super(entityType, world);
	}
	
	private int multiplier = Gobber2.CONFIG.GENERAL.medallionExpMultiplier;
	
	
	@Inject(at = @At("HEAD"), method = "onPlayerCollision")
	public void checkForMedallion(PlayerEntity player, CallbackInfo ci)
	{
		if(!world.isClient)
		{
			ItemStack offHand = player.getEquippedStack(EquipmentSlot.OFFHAND);
			
			if (offHand.getItem() == ItemInit.GOBBER2_MEDALLION_EXP) 
			{
				this.amount = this.amount * multiplier;
				this.remove();
			}			
		}
	}
}