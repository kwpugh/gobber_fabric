package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.items.armor.DragonArmor;
import com.kwpugh.gobber2.items.armor.EndArmor;
import com.kwpugh.gobber2.items.armor.GobberArmor;
import com.kwpugh.gobber2.items.armor.NetherArmor;
import com.kwpugh.gobber2.items.food.Goo;
import com.kwpugh.gobber2.items.food.GooeyApple;
import com.kwpugh.gobber2.items.food.GooeyBeef;
import com.kwpugh.gobber2.items.food.GooeyBeefstew;
import com.kwpugh.gobber2.items.food.GooeyBread;
import com.kwpugh.gobber2.items.fuel.Foo;
import com.kwpugh.gobber2.items.fuel.FooEnd;
import com.kwpugh.gobber2.items.medallions.*;
import com.kwpugh.gobber2.items.other.SpecialItem;
import com.kwpugh.gobber2.items.rings.RingAbove;
import com.kwpugh.gobber2.items.rings.RingAirWalking;
import com.kwpugh.gobber2.items.rings.RingAscent;
import com.kwpugh.gobber2.items.rings.RingAttraction;
import com.kwpugh.gobber2.items.rings.RingBlink;
import com.kwpugh.gobber2.items.rings.RingCuring;
import com.kwpugh.gobber2.items.rings.RingDismissal;
import com.kwpugh.gobber2.items.rings.RingEnderchest;
import com.kwpugh.gobber2.items.rings.RingExplorer;
import com.kwpugh.gobber2.items.rings.RingFarmer;
import com.kwpugh.gobber2.items.rings.RingHaste;
import com.kwpugh.gobber2.items.rings.RingMiner;
import com.kwpugh.gobber2.items.rings.RingPhoenix;
import com.kwpugh.gobber2.items.rings.RingRepair;
import com.kwpugh.gobber2.items.rings.RingReturn;
import com.kwpugh.gobber2.items.rings.RingStealth;
import com.kwpugh.gobber2.items.rings.RingSunshine;
import com.kwpugh.gobber2.items.rings.RingSwiftness;
import com.kwpugh.gobber2.items.rings.RingTeleport;
import com.kwpugh.gobber2.items.rings.RingTraveler;
import com.kwpugh.gobber2.items.rings.RingVision;
import com.kwpugh.gobber2.items.rings.RingVoid;
import com.kwpugh.gobber2.items.staffs.StaffClearing;
import com.kwpugh.gobber2.items.staffs.StaffEnsnarement;
import com.kwpugh.gobber2.items.staffs.StaffFarmer;
import com.kwpugh.gobber2.items.staffs.StaffNature;
import com.kwpugh.gobber2.items.staffs.StaffSniper;
import com.kwpugh.gobber2.items.staffs.StaffStars;
import com.kwpugh.gobber2.items.staffs.StaffTransformation;
import com.kwpugh.gobber2.items.tools.areatools.ModExcavator;
import com.kwpugh.gobber2.items.tools.areatools.ModHammer;
import com.kwpugh.gobber2.items.tools.areatools.ModTreeAxe;
import com.kwpugh.gobber2.items.tools.basetools.ModAxe;
import com.kwpugh.gobber2.items.tools.basetools.ModBow;
import com.kwpugh.gobber2.items.tools.basetools.ModHoe;
import com.kwpugh.gobber2.items.tools.basetools.ModPaxel;
import com.kwpugh.gobber2.items.tools.basetools.ModPickaxe;
import com.kwpugh.gobber2.items.tools.basetools.ModShovel;
import com.kwpugh.gobber2.items.tools.basetools.ModSword;
import com.kwpugh.gobber2.items.tools.endtools.BowEnd;
import com.kwpugh.gobber2.items.tools.endtools.ExcavatorEnd;
import com.kwpugh.gobber2.items.tools.endtools.HammerEnd;
import com.kwpugh.gobber2.items.tools.endtools.HoeEnd;
import com.kwpugh.gobber2.items.tools.endtools.PaxelEnd;
import com.kwpugh.gobber2.items.tools.endtools.PaxelEndStars;
import com.kwpugh.gobber2.items.tools.endtools.PickaxeEnd;
import com.kwpugh.gobber2.items.tools.endtools.ShovelEnd;
import com.kwpugh.gobber2.items.tools.endtools.SwordEnd;
import com.kwpugh.gobber2.items.tools.endtools.SwordEndSniper;
import com.kwpugh.gobber2.items.tools.endtools.TreeAxeEnd;
import com.kwpugh.gobber2.lists.*;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.MushroomStewItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit
{
	static boolean enableRingAbove = Gobber2.CONFIG.GENERAL.enableRingAbove;
	static boolean enableRingAirWalking = Gobber2.CONFIG.GENERAL.enableRingAirWalking;
	static boolean enableRingAscent = Gobber2.CONFIG.GENERAL.enableRingAscent;
	static boolean enableRingAttraction = Gobber2.CONFIG.GENERAL.enableRingAttraction;
	static boolean enableRingBlink = Gobber2.CONFIG.GENERAL.enableRingBlink;
	static boolean enableRingCuring = Gobber2.CONFIG.GENERAL.enableRingCuring;
	static boolean enableRingDismissal = Gobber2.CONFIG.GENERAL.enableRingDismissal;
	static boolean enableRingEnderchest = Gobber2.CONFIG.GENERAL.enableRingEnderchest;
	static boolean enableRingExplorer = Gobber2.CONFIG.GENERAL.enableRingExplorer;
	static boolean enableRingFarmer = Gobber2.CONFIG.GENERAL.enableRingFarmer;
	static boolean enableRingHaste = Gobber2.CONFIG.GENERAL.enableRingHaste;
	static boolean enableRingMiner = Gobber2.CONFIG.GENERAL.enableRingMiner;
	static boolean enableRingPhoenix = Gobber2.CONFIG.GENERAL.enableRingPhoenix;
	static boolean enableRingRepair = Gobber2.CONFIG.GENERAL.enableRingRepair;
	static boolean enableRingReturn = Gobber2.CONFIG.GENERAL.enableRingReturn;
	static boolean enableRingStealth = Gobber2.CONFIG.GENERAL.enableRingStealth;
	static boolean enableRingSunshine = Gobber2.CONFIG.GENERAL.enableRingSunshine;
	static boolean enableRingSwiftness = Gobber2.CONFIG.GENERAL.enableRingSwiftness;
	static boolean enableRingTeleport = Gobber2.CONFIG.GENERAL.enableRingTeleport;
	static boolean enableRingTraveler = Gobber2.CONFIG.GENERAL.enableRingTraveler;
	static boolean enableRingVision = Gobber2.CONFIG.GENERAL.enableRingVision;
	static boolean enableRingVoid = Gobber2.CONFIG.GENERAL.enableRingVoid;

	static boolean enableStaffClearing = Gobber2.CONFIG.GENERAL.enableStaffClearing;
	static boolean enableStaffEnsnarement = Gobber2.CONFIG.GENERAL.enableStaffEnsnarement;
	static boolean enableStaffFarmer = Gobber2.CONFIG.GENERAL.enableStaffFarmer;
	static boolean enableStaffNature = Gobber2.CONFIG.GENERAL.enableStaffNature;
	static boolean enableStaffSniper = Gobber2.CONFIG.GENERAL.enableStaffSniper;
	static boolean enableStaffStars = Gobber2.CONFIG.GENERAL.enableStaffStars;
	static boolean enableStaffTransformation = Gobber2.CONFIG.GENERAL.enableStaffTransformation;

	static boolean enableMedallionBreathing = Gobber2.CONFIG.GENERAL.enableMedallionBreathing;
	static boolean enableMedallionHealing = Gobber2.CONFIG.GENERAL.enableMedallionLesserHealing;
	static boolean enableMedallionHealing2 = Gobber2.CONFIG.GENERAL.enableMedallionHealing;
	static boolean enableMedallionHealing3 = Gobber2.CONFIG.GENERAL.enableMedallionGreaterHealing;
	static boolean enableMedallionExp = Gobber2.CONFIG.GENERAL.enableMedallionExp;
	static boolean enableMedallionHero = Gobber2.CONFIG.GENERAL.enableMedallionHero;
	static boolean enableMedallionSea = Gobber2.CONFIG.GENERAL.enableMedallionSea;

	static boolean enableSniperSowrd = Gobber2.CONFIG.GENERAL.enableSniperSowrd;
	static boolean enableSpecial = Gobber2.CONFIG.GENERAL.enableSpecialItem;
	static boolean enableTreeAxe = Gobber2.CONFIG.GENERAL.enableTreeAxe;
	static boolean enableHammers = Gobber2.CONFIG.GENERAL.enableHammers;
	static boolean enableExcavators = Gobber2.CONFIG.GENERAL.enableExcavators;
	static boolean enablePaxels = Gobber2.CONFIG.GENERAL.enablePaxels;
	static int gobberBowDurability = Gobber2.CONFIG.GENERAL.gobberBowDurability;
	static int netherBowDurability = Gobber2.CONFIG.GENERAL.gobberNetherBowDurability;
	static int endBowDurability = Gobber2.CONFIG.GENERAL.gobberEndBowDurability;
	static int staffTransformationDurability = Gobber2.CONFIG.GENERAL.staffTransformationDurability;

	public static final ArmorMaterial GOBBER_ARMOR_MATERIAL = new GobberArmorMaterial();
	public static final ArmorMaterial GOBBER_NETHER_ARMOR_MATERIAL = new NetherArmorMaterial();
	public static final ArmorMaterial GOBBER_END_ARMOR_MATERIAL = new EndArmorMaterial();
	public static final ArmorMaterial GOBBER_DRAGON_ARMOR_MATERIAL = new DragonArmorMaterial();
	public static final ToolMaterial GOBBER_TOOL_MATERIAL = new GobberToolMaterial();
	public static final ToolMaterial GOBBER_NETHER_TOOL_MATERIAL = new NetherToolMaterial();
	public static final ToolMaterial GOBBER_END_TOOL_MATERIAL = new EndToolMaterial();
	
	public static final Item GOBBER2_GLOB = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GLOB_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GLOB_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));	

	public static final Item GOBBER2_FOO = new Foo((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_FOO_NETHER = new Foo((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_FOO_END = new FooEnd((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_GOO = new Goo((new Item.Settings()).food(FoodList.goo).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_APPLE = new GooeyApple((new Item.Settings()).food(FoodList.gooey_apple).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BREAD = new GooeyBread((new Item.Settings()).food(FoodList.gooey_bread).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BEEF = new GooeyBeef((new Item.Settings()).food(FoodList.gooey_beef).group(Gobber2.GOBBER2_GROUP));
	public static final MushroomStewItem GOBBER2_GOOEY_BEEFSTEW = new GooeyBeefstew((new Item.Settings()).food(FoodList.gooey_beefstew).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_GOO_NETHER = new Goo((new Item.Settings()).food(FoodList.goo_nether).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_APPLE_NETHER = new GooeyApple((new Item.Settings()).food(FoodList.gooey_apple_nether).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BREAD_NETHER = new GooeyBread((new Item.Settings()).food(FoodList.gooey_bread_nether).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_GOOEY_BEEF_NETHER = new GooeyBeef((new Item.Settings()).food(FoodList.gooey_beef_nether).group(Gobber2.GOBBER2_GROUP));
	public static final MushroomStewItem GOBBER2_GOOEY_BEEFSTEW_NETHER = new GooeyBeefstew((new Item.Settings()).food(FoodList.gooey_beefstew_nether).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_INGOT = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_INGOT_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_INGOT_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_ROD = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_ROD_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_ROD_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_RING = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_MEDALLION = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_NETHER = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_SWORD = new ModSword(GOBBER_TOOL_MATERIAL, 1, -2.0F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SWORD_NETHER = new ModSword(GOBBER_NETHER_TOOL_MATERIAL, 3, -1.8F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SWORD_END = new SwordEnd(GOBBER_END_TOOL_MATERIAL, 6, -1.6F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SWORD_SNIPER = new SwordEndSniper(GOBBER_END_TOOL_MATERIAL, 6, -1.6F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_BOW = new ModBow((new Item.Settings()).maxDamage(gobberBowDurability).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOW_NETHER = new ModBow((new Item.Settings()).maxDamage(netherBowDurability).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOW_END = new BowEnd((new Item.Settings()).maxDamage(endBowDurability).fireproof().group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_PICKAXE = new ModPickaxe(GOBBER_TOOL_MATERIAL, -4, -2.4F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PICKAXE_NETHER = new ModPickaxe(GOBBER_NETHER_TOOL_MATERIAL, -3, -2.3F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PICKAXE_END = new PickaxeEnd(GOBBER_END_TOOL_MATERIAL, -2, -2.2F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_AXE = new ModAxe(GOBBER_TOOL_MATERIAL, 1.0F, -2.9F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_AXE_NETHER = new ModAxe(GOBBER_NETHER_TOOL_MATERIAL, 3.0F, -2.8F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_AXE_END = new ModAxe(GOBBER_END_TOOL_MATERIAL, 6.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
		
	public static final Item GOBBER2_SHOVEL = new ModShovel(GOBBER_TOOL_MATERIAL, -3.0F, -3.0F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SHOVEL_NETHER = new ModShovel(GOBBER_NETHER_TOOL_MATERIAL, -2.0F, -2.8F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_SHOVEL_END = new ShovelEnd(GOBBER_END_TOOL_MATERIAL, 0.0F, -2.6F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_HOE = new ModHoe(GOBBER_TOOL_MATERIAL, -9, 2.1F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HOE_NETHER = new ModHoe(GOBBER_NETHER_TOOL_MATERIAL, -9, 2.2F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HOE_END = new HoeEnd(GOBBER_END_TOOL_MATERIAL, -9, 2.2F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_PAXEL = new ModPaxel(GOBBER_TOOL_MATERIAL, 0.0F, -2.9F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PAXEL_NETHER = new ModPaxel(GOBBER_NETHER_TOOL_MATERIAL, 2.0F, -2.8F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PAXEL_END = new PaxelEnd(GOBBER_END_TOOL_MATERIAL, 5.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_PAXEL_STARS = new PaxelEndStars(GOBBER_END_TOOL_MATERIAL, 5.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_HAMMER = new ModHammer(GOBBER_TOOL_MATERIAL, -1, -2.4F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HAMMER_NETHER = new ModHammer(GOBBER_NETHER_TOOL_MATERIAL, 0, -2.3F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HAMMER_END = new HammerEnd(GOBBER_END_TOOL_MATERIAL, 1, -2.2F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_EXCAVATOR = new ModExcavator(GOBBER_TOOL_MATERIAL, -3.0F, -3.0F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_EXCAVATOR_NETHER = new ModExcavator(GOBBER_NETHER_TOOL_MATERIAL, -2.0F, -2.8F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_EXCAVATOR_END = new ExcavatorEnd(GOBBER_END_TOOL_MATERIAL, 0.0F, -2.6F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_TREE_AXE = new ModTreeAxe(GOBBER_TOOL_MATERIAL, 1.0F, -2.9F, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_TREE_AXE_NETHER = new ModTreeAxe(GOBBER_NETHER_TOOL_MATERIAL, 3.0F, -2.8F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_TREE_AXE_END = new TreeAxeEnd(GOBBER_END_TOOL_MATERIAL, 6.0F, -2.7F, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_HELMET = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS = new GobberArmor(GOBBER_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_HELMET_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS_NETHER = new NetherArmor(GOBBER_NETHER_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_LINKS_END = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HELMET_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS_END = new EndArmor(GOBBER_END_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item DRAGON_ELYTRA = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item DRAGON_STAR = new Item((new Item.Settings()).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_HELMET_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_CHESTPLATE_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_LEGGINGS_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_BOOTS_DRAGON = new DragonArmor(GOBBER_DRAGON_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).fireproof().group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_RING_ATTRACTION = new RingAttraction((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_RETURN = new RingReturn((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_SWIFTNESS = new RingSwiftness((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_FARMER = new RingFarmer((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_MINER = new RingMiner((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_ASCENT = new RingAscent((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_SUNSHINE = new RingSunshine((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_ABOVE = new RingAbove((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_RING_CURING = new RingCuring((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_VISION = new RingVision((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_HASTE = new RingHaste((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_PHOENIX = new RingPhoenix((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));	
	public static final Item GOBBER2_RING_REPAIR = new RingRepair((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_RING_ENDERCHEST = new RingEnderchest((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_EXPLORER = new RingExplorer((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_BLINK = new RingBlink((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_VOID = new RingVoid((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_AIRWALKING = new RingAirWalking((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_TELEPORT = new RingTeleport((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_DISMISSAL = new RingDismissal((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_STEALTH = new RingStealth((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_RING_TRAVELER = new RingTraveler((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_MEDALLION_BREATHING = new MedallionBreathing((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_HEALING = new MedallionHealing((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_HERO = new MedallionHero((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_EXP = new MedallionExp((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_HEALING2 = new MedallionHealing2((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_SEA = new MedallionSea((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_MEDALLION_HEALING3 = new MedallionHealing3((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));

	public static final Item GOBBER2_STAFF_CLEARING = new StaffClearing((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_TRANSFORMATION = new StaffTransformation((new Item.Settings()).maxDamage(staffTransformationDurability).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_ENSNAREMENT = new StaffEnsnarement((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_FARMER = new StaffFarmer((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_STARS = new StaffStars((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_NATURE = new StaffNature((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item GOBBER2_STAFF_SNIPER = new StaffSniper((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	
	public static final Item GOBBER2_SPECIAL_ITEM = new SpecialItem((new Item.Settings()).maxCount(1).group(Gobber2.GOBBER2_GROUP));
	public static final Item DIAMOND_NUGGET = new Item((new Item.Settings()).maxCount(64).group(Gobber2.GOBBER2_GROUP));
	
	public static void registerItems()
	{
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glob"), GOBBER2_GLOB);	
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glob_nether"), GOBBER2_GLOB_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_glob_end"), GOBBER2_GLOB_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_foo"), GOBBER2_FOO);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_foo_nether"), GOBBER2_FOO_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_foo_end"), GOBBER2_FOO_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_goo"), GOBBER2_GOO);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_apple"), GOBBER2_GOOEY_APPLE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_bread"), GOBBER2_GOOEY_BREAD);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beef"), GOBBER2_GOOEY_BEEF);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beefstew"), GOBBER2_GOOEY_BEEFSTEW);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_goo_nether"), GOBBER2_GOO_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_apple_nether"), GOBBER2_GOOEY_APPLE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_bread_nether"), GOBBER2_GOOEY_BREAD_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beef_nether"), GOBBER2_GOOEY_BEEF_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_gooey_beefstew_nether"), GOBBER2_GOOEY_BEEFSTEW_NETHER);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ingot"), GOBBER2_INGOT);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ingot_nether"), GOBBER2_INGOT_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ingot_end"), GOBBER2_INGOT_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_rod"), GOBBER2_ROD);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_rod_nether"), GOBBER2_ROD_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_rod_end"), GOBBER2_ROD_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring"), GOBBER2_RING);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_nether"), GOBBER2_RING_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_end"), GOBBER2_RING_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion"), GOBBER2_MEDALLION);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_nether"), GOBBER2_MEDALLION_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_end"), GOBBER2_MEDALLION_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword"), GOBBER2_SWORD);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword_nether"), GOBBER2_SWORD_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword_end"), GOBBER2_SWORD_END);

		if(enableSniperSowrd)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_sword_sniper"), GOBBER2_SWORD_SNIPER);
		}

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_bow"), GOBBER2_BOW);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_bow_nether"), GOBBER2_BOW_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_bow_end"), GOBBER2_BOW_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_pickaxe"), GOBBER2_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_pickaxe_nether"), GOBBER2_PICKAXE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_pickaxe_end"), GOBBER2_PICKAXE_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_axe"), GOBBER2_AXE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_axe_nether"), GOBBER2_AXE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_axe_end"), GOBBER2_AXE_END);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_shovel"), GOBBER2_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_shovel_nether"), GOBBER2_SHOVEL_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_shovel_end"), GOBBER2_SHOVEL_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hoe"), GOBBER2_HOE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hoe_nether"), GOBBER2_HOE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hoe_end"), GOBBER2_HOE_END);	
		
		if(enablePaxels)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel"), GOBBER2_PAXEL);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel_nether"), GOBBER2_PAXEL_NETHER);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel_end"), GOBBER2_PAXEL_END);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_paxel_stars"), GOBBER2_PAXEL_STARS);		
		}
	
		if(enableHammers)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hammer"), GOBBER2_HAMMER);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hammer_nether"), GOBBER2_HAMMER_NETHER);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_hammer_end"), GOBBER2_HAMMER_END);			
		}

		if(enableExcavators)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_excavator"), GOBBER2_EXCAVATOR);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_excavator_nether"), GOBBER2_EXCAVATOR_NETHER);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_excavator_end"), GOBBER2_EXCAVATOR_END);			
		}

		if(enableTreeAxe)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_tree_axe"), GOBBER2_TREE_AXE);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_tree_axe_nether"), GOBBER2_TREE_AXE_NETHER);
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_tree_axe_end"), GOBBER2_TREE_AXE_END);	
		}
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet"), GOBBER2_HELMET);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate"), GOBBER2_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings"), GOBBER2_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots"), GOBBER2_BOOTS);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet_nether"), GOBBER2_HELMET_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate_nether"), GOBBER2_CHESTPLATE_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings_nether"), GOBBER2_LEGGINGS_NETHER);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots_nether"), GOBBER2_BOOTS_NETHER);
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_links_end"), GOBBER2_LINKS_END);	
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet_end"), GOBBER2_HELMET_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate_end"), GOBBER2_CHESTPLATE_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings_end"), GOBBER2_LEGGINGS_END);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots_end"), GOBBER2_BOOTS_END);

		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "dragon_elytra"), DRAGON_ELYTRA);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "dragon_star"), DRAGON_STAR);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_helmet_dragon"), GOBBER2_HELMET_DRAGON);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_chestplate_dragon"), GOBBER2_CHESTPLATE_DRAGON);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_leggings_dragon"), GOBBER2_LEGGINGS_DRAGON);
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_boots_dragon"), GOBBER2_BOOTS_DRAGON);

		if(enableRingAttraction)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_attraction"), GOBBER2_RING_ATTRACTION);
		}

		if(enableRingReturn)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_return"), GOBBER2_RING_RETURN);
		}

		if(enableRingSwiftness)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_swiftness"), GOBBER2_RING_SWIFTNESS);
		}

		if(enableRingFarmer)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_farmer"), GOBBER2_RING_FARMER);
		}

		if(enableRingMiner)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_miner"), GOBBER2_RING_MINER);
		}

		if(enableRingAscent)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_ascent"), GOBBER2_RING_ASCENT);
		}

		if(enableRingSunshine)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_sunshine"), GOBBER2_RING_SUNSHINE);
		}

		if(enableRingAbove)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_above"), GOBBER2_RING_ABOVE);
		}

		if(enableRingCuring)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_curing"), GOBBER2_RING_CURING);
		}

		if(enableRingVision)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_vision"), GOBBER2_RING_VISION);
		}

		if(enableRingHaste)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_haste"), GOBBER2_RING_HASTE);
		}

		if(enableRingPhoenix)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_phoenix"), GOBBER2_RING_PHOENIX);
		}

		if(enableRingRepair)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_repair"), GOBBER2_RING_REPAIR);
		}

		if(enableRingEnderchest)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_enderchest"), GOBBER2_RING_ENDERCHEST);
		}

		if(enableRingExplorer)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_explorer"), GOBBER2_RING_EXPLORER);
		}

		if(enableRingBlink)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_blink"), GOBBER2_RING_BLINK);
		}

		if(enableRingVoid)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_void"), GOBBER2_RING_VOID);
		}

		if(enableRingAirWalking)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_airwalking"), GOBBER2_RING_AIRWALKING);
		}

		if(enableRingTeleport)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_teleport"), GOBBER2_RING_TELEPORT);
		}

		if(enableRingDismissal)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_dismissal"), GOBBER2_RING_DISMISSAL);
		}

		if(enableRingStealth)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_stealth"), GOBBER2_RING_STEALTH);
		}

		if(enableRingTraveler)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_ring_traveler"), GOBBER2_RING_TRAVELER);
		}

		if(enableMedallionBreathing)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_breathing"), GOBBER2_MEDALLION_BREATHING);
		}

		if(enableMedallionHealing)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_healing"), GOBBER2_MEDALLION_HEALING);
		}

		if(enableMedallionHero)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_hero"), GOBBER2_MEDALLION_HERO);
		}

		if(enableMedallionExp)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_exp"), GOBBER2_MEDALLION_EXP);
		}

		if(enableMedallionHealing2)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_healing2"), GOBBER2_MEDALLION_HEALING2);
		}

		if(enableMedallionSea)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_sea"), GOBBER2_MEDALLION_SEA);
		}

		if(enableMedallionHealing3)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_medallion_healing3"), GOBBER2_MEDALLION_HEALING3);
		}

		if(enableStaffClearing)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_clearing"), GOBBER2_STAFF_CLEARING);
		}

		if(enableStaffTransformation)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_transformation"), GOBBER2_STAFF_TRANSFORMATION);
		}

		if(enableStaffEnsnarement)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_ensnarement"), GOBBER2_STAFF_ENSNAREMENT);
		}

		if(enableStaffFarmer)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_farmer"), GOBBER2_STAFF_FARMER);
		}

		if(enableStaffNature)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_nature"), GOBBER2_STAFF_NATURE);
		}

		if(enableStaffStars)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_stars"), GOBBER2_STAFF_STARS);
		}

		if(enableStaffSniper)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_staff_sniper"), GOBBER2_STAFF_SNIPER);
		}
		
		if(enableSpecial)
		{
			Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "gobber2_special_item"), GOBBER2_SPECIAL_ITEM);
		}
		
		Registry.register(Registry.ITEM, new Identifier(Gobber2.MOD_ID, "diamond_nugget"), DIAMOND_NUGGET);
	}
}
