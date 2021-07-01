package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class Gobber2Dimension 
{
    public static final RegistryKey<World> GOBBER_WORLD_KEY = RegistryKey.of(Registry.DIMENSION, Gobber2.MOD_DIMENSION_ID);
    public static final RegistryKey<World> GOBBER_WORLD_KEY2 = RegistryKey.of(Registry.DIMENSION, Gobber2.MOD_DIMENSION2_ID);
    public static final RegistryKey<World> GOBBER_WORLD_KEY3 = RegistryKey.of(Registry.DIMENSION, Gobber2.MOD_DIMENSION3_ID);
    public static final RegistryKey<World> GOBBER_WORLD_KEY4 = RegistryKey.of(Registry.DIMENSION, Gobber2.MOD_DIMENSION4_ID);

    public static void setupDimension()
    {
    	//TBD - refer to BumbleZone in the future for ideas
    }
}