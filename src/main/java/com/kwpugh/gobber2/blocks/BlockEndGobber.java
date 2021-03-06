package com.kwpugh.gobber2.blocks;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;

public class BlockEndGobber extends Block 
{	
	public BlockEndGobber(FabricBlockSettings settings) 
	{
		super(settings);
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
	{
		tooltip.add(new TranslatableText("With teleport capability").formatted(Formatting.GREEN));
	}
}