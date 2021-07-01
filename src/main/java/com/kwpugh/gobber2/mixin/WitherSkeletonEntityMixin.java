package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.world.Gobber2Dimension;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kwpugh.gobber2.init.EffectsInit;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityMixin extends AbstractSkeletonEntity
{
	public WitherSkeletonEntityMixin(EntityType<? extends WitherSkeletonEntity> entityType, World world)
	{
		super(entityType, world);
	}

	@Inject(method="initialize",at=@At("TAIL"),cancellable = true)
	public void gobberInitialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag, CallbackInfoReturnable<EntityData> cir)
	{
		this.gobberApplyAttributeModifiers();
		this.updateEnchantments(difficulty);
	}

	private void gobberApplyAttributeModifiers()
	{
		RegistryKey<World> registryKey = world.getRegistryKey();
		if(registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
		{
			this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(new EntityAttributeModifier("Gobber Health Bonus", 80.0D, EntityAttributeModifier.Operation.ADDITION));
			this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(new EntityAttributeModifier("Gobber Attack Bonus", 20.0D, EntityAttributeModifier.Operation.ADDITION));
			this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addPersistentModifier(new EntityAttributeModifier("Gobber Armor Bonus", 20.0D, EntityAttributeModifier.Operation.ADDITION));
			this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addPersistentModifier(new EntityAttributeModifier("Gobber Movement Bonus", 0.085D, EntityAttributeModifier.Operation.ADDITION));
		}
	}

	@Inject(at = @At("TAIL"), method = "dropEquipment")
	protected void gobberDropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops, CallbackInfo ci)
	{
		if(!world.isClient && (source.getAttacker() instanceof PlayerEntity))
		{
			PlayerEntity player = (PlayerEntity) source.getAttacker();
			ItemStack mainHand = player.getEquippedStack(EquipmentSlot.MAINHAND);
			ItemStack offHand = player.getEquippedStack(EquipmentSlot.OFFHAND);
			
			if(player.hasStatusEffect(StatusEffects.WITHER));
			{
				player.removeStatusEffect(StatusEffects.WITHER);
			}
			
			if(mainHand.getItem() == ItemInit.GOBBER2_SWORD_NETHER)
			{
				this.dropItem(Items.WITHER_SKELETON_SKULL);  
			}
			
			if(offHand.getItem() == ItemInit.GOBBER2_MEDALLION_EXP)
			{
				StatusEffectInstance effect = new StatusEffectInstance(EffectsInit.KNOWLEDGE, 120, 0, true, true);
				player.addStatusEffect(effect);
			}
		}
	}
}