package com.kwpugh.gobber2.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;

public class EnableUtil
{
    public static boolean isEnabled(ItemStack stack)
    {
        return stack.hasTag() && stack.getTag().getBoolean("IsEnabled");
    }

    public static void changeEnabled(PlayerEntity player, Hand hand)
    {
        changeEnabled(player.getMainHandStack());
    }

    public static void changeEnabled(ItemStack stack)
    {
        if(!stack.hasTag())
        {
            stack.setTag(new CompoundTag());
        }
        boolean isEnabled = isEnabled(stack);
        stack.getTag().putBoolean("IsEnabled", !isEnabled);
    }
}