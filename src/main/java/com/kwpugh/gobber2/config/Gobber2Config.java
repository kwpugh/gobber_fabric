package com.kwpugh.gobber2.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "gobber2")
public class Gobber2Config extends PartitioningSerializer.GlobalData 
{
    public Ores ORES = new Ores();
    public General GENERAL = new General();
    
    @Config(name = "ores")
    public static class Ores implements ConfigData 
    {
		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nLucky Block"
				+"\n***********************")
		public int luckyVeinSize = 4;
		public int luckyMaxLevel = 120;
		public int luckyPerChunk = 15;
		public int luckyExpOrbs = 2;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nLucky Block - Deepslate"
				+"\n***********************")
		public int luckyDeepslateVeinSize = 4;
		public int luckyDeepslateMaxLevel = 0;
		public int luckyDeepslatePerChunk = 15;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nLucky Block - Nether"
				+"\n***********************")
		public int luckyNetherVeinSize = 4;
		public int luckyNetherMaxLevel = 128;
		public int luckyNetherPerChunk = 15;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nLucky Block - End"
				+"\n***********************")
		public int luckyEndVeinSize = 9;
		public int luckyEndMaxLevel = 180;
		public int luckyEndPerChunk = 25;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nLucky Block - Drops"
				+ "\n- only use values between .001 to .999"
				+ "\n- values cannot overlap"
				+ "\n- values should be in decending order from"
				+ "\ncutoff, common, uncommon, and rare"
				+ "\n- values above cutoff threshold get nothing"
				+ "\n***********************")
		public boolean enableExtraLoot = false;
		public boolean enableFortune = false;
		public double cutoffThreshold = .10;
		public double commonThreshold = .02;
		public double uncommonThreshold = .009;
		public double rareThreshold = .006;
		public String defaultDrop = "gobbers:gobber2_foo";

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nBlast Resistance"
				+"\n***********************")
		public int oreBlastResistance = 1300;
		public int glassBlastResistance = 1300;
		public int blockBlastResistance = 1300;

	    @Comment("\n"
	    		+"\n"
	    		+ "***********************"    		
	    		+"\nGobber Ore"
	    		+"\n***********************")
	    public int gobberVeinSize = 4;
    	public int gobberMaxLevel = 5;
    	public int gobberPerChunk = 20;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nDeepslate Gobber Ore"
				+"\n***********************")
		public int gobberDeepslateVeinSize = 4;
		public int gobberDeepslateMaxLevel = 0;
		public int gobberDeepslatePerChunk = 20;

        @Comment("\n"
        		+"\n"
        		+ "***********************"
	    		+"\nNether Gobber Ore"
	    		+"\n***********************")
	    public int netherGobberVeinSize = 4;
    	public int netherGobberMaxLevel = 40;
    	public int netherGobberPerChunk = 20;
    	
        @Comment("\n"
        		+"\n"
        		+ "***********************"
 	    		+"\nEnd Gobber Ore"
 	    		+"\n***********************")
 	    public int endGobberVeinSize = 9;
     	public int endGobberMaxLevel = 180;
     	public int endGobberPerChunk = 25;
    }
    
    @Config(name = "general")
    public static class General implements ConfigData 
    {
    	 @Comment("\n"
    			+"\n"
    	 		+ "***********************"
 	    		+"\nRing Settings"
 	    		+"\n***********************")
		public int ringAboveCooldown = 120;
		public boolean enableAirWalkingPlaceGlass = true;
		public int ringAscentDuration = 3200;
		public int ringAttractionRange = 8;
		public boolean ringAttractionEnableCoal = false;
		public int ringBlinkDistance = 100;
		public int ringBlinkCooldown = 120;
		public boolean enableCuringAlwaysOn = false;
		public int ringCuringCooldown = 120;
		public double ringDismissalRange = 8;
		public double ringDismissalVelocity = 0.2D;
		public double ringDismissalLift = 1.5D;
		public int ringExplorerCooldown = 240;
		public boolean ringExplorerUsePlayerPos = false;
		public int ringExplorerMin = 5000;
		public int ringExplorerMax = 20000;
		public int ringFarmerHorizRange = 12;
		public int ringFarmerVertRange = 6;
		public int ringFarmerInterval = 60;
		public int ringFarmerIntervalCactus = 20;
		public boolean ringFarmerExtraCrops = false;
		public int ringPhoenixHealAmount = 5;
		public int ringHasteLevel = 2;
		public int ringLuckLevel = 4;
		public int ringMinerCooldown = 120;
 		public int ringMinerRange = 5;
 		public int ringMinerVerticalRange = 4;
		public boolean ringMinerCentralizedBreaking = false;
		public boolean ringMinerCentralizedBreakingDelay = true;
		public boolean ringMinerInstantBreak = false;
 		public int ringRepairInterval = 90;
		public int ringStrengthLevel = 2;
		public int ringStrengthResistenceLevel = 1;
		public boolean ringSunshineAuto = false;
		public int ringSwiftnessLevel = 1;
		public int ringSwiftnessJump = 3;
 		public double ringTravelerLaunch = 4.0;
 		public double ringTravelerCruising = 0.2;
 		public boolean allowWorkInEnderchest = false;
 		public boolean enableRingStealthInvisible = true;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nMedallion Settings"
				+"\n***********************")
		public boolean medallionExpXPBoost = true;
		public int medallionLesserHealingAmount = 1;
		public int medallionHealingAmount = 2;
		public int medallionGreaterHealingAmount = 3;
		public boolean medallionHealingIncludesSaturation = false;
		public boolean medallionSeaAlwaysOn = false;
		public int medallionShieldingHorizRange = 3;
		public int medallionShieldingVertRange = 2;
		public boolean medallionShieldingAgainstWither = false;
		public int medallionSufferingRange = 6;
		public int medallionSufferingDamage = 2;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nStaff Settings"
				+"\n***********************")
		public int staffTransformationDurability = 1025;
		public int staffClearingHorizRange = 6;
		public int staffClearingVertRange = 3;
		public int staffClearingCooldown = 120;
		public int staffSniperCooldown = 240;
		public float staffSniperArrowVelocity = 60.0F;
		public int staffSniperArrowExtraDamage = 1;
		public boolean staffSniperEnableQuickUse = true;
		public int staffFarmerHorizRange = 12;
		public int staffFarmerVertRange = 6;
		public int staffFarmerInterval = 60;
		public int staffFarmerIntervalCactus = 20;
		public int staffFarmerIntervalExtra = 20;
		public boolean staffFarmerExtraCrops = false;
		public boolean staffFarmerReplant = true;
		public boolean staffEnsnarementHotileMobs = false;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nGeneral Effect Settings"
				+"\n-used with optimization mods"
				+"\nthat break things"
				+"\nValue of 300 works"
				+"\n- only needed if there is a problem"
				+"\n***********************")
		public int effectDuration = 8;
		public int effectDurationNightVision = 240;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nBlessing Effect Settings"
				+"\n***********************")
		public int effectBlessingDuration = 90;
		public int effectBlessingPoints = 1;
		public int effectBlessingFood = 1;
		public int effectBlessingDelay = 20;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nGobberForce Settings (WIP)"
				+"\n- not ready for prime time"
				+"\n- use at your own risk"
				+"\n- values are unbalanced"
				+"\n***********************")
		public boolean enableGobberForce = false;
		public boolean enableGFNaturalRegen = true;
		public boolean enableGFBreathing = true;
		public boolean enableGFDamageShield = true;
		public boolean enableGFVigor = true;
		public boolean enableGFAutoFeed = true;
		public boolean enableGFHealthRestore = true;
		public boolean enableGFExtraHearts = true;
		public boolean enableGFChrisma = true;
		public boolean enableGFBadOmen = true;
		public boolean enableGFKillEarning = true;
		public boolean enableGFMiningEarning = true;
		public int forceNaturalRegenDelayGobber = 1200;
		public int forceNaturalRegenDelayNether = 900;
		public int forceNaturalRegenDelayEnd = 600;
		public int forceNaturalRegenDelayDragon = 300;
		public int forceEarnedFullArmor = 1;
		public int forceEarnedFromKill = 2;
		public int forceEarnedFromOre = 3;
		public int forceDamageAbsorbLevel = 50;
		public int forceExhausionLevel = 75;
		public int forceExhausionCost = 1;
		public int forceAirLevel = 30;
		public int forceAirCost = 1;
		public int forceHungerRestoreLevel = 50;
		public int forceHungerRestoreCost = 5;
		public int forceHealthRestoreLevel = 50;
		public int forceHealthRestoreCost = 5;
		public int forceExtraHeartsLevel = 100;
		public int forceExtraHeartsCost = 10;
		public int forceExtraHeartsGobber = 2;
		public int forceRemoveBadOmenLevel = 50;
		public int forceRemoveBadOmenCost = 5;
		public int forceCharismaLevel = 100;
		public int forceCharismaCost = 1;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nExperience Effect Settings"
				+"\n-used by Experience Boost enchant,"
				+"\nMedallion of Experience, and"
				+"\nNether Sword perks"
				+"\n-to nerf xp, either increase effect"
				+"\ndelay and/or decrease XP per tick"
				+"\n***********************")
		public int effectDurationExpBoost = 60;
		public int effectDelayExpBoost = 60;
		public int ExperienceBaseXPPerTick = 8;
		public int medallionExpAmplifer = 4;
		public int medallionExpDuration = 60;
		public boolean medallionExpFromAnyBlock = false;
		public int netherSwordXPPerTick = 8;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEnable/Disable Enchantments"
				+"\n***********************")
		public boolean enableApotropaic = true;
		public boolean enableBlinders = true;
		public boolean enableBlessing = true;
		public boolean enableFasterObsidian = true;
		public boolean enableFluidMaster = true;
		public boolean enableExpBoost = true;
		public boolean enableQuickUse = true;
		public boolean enableQuietFeet = true;
		public boolean enableRebuffing = true;
		public boolean enableSmithBlade = true;
		public boolean enableSolidFooting = true;
		public boolean enableSummoner = true;
		public boolean enableUntouchable = true;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEnchantment Settings"
				+"\n***********************")
		public int smithbladeAttackAmount = 2 ;
		public int smithbladeMaxLevel = 5;
		public int rebuffingMaxLevel = 3;
		public int untouchableBaseDamage = 8;
		public int untouchableMaxLevel = 4;
		public float untouchableAttackChance = .5F;
		public boolean enableRingEnchanting = true;
		public boolean enableStaffEnchanting = true;
		public boolean enableMedallionEnchanting = true;
		public boolean apotropaicEventEnable = true;
		public boolean summonerEventEnable = true;
		public boolean summonerEnableRandomMode = false;
		public float summonerRandomModeChance = .3F;
		public boolean summonerEnableBonusStats = true;
		public boolean summonerGoldGear = true;
		public int summonerMobLifespan = 120;
		public double summonerHealthBonus = 10.0D;
		public double summonerAttackBonus = 10.0D;
		public double summonerMovementBonus = 0.40D;
		public double summonerArmorBonus = 10.0D;

		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nTool Settings"
 	    		+"\n***********************")	
		public int swordSniperCooldoown = 240;
		public float swordSniperArrowVelocity = 60.0F;
		public int swordSniperArrowExtraDamage = 1;
		public boolean swordSniperEnableQuickUse = true;
		public boolean enableEndHammer5x5 = true;
		public boolean enableEndExcavator5x5 = true;
		public boolean enableNetherSwordPerks = true;
	
	   	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nBow Durability"
    			+"\n******************************")
    	public int gobberBowDurability = 3800;
	   	public int gobberNetherBowDurability = 5200;
	   	public int gobberEndBowDurability = 8000;

		@Comment("\n"
				+"\n"
				+"\n"
				+"******************************"
				+"\nBow Zoom Multiplier"
				+"\n- vanilla bow value = .15F"
				+"\n- pretty good value = .45F"
				+"\n- awesome value = .75F"
				+"\n******************************")
		public float bowZoomMultiplier = .45F;

		@Comment("\n"
				+"\n"
				+"\n"
				+"******************************"
				+"\nGlobal Bow Settings"
				+"\n******************************")
		public float projectileSpeed = 6.5F;
		public float projectileDivergence = 0.0F;
		public float projectileRoll = 0.0F;
		public double projectilePowerDamageBonus = 0.75D;
		public int projectileFlameBurnSeconds = 100;
		public double projectileExtraDamage = 0.0D;
		public int projectileDropRange = 45;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nGobber Arrow Settings"
				+"\n- effects - poison"
				+"\n- cobweb and explode are mutually exclusive"
				+"\n***********************")
		public boolean enableEffectsGobberArrow = true;
		public int effectDurationGobberArrow = 300;
		public boolean enableGobberArrowCobweb = false;
		public boolean enableGobberArrowExplode = false;
		public int explosionFactorGobberArrow = 2;
		public int extraDamageGobberArrow = 5;
		public byte piercingLeveGobberArrow = 0;
		public int punchLevelGobberArrow = 0;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nNether Arrow Settings"
				+"\n- effects - slowing, wither, glowing"
				+"\n***********************")
		public boolean enableEffectsNetherArrow = true;
		public int effectDurationNetherArrow = 300;
		public boolean enableNetherArrowFire = true;
		public int secondsEntityOnFireDuration = 30;
		public int extraDamageNetherArrow = 7;
		public byte piercingLevelNetherArrow = 1;
		public int punchLevelNetherArrow = 1;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEnd Arrow Settings"
				+"\n- effects - slowing, wither"
				+"\n- cloud, cage, and levitation are mutually exclusive"
				+"\n***********************")
		public boolean enableEffectsEndArrow = true;
		public int effectDurationEndArrow = 300;
		public boolean enablEndArrowCloud = true;
		public boolean enableEndArrowGlassCage = false;
		public boolean enableLevitationEndArrow = false;
		public int extraDamageEndArrow = 10;
		public byte piercingLevelEndArrow = 2;
		public int punchLevelEndArrow = 2;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nFull Tool Damage"
				+"\n- effects hammer/excavator/treeaxe"
				+"\n***********************")
		public boolean enableFullDamage = false;

      	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Tool Material Stats"
    			+"\n******************************")
    	public int gobberDurability = 3800;
       	public float gobberMiningSpeed = 9.0F;
       	public float gobberAttackDamage = 9.0F;
		public float gobberAxeAttackDamage = 10.0F;
		public float gobberSwordAttackSpeed = 2.0F;
	public float gobberAxeAttackDamage = 10.0F;
       	public int gobberEnchantability = 20;
       	
      	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Nether Tool Material Stats"
    			+"\n******************************")
    	public int gobberNetherDurability = 5200;
       	public float gobberNetherMiningSpeed = 12.0F;
       	public float gobberNetherAttackDamage = 9.0F;
		public float gobberNetherAxeAttackDamage = 10.0F;
		public float gobberNetherSwordAttackSpeed = 2.2F;
       	public int gobberNetherEnchantability = 25;

      	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber End Tool Material Stats"
    			+"\n******************************")
      	public boolean unbreakableEndTools = true;
    	public int gobberEndDurability = 8000;
       	public float gobberEndMiningSpeed = 14.0F;
       	public float gobberEndAttackDamage = 9.0F;
		public float gobberEndAxeAttackDamage = 10.0F;
		public float gobberEndSwordAttackSpeed = 2.4F;
       	public int gobberEndEnchantability = 30;

		@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Armor Material Stats"
    			+"\n******************************")
    	public int gobberDurabilityMultiplier = 71;
      	public int gobberArmorEnchantability = 25;
      	public float gobberToughness = 3.0F;
      	public float gobberKnockbackResistance = 0.0F;
      	public int gobberHeadProtection = 6;
      	public int gobberChestProtection = 11;
      	public int gobberLeggingsProtection = 9;
      	public int gobberBootsProtection = 6;
       	
       	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber Nether Armor Material Stats"
    			+"\n******************************")
       	public int gobberNetherDurabilityMultiplier = 83;
      	public int gobberNetherArmorEnchantability = 30;
      	public float gobberNetherToughness = 3.25F;
      	public float gobberNetherKnockbackResistance = 0.1F;
		public int netherHeadProtection = 7;
		public int netherChestProtection = 12;
		public int netherLeggingsProtection = 10;
		public int netherBootsProtection = 7;
       	
       	@Comment("\n"
    			+"\n"
    			+"\n"
    			+"******************************"
    			+"\nGobber End Armor Material Stats"
    			+"\n******************************")
       	public int gobberEndDurabilityMultiplier = 100;
      	public int gobberEndArmorEnchantability = 30;
      	public float gobberEndToughness = 3.5F;
      	public float gobberEndKnockbackResistance = 0.2F;
		public int endHeadProtection = 8;
		public int endChestProtection = 13;
		public int endLeggingsProtection = 11;
		public int endBootsProtection = 8;

		@Comment("\n"
				+"\n"
				+"\n"
				+"******************************"
				+"\nDragon Armor Material Stats"
				+"\n******************************")
		public int gobberDragonDurabilityMultiplier = 100;
		public int gobberDragonArmorEnchantability = 30;
		public float gobberDragonToughness = 3.75F;
		public float gobberDragonKnockbackResistance = 0.3F;
		public int dragonHeadProtection = 8;
		public int dragonChestProtecction = 13;
		public int dragonLeggingsProtection = 11;
		public int dragonBootsProtection = 8;
		public boolean enableaArmorCap = true;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nGobber Armor Features"
				+"\n***********************")
		public boolean enableGobberBreathing = false;
		public boolean enableGobberHealthPerks = false;
		public int healingPointsGobberArmor = 2;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nNether Armor Features"
				+"\n***********************")
		public boolean enableNetherFirePerk = false;
		public boolean enableNetherHealthPerks = false;
		public int healingPointsNetherArmor = 2;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nEnd Armor Features"
				+"\n***********************")
		public boolean unbreakableEndArmor = true;
		public boolean enableEndBreathing = false;
		public boolean enableEndFirePerk = false;
		public boolean enableEndHealthPerks = false;
		public int healingPointsEndArmor = 3;
		public boolean enableEndNoFallDamage = true;
		public boolean enableGlidingEndArmor = true;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nDragon Armor Features"
				+"\n***********************")
		public boolean unbreakableDragonArmor = true;
		public boolean enableDragonBreathing = true;
		public boolean enableDragonFirePerk = true;
		public boolean enableDragonHealthPerks = false;
		public int healingPointsDragonArmor = 4;
		public boolean enableDragonCuring  =  true;
		public boolean enableDragonNoFallDamage = true;
		public boolean enableDragonFlying = true;
		public boolean enableGlidingDragonArmor = true;


		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nLoot Chests"
 	    		+"\n***********************")
		public boolean lootEnable = true;
		public float lootChance = .04F;
		
		@Comment("\n"
				+"\n"
				+ "***********************"
 	    		+"\nSpecial Item"
				+"\n"
				+ "\nItem provides a one-time random teleport"
	            +"\nand is consumed after successful teleport."
	            +"\nMin/Max range from world spawn to search"
				+"\nor from the player's current position"
		    	+"\nItem does NOT have a standard recipe"
		    	+ "\n***********************")
		public int specialItemMin = 20000;
		public int specialItemMax = 75000;
		public boolean specialItemUsePlayerPos = false;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nFuel (Foo) Settings"
				+"\n"
				+ "\n***********************")
		public int fooBurntime = 12000;
		public int fooNetherBurntime = 24000;
		public int fooEndBurntime= 32000;

		@Comment("\n"
				+"\n"
				+ "***********************"
				+"\nGobber Food Settings"

				+ "\n***********************")
		public int gooHunger = 7;
		public float gooSaturation = 0.7F;
		public int gooeyAppleHunger = 8;
		public float gooeyAppleSaturation = 0.8F;
		public int gooeyBreadHunger = 8;
		public float gooeyBreadSaturation = 0.8F;
		public int gooeyBeefHunger = 9;
		public float gooeyBeefSaturation = 0.9F;
		public int gooeyBeefStewHunger = 10;
		public float gooeyBeefStewSaturation = 1.0F;
		public int gooHungerNether = 8;
		public float gooSaturationNether = 0.8F;
		public int gooeyAppleHungerNether = 8;
		public float gooeyAppleSaturationNether = 0.8F;
		public int gooeyBreadHungerNether = 9;
		public float gooeyBreadSaturationNether = 0.9F;
		public int gooeyBeefHungerNether = 10;
		public float gooeyBeefSaturationNether = 1.0F;
		public int gooeyBeefStewHungerNether = 12;
		public float gooeyBeefStewSaturationNether = 1.5F;
    }
}
