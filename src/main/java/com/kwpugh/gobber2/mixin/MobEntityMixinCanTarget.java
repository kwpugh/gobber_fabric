package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.util.PlayerEquipUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(MobEntity.class)
public abstract class MobEntityMixinCanTarget extends Entity 
{
	public MobEntityMixinCanTarget(EntityType<?> type, World world)
	{
		super(type, world);
	}
	
	@Inject(at = @At("RETURN"), method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", cancellable = true)
	public void gobberCanTarget(LivingEntity target, CallbackInfoReturnable<Boolean> cir)
	{
		MobEntity self = (MobEntity) (Object) this;
		     
		if((target instanceof PlayerEntity))
		{
			PlayerEntity player = (PlayerEntity) target;
			
			if(!this.world.isClient && !player.isCreative())
			{
				if(self instanceof HostileEntity)
				{
					if(PlayerEquipUtil.isPlayerGotStleath(player))
					{
						cir.setReturnValue(false); 
					}
					else
					{
						cir.setReturnValue(true);
					}				    	  
				}
								
				if(self instanceof PhantomEntity)
				{					  
					if(PlayerEquipUtil.isPlayerPhantomFree(player))
					{
						cir.setReturnValue(false); 
					}
					else
					{
						cir.setReturnValue(true);
					}	    	  
				}
				
				if(self instanceof PhantomEntity)
				{					  
					if(PlayerEquipUtil.isPlayerWearingGobberArmor(player))
					{
						cir.setReturnValue(false); 
					}
					else
					{
						cir.setReturnValue(true);
					}	    	  
				}			
			}
		}
	}
}