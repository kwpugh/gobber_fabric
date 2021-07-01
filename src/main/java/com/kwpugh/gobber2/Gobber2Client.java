package com.kwpugh.gobber2;

import com.kwpugh.gobber2.events.ToolTips;
import com.kwpugh.gobber2.init.BlockInit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class Gobber2Client implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
    	BlockRenderLayerMapImpl.INSTANCE.putBlocks(RenderLayer.getCutout(),
				BlockInit.GOBBER2_GLASS,
				BlockInit.GOBBER2_GLASS_NETHER,
				BlockInit.GOBBER2_GLASS_END,
				BlockInit.CLEAR_GLASS
		);
    	
    	ToolTips.init();
    }
}