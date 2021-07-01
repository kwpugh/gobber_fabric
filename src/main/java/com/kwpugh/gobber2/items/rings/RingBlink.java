package com.kwpugh.gobber2.items.rings;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.EnableUtil;
import com.kwpugh.gobber2.util.HitResultUtil;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class RingBlink extends Item
{
	public RingBlink(Settings settings)
	{
		super(settings);
	}
	
	static int blinkDistance = Gobber2.CONFIG.GENERAL.ringBlinkDistance;
	static int blinkCooldown = Gobber2.CONFIG.GENERAL.ringBlinkCooldown;
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if(EnableUtil.isEnabled(stack))
		{
			// TBD
		}
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);
		ItemStack stack2 = player.getMainHandStack();	
		boolean hasQuickUse = stack2.getEnchantments().toString().contains("quickuse");
					
		if(!hasQuickUse)
		{
			player.getItemCooldownManager().set(this, blinkCooldown);
		}
		
		BlockHitResult pos = HitResultUtil.getNearestPositionWithAir(world, player, blinkDistance);

		if(pos != null && (pos.getType() == HitResult.Type.BLOCK || player.pitch >= -5))
        {
        	int side = pos.getType().ordinal();

        	if(side != -1)
            {                 
                player.stopRiding();
           		player.requestTeleport(pos.getPos().x, pos.getPos().y, pos.getPos().z);
           		player.fallDistance = 0.0F;
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);	
            }			
        }
		
		return TypedActionResult.success(stack, world.isClient);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_blink.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_ring_blink.tip2", blinkDistance).formatted(Formatting.YELLOW));
	}
}