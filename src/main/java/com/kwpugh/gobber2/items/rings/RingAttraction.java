package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RingAttraction extends Item
{
    static final String ATTRACTION_MODE = "Attraction Mode";
	
    public RingAttraction(Settings settings)
    {
        super(settings);
    }

    static int configRange = Gobber2.CONFIG.GENERAL.ringAttractionRange;
    
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{		
		if(!world.isClient && isActive(stack))
		{
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			BlockPos playerPos = new BlockPos(player.getPos());
			
			// Check for a particular block that stops the attraction
			for (BlockPos targetPos : BlockPos.iterateOutwards(playerPos, configRange, 5, configRange))
			{
				BlockState blockstate = world.getBlockState(targetPos);
				
				if ((blockstate.getBlock() == Blocks.COAL_BLOCK))
				{
					return;
				}				
			}

			// Scan and collect items
			List<ItemEntity> entityItems = player.getServerWorld().getEntitiesByClass(ItemEntity.class, player.getBoundingBox().expand(configRange), EntityPredicates.VALID_ENTITY);
			for (ItemEntity entityItemNearby : entityItems)
			{		
				entityItemNearby.onPlayerCollision(player);				
			}
			
			// Scan and collect XP orbs
			List<Entity> entityXP = player.getServerWorld().getEntitiesByClass(ExperienceOrbEntity.class, player.getBoundingBox().expand(configRange), EntityPredicates.VALID_ENTITY);
			for (Entity entityXPNearby : entityXP)
			{		
				entityXPNearby.onPlayerCollision(player);				
			}
		}
	}
	
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack magnet = player.getStackInHand(hand);
        
        if (!world.isClient && !player.isSneaking())
        {
        	toggleMode(magnet);
            
            //return new TypedActionResult<>(ActionResult.SUCCESS, magnet);
        }
        
        return new TypedActionResult<>(ActionResult.SUCCESS, magnet);
    }

    public boolean isActive(ItemStack magnet)
    {
        return getMagnetMode(magnet).getBoolean();
    }

    private void setMagnetMode(ItemStack magnet, MagnetMode mode)
    {
        checkTag(magnet);
        magnet.getTag().putBoolean(ATTRACTION_MODE, mode.getBoolean());
    }

    private MagnetMode getMagnetMode(ItemStack magnet)
    {
        if (!magnet.isEmpty())
        {
            checkTag(magnet);
            
            return magnet.getTag().getBoolean(ATTRACTION_MODE) ? MagnetMode.ACTIVE : MagnetMode.INACTIVE;
        }
        return MagnetMode.INACTIVE;
    }

    private void toggleMode(ItemStack magnet)
    {
        MagnetMode currentMode = getMagnetMode(magnet);
        
        if (currentMode.getBoolean())
        {
            setMagnetMode(magnet, MagnetMode.INACTIVE);
            
            return;
        }
        
        setMagnetMode(magnet, MagnetMode.ACTIVE);
    }

    private void checkTag(ItemStack magnet)
    {
        if (!magnet.isEmpty())
        {
            if (!magnet.hasTag())
            {
                magnet.setTag(new CompoundTag());
            }
            CompoundTag nbt = magnet.getTag();
            
            if (!nbt.contains(ATTRACTION_MODE))
            {
                nbt.putBoolean(ATTRACTION_MODE, false);
            }
        }
    }

    public enum MagnetMode
    {
        ACTIVE(true), INACTIVE(false);

        final boolean state;

        MagnetMode(boolean state)
        {
            this.state = state;
        }

        public boolean getBoolean()
        {
            return state;
        }
    }
    
    public boolean hasGlint(ItemStack magnet)
    {
    	return isActive(magnet);
    }
    
    @Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_attraction.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.right_click").formatted(Formatting.YELLOW));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_attraction.tip3", configRange).formatted(Formatting.RED));
	} 
}