package com.kwpugh.gobber2.items.medallions;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class MedallionHealing extends Item
{
    public MedallionHealing(Settings settings)
    {
        super(settings);
    }

    static int amount = Gobber2.CONFIG.GENERAL.medallionLesserHealingAmount;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        PlayerEntity player = (PlayerEntity) entity;

        PlayerSpecialAbilities.giveHealing(world, player, stack,amount);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("item.gobber2.while_in_inventory").formatted(Formatting.YELLOW));
    }
}