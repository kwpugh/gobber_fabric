package com.kwpugh.gobber2.mixin;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.kwpugh.gobber2.init.ItemInit;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinGlide extends Entity 
{
  public LivingEntityMixinGlide(EntityType<?> type, World world)
  {
    super(type, world);
  }

  //@ModifyArg(method = "Lnet/minecraft/entity/LivingEntity;initAi()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"), index = 1)
  @ModifyArg(method = "initAi()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"), index = 1)
  private boolean gobberInitAi(boolean value)
  {
    boolean bl = this.getFlag(7);
    LivingEntity livingEntity = (LivingEntity) (Object) this;
    ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
    if (bl && !this.onGround && !this.hasVehicle() && itemStack.getItem() == ItemInit.GOBBER2_CHESTPLATE_END)
    {
      return true;
    }
    return value;
  }
}