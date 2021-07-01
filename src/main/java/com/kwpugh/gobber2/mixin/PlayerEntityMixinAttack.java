package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinAttack extends LivingEntity
{
	public PlayerEntityMixinAttack(EntityType<? extends LivingEntity> entityType, World world) 
	{
		super(entityType, world);
	}
	
	@Inject(at = @At("TAIL"), method = "attack")
	public void gobberAttack(Entity target, CallbackInfo ci)
	{	
		PlayerEntity player = (PlayerEntity) (Object) this;

		Item item = player.getMainHandStack().getItem();
		
		if(item == ItemInit.GOBBER2_SWORD_NETHER)
		{
			target.setOnFireFor(10);
			target.damage(DamageSource.player(player), 12);
		}	  
	}
}