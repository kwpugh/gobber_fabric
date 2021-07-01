package com.kwpugh.gobber2.items.tools.endtools;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.items.tools.areatools.AreaToolUtil;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExcavatorEnd extends ShovelItem
{
	private static final Set<Block> EFFECTIVE_BLOCKS;

	public ExcavatorEnd(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings)
	{
		super(material, attackDamage, attackSpeed, settings);
	}

	int radius = 1;
	static boolean enable5x5 = Gobber2.CONFIG.GENERAL.enableEndExcavator5x5;
	static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
	{
		ItemStack itemStack = user.getStackInHand(hand);

		if(enable5x5)
		{
			if (!world.isClient && user.isSneaking())
			{
				EnableUtil.changeEnabled(user, hand);
				user.sendMessage((new TranslatableText("item.gobber2.range_changed")), true);
				if(radius == 1)
				{
					radius = radius + 1;
				}
				else
				{
					radius = radius - 1;
				}
			}	
		}

		return TypedActionResult.success(itemStack);
	}
	
    @Override
    public boolean isEffectiveOn(BlockState state)
    {
        return EFFECTIVE_BLOCKS.contains(state.getBlock());
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity playerIn)
    {
        if(!playerIn.isSneaking() && playerIn.getMainHandStack().isEffectiveOn(world.getBlockState(pos)))
    	{
        	AreaToolUtil.attemptBreakNeighbors(world, playerIn, radius, "excavator", false);
        }

        return true;
    }

    static
    {
        EFFECTIVE_BLOCKS = Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.SOUL_SOIL);
    }
	
	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) 
	{
		if(unbreakable)
		{
			stack.getOrCreateTag().putBoolean("Unbreakable", true);
		}
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		String range;
		if(radius == 1)
		{
			range = "3x3";
		}
		else
		{
			range = "5x5";
		}
		
		tooltip.add(new TranslatableText("item.gobber2.gobber2_excavator.tip1").formatted(Formatting.GREEN));
		if(enable5x5)
		{
			tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip3").formatted(Formatting.YELLOW));
			tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip4").formatted(Formatting.YELLOW));
		}
		tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip2", range).formatted(Formatting.RED));
	}
}