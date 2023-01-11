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
import net.minecraft.world.World;

public class GobberArrowNetherEntity extends PersistentProjectileEntity
{
    private int duration = Gobber2.CONFIG.GENERAL.effectDurationNetherArrow;
    private AreaEffectCloudEntity cloud;

    public GobberArrowNetherEntity(EntityType<? extends GobberArrowNetherEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public GobberArrowNetherEntity(World world, LivingEntity owner)
    {
        super(EntityInit.GOBBER2_ARROW_NETHER, owner, world);
    }

    public GobberArrowNetherEntity(World world, double x, double y, double z)
    {
        super(EntityInit.GOBBER2_ARROW_NETHER, x, y, z, world);
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
        return new ItemStack(ItemInit.GOBBER2_ARROW_NETHER);
    }

    protected void onHit(LivingEntity target)
    {
        super.onHit(target);

        this.setPierceLevel((byte) (this.getPierceLevel() + Gobber2.CONFIG.GENERAL.piercingLevelNetherArrow));
        this.setPunch(this.getPunch() + Gobber2.CONFIG.GENERAL.punchLevelNetherArrow);
        target.damage(DamageSource.GENERIC, Gobber2.CONFIG.GENERAL.extraDamageNetherArrow);

        if(Gobber2.CONFIG.GENERAL.enableEffectsNetherArrow)
        {
            StatusEffectInstance wither = new StatusEffectInstance(StatusEffects.WITHER, this.duration, 4);
            StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, this.duration, 4);
            StatusEffectInstance glowing = new StatusEffectInstance(StatusEffects.GLOWING, this.duration, 1);

            target.addStatusEffect(wither, this.getEffectCause());
            target.addStatusEffect(slowness, this.getEffectCause());
            target.addStatusEffect(glowing, this.getEffectCause());
        }

        if(Gobber2.CONFIG.GENERAL.enableNetherArrowFire)
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
                        world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                    }

                    target.setOnFireFor(Gobber2.CONFIG.GENERAL.secondsEntityOnFireDuration);
                }
            }
        }
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
