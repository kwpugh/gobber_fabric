package com.kwpugh.gobber2.init;

import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;

public class FuelInit 
{
	public static void registerFuels() 
	{
		FuelRegistryImpl registry = FuelRegistryImpl.INSTANCE;

		registry.add(ItemInit.GOBBER2_FOO, 12000);
		registry.add(ItemInit.GOBBER2_FOO_NETHER, 24000);
		registry.add(ItemInit.GOBBER2_FOO_END, 32000);	
	}
}