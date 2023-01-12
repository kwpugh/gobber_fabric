package com.kwpugh.gobber2.blocks;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.Gobber2Config;
import com.kwpugh.gobber2.init.BlockInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.List;

public class BaseOreBlock extends OreBlock
{
    public static final Gobber2Config.Ores CONFIG = Gobber2.CONFIG.ORES;
    private static int maxLevel;
    private static int veinSize;
    private static int chunkChance;
    private static String miningLevel;
    private static String spawnDim;
    private static int blastRes;

    public BaseOreBlock(FabricBlockSettings settings)
    {
        super(settings.requiresTool());
        this.settings.requiresTool();
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience)
    {
        super.onStacksDropped(state, world, pos, stack, dropExperience);

        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0)
        {
            this.dropExperience(world, pos, CONFIG.luckyExpOrbs);
        }
    }

    @Override
    public float getBlastResistance()
    {
        return Gobber2.CONFIG.ORES.oreBlastResistance;
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options)
    {
        oreTips(stack);

        tooltip.add(Text.translatable("item.gobber2.ore_spawn.tip1", maxLevel).formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.gobber2.ore_spawn.tip2", veinSize).formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.gobber2.ore_spawn.tip3", chunkChance).formatted(Formatting.GREEN));
        tooltip.add(Text.translatable("item.gobber2.ore_mining.tip1", miningLevel).formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("item.gobber2.ore_dim.tip1", spawnDim).formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("item.gobber2.blast_res.tip1", blastRes).formatted(Formatting.YELLOW));
    }

    public static void oreTips(ItemStack stack)
    {
        miningLevel = "Iron";
        spawnDim = "Overworld";
        blastRes = Gobber2.CONFIG.ORES.oreBlastResistance;

        if(stack.isOf(BlockInit.GOBBER2_LUCKY_BLOCK.asItem()))
        {
            maxLevel = CONFIG.luckyMaxLevel;
            veinSize = CONFIG.luckyVeinSize;
            chunkChance = CONFIG.luckyPerChunk;
        }
        else if(stack.isOf(BlockInit.GOBBER2_LUCKY_BLOCK_DEEPSLATE.asItem()))
        {
            maxLevel = CONFIG.luckyDeepslateMaxLevel;
            veinSize = CONFIG.luckyDeepslateVeinSize;
            chunkChance = CONFIG.luckyDeepslatePerChunk;
        }
        else if(stack.isOf(BlockInit.GOBBER2_ORE.asItem()))
        {
            maxLevel = CONFIG.gobberMaxLevel;
            veinSize = CONFIG.gobberVeinSize;
            chunkChance = CONFIG.gobberPerChunk;
            miningLevel = "Diamond";
        }
        else if(stack.isOf(BlockInit.GOBBER2_ORE_DEEPSLATE.asItem()))
        {
            maxLevel = CONFIG.gobberDeepslateMaxLevel;
            veinSize = CONFIG.gobberDeepslateVeinSize;
            chunkChance = CONFIG.gobberDeepslatePerChunk;
            miningLevel = "Diamond";
        }
        else if(stack.isOf(BlockInit.GOBBER2_LUCKY_BLOCK_NETHER.asItem()))
        {
            maxLevel = CONFIG.luckyNetherMaxLevel;
            veinSize = CONFIG.luckyNetherVeinSize;
            chunkChance = CONFIG.luckyNetherPerChunk;
            miningLevel = "Diamond";
            spawnDim = "Nether";
        }
        else if(stack.isOf(BlockInit.GOBBER2_ORE_NETHER.asItem()))
        {
            maxLevel = CONFIG.netherGobberMaxLevel;
            veinSize = CONFIG.netherGobberVeinSize;
            chunkChance = CONFIG.netherGobberPerChunk;
            miningLevel = "Netherite";
            spawnDim = "Nether";
        }
        else if(stack.isOf(BlockInit.GOBBER2_LUCKY_BLOCK_END.asItem()))
        {
            maxLevel = CONFIG.luckyEndMaxLevel;
            veinSize = CONFIG.luckyEndVeinSize;
            chunkChance = CONFIG.luckyEndPerChunk;
            miningLevel = "Netherite";
            spawnDim = "The End";
        }
        else if(stack.isOf(BlockInit.GOBBER2_ORE_END.asItem()))
        {
            maxLevel = CONFIG.endGobberMaxLevel;
            veinSize = CONFIG.endGobberVeinSize;
            chunkChance = CONFIG.endGobberPerChunk;
            miningLevel = "Gobber";
            spawnDim = "The End";
        }
    }
}


