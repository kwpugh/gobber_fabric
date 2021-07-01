package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.world.Gobber2Dimension;

import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.chunk.Chunk;

public class RingAbove extends Item
{
	public RingAbove(Item.Settings settings)
	{
		super(settings);
	}
	
	static int cooldown = Gobber2.CONFIG.GENERAL.ringAboveCooldown;
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		RegistryKey<World> registryKey = world.getRegistryKey();
		ItemStack stack = player.getStackInHand(hand);
		
		if (!world.isClient && !(registryKey == World.OVERWORLD || registryKey == Gobber2Dimension.GOBBER_WORLD_KEY || registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2))
		{						
			player.sendMessage((new TranslatableText("item.gobber2.gobber2_ring_above.tip5")), true);   // not in this world
			return TypedActionResult.success(stack);
		}
						
		if (!world.isClient && (registryKey == World.OVERWORLD || registryKey == Gobber2Dimension.GOBBER_WORLD_KEY || registryKey == Gobber2Dimension.GOBBER_WORLD_KEY2))
		{
			ItemStack stack2 = player.getMainHandStack();	
			boolean hasQuickUse = stack2.getEnchantments().toString().contains("quickuse");
			
			// Teleports downward while sneak + right-click
			if(player.isSneaking())  
			{
				//Checking from bottom of world and working upward
				double x = player.getX();
				double y = 3;
				double z = player.getZ();
				
				Chunk chunk = world.getChunk((int) player.getX() >> 4, (int)player.getZ() >> 4);

				while (y < world.getHeight())
				{
		            y++;

		            BlockPos headPos = new BlockPos(x, y+1, z);
		            if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		            {
		            	//System.out.println("head material: " + chunk.getBlockState(headPos).getBlock() + " at: " + x + " " + y + " " + z);
		            	
		                BlockPos legPos = new BlockPos(x, y, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                	//System.out.println("leg material: " + chunk.getBlockState(headPos).getBlock() + " at: " + x + " " + y + " " + z);
		                	
		                    BlockPos groundPos = new BlockPos(x, y-1, z);
		                    if (     (!chunk.getBlockState(groundPos).getBlock().equals(Blocks.LAVA)) &&
		                    		(chunk.getBlockState(groundPos).getMaterial().equals(Material.STONE) ||
		                    		chunk.getBlockState(groundPos).getBlock().equals(Blocks.STONE) ||
		                    		!chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))   )
		                    {	
		                    	//System.out.println("ground state: " + chunk.getBlockState(headPos).getBlock() + " at: " + x + " " + y + " " + z);
		                    	
		                    	player.stopRiding();
		                    	player.requestTeleport(x, y, z);
		    	           		player.fallDistance = 0.0F;

		    	           		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		    	           		
		    	           		if(!hasQuickUse)
		    	           		{
		    	           			player.getItemCooldownManager().set(this, cooldown);
		    	           		}
		    	           		
		    	           		return TypedActionResult.success(stack);                     
		                    }
		                }
		            }
		        }				
			}
			else  // teleport upward with right-click
			{	
				//Checking from top of world downward 
				double x = player.getX();
				double y = 254;
				double z = player.getZ();
				
				Chunk chunk = world.getChunk((int) player.getX() >> 4, (int)player.getZ() >> 4);

				while (y > 3)
				{
		            y--;

		            BlockPos groundPos = new BlockPos(x, y-2, z);
		            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR) &&
		            		!chunk.getBlockState(groundPos).getBlock().equals(Blocks.LAVA))
		            {
		                BlockPos legPos = new BlockPos(x, y-1, z);
		                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR))
		                {
		                    BlockPos headPos = new BlockPos(x, y, z);
		                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR))
		                    {	                    	
		                    	player.stopRiding();
		                    	player.requestTeleport(x, y, z);
		    	           		player.fallDistance = 0.0F;

		    	           		world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		    	           		
		    	           		if(!hasQuickUse)
		    	           		{
		    	           			player.getItemCooldownManager().set(this, cooldown);
		    	           		}
		    	           		
		    	           		return TypedActionResult.success(stack);                       
		                    }
		                }
		            }
		        }		
			}
		}
		
		return TypedActionResult.success(stack); 
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_above.tip1").formatted(Formatting.GREEN));
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_above.tip2").formatted(Formatting.GREEN));
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_above.tip3").formatted(Formatting.YELLOW));
	    tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_above.tip4").formatted(Formatting.YELLOW));
	}   
}