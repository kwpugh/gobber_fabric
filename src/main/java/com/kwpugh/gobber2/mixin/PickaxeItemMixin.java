package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.items.tools.areatools.ObsidianBreaking;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin 
{
    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    public void getMiningSpeedMultiplierInject(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) 
    {
       	if(ObsidianBreaking.fastAtObsidian(state, stack))
		{
			cir.setReturnValue(ObsidianBreaking.fastObsidianSpeed());
		}
    }
}