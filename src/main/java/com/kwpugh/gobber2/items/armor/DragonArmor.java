package com.kwpugh.gobber2.items.armor;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.api.ArmorRemoveHandler;
import com.kwpugh.gobber2.api.ArmorTickable;
import com.kwpugh.gobber2.util.PlayerEquipUtil;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class DragonArmor extends ArmorItem implements ArmorRemoveHandler, ArmorTickable, FabricElytraItem
{
    boolean enableDragonBreathing = Gobber2.CONFIG.GENERAL.enableDragonBreathing;
    boolean enableDragonFirePerk = Gobber2.CONFIG.GENERAL.enableDragonFirePerk;
    boolean enableDragonHealthPerks = Gobber2.CONFIG.GENERAL.enableDragonHealthPerks;
    boolean enableDragonCuring  = Gobber2.CONFIG.GENERAL.enableDragonCuring;
    boolean enableDragonNoFallDamage = Gobber2.CONFIG.GENERAL.enableDragonNoFallDamage;
    boolean unbreakableDragonArmor = Gobber2.CONFIG.GENERAL.unbreakableDragonArmor;
    int healingPointsDragonArmor = Gobber2.CONFIG.GENERAL.healingPointsDragonArmor;
    boolean enableDragonGliding  =  Gobber2.CONFIG.GENERAL.enableGlidingDragonArmor;

    boolean enableFlying = Gobber2.CONFIG.GENERAL.enableDragonFlying;

    public DragonArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings)
    {
        super(material, slot, settings);
    }

    // Depends on PlayerEntityMixinArmor and ArmorTickable interface
    @Override
    public void tickArmor(ItemStack stack, PlayerEntity player)
    {
        World world = player.world;

        if(PlayerEquipUtil.isWearingDragonArmor(player))
        {
            if (enableDragonHealthPerks)
            {
                if (player.age % 180 == 0)
                {
                    PlayerSpecialAbilities.giveGreaterAbsorption(player);
                    PlayerSpecialAbilities.giveSaturationEffect(player);
                    PlayerSpecialAbilities.giveHealing(player, healingPointsDragonArmor);
                }
            }

            if(enableDragonCuring)
            {
                PlayerSpecialAbilities.giveCuringEffect(world, player);
            }

            if(enableDragonNoFallDamage)
            {
                player.fallDistance = 0.0F;
            }

            if(enableFlying)
            {
                player.getAbilities().allowFlying = true;
            }
        }
    }


    @Override
    // Depends on PlayerEntityMixinArmor and ArmorRemoveHandler interface
    public void onRemoved(PlayerEntity player)
    {
        if(enableFlying)
        {
            if(this.slot == EquipmentSlot.HEAD ||
                    this.slot == EquipmentSlot.CHEST ||
                    this.slot == EquipmentSlot.LEGS ||
                    this.slot == EquipmentSlot.FEET)
            {
                if(!player.isCreative() && !player.isSpectator())
                {
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                }
            }
        }

    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player)
    {
        if(unbreakableDragonArmor)
        {
            stack.getOrCreateNbt().putBoolean("Unbreakable", true);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        if(enableDragonGliding || enableDragonBreathing || enableDragonCuring || enableDragonFirePerk || enableDragonHealthPerks || enableDragonNoFallDamage)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip1").formatted(Formatting.GREEN));
        }

        if(enableDragonFirePerk)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip2").formatted(Formatting.GREEN));
        }

        if(enableDragonBreathing)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip3").formatted(Formatting.GREEN));
        }

        if(enableDragonCuring)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip4").formatted(Formatting.GREEN));
        }

        if(enableDragonHealthPerks)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip5").formatted(Formatting.GREEN));
        }

        if(enableDragonNoFallDamage)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip6").formatted(Formatting.GREEN));
        }

        if(enableFlying)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip7").formatted(Formatting.GREEN));
        }
        
        if(enableDragonGliding)
		{
			tooltip.add(Text.translatable("item.gobber2.gobber2_armor_dragon.tip8").formatted(Formatting.GREEN));
		}
    }
}
