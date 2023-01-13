package com.kwpugh.gobber2.items.tools.areatools;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.pugh_tools.Tools.TreeAxe;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ModTreeAxe extends TreeAxe
{
    public ModTreeAxe(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, Gobber2.CONFIG.GENERAL.enableFullDamage, settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(Text.translatable("item.gobber2.gobber2_tree_axe.tip1").formatted(Formatting.GREEN));
    }
}
