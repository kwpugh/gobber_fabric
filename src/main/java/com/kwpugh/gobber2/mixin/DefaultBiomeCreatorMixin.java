package com.kwpugh.gobber2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kwpugh.gobber2.world.OreGen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;

@Mixin(DefaultBiomeCreator.class)
public class DefaultBiomeCreatorMixin
{
  @Inject(method = "composeEndSpawnSettings(Lnet/minecraft/world/biome/GenerationSettings$Builder;)Lnet/minecraft/world/biome/Biome;", at = @At("HEAD"))
  
  private static void addEndOres(GenerationSettings.Builder builder, CallbackInfoReturnable<Biome> cir)
  {
    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGen.ORE_GOBBER_THEEND);
  }
}