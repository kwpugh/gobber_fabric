package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.enchantments.BlindersEnchantment;
import com.kwpugh.gobber2.enchantments.FasterObsidianEnchantment;
import com.kwpugh.gobber2.enchantments.QuickUseEnchantment;
import com.kwpugh.gobber2.enchantments.RebuffingEnchantment;
import com.kwpugh.gobber2.enchantments.SmithBladeEnchantment;
import com.kwpugh.gobber2.enchantments.SolidFootingEnchantment;
import com.kwpugh.gobber2.enchantments.UntouchableEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EnchantmentInit 
{	
	static boolean smithblade = Gobber2.CONFIG.GENERAL.enableSmithblade;
	static boolean rebuffing = Gobber2.CONFIG.GENERAL.enableRebuffing;	
	static boolean untouchable = Gobber2.CONFIG.GENERAL.enableUntouchable;
	static boolean fasterObsidian = Gobber2.CONFIG.GENERAL.enableFasterObsidian;
	static boolean quickUse = Gobber2.CONFIG.GENERAL.enableQuickUse;
	static boolean blinders = Gobber2.CONFIG.GENERAL.enableBlinders;
	static boolean solidFooting = Gobber2.CONFIG.GENERAL.enableSolidFooting;
	
	public static final Enchantment SMITHBLADE = new SmithBladeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment REBUFFING = new RebuffingEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
	public static final Enchantment UNTOUCHABLE = new UntouchableEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
	public static final Enchantment FASTEROBSIDIAN = new FasterObsidianEnchantment(Enchantment.Rarity.COMMON, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment QUICKUSE = new QuickUseEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment BLINDERS = new BlindersEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
	public static final Enchantment SOLIDFOOTING = new SolidFootingEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
	
    public static void registerEnchantments()   
    {
    	if(smithblade)
    	{
    		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "smithblade"), SMITHBLADE);
    	}
    	
    	if(rebuffing)
    	{
    		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "rebuffing"), REBUFFING);
    	}   		
     	
     	if(untouchable)
     	{
     		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "untouchable"), UNTOUCHABLE);
     	}
     	
     	if(fasterObsidian)
    	{
    		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "faster_obsidian"), FASTEROBSIDIAN);
    	} 
     
     	if(quickUse)
     	{
     		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "quickuse"), QUICKUSE);
     	}
     	
     	if(blinders)
     	{
     		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "blinders"), BLINDERS);
     	}
     	
     	if(solidFooting)
     	{
     		Registry.register(Registry.ENCHANTMENT, new Identifier(Gobber2.MOD_ID, "solidfooting"), SOLIDFOOTING);
     	}
    	
    }
}