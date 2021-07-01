package com.kwpugh.gobber2.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.world.Gobber2Dimension;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends MobEntity
{
    private ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) 
    {
		super(entityType, world);
	}
  
    @Inject(method="applyAttributeModifiers",at=@At("TAIL"),cancellable = true)
    public void gobberApplyAttributeModifiers(float chanceMultiplier, CallbackInfo ci) 
    {
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
		{
	        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("Gobber Health Bonus", 40.0D, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(new EntityAttributeModifier("Gobber Attack Bonus", 6.0D, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier("Gobber Armor Bonus", 8.0D, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addPersistentModifier(new EntityAttributeModifier("Gobber Movement Bonus", 0.05D, EntityAttributeModifier.Operation.ADDITION));
		}     
    }

	@Inject(method="damage",at=@At("HEAD"),cancellable = true)
	public void gobberDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
	{
		Entity self = (Entity) (Object) this;
		if(self instanceof ZombieEntity)
		{
			ZombieEntity zombie = (ZombieEntity) self;
		}

		RegistryKey<World> registryKey = world.getRegistryKey();
		if(!this.world.isClient() && registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
		{
			if((source.getAttacker() instanceof PlayerEntity) && source.isProjectile())
			{
				source.getAttacker().damage(DamageSource.GENERIC, 5.0F);
			}
		}
	}

    @Inject(method="initEquipment",at=@At("TAIL"),cancellable = true)
    private void gobberInitEquipment(LocalDifficulty difficulty, CallbackInfo ci)
    {    	
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
		{
		 	this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
	    	this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
	    	this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
	    	this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
	    	this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));	
		}
    }
}