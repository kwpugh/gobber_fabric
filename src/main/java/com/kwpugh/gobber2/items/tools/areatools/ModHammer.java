package com.kwpugh.gobber2.items.tools.areatools;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModHammer extends PickaxeItem
{
	public ModHammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings)
	{
		super(material, attackDamage, attackSpeed, settings);
	}

	int radius = 1;
	boolean obsidianFlag;
	boolean silkTouchFlag;
	
	@Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity playerIn)
    {
		if(!world.isClient)
		{
		       if(!playerIn.isSneaking() && playerIn.getMainHandStack().isEffectiveOn(world.getBlockState(pos)))
		        {
		        	obsidianFlag = (state.getBlock() == Blocks.OBSIDIAN || state.getBlock() == Blocks.CRYING_OBSIDIAN) ? true : false;
		        	AreaToolUtil.attemptBreakNeighbors(world, playerIn, radius, "hammer", obsidianFlag);
		        }

		        return true;
		}
		
		return false;
 
    }
 
	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state)
	{
		Material material = state.getMaterial();
		
	 	if(ObsidianBreaking.fastAtObsidian(state, stack))
		{
			return ObsidianBreaking.fastObsidianSpeed();
		}
	 	
		return material != Material.METAL && material != Material.REPAIR_STATION && material != Material.STONE ? super.getMiningSpeedMultiplier(stack, state) : this.miningSpeed;
	}
	   
	@Override
	public boolean isEffectiveOn(BlockState state)
	{
		if (Items.DIAMOND_PICKAXE.isEffectiveOn(state))
		{
			return true;
		}

		return Items.DIAMOND_PICKAXE.getMiningSpeedMultiplier(getDefaultStack(), state) > 1.0f;
	}	
    
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		String range = "3x3";
		
		tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_hammer.tip2", range).formatted(Formatting.RED));
	}
}
