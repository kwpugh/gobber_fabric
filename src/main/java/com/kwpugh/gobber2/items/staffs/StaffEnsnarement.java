package com.kwpugh.gobber2.items.staffs;

import java.util.List;
import java.util.Optional;

import com.kwpugh.gobber2.Gobber2;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.MuleEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/*
 * This item also relies on mixins
 * into VillagerEntity, AbstractDonkeyEntity, and
 * WanderingTraderEntity to change interactMob
 * methods and bypass usually GUIs
 * 
 */
public class StaffEnsnarement extends Item 
{
	public StaffEnsnarement(Settings settings)
	{
		super(settings);
	}
   
	static boolean enableHostileUse = Gobber2.CONFIG.GENERAL.staffEnsnarementHotileMobs;
	
	// Right-click on entity, if right type, save entity info to tag and delete entity
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) 
    {
    	if(!player.world.isClient)
    	{
	       	 if((enableHostileUse) && (stack.getOrCreateTag().isEmpty()) &&
	    			 (entity instanceof HostileEntity) && !(entity instanceof WitherEntity))
	    	 {
	       		 if(saveEntityToStack(entity, stack))
	       		 {
	       			 player.setStackInHand(hand, stack);
	       		 }
	  
	 			return ActionResult.SUCCESS;        		 
	    	 }
       	 
        	 if((stack.getOrCreateTag().isEmpty()) &&
        			 (entity instanceof AnimalEntity ||	
        				entity instanceof HorseEntity ||
        				entity instanceof DonkeyEntity ||
        				entity instanceof LlamaEntity ||
        				entity instanceof MuleEntity ||
        				entity instanceof RabbitEntity ||
        				entity instanceof GolemEntity ||
        				entity instanceof VillagerEntity) ||
        				entity instanceof WanderingTraderEntity)
        	 {
           		 if(saveEntityToStack(entity, stack))
           		 {
           			 player.setStackInHand(hand, stack);
           		 }
      
     			return ActionResult.SUCCESS;        		 
        	 }
    	}

        return ActionResult.SUCCESS;
    }
 
    // Right-click on block, if staff has stored entity set it's position, spawn it in, and remove tags on staff
    @SuppressWarnings("resource")
	public ActionResult useOnBlock(ItemUsageContext context)
    {	  	
    	ItemStack stack = context.getStack();
    	if(!(context.getWorld() instanceof ServerWorld)) return ActionResult.SUCCESS;
    	if(!context.getWorld().isClient && stack.hasTag() && stack.getTag().contains("captured_entity"))
    	{
        	ServerWorld serverWorld = (ServerWorld) context.getWorld();
        	BlockPos pos = context.getBlockPos().offset(context.getSide());
        	ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();

        	CompoundTag entityTag = context.getStack().getSubTag("captured_entity");   // KEEP  
        	
          	Optional<Entity> entity = EntityType.getEntityFromTag(entityTag, serverWorld);
  
        	if(entity.isPresent()) 
        	{
        		Entity entity2 = entity.get();
        		entity2.updatePositionAndAngles(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, player.yaw, player.pitch);
        		serverWorld.spawnEntity(entity2);
        	}	

        	stack.removeSubTag("name");  //KEEP
        	stack.removeSubTag("captured_entity");  // KEEP  

        	context.getPlayer().getStackInHand(context.getHand());

        	return ActionResult.SUCCESS;
        }   
     
        return ActionResult.SUCCESS;
    }

    // Method to save an entity to a tag and remove entity from world
    public boolean saveEntityToStack(LivingEntity entity, ItemStack stack)
    {
    	CompoundTag entityTag = new CompoundTag();
    	
    	if(!entity.saveSelfToTag(entityTag))
    	{
    		return false;
    	}
    	
    	stack.getOrCreateTag().put("captured_entity", entityTag);
    	stack.getOrCreateTag().putString("name", entity.getDisplayName().getString());
    	entity.remove();
    	
    	return true;
    }
 
    @Override
	public boolean hasGlint(ItemStack stack)
	{
		return stack.hasTag() && !stack.getOrCreateSubTag("captured_entity").isEmpty();
	}
    
    @Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{		
		tooltip.add(new TranslatableText("item.gobber2.gobber2_staff_ensnarement.tip1").formatted(Formatting.GREEN));
		
		if (stack.hasTag() && !stack.getOrCreateSubTag("captured_entity").isEmpty())
		{
			tooltip.add((new TranslatableText("item.gobber2.gobber2_staff_ensnarement.tip3", stack.getTag().getString("name")).formatted(Formatting.GREEN)));
		}
	} 
}