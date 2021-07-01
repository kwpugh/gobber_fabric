package com.kwpugh.gobber2.blocks.entities;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.util.BlockEffects;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class BlockMaturatorEntity extends BlockEntity implements Tickable
{
	static int interval = Gobber2.CONFIG.GENERAL.growthInterval;
	static int range = Gobber2.CONFIG.GENERAL.growthRange;
		
	public BlockMaturatorEntity()
	{
		super(BlockInit.BLOCK_MATURATOR_ENTITY);
	}
	        
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			{
				BlockEffects.growCrops(world, pos, interval, range);
			}
		}		
	}
}