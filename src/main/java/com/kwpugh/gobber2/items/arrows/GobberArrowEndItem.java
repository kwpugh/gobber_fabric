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

public class GobberArrowEndItem extends ArrowItem
{
    public GobberArrowEndItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter)
    {
        return new GobberArrowEndEntity(world, shooter);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        if(Gobber2.CONFIG.GENERAL.extraDamageEndArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.extra", Gobber2.CONFIG.GENERAL.extraDamageEndArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.piercingLevelEndArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.piercing", Gobber2.CONFIG.GENERAL.piercingLevelEndArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.punchLevelEndArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.punch", Gobber2.CONFIG.GENERAL.punchLevelEndArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.enableEffectsEndArrow)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.effects2").formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.enablEndArrowCloud)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow_end.effects_cloud").formatted(Formatting.PURPLE));
        }

        if(Gobber2.CONFIG.GENERAL.enableEndArrowGlassCage)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow_end.glass_cage").formatted(Formatting.YELLOW));
        }

        if(Gobber2.CONFIG.GENERAL.enableLevitationEndArrow)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow_end.levitation").formatted(Formatting.YELLOW));
        }
    }
}
