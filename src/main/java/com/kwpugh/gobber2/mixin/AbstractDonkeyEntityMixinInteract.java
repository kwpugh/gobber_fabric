package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

@Mixin(AbstractDonkeyEntity.class)
public class AbstractDonkeyEntityMixinInteract
{
	@Inject(method = "interactMob", at = @At(value = "HEAD"), cancellable = true)
	public void gobberInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir)
	{
		ItemStack stack = player.getStackInHand(hand);
		if(stack.getItem() == ItemInit.GOBBER2_STAFF_ENSNAREMENT) 
		{
			player.swingHand(hand);

			cir.setReturnValue(ActionResult.PASS);	
		}
	}
}