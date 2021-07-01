package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.world.Gobber2Dimension;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.AbstractSkeletonEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonEntityMixin extends HostileEntity implements RangedAttackMob
{
    private AbstractSkeletonEntityMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(method="initEquipment",at=@At("TAIL"),cancellable = true)
    private void gobberInitEquipment(LocalDifficulty difficulty, CallbackInfo ci)
    {
        RegistryKey<World> registryKey = world.getRegistryKey();
        if(registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2)
        {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
            this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
        }
    }
}