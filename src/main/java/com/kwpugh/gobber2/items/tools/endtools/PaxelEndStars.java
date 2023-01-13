package com.kwpugh.gobber2.items.tools.endtools;

import com.google.common.collect.BiMap;
import com.google.common.collect.Sets;
import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.EnableUtil;
import com.kwpugh.gobber2.util.ObsidianBreaking;
import com.kwpugh.pugh_tools.Tools.Paxel;
import com.kwpugh.pugh_tools.mixin.AccessorShovel;
import com.kwpugh.pugh_tools.mixin.InvokerAxe;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PaxelEndStars extends Paxel
{
    private static final Set<Material> AXE_BLOCKS;
    private static final Map<Block, BlockState> PATH_STATES;

    static boolean unbreakable = Gobber2.CONFIG.GENERAL.unbreakableEndTools;

    static
    {
        AXE_BLOCKS = Sets.newHashSet(Material.WOOD, Material.NETHER_WOOD,
                Material.PLANT, Material.REPLACEABLE_PLANT,
                Material.BAMBOO, Material.GOURD);
        PATH_STATES = AccessorShovel.getPathStates();
    }

    public PaxelEndStars(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings)
    {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = player.getMainHandStack();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        // Shovel/Axe right-click functionality
        if(!EnableUtil.isEnabled(stack))
        {
            // Use invoker on standard axe to get stripped logs
            Optional<BlockState> optional = ((InvokerAxe) (AxeItem) Items.WOODEN_AXE).invokeGetStrippedState(blockState);

            Optional<BlockState> optional2 = Oxidizable.getDecreasedOxidationState(blockState);
            Optional<BlockState> optional3 = Optional.ofNullable((Block)((BiMap)HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get()).get(blockState.getBlock())).map((block) -> {
                return block.getStateWithProperties(blockState);
            });
            ItemStack itemStack = context.getStack();
            Optional<BlockState> optional4 = Optional.empty();
            if (optional.isPresent())
            {
                world.playSound(player, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                optional4 = optional;
            } else if (optional2.isPresent())
            {
                world.playSound(player, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
                optional4 = optional2;
            } else if (optional3.isPresent())
            {
                world.playSound(player, blockPos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.syncWorldEvent(player, WorldEvents.WAX_REMOVED, blockPos, 0);
                optional4 = optional3;
            }

            if (optional4.isPresent())
            {
                if (player instanceof ServerPlayerEntity)
                {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, blockPos, itemStack);
                }

                world.setBlockState(blockPos, optional4.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                if (player != null)
                {
                    itemStack.damage(1, (LivingEntity)player, (Consumer)((p) -> {
                        player.sendToolBreakStatus(context.getHand());
                    }));
                }

                return ActionResult.success(world.isClient);
            }
            else
            {
                // Path-making  logic - from ShovelItem
                if (context.getSide() == Direction.DOWN)
                {
                    return ActionResult.PASS;
                }
                else
                {
                    BlockState blockState2 = PATH_STATES.get(blockState.getBlock());
                    BlockState blockState3 = null;
                    if (blockState2 != null && world.getBlockState(blockPos.up()).isAir())
                    {
                        world.playSound(player, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        blockState3 = blockState2;
                    }
                    else if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT))
                    {
                        if (!world.isClient())
                        {
                            world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, blockPos, 0);
                        }

                        CampfireBlock.extinguish(context.getPlayer(), world, blockPos, blockState);
                        blockState3 = blockState.with(CampfireBlock.LIT, false);
                    }

                    if (blockState3 != null)
                    {
                        if (!world.isClient)
                        {
                            world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                            if (player != null)
                            {
                                context.getStack().damage(1, (LivingEntity)player, (Consumer)((p) -> {
                                    player.sendToolBreakStatus(context.getHand());
                                }));
                            }
                        }

                        return ActionResult.success(world.isClient);
                    }
                }
            }
        }

        // Torch-placing logic
        if(!world.isClient)
        {
            BlockPos torchPos;
            BlockPos pos = context.getBlockPos();
            BlockState state = context.getWorld().getBlockState(pos);

            if(context.getWorld().getBlockState(pos).getBlock() == Blocks.TORCH
                    || context.getWorld().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
            {
                return ActionResult.FAIL;
            }

            Boolean isWallTorch = false;
            switch(context.getSide())
            {
                case DOWN:
                    return ActionResult.FAIL;
                case UP:
                    torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
                    break;
                case NORTH:
                    torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
                    isWallTorch = true;
                    break;
                case SOUTH:
                    torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
                    isWallTorch = true;
                    break;
                case WEST:
                    torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
                    isWallTorch = true;
                    break;
                case EAST:
                    torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
                    isWallTorch = true;
                    break;
                default:
                    return ActionResult.FAIL;
            }

            if(context.getWorld().getBlockState(torchPos).isAir() || context.getWorld().getBlockState(torchPos).getFluidState().isStill())
            {
                if(state.isSolidBlock(world, pos))
                {
                    if(isWallTorch)
                    {
                        context.getWorld().setBlockState(torchPos, Blocks.WALL_TORCH.getDefaultState().with(HorizontalFacingBlock.FACING, context.getSide()));
                        context.getWorld().playSound(null, context.getPlayer().getBlockPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
                    }
                    else
                    {
                        context.getWorld().setBlockState(torchPos, Blocks.TORCH.getDefaultState());
                        context.getWorld().playSound(null, context.getPlayer().getBlockPos(), SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
                    }
                }

                return ActionResult.SUCCESS;
            }

            return ActionResult.FAIL;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient && user.isSneaking())
        {
            EnableUtil.changeEnabled(user, hand);
            user.sendMessage((Text.translatable("Status changed")), true);
        }

        return TypedActionResult.success(itemStack);
    }

    // Needed to override mining speed if Faster Obsidian enchant is present
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state)
    {
        Material material = state.getMaterial();

        if(ObsidianBreaking.fastAtObsidian(state, stack))
        {
            return ObsidianBreaking.fastObsidianSpeed();
        }
        else if(AXE_BLOCKS.contains(material) || material == Material.METAL || material == Material.REPAIR_STATION || material == Material.STONE)
        {
            return this.miningSpeed;
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
        tooltip.add(Text.translatable("item.gobber2.gobber2_staff_stars.tip1").formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.gobber2.sneak_right_click").formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("item.gobber2.gobber2_paxel_stars.tip1", EnableUtil.isEnabled(itemStack)).formatted(Formatting.RED));
    }
}
