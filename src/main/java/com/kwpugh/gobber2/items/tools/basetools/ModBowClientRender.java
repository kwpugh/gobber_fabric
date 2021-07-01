package com.kwpugh.gobber2.items.tools.basetools;

import com.kwpugh.gobber2.init.ItemInit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

/*
 * Needed to produce client side animation of bow pulling with arrow 
 */

@Environment(EnvType.CLIENT)
public class ModBowClientRender implements ClientModInitializer 
{
  @Override
  public void onInitializeClient() 
  {
    FabricModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW, new Identifier("pull"), (stack, world, entity) -> 
    {
      if (entity == null) 
      {
        return 0.0F;
      } 
      else 
      {
        return entity.getActiveItem() != stack ? 0.0F
            : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
      }
    });
    
    FabricModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW, new Identifier("pulling"),
        (stack, world, entity) -> 
    	{
          return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
  
    
    
    FabricModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_NETHER, new Identifier("pull"), (stack, world, entity) -> 
    {
    	if (entity == null) 
    {
      return 0.0F;
    } 
    else 
    {
      return entity.getActiveItem() != stack ? 0.0F
          : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
    }
    });
  
    FabricModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_NETHER, new Identifier("pulling"),
      (stack, world, entity) -> 
   	{
        return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
    });
    
    

    FabricModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_END, new Identifier("pull"), (stack, world, entity) -> 
    {
    	if (entity == null) 
    {
      return 0.0F;
    } 
    else 
    {
      return entity.getActiveItem() != stack ? 0.0F
          : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
    }
    });
  
    FabricModelPredicateProviderRegistry.register(ItemInit.GOBBER2_BOW_END, new Identifier("pulling"),
      (stack, world, entity) -> 
   	{
        return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
    }); 
  }

}