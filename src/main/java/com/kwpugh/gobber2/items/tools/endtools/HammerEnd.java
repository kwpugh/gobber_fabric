package com.kwpugh.gobber2.items.tools.endtools;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.items.tools.areatools.AreaToolUtil;
import com.kwpugh.gobber2.items.tools.areatools.ModHammer;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HammerEnd extends ModHammer
{
	public HammerEnd(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings)
	{
		super(material, attackDamage, attackSpeed, settings);
	}

	int radius = 1;
	String radiusText = "3x3";
	boolean obsidianFlag;
	static boolean enable5x5 = Gobber2.CONFIG.GENERAL.enableEndHammer5x5;
	static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;
	
	@Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity playerIn)
    {
        if(!playerIn.isSneaking() && playerIn.getMainHandStack().isEffectiveOn(world.getBlockState(pos)))
    	{
        	obsidianFlag = (state.getBlock() == Blocks.OBSIDIAN || state.getBlock() == Blocks.CRYING_OBSIDIAN) ? true : false;
        	AreaToolUtil.attemptBreakNeighbors(world, playerIn, radius, "hammer", obsidianFlag);
        }

        return true;
    }
    
	@Override
	public boolean isEffectiveOn(BlockState state) 
	{
		if (Items.NETHERITE_PICKAXE.isEffectiveOn(state))
		{
			return true;
		}

		return Items.NETHERITE_PICKAXE.getMiningSpeedMultiplier(null, state) > 1.0f;
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
	{
		ItemStack itemStack = user.getStackInHand(hand);

		if(enable5x5)
		{
			if (!world.isClient && user.isSneaking())
			{
				EnableUtil.changeEnabled(user, hand);
				//user.sendMessage((new TranslatableText("item.gobber2.range_changed")), true);
				if(radius == 1)
				{
					radius = radius + 1;
					radiusText = "5x5";
					user.sendMessage((new TranslatableText("item.gobber2.gobber2_hammer.tip5", radiusText)), true);
				}
				else
				{
					radius = radius - 1;
					radiusText = "3x3";
					user.sendMessage((new TranslatableText("item.gobber2.gobber2_hammer.tip5", radiusText)), true);
				}
			}	
		}

		return TypedActionResult.success(itemStack);
	}
	
	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) 
	{
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
	}
	
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip1").formatted(Formatting.GREEN));
		
		if(enable5x5)
		{
			tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip3").formatted(Formatting.YELLOW));
			tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip4").formatted(Formatting.YELLOW));
		}
		
		// Come back to figure out what the issue is on servers
		//tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip2", radiusText).formatted(Formatting.RED));
	}
}