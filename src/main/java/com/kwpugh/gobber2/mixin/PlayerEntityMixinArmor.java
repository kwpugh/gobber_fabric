package com.kwpugh.gobber2.mixin;

import com.kwpugh.gobber2.api.ArmorRemoveHandler;
import com.kwpugh.gobber2.api.ArmorTickable;
import com.kwpugh.gobber2.util.ItemUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Adapted from TechReborn - credit to the authors
 */

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixinArmor extends LivingEntity
{
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    protected PlayerEntityMixinArmor(EntityType<? extends LivingEntity> type, World world)
    {
        super(type, world);
    }

    private final DefaultedList<ItemStack> gobber_armorcache = DefaultedList.ofSize(4, ItemStack.EMPTY);

    @Inject(method = "tick", at = @At("HEAD"))
    public void gobberTickArmor(CallbackInfo ci)
    {
        int i = 0;
        for (ItemStack stack : getArmorItems())
        {
            ItemStack cachedStack = gobber_armorcache.get(i);
            if (!ItemUtils.isItemEqual(cachedStack, stack, false, false))
            {
                if (cachedStack.getItem() instanceof ArmorRemoveHandler)
                {
                    ((ArmorRemoveHandler) cachedStack.getItem()).onRemoved((PlayerEntity) (Object) this);
                }
                gobber_armorcache.set(i, stack.copy());
            }
            i++;

            if (!stack.isEmpty() && stack.getItem() instanceof ArmorTickable)
            {
                ((ArmorTickable) stack.getItem()).tickArmor(stack, (PlayerEntity) (Object) this);
            }
        }
    }
}
