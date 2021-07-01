package com.kwpugh.gobber2.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ArmorTickable
{
    // used to tick armor piece(s), refer to PlayerEntityMixinArmor
    void tickArmor(ItemStack stack, PlayerEntity playerEntity);
}