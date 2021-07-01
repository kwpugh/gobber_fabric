package com.kwpugh.gobber2.blocks;

import java.util.List;

import com.kwpugh.gobber2.blocks.entities.BlockMaturatorEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;

public class BlockMaturator extends Block implements BlockEntityProvider
{
	public BlockMaturator(Settings settings)
	{
		super(settings);
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockView blockView)
	{
		return new BlockMaturatorEntity();
	} 
	
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
	{
		tooltip.add(new TranslatableText("item.gobber2.block_maturator.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.requires_redstone").formatted(Formatting.YELLOW));
	}
}