package com.kwpugh.gobber2.lists;

import net.minecraft.item.FoodComponent;

public class FoodList
{
    public static FoodComponent goo = (new FoodComponent.Builder()).hunger(7).saturationModifier(0.7F).build();
    public static FoodComponent gooey_apple = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.8F).build();
    public static FoodComponent gooey_bread = (new FoodComponent.Builder()).hunger(8).saturationModifier(0.8F).build();
    public static FoodComponent gooey_beef = (new FoodComponent.Builder()).hunger(9).saturationModifier(0.9F).build();
    public static FoodComponent gooey_beefstew = (new FoodComponent.Builder()).hunger(10).saturationModifier(1.0F).build();
    
    public static FoodComponent goo_nether = (new FoodComponent.Builder()).hunger(9).saturationModifier(0.9F).build();
    public static FoodComponent gooey_apple_nether = (new FoodComponent.Builder()).hunger(10).saturationModifier(1.0F).build();
    public static FoodComponent gooey_bread_nether = (new FoodComponent.Builder()).hunger(10).saturationModifier(1.0F).build();
    public static FoodComponent gooey_beef_nether = (new FoodComponent.Builder()).hunger(12).saturationModifier(1.2F).build();
    public static FoodComponent gooey_beefstew_nether = (new FoodComponent.Builder()).hunger(14).saturationModifier(1.5F).build();
}