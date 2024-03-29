package com.kwpugh.gobber2.items.tools.areatools;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.pugh_tools.Tools.Excavator;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ModExcavator extends Excavator
{
    public ModExcavator(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings)
    {
        super(material, attackDamage, attackSpeed, Gobber2.CONFIG.GENERAL.enableFullDamage, settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(Text.translatable("item.gobber2.gobber2_excavator.tip1").formatted(Formatting.GREEN));
    }
}
