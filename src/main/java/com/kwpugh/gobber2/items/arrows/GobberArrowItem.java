package com.kwpugh.gobber2.items.arrows;

import com.kwpugh.gobber2.Gobber2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class GobberArrowItem extends ArrowItem
{
    public GobberArrowItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter)
    {
        return new GobberArrowEntity(world, shooter);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        if(Gobber2.CONFIG.GENERAL.extraDamageGobberArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.extra", Gobber2.CONFIG.GENERAL.extraDamageGobberArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.piercingLeveGobberArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.piercing", Gobber2.CONFIG.GENERAL.piercingLeveGobberArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.punchLevelGobberArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.punch", Gobber2.CONFIG.GENERAL.punchLevelGobberArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.enableEffectsGobberArrow)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.effects").formatted(Formatting.BLUE));
        }

        if(Gobber2.CONFIG.GENERAL.enableGobberArrowExplode)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.explosive").formatted(Formatting.YELLOW));
        }

        if(Gobber2.CONFIG.GENERAL.enableGobberArrowCobweb)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.cobwebs").formatted(Formatting.YELLOW));
        }
    }
}
