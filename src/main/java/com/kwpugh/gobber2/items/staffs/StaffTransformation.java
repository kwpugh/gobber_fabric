package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import com.kwpugh.pugh_lib.api.CustomRecipeRemainder;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class StaffTransformation extends Item implements CustomRecipeRemainder
{
	public StaffTransformation(Settings properties)
	{
		super(properties);
	}
	
    @Override
    public boolean hasRecipeRemainder()
    {
        return true;
    }

    @Override
	public ItemStack getRecipeRemainder(ItemStack stackIn)
    {   	
    	ItemStack stack = stackIn.copy();
    	stack.setDamage(stack.getDamage() + 1);

    	if(stack.getDamage() >= stack.getMaxDamage())
    	{
    		stack.decrement(1);
    	}

        return stack;
    }

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		tooltip.add((new TranslatableText("item.gobber2.gobber2_staff_transformation.tip1").formatted(Formatting.GREEN)));
		tooltip.add((new TranslatableText("item.gobber2.gobber2_staff_transformation.tip2").formatted(Formatting.YELLOW)));
	}
}