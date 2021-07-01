package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.world.Gobber2Dimension;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class RingReturn extends Item
{
	public RingReturn(Item.Settings settings)
	{
		super(settings);
	}
		
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		RegistryKey<World> registryKey = world.getRegistryKey();
		
		if (!world.isClient && !player.isSneaking())
		{			
	       ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

	       if(registryKey == World.OVERWORLD)
	       {          	  
	    	   if(serverPlayer.getSpawnPointPosition() != null) //player bed location not null
	           {                	
	    		   BlockPos bedLoc = serverPlayer.getSpawnPointPosition(); //get player bed position     		            
	    		   serverPlayer.stopRiding();      
	               setPositionAndUpdate(serverPlayer, world, bedLoc);
	               player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_return.tip4")), true);   //Welcome Home!
	           }
	           else
	           {
	        	   player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_return.tip5")), true);  //Set a bed spawn first!
	        	   TypedActionResult.success(stack);
	           }
	        }
	        else
	        {
	        	player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_return.tip6")), true);  //Only works in the OverWorld
	        }
		}
		
		
		if (!world.isClient && player.isSneaking())
		{
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
			ServerWorld serverWorld = (ServerWorld) world;
		
			if(registryKey == World.OVERWORLD  || registryKey == Gobber2Dimension.GOBBER_WORLD_KEY)
			{          	  
				if(serverWorld.getSpawnPos() != null) //world spawn location not null
				{                	
					BlockPos worldSpawn = serverWorld.getSpawnPos(); //get player bed position     		            
					serverPlayer.stopRiding();      
					setPositionAndUpdate(serverPlayer, world, worldSpawn);
					player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_return.tip7")), true);   //Welcome to World Spawn!
				}
         
				TypedActionResult.success(stack);	   
			}
			else			 
	        {
	        	player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_return.tip8")), true);  //Does not work in this dimension.
	        }
		}
		
		return TypedActionResult.success(stack);
	}
	
	public void setPositionAndUpdate(ServerPlayerEntity player, World world, BlockPos bedLoc)
    {
	  player.teleport(bedLoc.getX() + 0.5F, bedLoc.getY() + 0.6F, bedLoc.getZ() + 0.5F);
	  player.fallDistance = 0;
    }
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_return.tip1").formatted(Formatting.GREEN));
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_return.tip2").formatted(Formatting.YELLOW));
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_return.tip3").formatted(Formatting.YELLOW));
	}   
}