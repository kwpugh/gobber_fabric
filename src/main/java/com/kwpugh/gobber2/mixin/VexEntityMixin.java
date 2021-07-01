package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.world.Gobber2Dimension;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

@Mixin(VexEntity.class)
public abstract class VexEntityMixin extends HostileEntity
{
	public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world)
	{
		super(entityType, world);
	}
 
	@Inject(method="initEquipment",at=@At("TAIL"),cancellable = true)
	private void gobberInitEquipment(LocalDifficulty difficulty, CallbackInfo ci)
	{    	
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
		{
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));	
		}
	}  
}