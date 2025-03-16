package com.miniverse.hbm.explosion;

import java.util.*;

import com.miniverse.hbm.blocks.ModBlocks;
import com.google.common.collect.Sets;
import com.miniverse.hbm.util.Tuple.Pair;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@Deprecated
public class ExplosionNT {

    public Set<ExAttrib> attributes = new HashSet<>();

    private final Random explosionRNG = new Random();
    private final Level level;
    private final Explosion explosion;
    private final double x;
    private final double y;
    private final double z;
    private final Entity source;
    private final float radius;
    protected int resolution = 16;
    protected Map<Player, Vec3> affectedEntities = new HashMap<>();
    private final ExplosionDamageCalculator damageCalculator;
    public final Set<BlockPos> toBlow = Sets.newHashSet();

    @Deprecated public static final List<ExAttrib> nukeAttribs = Arrays.asList(
            ExAttrib.FIRE, ExAttrib.NOPARTICLE, ExAttrib.NOSOUND, ExAttrib.NODROP, ExAttrib.NOHURT
    );

    public ExplosionNT(Level level, Entity source, double x, double y, double z, float radius) {
        this.level = level;
        this.source = source;
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.damageCalculator = new ExplosionDamageCalculator();
        this.explosion = new Explosion(
                level,
                source,
                null, // DamageSource is provided differently in 1.20.1
                damageCalculator,
                x, y, z,
                radius,
                false, // Create fire - we'll handle this ourselves
                Explosion.BlockInteraction.DESTROY // We'll override behavior as needed
        );
    }

    public ExplosionNT addAttrib(ExAttrib attrib) {
        attributes.add(attrib);
        return this;
    }

    public ExplosionNT addAllAttrib(List<ExAttrib> attrib) {
        attributes.addAll(attrib);
        return this;
    }

    public ExplosionNT addAllAttrib(ExAttrib... attrib) {
        for(ExAttrib a : attrib) attributes.add(a);
        return this;
    }

    public ExplosionNT overrideResolution(int res) {
        resolution = res;
        return this;
    }

    public void explode() {
        doExplosionA();
        doExplosionB(false);
    }

    public void doExplosionA() {
        float f = this.radius;
        Set<BlockPos> set = Sets.newHashSet();

        for(int i = 0; i < this.resolution; ++i) {
            for(int j = 0; j < this.resolution; ++j) {
                for(int k = 0; k < this.resolution; ++k) {

                    if(i == 0 || i == this.resolution - 1 || j == 0 || j == this.resolution - 1 || k == 0 || k == this.resolution - 1) {

                        double d0 = (double) ((float) i / ((float) this.resolution - 1.0F) * 2.0F - 1.0F);
                        double d1 = (double) ((float) j / ((float) this.resolution - 1.0F) * 2.0F - 1.0F);
                        double d2 = (double) ((float) k / ((float) this.resolution - 1.0F) * 2.0F - 1.0F);

                        double dist = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= dist;
                        d1 /= dist;
                        d2 /= dist;

                        float remainingPower = this.radius * (0.7F + this.level.random.nextFloat() * 0.6F);
                        double currentX = this.x;
                        double currentY = this.y;
                        double currentZ = this.z;

                        for(float step = 0.3F; remainingPower > 0.0F; remainingPower -= step * 0.75F) {
                            BlockPos blockPos = new BlockPos((int)Math.floor(currentX), (int)Math.floor(currentY), (int)Math.floor(currentZ));
                            BlockState blockState = this.level.getBlockState(blockPos);
                            FluidState fluidState = this.level.getFluidState(blockPos);

                            if (!this.level.isInWorldBounds(blockPos)) {
                                break;
                            }

                            if (!blockState.isAir() || !fluidState.isEmpty()) {
                                Optional<Float> resistanceOpt = this.damageCalculator.getBlockExplosionResistance(
                                        this.explosion,
                                        this.level,
                                        blockPos,
                                        blockState,
                                        fluidState
                                );

                                float resistance = resistanceOpt.orElse(0.0F);
                                remainingPower -= (resistance + 0.3F) * step;
                            }

                            if (remainingPower > 0.0F && this.damageCalculator.shouldBlockExplode(
                                    this.explosion,
                                    this.level,
                                    blockPos,
                                    blockState,
                                    remainingPower
                            )) {
                                set.add(blockPos);
                            } else if(this.has(ExAttrib.ERRODE) && errosion.containsKey(blockState.getBlock())) {
                                set.add(blockPos);
                            }

                            currentX += d0 * (double) step;
                            currentY += d1 * (double) step;
                            currentZ += d2 * (double) step;
                        }
                    }
                }
            }
        }

        this.toBlow.addAll(set);

        if (!has(ExAttrib.NOHURT)) {
            float size = this.radius * 2.0F;
            int i = Mth.floor(this.x - (double) size - 1.0D);
            int j = Mth.floor(this.x + (double) size + 1.0D);
            int k = Mth.floor(this.y - (double) size - 1.0D);
            int l = Mth.floor(this.y + (double) size + 1.0D);
            int i1 = Mth.floor(this.z - (double) size - 1.0D);
            int j1 = Mth.floor(this.z + (double) size + 1.0D);

            List<Entity> list = this.level.getEntities(
                    this.source,
                    new AABB((double) i, (double) k, (double) i1, (double) j, (double) l, (double) j1)
            );

            net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(
                    this.level,
                    this.explosion,
                    list,
                    size
            );

            Vec3 vec3 = new Vec3(this.x, this.y, this.z);

            for(int k1 = 0; k1 < list.size(); ++k1) {
                Entity entity = list.get(k1);

                if (!entity.ignoreExplosion()) {
                    double distance = Math.sqrt(entity.distanceToSqr(vec3)) / (double) size;

                    if (distance <= 1.0D) {
                        double dx = entity.getX() - this.x;
                        double dy = (entity instanceof LivingEntity ? entity.getEyeY() : entity.getY()) - this.y;
                        double dz = entity.getZ() - this.z;
                        double magnitude = Math.sqrt(dx * dx + dy * dy + dz * dz);

                        if (magnitude != 0.0D) {
                            dx /= magnitude;
                            dy /= magnitude;
                            dz /= magnitude;

                            double exposure = this.getSeenPercent(vec3, entity);
                            double impact = (1.0D - distance) * exposure;

                            entity.hurt(getDamageSource(), (float) ((int) ((impact * impact + impact) / 2.0D * 8.0D * (double) size + 1.0D)));

                            double knockback = impact;

                            if (entity instanceof LivingEntity) {
                                knockback = ProtectionEnchantment.getExplosionKnockbackAfterDampener((LivingEntity) entity, impact);
                            }

                            entity.setDeltaMovement(entity.getDeltaMovement().add(dx * knockback, dy * knockback, dz * knockback));

                            if (entity instanceof Player) {
                                Player player = (Player) entity;
                                if (!player.isSpectator() && (!player.isCreative() || !player.getAbilities().flying)) {
                                    this.affectedEntities.put(player, new Vec3(dx * impact, dy * impact, dz * impact));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private DamageSource getDamageSource() {
        DamageSources damageSources = level.damageSources();
        return (source instanceof LivingEntity) ?
                damageSources.explosion(this.explosion.getExploder(), source) :
                damageSources.explosion(this.explosion);
    }

    public void doExplosionB(boolean spawnParticles) {
        if (!has(ExAttrib.NOSOUND)) {
            this.level.playSound(
                    null,
                    this.x, this.y, this.z,
                    SoundEvents.GENERIC_EXPLODE,
                    SoundSource.BLOCKS,
                    4.0F,
                    (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F
            );
        }

        boolean isHugeExplosion = this.radius >= 2.0F;

        if (!has(ExAttrib.NOPARTICLE)) {
            if (isHugeExplosion && spawnParticles) {
                ((ServerLevel)this.level).sendParticles(
                        ParticleTypes.EXPLOSION_EMITTER,
                        this.x, this.y, this.z,
                        1, 0.0D, 0.0D, 0.0D, 0.0D
                );
            } else {
                ((ServerLevel)this.level).sendParticles(
                        ParticleTypes.EXPLOSION,
                        this.x, this.y, this.z,
                        1, 0.0D, 0.0D, 0.0D, 0.0D
                );
            }
        }

        if (spawnParticles) {
            for (BlockPos blockPos : this.toBlow) {
                BlockState blockState = this.level.getBlockState(blockPos);
                Block block = blockState.getBlock();

                if (!has(ExAttrib.NOPARTICLE)) {
                    double d0 = (double)((float)blockPos.getX() + this.level.random.nextFloat());
                    double d1 = (double)((float)blockPos.getY() + this.level.random.nextFloat());
                    double d2 = (double)((float)blockPos.getZ() + this.level.random.nextFloat());
                    double d3 = d0 - this.x;
                    double d4 = d1 - this.y;
                    double d5 = d2 - this.z;
                    double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5D / (d6 / (double)this.radius + 0.1D);
                    d7 *= (double)(this.level.random.nextFloat() * this.level.random.nextFloat() + 0.3F);
                    d3 *= d7;
                    d4 *= d7;
                    d5 *= d7;

                    ((ServerLevel)this.level).sendParticles(
                            ParticleTypes.POOF,
                            (d0 + this.x) / 2.0D,
                            (d1 + this.y) / 2.0D,
                            (d2 + this.z) / 2.0D,
                            1, d3, d4, d5, 0.0D
                    );

                    ((ServerLevel)this.level).sendParticles(
                            ParticleTypes.SMOKE,
                            d0, d1, d2,
                            1, d3, d4, d5, 0.0D
                    );
                }

                if (!blockState.isAir()) {
                    boolean doesErrode = false;
                    Block errodesInto = Blocks.AIR;

                    if (this.has(ExAttrib.ERRODE) && this.explosionRNG.nextFloat() < 0.6F) {
                        if (errosion.containsKey(block)) {
                            doesErrode = true;
                            errodesInto = errosion.get(block);
                        }
                    }

                    if (this.level instanceof ServerLevel) {
                        if (block.dropFromExplosion(this.explosion) && !has(ExAttrib.NODROP) && !doesErrode) {
                            float chance = 1.0F;

                            if (!has(ExAttrib.ALLDROP)) {
                                chance = 1.0F / this.radius;
                            }

                            block.dropFromExplosion(
                                    this.explosion
                            );
                        }

                        block.onBlockExploded(blockState, this.level, blockPos, this.explosion);

                        if (blockState.isSolid()) {
                            if (doesErrode) {
                                this.level.setBlock(blockPos, errodesInto.defaultBlockState(), 3);
                            }

                            if (has(ExAttrib.DIGAMMA)) {
                                this.level.setBlock(blockPos, ModBlocks.ASH_DIGAMMA.get().defaultBlockState(), 3);

                                if (this.explosionRNG.nextInt(5) == 0 && this.level.getBlockState(blockPos.above()).isAir()) {
                                    this.level.setBlock(blockPos.above(), ModBlocks.FIRE_DIGAMMA.get().defaultBlockState(), 3);
                                }
                            } else if (has(ExAttrib.DIGAMMA_CIRCUIT)) {
                                if (blockPos.getX() % 3 == 0 && blockPos.getZ() % 3 == 0) {
                                    this.level.setBlock(blockPos, ModBlocks.PRIBRIS_DIGAMMA.get().defaultBlockState(), 3);
                                } else if ((blockPos.getX() % 3 == 0 || blockPos.getZ() % 3 == 0) && this.explosionRNG.nextBoolean()) {
                                    this.level.setBlock(blockPos, ModBlocks.PRIBRIS_DIGAMMA.get().defaultBlockState(), 3);
                                } else {
                                    this.level.setBlock(blockPos, ModBlocks.ASH_DIGAMMA.get().defaultBlockState(), 3);

                                    if (this.explosionRNG.nextInt(5) == 0 && this.level.getBlockState(blockPos.above()).isAir()) {
                                        this.level.setBlock(blockPos.above(), ModBlocks.FIRE_DIGAMMA.get().defaultBlockState(), 3);
                                    }
                                }
                            } else if (has(ExAttrib.LAVA_V)) {
                                this.level.setBlock(blockPos, ModBlocks.VOLCANIC_LAVA_BLOCK.get().defaultBlockState(), 3);
                            } else if (has(ExAttrib.LAVA_R)) {
                                this.level.setBlock(blockPos, ModBlocks.RAD_LAVA_BLOCK.get().defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }

        if (has(ExAttrib.FIRE) || has(ExAttrib.BALEFIRE) || has(ExAttrib.LAVA)) {
            for (BlockPos blockPos : this.toBlow) {
                if (this.level.getBlockState(blockPos).isAir() &&
                        this.level.getBlockState(blockPos.below()).isSolidRender(this.level, blockPos.below())) {

                    boolean shouldReplace = true;

                    if (!has(ExAttrib.ALLMOD) && !has(ExAttrib.DIGAMMA)) {
                        shouldReplace = this.explosionRNG.nextInt(3) == 0;
                    }

                    if (shouldReplace) {
                        if (has(ExAttrib.FIRE)) {
                            this.level.setBlock(blockPos, Blocks.FIRE.defaultBlockState(), 3);
                        } else if (has(ExAttrib.BALEFIRE)) {
                            this.level.setBlock(blockPos, ModBlocks.BALEFIRE.get().defaultBlockState(), 3);
                        } else if (has(ExAttrib.LAVA)) {
                            this.level.setBlock(blockPos, Blocks.LAVA.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    public Map<Player, Vec3> getHitPlayers() {
        return this.affectedEntities;
    }

    public LivingEntity getSourceMob() {
        if (this.source == null) {
            return null;
        } else if (this.source instanceof PrimedTnt) {
            return ((PrimedTnt)this.source).getOwner();
        } else {
            return this.source instanceof LivingEntity ? (LivingEntity)this.source : null;
        }
    }

    // Helper method to calculate explosion exposure
    private double getSeenPercent(Vec3 source, Entity entity) {
        AABB aabb = entity.getBoundingBox();
        double d0 = 1.0D / ((aabb.maxX - aabb.minX) * 2.0D + 1.0D);
        double d1 = 1.0D / ((aabb.maxY - aabb.minY) * 2.0D + 1.0D);
        double d2 = 1.0D / ((aabb.maxZ - aabb.minZ) * 2.0D + 1.0D);
        double d3 = (1.0D - Math.floor(1.0D / d0) * d0) / 2.0D;
        double d4 = (1.0D - Math.floor(1.0D / d2) * d2) / 2.0D;

        if (!(d0 < 0.0D) && !(d1 < 0.0D) && !(d2 < 0.0D)) {
            int i = 0;
            int j = 0;

            for (double d5 = 0.0D; d5 <= 1.0D; d5 += d0) {
                for (double d6 = 0.0D; d6 <= 1.0D; d6 += d1) {
                    for (double d7 = 0.0D; d7 <= 1.0D; d7 += d2) {
                        double d8 = Mth.lerp(d5, aabb.minX, aabb.maxX);
                        double d9 = Mth.lerp(d6, aabb.minY, aabb.maxY);
                        double d10 = Mth.lerp(d7, aabb.minZ, aabb.maxZ);
                        Vec3 vec3 = new Vec3(d8 + d3, d9, d10 + d4);

                        if (this.level.clip(new ClipContext(
                                vec3,
                                source,
                                ClipContext.Block.COLLIDER,
                                ClipContext.Fluid.NONE,
                                entity
                        )).getType() == net.minecraft.world.phys.HitResult.Type.MISS) {
                            ++i;
                        }

                        ++j;
                    }
                }
            }

            return (double)i / (double)j;
        } else {
            return 0.0D;
        }
    }

    // unconventional name, sure, but it's short
    public boolean has(ExAttrib attrib) {
        return this.attributes.contains(attrib);
    }

    //this solution is a bit hacky but in the end easier to work with
    public static enum ExAttrib {
        FIRE,       //classic vanilla fire explosion
        BALEFIRE,   //same with but with balefire
        DIGAMMA,
        DIGAMMA_CIRCUIT,
        LAVA,       //again the same thing but lava
        LAVA_V,     //again the same thing but volcanic lava
        LAVA_R,     //again the same thing but radioactive lava
        ERRODE,     //will turn select blocks into gravel or sand
        ALLMOD,     //block placer attributes like fire are applied for all destroyed blocks
        ALLDROP,    //miner TNT!
        NODROP,     //the opposite
        NOPARTICLE,
        NOSOUND,
        NOHURT
    }

    public static final HashMap<Block, Block> errosion = new HashMap<>();

    static {
        // These blocks will need to be updated to reference your mod's blocks in 1.20.1
        // errosion.put(ModBlocks.CONCRETE.get(), Blocks.GRAVEL);
        // errosion.put(ModBlocks.CONCRETE_SMOOTH.get(), Blocks.GRAVEL);
        // errosion.put(ModBlocks.BRICK_CONCRETE.get(), ModBlocks.BRICK_CONCRETE_BROKEN.get());
        // errosion.put(ModBlocks.BRICK_CONCRETE_BROKEN.get(), Blocks.GRAVEL);
    }
}