package com.kwpugh.gobber2.items.armor;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class NetherArmor extends ArmorItem
{
	public NetherArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
	{
		super(material, slot, settings);
	}
	
	static boolean enablePerks = Gobber2.CONFIG.GENERAL.enableNetherPerks;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{				
		if(!world.isClient && entity instanceof PlayerEntity && enablePerks) 
		{
			PlayerEntity player = (PlayerEntity) entity;
			
	    	ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
			ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
			ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
		    ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);
					
	    	if((head.getItem() == ItemInit.GOBBER2_HELMET_NETHER && 
	    			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER && 
	    			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER && 
	    			feet.getItem() == ItemInit.GOBBER2_BOOTS_NETHER))
	      	{
	    		if(player.age % 180 == 0)
				{
					PlayerSpecialAbilities.giveLesserAbsorption(world, player, stack);
					PlayerSpecialAbilities.giveSaturationEffect(world, player, stack);
					PlayerSpecialAbilities.giveHealing(world, player, stack, 2);
				}	
	    		
	    		PlayerSpecialAbilities.givePhoenixEffect(world, player);
	      	}
		}
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
		tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_nether.tip1").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_nether.tip2").formatted(Formatting.GREEN));
		tooltip.add(new TranslatableText("item.gobber2.gobber2_armor_nether.tip3").formatted(Formatting.GREEN));
	}
}