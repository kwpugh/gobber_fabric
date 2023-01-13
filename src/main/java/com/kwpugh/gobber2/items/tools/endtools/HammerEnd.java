package com.kwpugh.gobber2.items.tools.endtools;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.ObsidianBreaking;
import com.kwpugh.pugh_tools.Tools.AreaUtil;
import com.kwpugh.pugh_tools.Tools.Hammer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class HammerEnd extends Hammer
{
    public HammerEnd(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings)
    {
        super(material, attackDamage, attackSpeed, Gobber2.CONFIG.GENERAL.enableFullDamage, settings);
    }

    int radius = 1;
    String radiusText = "3x3";
    boolean obsidianFlag;
    boolean enable5x5 = Gobber2.CONFIG.GENERAL.enableEndHammer5x5;
    static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;

    // Used to add tag when pulled from creative tab
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        NbtCompound tag = stack.getNbt();

        if(tag != null)
        {
            if(!tag.contains("radius"))
            {
                if(unbreakable)
                {
                    stack.getOrCreateNbt().putBoolean("Unbreakable", true);
                }

                stack.getOrCreateNbt().putInt("radius", 1);
                stack.getOrCreateNbt().putString("radiusText", "3x3");
            }
        }
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity playerIn)
    {
        ItemStack stack = playerIn.getMainHandStack();
        NbtCompound tag = stack.getNbt();
        int materialMiningLevel = this.getMaterial().getMiningLevel();

        if(isCorrectMiningLevel(state))
        {
            if(tag != null)
            {
                radius = tag.getInt("radius");

                if(!playerIn.isSneaking() && playerIn.getMainHandStack().isSuitableFor(world.getBlockState(pos)))
                {
                    obsidianFlag = (state.getBlock() == Blocks.OBSIDIAN || state.getBlock() == Blocks.CRYING_OBSIDIAN) ? true : false;
                    AreaUtil.attemptBreakNeighbors(world, playerIn, radius, "hammer", obsidianFlag, materialMiningLevel);
                }
            }

            return true;
        }

        return false;
    }

    public boolean isCorrectMiningLevel(BlockState state)
    {
        if(Items.NETHERITE_PICKAXE.isSuitableFor(state))
        {
            return true;
        }

        return Items.NETHERITE_PICKAXE.getMiningSpeedMultiplier(null, state) > 1.0f;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);

        if(enable5x5)
        {
            if (!world.isClient && user.isSneaking())
            {
                // Get tags for change mode checking
                NbtCompound subTags = itemStack.getNbt();

                if(subTags !=null)
                {
                    radius = subTags.getInt("radius");

                    if(radius == 1)
                    {
                        radius = radius + 1;
                        radiusText = "5x5";
                    }
                    else
                    {
                        radius = radius - 1;
                        radiusText = "3x3";
                    }
                    user.sendMessage((Text.translatable("Changed to: " + radiusText)), true);

                    // Write new values back to tags
                    subTags.putInt("radius", radius);
                    subTags.putString("radiusText", radiusText);
                    itemStack.setNbt(subTags);

                    return TypedActionResult.success(itemStack);
                }
            }
        }

        return TypedActionResult.success(itemStack);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player)
    {
        stack.getOrCreateNbt().putInt("radius", 1);
        stack.getOrCreateNbt().putString("radiusText", "3x3");

        if(world.isClient) return;

        if(unbreakable)
        {
            stack.getOrCreateNbt().putBoolean("Unbreakable", true);
        }
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

    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        // Get tag values for display in tooltips
        NbtCompound tag = itemStack.getNbt();
        radiusText = tag.getString("radiusText");

        tooltip.add(Text.translatable("item.gobber2.gobber2_hammer.tip1").formatted(Formatting.GREEN));

        if(enable5x5)
        {
            tooltip.add(Text.translatable("item.gobber2.gobber2_hammer.tip3").formatted(Formatting.YELLOW));
            tooltip.add(Text.translatable("item.gobber2.gobber2_hammer.tip4").formatted(Formatting.YELLOW));
        }

        tooltip.add(Text.translatable("item.gobber2.gobber2_hammer.tip2", radiusText).formatted(Formatting.RED));
    }
}
