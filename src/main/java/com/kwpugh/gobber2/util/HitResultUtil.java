package com.kwpugh.gobber2.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class HitResultUtil
{
	public static BlockHitResult getNearestPositionWithAir(World world, PlayerEntity player, int reach)
    {
        return getPosWithReachDistance(world, player, reach, false, false, true);
    }

    private static BlockHitResult getPosWithReachDistance(World world, PlayerEntity player, double distance, boolean p1, boolean p2, boolean p3)
    {
        float f = player.pitch;
        float f1 = player.yaw;
        double d0 = player.getX();
        double d1 = player.getY() + (double) player.getStandingEyeHeight();
        double d2 = player.getZ();
        Vec3d vec3 = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3d vec31 = vec3.add((double) f6 * distance, (double) f5 * distance, (double) f7 * distance);

        return world.raycast(new RaycastContext(vec3, vec31, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.ANY, player));    		
    }
}