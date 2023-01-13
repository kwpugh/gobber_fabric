package com.kwpugh.gobber2.items.tools.endtools;

import com.kwpugh.gobber2.Gobber2;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class SwordEndSniper extends SwordItem implements Wearable
{
	static int cooldown = Gobber2.CONFIG.GENERAL.swordSniperCooldoown;
	static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;
	static boolean quickUseAllowed = Gobber2.CONFIG.GENERAL.swordSniperEnableQuickUse;

	public SwordEndSniper(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getStackInHand(hand);		
		ItemStack stack2 = player.getMainHandStack();
		boolean hasQuickUse = stack2.getEnchantments().toString().contains("quickuse");

        if (!world.isClient)
        {
			if(!hasQuickUse || !quickUseAllowed)
			{
				player.getItemCooldownManager().set(this, cooldown);
			}

            ArrowItem itemarrow = (ArrowItem)Items.ARROW;
            PersistentProjectileEntity persistentProjectileEntity = itemarrow.createArrow(world, new ItemStack(Items.ARROW), player);
            float arrowVelocity = Gobber2.CONFIG.GENERAL.swordSniperArrowVelocity;
            persistentProjectileEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, arrowVelocity, 0.0F);
            persistentProjectileEntity.setDamage(Gobber2.CONFIG.GENERAL.swordSniperArrowExtraDamage);

			if (EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, stack) > 0)
			{
				persistentProjectileEntity.setOnFireFor(100);
			}

            world.spawnEntity(persistentProjectileEntity);
            persistentProjectileEntity.pickupType = PickupPermission.DISALLOWED;
        }
		
		return TypedActionResult.success(stack);
	}
	
	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) 
	{
		if(world.isClient) return;

		if(unbreakable)
		{
			stack.getOrCreateNbt().putBoolean("Unbreakable", true);
		}
	}
		
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) 
	{
	    tooltip.add(Text.translatable("item.gobber2.gobber2_staff_sniper.tip1").formatted(Formatting.LIGHT_PURPLE));
	}  
}
