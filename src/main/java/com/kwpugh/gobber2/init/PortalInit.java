package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;

import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class PortalInit
{
	static boolean mining = Gobber2.CONFIG.GENERAL.enableMining;
	static boolean hunting = Gobber2.CONFIG.GENERAL.enableHunting;
	static boolean caving = Gobber2.CONFIG.GENERAL.enableCaving;
	static boolean nethering = Gobber2.CONFIG.GENERAL.enableNethering;

    public static void registerPortal()   
    {
    	//  50, 133, 168 = light blue portal color
    	//  13, 130, 21 = green portal
		//  28, 27, 31 = black portal
		//  235, 52, 55 = red portal
    	
    	if(mining)
    	{
    		CustomPortalApiRegistry.addPortal(Blocks.COBBLESTONE, new Identifier(Gobber2.MOD_ID, "mining"), 50, 133, 168);
    	}
    	
    	if(hunting)
    	{
    		CustomPortalApiRegistry.addPortal(Blocks.OAK_LOG, new Identifier(Gobber2.MOD_ID, "hunting"), 13, 130, 21);
    	}

		if(caving)
		{
			CustomPortalApiRegistry.addPortal(Blocks.DIORITE, new Identifier(Gobber2.MOD_ID, "caving"), 28, 27, 31);
		}

		if(nethering)
		{
			CustomPortalApiRegistry.addPortal(Blocks.BASALT, new Identifier(Gobber2.MOD_ID, "nethering"), 235, 52, 55);
		}
    }	
}
