package com.kwpugh.gobber2.blocks;

import com.kwpugh.gobber2.Gobber2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.List;

public class ModGlass extends AbstractGlassBlock 
{
	private static int blastRes = Gobber2.CONFIG.ORES.glassBlastResistance;

	public ModGlass(AbstractBlock.Settings settings) 
	{
		super(settings);
	}
	  
	@Deprecated
	@Override
    public int getOpacity(BlockState state, BlockView view, BlockPos pos)
	{
        return super.getOpacity(state, view, pos);
    }

    @Override
	public float getBlastResistance()
	{
		return Gobber2.CONFIG.ORES.glassBlastResistance;
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
	{

		tooltip.add(Text.translatable("item.gobber2.gobber2_glass.tip1").formatted(Formatting.GREEN));
		tooltip.add(Text.translatable("item.gobber2.blast_res.tip1", blastRes).formatted(Formatting.YELLOW));
	}
}
