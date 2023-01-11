package com.kwpugh.gobber2.items.arrows;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.EntityInit;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.init.TagInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class GobberArrowEntity extends PersistentProjectileEntity
{
    private int duration = Gobber2.CONFIG.GENERAL.effectDurationGobberArrow;
    private AreaEffectCloudEntity cloud;

    public GobberArrowEntity(EntityType<? extends GobberArrowEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public GobberArrowEntity(World world, LivingEntity owner)
    {
        super(EntityInit.GOBBER2_ARROW, owner, world);
    }

    public GobberArrowEntity(World world, double x, double y, double z)
    {
        super(EntityInit.GOBBER2_ARROW, x, y, z, world);
    }

    public void tick()
    {
        super.tick();

        if (this.world.isClient && !this.inGround)
        {
            this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    protected ItemStack asItemStack()
    {
        return new ItemStack(ItemInit.GOBBER2_ARROW);
    }

    protected void onHit(LivingEntity target)
    {
        super.onHit(target);

        this.setPierceLevel((byte) (this.getPierceLevel() + Gobber2.CONFIG.GENERAL.piercingLeveGobberArrow));
        this.setPunch(this.getPunch() + Gobber2.CONFIG.GENERAL.punchLevelGobberArrow);
        target.damage(DamageSource.GENERIC, Gobber2.CONFIG.GENERAL.extraDamageGobberArrow);

        if(Gobber2.CONFIG.GENERAL.enableEffectsGobberArrow)
        {
            StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, this.duration, 2);

            target.addStatusEffect(slowness, this.getEffectCause());
            target.addStatusEffect(glowing, this.getEffectCause());
            target.addStatusEffect(poison, this.getEffectCause());
        }

        // makes no sense to do both
        if(Gobber2.CONFIG.GENERAL.enableGobberArrowExplode)
        {
            explodeOnHit(target.getX(), target.getBodyY(1.0), target.getZ());
        }
        else if(Gobber2.CONFIG.GENERAL.enableGobberArrowCobweb)
        {
            BlockPos pos;
            BlockState state;
            Block block;

            for (int x = 1; x >= -1; x--)
            {
                for (int z = 1; z >= -1; z--)
                {
                    pos = target.getBlockPos().add(x, 0, z);
                    state = world.getBlockState(pos);
                    block = state.getBlock();

                    if(state.isIn(TagInit.AIR_BLOCKS))
                    {
                        world.setBlockState(pos, Blocks.COBWEB.getDefaultState());
                    }
                }
            }
        }
    }

    private void explodeOnHit(double x, double y, double z)
    {
        boolean griefingAllowed = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
        this.world.createExplosion(this, x, y, z, Gobber2.CONFIG.GENERAL.explosionFactorGobberArrow, griefingAllowed ? Explosion.DestructionType.BREAK : Explosion.DestructionType.NONE);
        this.discard();
    }

    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Duration"))
        {
            this.duration = nbt.getInt("Duration");
        }
    }

    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Duration", this.duration);
    }
}
