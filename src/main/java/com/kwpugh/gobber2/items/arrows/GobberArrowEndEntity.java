package com.kwpugh.gobber2.items.arrows;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.BlockInit;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GobberArrowEndEntity extends PersistentProjectileEntity
{
    private int duration = Gobber2.CONFIG.GENERAL.effectDurationEndArrow;
    private AreaEffectCloudEntity cloud;

    public GobberArrowEndEntity(EntityType<? extends GobberArrowEndEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public GobberArrowEndEntity(World world, LivingEntity owner)
    {
        super(EntityInit.GOBBER2_ARROW_END, owner, world);
    }

    public GobberArrowEndEntity(World world, double x, double y, double z)
    {
        super(EntityInit.GOBBER2_ARROW_END, x, y, z, world);
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
        return new ItemStack(ItemInit.GOBBER2_ARROW_END);
    }

    protected void onHit(LivingEntity target)
    {
        super.onHit(target);

        this.setPierceLevel((byte) (this.getPierceLevel() + Gobber2.CONFIG.GENERAL.piercingLevelEndArrow));
        this.setPunch(this.getPunch() + Gobber2.CONFIG.GENERAL.punchLevelEndArrow);
        target.damage(DamageSource.GENERIC, Gobber2.CONFIG.GENERAL.extraDamageEndArrow);

        StatusEffectInstance wither = new StatusEffectInstance(StatusEffects.WITHER, this.duration, 4);
        StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.SLOWNESS, this.duration, 4);
        StatusEffectInstance glowing = new StatusEffectInstance(StatusEffects.GLOWING, this.duration, 1);
        StatusEffectInstance levitation = new StatusEffectInstance(StatusEffects.LEVITATION, this.duration, 1);

        if(Gobber2.CONFIG.GENERAL.enableEffectsEndArrow)
        {
            target.addStatusEffect(wither, this.getEffectCause());
            target.addStatusEffect(slowness, this.getEffectCause());
            target.addStatusEffect(glowing, this.getEffectCause());
        }

        // makes no sense to do all three
        if(Gobber2.CONFIG.GENERAL.enablEndArrowCloud)
        {
            this.cloud = new AreaEffectCloudEntity(target.world, target.getX(), target.getY(), target.getZ());
            this.cloud.setRadius(3.0F);
            this.cloud.setDuration(90);
            this.cloud.setParticleType(ParticleTypes.DRAGON_BREATH);
            this.cloud.addEffect(wither);
            this.cloud.addEffect(slowness);
            this.cloud.addEffect(glowing);

            world.spawnEntity(this.cloud);
        }
        else if(Gobber2.CONFIG.GENERAL.enableEndArrowGlassCage)
        {
            BlockPos pos;
            BlockState state;
            Block block;

            for(int x = 2; x >= -2; x--)
            {
                for(int y = 2; y >= 0; y--)
                {
                    for(int z = 2; z >= -2; z--)
                    {
                        pos = target.getBlockPos().add(x, y, z);
                        state = world.getBlockState(pos);
                        block = state.getBlock();

                        if(state.isIn(TagInit.AIR_BLOCKS))
                        {
                            world.setBlockState(pos, BlockInit.CLEAR_GLASS.getDefaultState());
                        }
                    }
                }
            }
        }
        else if(Gobber2.CONFIG.GENERAL.enableLevitationEndArrow)
        {
            target.addStatusEffect(levitation, this.getEffectCause());
        }
    }

    protected void onBlockHit(BlockHitResult blockHitResult)
    {
        super.onBlockHit(blockHitResult);
        Vec3d vec3d = blockHitResult.getPos().subtract(this.getX(), this.getY(), this.getZ());
        this.setVelocity(vec3d);
        Vec3d vec3d2 = vec3d.normalize().multiply(0.05000000074505806D);
        this.setPos(this.getX() - vec3d2.x, this.getY() - vec3d2.y, this.getZ() - vec3d2.z);
        this.playSound(this.getSound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        this.shake = 7;
        this.setSound(SoundEvents.ENTITY_ARROW_HIT);
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
