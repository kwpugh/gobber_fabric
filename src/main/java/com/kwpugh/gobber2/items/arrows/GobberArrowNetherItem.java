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

public class GobberArrowNetherItem extends ArrowItem
{
    public GobberArrowNetherItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter)
    {
        return new GobberArrowNetherEntity(world, shooter);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        if(Gobber2.CONFIG.GENERAL.extraDamageNetherArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.extra", Gobber2.CONFIG.GENERAL.extraDamageNetherArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.piercingLevelNetherArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.piercing", Gobber2.CONFIG.GENERAL.piercingLevelNetherArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.punchLevelNetherArrow > 0)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.punch", Gobber2.CONFIG.GENERAL.punchLevelNetherArrow).formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.enableEffectsNetherArrow)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow.effects2").formatted(Formatting.GREEN));
        }

        if(Gobber2.CONFIG.GENERAL.enableNetherArrowFire)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_arrow_nether.fire").formatted(Formatting.RED));
        }
    }
}
