package com.kwpugh.gobber2.items.tools.endtools;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.ObsidianBreaking;
import com.kwpugh.pugh_tools.Tools.Paxel;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class PaxelEnd extends Paxel
{
    static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;

    public PaxelEnd(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state)
    {
        Material material = state.getMaterial();

        if(ObsidianBreaking.fastAtObsidian(state, stack))
        {
            return ObsidianBreaking.fastObsidianSpeed();
        }

        return material != Material.METAL && material != Material.REPAIR_STATION && material != Material.STONE ? super.getMiningSpeedMultiplier(stack, state) : this.miningSpeed;
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
        tooltip.add(Text.translatable("item.gobber2.gobber2_paxel.tip1").formatted(Formatting.GREEN));
    }
}
