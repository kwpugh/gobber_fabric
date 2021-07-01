package com.kwpugh.gobber2.items.rings;

import java.util.Iterator;
import java.util.List;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RingDismissal extends Item
{	
	public RingDismissal(Settings settings)
	{
		super(settings);
	}

	static double range = Gobber2.CONFIG.GENERAL.ringDismissalRange;
	static double velocity = Gobber2.CONFIG.GENERAL.ringDismissalVelocity;
	static double lift = Gobber2.CONFIG.GENERAL.ringDismissalLift;
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
	 	if(world.isClient) return;
	
		PlayerEntity player = (PlayerEntity)entity;
		BlockPos pos = player.getBlockPos();
        ItemStack equippedMain = player.getMainHandStack();
 
        if(stack == equippedMain)
        {
    		Vec3d look = player.getRotationVector().normalize();		
			double lookX = look.getX();
			double lookY = look.getY();
			double lookZ = look.getZ();
			
        	// Scan for hostile mobs
    		Box mobBox = (new Box(pos)).expand(range, range, range);
    		List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
    		Iterator<Entity> iterator2 = list2.iterator();
    		
    		Entity targetEntity;
    		
    		// Cycle through and effect entities
    		while(iterator2.hasNext())
    		{
    			targetEntity = (Entity)iterator2.next();
    						
    			// Exclude some of the harder mobs
    			if(targetEntity instanceof WitherEntity ||
    					targetEntity instanceof GuardianEntity ||
    					targetEntity instanceof BlazeEntity ||
    					targetEntity instanceof VexEntity) 
    				continue;
    			
    			if(targetEntity instanceof HostileEntity)
    			{
    				targetEntity.addVelocity(lookX * velocity, lookY * lift, lookZ * velocity);
    			}
    		}      	
        }
	}	
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_dismissal.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.while_in_main hand").formatted(Formatting.YELLOW));
	} 
}