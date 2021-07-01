package com.kwpugh.gobber2.api;

import net.minecraft.entity.player.PlayerEntity;

public interface ArmorRemoveHandler
{
    // used to detect if armor piece is removed, refer to PlayerEntityMixinArmor
    void onRemoved(PlayerEntity playerEntity);
}
