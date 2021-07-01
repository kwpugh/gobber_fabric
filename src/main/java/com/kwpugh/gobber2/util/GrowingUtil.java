package com.kwpugh.gobber2.util;

import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.FungusBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowingUtil
{
	//  Accelerates growth in area of effect
	public static void growCrops(World world, PlayerEntity player, int baseTickDelay, int radius)
	{
		BlockPos playerPos = new BlockPos(player.getPos());
	
		for (BlockPos targetPos : BlockPos.iterateOutwards(playerPos, radius, 5, radius))
		{
			BlockState blockstate = world.getBlockState(targetPos);
			Block block = blockstate.getBlock();

			if (   (block instanceof CropBlock) ||  //Beets Carrots Potatoes
					block instanceof BambooSaplingBlock ||
					block instanceof CocoaBlock ||
					block instanceof StemBlock ||
					block instanceof SweetBerryBushBlock ||
					block instanceof FungusBlock ||
					block instanceof SaplingBlock  //all saplings
					)
			{
				Fertilizable fertilizable = (Fertilizable)blockstate.getBlock();
				if (fertilizable.isFertilizable(world, targetPos, blockstate, world.isClient))
				{
					if (world instanceof ServerWorld)
					{
						if (fertilizable.canGrow(world, world.random, targetPos, blockstate))
						{
							if (player.age % (baseTickDelay) == 0)
							{
								fertilizable.grow((ServerWorld)world, world.random, targetPos, blockstate);
							} 
						}
					}
				}
			}
		}
		
		for (BlockPos tickTarget : BlockPos.iterateOutwards(playerPos, radius, 5, radius))
		{
			BlockState blockstate2 = world.getBlockState(tickTarget);
			Block blockToTick = blockstate2.getBlock();
				
			if(blockToTick instanceof SugarCaneBlock || 
					blockToTick instanceof CactusBlock ||
					blockToTick instanceof ChorusFlowerBlock)   
			{
				if (world.getTime() % (baseTickDelay / 16) == 0)
				{
					blockToTick.randomTick(blockstate2, (ServerWorld) world, tickTarget, world.random);
				}				
			}
		}
	}
}