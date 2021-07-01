package com.kwpugh.gobber2.items.tools.areatools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.kwpugh.gobber2.Gobber2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class ModTreeAxe extends AxeItem
{  
	public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, 
			Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, 
			Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, 
			Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, 
			Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, 
			Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, 
			Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, 
			Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, 
			Blocks.ACACIA_PRESSURE_PLATE, Blocks.WARPED_STEM, Blocks.CRIMSON_STEM, Blocks.NETHER_WART_BLOCK, Blocks.WARPED_WART_BLOCK);
    public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.WOOD, Material.GOURD, Material.CACTUS);
    public static final Random random = new Random();
    static List<BlockPos> breakList = new ArrayList<BlockPos>();
    BlockPos breakPos;
    
	public ModTreeAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) 
	{
		super(material, attackDamage, attackSpeed, settings);
	}
	
	static int delayTick = Gobber2.CONFIG.GENERAL.treeAxeDelayTick;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
	{
		if (!world.isClient)
		{
			if(entity instanceof PlayerEntity)
			{				
				if(!breakList.isEmpty())
				{
					if(((PlayerEntity) entity).age % delayTick == 0)
					{
						//((PlayerEntity) entity).sendMessage((new TranslatableText("Chopping logs...")), true);  						
						breakPos = breakList.get(0);
						world.breakBlock(breakPos, true);
						breakList.remove(0);
					}								
				}				
			}
		}
	}
	
	//Convenience right-click to clear out the remains of nether trees
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack mainHand = player.getStackInHand(hand);		

		if (!world.isClient)
		{
			//Scan blocks in area looking for ones to add to list
			Block block;

			for (int x = -5; x <= 5; x++)
			{
				for (int y = -2; y <= 40; y++)
				{
					for (int z = -5; z <= 5; z++)
					{
						BlockPos pos = player.getBlockPos().add(x,y, z);
						block = world.getBlockState(pos).getBlock();
						
						if (block == Blocks.NETHER_WART_BLOCK ||
								block == Blocks.WARPED_WART_BLOCK ||
								block == Blocks.SHROOMLIGHT)
						{
							breakList.add(pos);
						}
					}
				}	
			}	
		}
		
		return TypedActionResult.success(mainHand);
	}
	
    @Override
    public ActionResult useOnBlock(ItemUsageContext context)  // right-click to strip logs
    {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = (Block)STRIPPED_BLOCKS.get(blockState.getBlock());
        if (block != null) 
        {
           PlayerEntity playerEntity = context.getPlayer();
           world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
           if (!world.isClient) 
           {
              world.setBlockState(blockPos, (BlockState)block.getDefaultState().with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS)), 11);
              if (playerEntity != null) {
                  context.getStack().damage(3, (LivingEntity)playerEntity, (Consumer)((p) -> {
                     ((LivingEntity) p).sendToolBreakStatus(context.getHand());
                  }));
               }
           }

           return ActionResult.success(world.isClient);
        } 
        else 
        {
           return ActionResult.PASS;
        }
     }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) 
    {
    	if (!world.isClient && state.getHardness(world, pos) != 0.0F)
    	{
    		if (entity instanceof PlayerEntity)
    		{
    			PlayerEntity player = (PlayerEntity) entity;
    			
    			// if couldn't break the tree, resort to brute force
    			if (!attemptFellTree(world, pos, player))
    			{
    				attemptBreakNeighbors(world, pos, player, EFFECTIVE_ON, EFFECTIVE_MATERIALS, false);
    			}
    		}        
    	}

    	return super.postMine(stack, world, state, pos, entity);
     }
      
    // Traverse the connected leaves and blocks and break them
    private boolean attemptFellTree(World world, BlockPos pos, PlayerEntity player)
    {
    	if(!world.isClient)
    	{
            ArrayList<BlockPos> logs = new ArrayList<>();
            ArrayList<BlockPos> candidates = new ArrayList<>();
            candidates.add(pos);

            int leaves = 0;

            for (int i = 0; i < candidates.size(); i++)
            {
                if (logs.size() > 200) 
            	{
                	player.sendMessage((new TranslatableText("Tree too big!")), true);
            		return false;   // Tree too big?
            	}

                BlockPos candidate = candidates.get(i);
                Block block = world.getBlockState(candidate).getBlock();

                if (BlockTags.LEAVES.contains(block) || block == Blocks.NETHER_WART_BLOCK || block == Blocks.WARPED_WART_BLOCK)
                {                	
                    leaves++;
                }
                else if (logs.size() == 0 || BlockTags.LOGS.contains(block))
                {
                    logs.add(candidate);

                    for (int x = -1; x <= 1; x++)
                    {
                        for (int y = 0; y <= 1; y++)
                        { 
                            for (int z = -1; z <= 1; z++)
                            {
                                BlockPos neighbor = candidate.add(x, y, z);
                                if (candidates.contains(neighbor)) continue; 
                                candidates.add(neighbor);
                            }
                        }
                    }
                }
            }

            if (logs.size() == 0) return false; 

            if (leaves >= logs.size()/6.0)
            {
                for(int i = 0; i < logs.size(); i++)
                {
                	BlockPos log = logs.get(i);
                	attemptBreak(world, log, player, EFFECTIVE_ON, EFFECTIVE_MATERIALS);
                }
                
                return true;
            }            
    	}
    	
    	return false;

    }

    public static void attemptBreakNeighbors(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials, boolean checkHarvestLevel)
    {
    	if(world.isClient) return;
  
        world.setBlockState(pos, Blocks.GLASS.getDefaultState());
        BlockHitResult blockHitResult = calcRay(world, player, RaycastContext.FluidHandling.ANY);      
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        if (blockHitResult.getType() == HitResult.Type.BLOCK)
        {
        	BlockHitResult blockTrace = (BlockHitResult) blockHitResult;
                       
            Direction face = blockTrace.getSide();

            for (int a = -1; a <= 1; a++)
            {
                for (int b = -1; b <= 1; b++)
                {
                    if (a == 0 && b == 0) continue;

                    BlockPos target = null;

                    if (face == Direction.UP    || face == Direction.DOWN)  target = pos.add(a, 0, b);
                    if (face == Direction.NORTH || face == Direction.SOUTH) target = pos.add(a, b, 0);
                    if (face == Direction.EAST  || face == Direction.WEST)  target = pos.add(0, a, b);

                    attemptBreak(world, target, player, effectiveOn, effectiveMaterials);
                }
            }
        }
    }
    
    public static BlockHitResult calcRay(World worldIn, PlayerEntity player, RaycastContext.FluidHandling fluidMode)
    {
        float f = player.pitch;
        float f1 = player.yaw;
        Vec3d vec3d = player.getCameraPosVec(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 3.0D;
        Vec3d vec3d1 = vec3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return worldIn.raycast(new RaycastContext(vec3d, vec3d1, RaycastContext.ShapeType.OUTLINE, fluidMode, player));
    }

    // Called to add blocks to the delayed breakList
    public static void attemptBreak(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials)
    {
    	BlockState state = world.getBlockState(pos);  
        boolean isEffective = effectiveOn.contains(state.getBlock()) || effectiveMaterials.contains(state.getMaterial());
                
        if (player.isUsingEffectiveTool(state) && isEffective)
        {     
            breakList.add(pos);
        }
    }
    
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{		
		tooltip.add(new TranslatableText("item.gobber2.gobber2_tree_axe.tip1").formatted(Formatting.GREEN));	
	}
} 