package com.kwpugh.gobber2.items.fuel;

import java.util.List;
import java.util.Random;

import com.kwpugh.gobber2.world.Gobber2Dimension;

import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class Foo extends Item
{
	public Foo(Settings settings) 
	{
		super(settings);
	}

   @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {        
        ItemStack stack = player.getStackInHand(hand);
        
        if (!world.isClient && player.isSneaking())
        {        
        	if (world instanceof ServerWorld)
        	{
        		ServerWorld serverWorld = (ServerWorld) world;                    
        		MinecraftServer minecraftServer = serverWorld.getServer();                
        		RegistryKey<World> registryKey = player.world.getRegistryKey() == World.OVERWORLD ? Gobber2Dimension.GOBBER_WORLD_KEY : World.OVERWORLD;
       
        		ServerWorld serverWorld2 = minecraftServer.getWorld(registryKey);
        		ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
       
        		// Check a number of times for a safe spot
        		for(int i = 1; i < 6; i++)
        		{
        			if(i == 1) 
        			{
        				serverPlayer.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_explorer.tip2")), true);   //checking...
        			}
		
        			if(i > 1 && i < 6)
        			{
        				serverPlayer.sendMessage((new TranslatableText("Still checking...")), true);
        			}

        			BlockPos playerLoc = player.getBlockPos();			
        			Random rand = new Random();

        			// Use current world spawn x and z for starting point
        			int x = (int) Math.round(playerLoc.getX()) + rand.nextInt(10 + 5) - 5;
        			int y = 150;
        			int z = (int) Math.round(playerLoc.getZ()) + rand.nextInt(10 + 5) - 5;
		
        			if (serverWorld2 != null)
        			{
        				Chunk chunk = serverWorld2.getChunk(x >> 4, z >> 4);
        				Biome biome = serverWorld2.getBiome(new BlockPos(x, y, z));
			
        				//Lets not dump the player in the middle of a body of water, shall we?
        				if((biome.getCategory().getName().equals("ocean")) || 
        						(biome.getCategory().getName().equals("river")) || 
        						(biome.getCategory().getName().equals("beach")))
        				{
        					continue;
        				}

        				//Let's avoid putting them underground
        				while (y > 60)
        				{
        					y--;
        					BlockPos groundPos = new BlockPos(x, y-2, z);
        		
        					if(!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR) && 
        							(!chunk.getBlockState(groundPos).getBlock().equals(Blocks.BEDROCK) &&
        									(!chunk.getBlockState(groundPos).getBlock().equals(Blocks.WATER) &&
        											(y-2) > 60)))
        					{
        						BlockPos legPos = new BlockPos(x, y-1, z);
	                
        						if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
        						{
        							BlockPos headPos = new BlockPos(x, y, z);
	        
        							if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
        							{
        								serverPlayer.stopRiding();
        								serverPlayer.teleport(serverWorld2, x, y, z, serverPlayer.yaw, serverPlayer.pitch);
        								serverPlayer.fallDistance = 0.0F;

        								world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        								
        								if(registryKey == World.OVERWORLD)
        								{
        									serverPlayer.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_explorer.tip6")), true);  
        								}
        								else
        								{
        									serverPlayer.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_explorer.tip5")), true);  
        								}
        
        								return TypedActionResult.success(stack);
   	   			                    }
   	   			                }
   	   			            }
   	   			        }
   					}
        		}
        		
        		serverPlayer.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_explorer.tip7")), true);  
        		
   				return TypedActionResult.success(stack);
                         
        	}
	       
        	return TypedActionResult.success(stack);            
        }
    
        return TypedActionResult.success(stack);
    }
	   
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_foo.tip1").formatted(Formatting.GREEN));
	}
}