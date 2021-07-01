package com.kwpugh.gobber2.items.tools.areatools;

import java.util.ArrayList;
import java.util.List;

import com.kwpugh.gobber2.init.TagInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class AreaToolUtil
{	
    public static void attemptBreakNeighbors(World world, PlayerEntity player, int radius, String type, boolean obsidian)
    {
        if(!world.isClient)
        {
        	boolean okToBreak;
            List<BlockPos> targetBlocks = calcRay(world, player, radius);
            
            for(BlockPos pos : targetBlocks)
            {
            	BlockState state = world.getBlockState(pos);
            	Block block = state.getBlock();
            	Float hardness = state.getHardness(world, pos);
            	okToBreak = obsidian ? true : hardness < 50.0F;
            	Hand hand = player.getActiveHand();
            	ItemStack stack = player.getStackInHand(hand);
            	
             	if(type == "hammer" && state.isToolRequired() && okToBreak)
            	{
            		if(player.isUsingEffectiveTool(state) || block.isIn(TagInit.HAMMER_ADDITIONS))
            		{
            			if(!block.hasBlockEntity() || 
                				!(block instanceof BlockWithEntity) || 
                				!(block instanceof BlockEntityProvider))
                		{
        					world.breakBlock(pos, false);
        					Block.dropStacks(state, world, pos, null, player, stack);   // Use this version to account for enchantments on stack        			
                		}
            		}          		
            	}
             	
            	if(type == "excavator" && stack.isEffectiveOn(state)|| block.isIn(TagInit.EXCAVATOR_ADDITIONS))
            	{
            		if(player.isUsingEffectiveTool(state))
            		{
            			if(!block.hasBlockEntity() || 
                				!(block instanceof BlockWithEntity) || 
                				!(block instanceof BlockEntityProvider))
                		{
            				world.breakBlock(pos, false);
        					Block.dropStacks(state, world, pos, null, player, stack);   // Use this version to account for enchantments on stac
                		}
            		}           		
            	}                             
            }
        }
    }
       
    public static List<BlockPos> calcRay(World world, PlayerEntity playerIn, int radius)
    {
    	/* 
    	 * Adapted from "Vanilla Hammers" by Draylar - credit to the author
    	 */
    	
        ArrayList<BlockPos> blockResultList = new ArrayList<>();

        Vec3d cameraPos = playerIn.getCameraPosVec(1);
        Vec3d rotation = playerIn.getRotationVec(1);
        Vec3d cameraPosWithRotation = cameraPos.add(rotation.x * 5, rotation.y * 5, rotation.z * 5);

        BlockHitResult blockHitResult = world.raycast(new RaycastContext(cameraPos, cameraPosWithRotation, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, playerIn));
        
        if (blockHitResult.getType() == HitResult.Type.BLOCK)
        {
            Direction.Axis axis = blockHitResult.getSide().getAxis();
            
            //Get all of the blocks within the radius around blockHitResult
            ArrayList<BlockPos> targetPos = new ArrayList<>();
            for(int x = -radius; x <= radius; x++)
            {
                for(int y = -radius; y <= radius; y++)
                {
                    for(int z = -radius; z <= radius; z++)
                    {
                    	targetPos.add(new BlockPos(x, y, z));                   	
                    }
                }
            }

            BlockPos origin = blockHitResult.getBlockPos();

            //Depending on the side and axis, add blockPos on same plane
            for(BlockPos pos : targetPos)
            {
                if(axis == Direction.Axis.Y)
                {
                    if(pos.getY() == 0)
                    {
                    	blockResultList.add(origin.add(pos));
                    }
                }
                else if (axis == Direction.Axis.X)
                {
                    if(pos.getX() == 0)
                    {
                    	blockResultList.add(origin.add(pos));
                    }
                }
                else if (axis == Direction.Axis.Z)
                {
                    if(pos.getZ() == 0)
                    {
                    	blockResultList.add(origin.add(pos));
                    }
                }
            }
        }

        return blockResultList;
    }
}