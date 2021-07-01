package com.kwpugh.gobber2.items.other;

import java.util.List;
import java.util.Random;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

/*
 *  This item does not have a recipe
 *  and is consumed after a successful
 *  teleport.
 *  
 *  It is the same logic as the Ring of 
 *  the Explorer minus the cooldown.
 */

public class SpecialItem extends Item
{
	public SpecialItem(Item.Settings settings)
	{
		super(settings);
	}
	
	static int min = Gobber2.CONFIG.GENERAL.specialItemMin;
	static int max = Gobber2.CONFIG.GENERAL.specialItemMax;
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		
		if (!world.isClient && !player.isSneaking())
		{				
			if (world instanceof ServerWorld)
			{	
				// Check a number of times for a safe spot
				for(int i = 1; i < 6; i++)
				{
					ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
					
					if(i == 1) 
					{
						serverPlayer.sendMessage((new TranslatableText("Checking for a safe spot, please wait a moment")), true);
					}
					
					if(i > 1 && i < 6)
					{
						serverPlayer.sendMessage((new TranslatableText("Still checking...")), true);
					}
					
					RegistryKey<World> registryKey = world.getRegistryKey();
					
					if(!(registryKey == World.OVERWORLD))
					{
						serverPlayer.sendMessage((new TranslatableText("Only works in Overworld")), true);
						return TypedActionResult.success(stack);
					}
			
					ServerWorld serverWorld = (ServerWorld)world;		
					BlockPos worldSpawn = serverWorld.getSpawnPos();			
					Random rand = new Random();

					// Use current world spawn x and z for starting point
					int x = (int) Math.round(worldSpawn.getX()) + rand.nextInt(max + min) - min;
					int y = 150;
					int z = (int) Math.round(worldSpawn.getZ()) + rand.nextInt(max + min) - min;

					Chunk chunk = world.getChunk(x >> 4, z >> 4);
					Biome biome = world.getBiome(new BlockPos(x, y, z));

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
			                    	serverPlayer.requestTeleport(x, y, z);
			                    	serverPlayer.fallDistance = 0.0F;

			    	           		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			    	           		
			    	           		stack.decrement(1);
			    	           		serverPlayer.swingHand(hand, true);
			    	           		
			    	           		serverPlayer.sendMessage((new TranslatableText("Enjoy your exploring.")), true);
			    	           		return TypedActionResult.success(stack);
			                    }
			                }
			            }
			        }	 
				}
				
				return TypedActionResult.success(stack);		
			}

			return TypedActionResult.success(stack);			
		}
		
		return TypedActionResult.success(stack);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_explorer.tip1").formatted(Formatting.GREEN));
	    tooltip.add(new TranslatableText("item.gobber2.right_click_activate").formatted(Formatting.YELLOW));
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_special_item.tip1").formatted(Formatting.BLUE));
	}   
}