package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.init.EnchantmentInit;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinAirWalking extends LivingEntity
{
    @Shadow public abstract float getBlockBreakingSpeed(BlockState block);

    public PlayerEntityMixinAirWalking(EntityType<? extends LivingEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Inject(at = @At(value="HEAD"), method = "tick", cancellable = true)
    private void gobberTick(CallbackInfo ci)
    {
        if(this.getEntityWorld().isClient) return;

        Entity self = (Entity) (Object) this;
        ServerWorld server = (ServerWorld) (Object) this.world;
        if(self instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) self;
            if(PlayerEquipUtil.isPlayerGotAirWalking(player))
            {
                player.setNoGravity(true);
            }
            else
            {
                player.setNoGravity(false);
            }
        }
    }
}