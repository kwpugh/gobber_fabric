package com.kwpugh.gobber2.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.world.Gobber2Dimension;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

@Mixin(PiglinBruteEntity.class)
public abstract class PiglinBruteEntityMixin extends AbstractPiglinEntity implements CrossbowUser
{
	public PiglinBruteEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world)
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
	    	this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.NETHERITE_HELMET));
	    	this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
	    	this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
	    	this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.NETHERITE_BOOTS));	
		}
    }

	@Inject(method="damage",at=@At("HEAD"),cancellable = true)
	public void gobberDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
	{
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(!this.world.isClient() && registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
		{
			if((source.getAttacker() instanceof PlayerEntity) && source.isProjectile())
			{
				source.getAttacker().damage(DamageSource.GENERIC, 5.0F);
			}
		}
	}

    @Inject(method="initialize",at=@At("TAIL"),cancellable = true)
    public void initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag, CallbackInfoReturnable<EntityData> cir)
    {
    	this.gobberApplyAttributeModifiers();
    	this.updateEnchantments(difficulty);
    }
    
    private void gobberApplyAttributeModifiers() 
    {
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
		{
	        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("Gobber Health Bonus", 60.0D, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(new EntityAttributeModifier("Gobber Attack Bonus", 20.0D, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier("Gobber Armor Bonus", 20.0D, EntityAttributeModifier.Operation.ADDITION));
	        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addPersistentModifier(new EntityAttributeModifier("Gobber Movement Bonus", 0.075D, EntityAttributeModifier.Operation.ADDITION));
		}     
    }  
}