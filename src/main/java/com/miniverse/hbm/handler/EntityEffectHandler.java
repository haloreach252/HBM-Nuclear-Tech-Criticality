package com.miniverse.hbm.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.capabilities.CustomCapabilities;
import com.miniverse.hbm.config.WorldConfig;
import com.miniverse.hbm.interfaces.Unported;
import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toclient.AuxParticlePacketNT;
import com.miniverse.hbm.packet.toclient.ExtPropPacket;
import com.miniverse.hbm.util.ContaminationUtil;
import com.miniverse.hbm.util.ContaminationUtil.HazardType;
import com.miniverse.hbm.util.ContaminationUtil.ContaminationType;
import com.miniverse.hbm.capabilities.HbmLivingProps;
import com.miniverse.hbm.capabilities.HbmPlayerProps;
import com.miniverse.hbm.config.GeneralConfig;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

@Unported("Theres a lot going on here")
public class EntityEffectHandler {

    public static void onUpdate(LivingEntity entity) {

        if(entity.tickCount % 20 == 0) {
            entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> {
                props.setRadBuf(props.getRadEnv());
                props.setRadEnv(0);
            });
        }

        if(entity instanceof Player && entity == HBMNuclearTechCriticality.proxy.me()) {
            Player player = HBMNuclearTechCriticality.proxy.me();
            if(player != null) {
                BiomeGenBase biome = player.worldObj.getBiomeGenForCoords((int) Math.floor(player.getX()), (int) Math.floor(player.getZ()));
                if(biome == BiomeGenCraterBase.craterBiome || biome == BiomeGenCraterBase.craterInnerBiome) {
                    Random rand = player.getRNG();
                    for(int i = 0; i < 3; i++) player.worldObj.spawnParticle("townaura", player.getX() + rand.nextGaussian() * 3, player.getY() + rand.nextGaussian() * 2, player.getZ() + rand.nextGaussian() * 3, 0, 0, 0);
                }
            }
        }

        if(entity instanceof ServerPlayer serverPlayer) {
            LazyOptional<HbmLivingProps> livingPropsOpt = serverPlayer.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY);
            LazyOptional<HbmPlayerProps> playerPropsOpt = serverPlayer.getCapability(CustomCapabilities.HBM_PLAYER_CAPABILITY);

            livingPropsOpt.ifPresent(props -> playerPropsOpt.ifPresent(pprps -> {
                if(pprps.shield < pprps.getEffectiveMaxShield() && entity.tickCount > pprps.lastDamage + 60) {
                    int tsd = entity.tickCount - (pprps.lastDamage + 60);
                    pprps.shield += Math.min(pprps.getEffectiveMaxShield() - pprps.shield, 0.005F * tsd);
                }

                if(pprps.shield > pprps.getEffectiveMaxShield())
                    pprps.shield = pprps.getEffectiveMaxShield();

                PacketThreading.createSendToThreadedPacket(new ExtPropPacket(props, pprps), (ServerPlayer) entity);
            }));
        }

        if(!entity.level().isClientSide) {
            int timer = HbmLivingProps.getTimer(entity);
            if(timer > 0) {
                HbmLivingProps.setTimer(entity, timer - 1);

                if(timer == 1) {
                    ExplosionNukeSmall.explode(entity.worldObj, entity.posX, entity.posY, entity.posZ, ExplosionNukeSmall.PARAMS_MEDIUM);
                }
            }
            //only sets players on fire so mod compatibility doesnt die
            if((GeneralConfig.enable528 && GeneralConfig.enable528NetherBurn) && entity instanceof Player && !entity.isImmuneToFire() && entity.worldObj.provider.isHellWorld) {
                entity.setFire(5);
            }

            BiomeGenBase biome = entity.worldObj.getBiomeGenForCoords((int) Math.floor(entity.posX), (int) Math.floor(entity.posZ));
            double radiation = 0;
            if(biome == BiomeGenCraterBase.craterOuterBiome) radiation = WorldConfig.craterBiomeOuterRad.get();
            if(biome == BiomeGenCraterBase.craterBiome) radiation = WorldConfig.craterBiomeRad.get();
            if(biome == BiomeGenCraterBase.craterInnerBiome) radiation = WorldConfig.craterBiomeInnerRad.get();

            if(entity.isWet()) radiation *= WorldConfig.craterBiomeWaterMult.get();

            if(radiation > 0) {
                ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, (float)radiation / 20F);
            }
        }

        handleContamination(entity);
        handleContagion(entity);
        handleRadiation(entity);
        handleDigamma(entity);
        handleLungDisease(entity);
        handleOil(entity);
        handlePollution(entity);
        handleTemperature(entity);

        handleDashing(entity);
        handlePlinking(entity);

        if(entity instanceof Player) handleFauxLadder((Player) entity);
    }

    private static void handleFauxLadder(Player player) {

        HbmPlayerProps props = HbmPlayerProps.getData(player);

        if(props.isOnLadder) {
            float f5 = 0.15F;

            if(player.motionX < (double) (-f5)) {
                player.motionX = (double) (-f5);
            }

            if(player.motionX > (double) f5) {
                player.motionX = (double) f5;
            }

            if(player.motionZ < (double) (-f5)) {
                player.motionZ = (double) (-f5);
            }

            if(player.motionZ > (double) f5) {
                player.motionZ = (double) f5;
            }

            player.fallDistance = 0.0F;

            if(player.motionY < -0.15D) {
                player.motionY = -0.15D;
            }

            if(player.isSneaking() && player.motionY < 0.0D) {
                player.motionY = 0.0D;
            }

            if(player.isCollidedHorizontally) {
                player.motionY = 0.2D;
            }

            props.isOnLadder = false;

            if(!player.worldObj.isRemote) ArmorUtil.resetFlightTime(player);
        }
    }

    private static void handleContamination(LivingEntity entity) {

        if(entity.worldObj.isRemote)
            return;

        List<ContaminationEffect> contamination = HbmLivingProps.getCont(entity);
        List<ContaminationEffect> rem = new ArrayList();

        for(ContaminationEffect con : contamination) {
            ContaminationUtil.contaminate(entity, HazardType.RADIATION, con.ignoreArmor ? ContaminationType.RAD_BYPASS : ContaminationType.CREATIVE, con.getRad());

            con.time--;

            if(con.time <= 0)
                rem.add(con);
        }

        contamination.removeAll(rem);
    }

    private static void handleRadiation(LivingEntity entity) {

        Level world = entity.worldObj;

        if(!world.isRemote) {

            if(ContaminationUtil.isRadImmune(entity))
                return;

            int ix = (int)MathHelper.floor_double(entity.posX);
            int iy = (int)MathHelper.floor_double(entity.posY);
            int iz = (int)MathHelper.floor_double(entity.posZ);

            float rad = ChunkRadiationManager.proxy.getRadiation(world, ix, iy, iz);

            if(world.provider.isHellWorld && RadiationConfig.hellRad > 0 && rad < RadiationConfig.hellRad)
                rad = (float) RadiationConfig.hellRad;

            if(rad > 0) {
                ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, rad / 20F);
            }

            if(entity.worldObj.isRaining() && BombConfig.cont > 0 && AuxSavedData.getThunder(entity.worldObj) > 0 && entity.worldObj.canBlockSeeTheSky(ix, iy, iz)) {
                ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, BombConfig.cont * 0.0005F);
            }

            if(entity instanceof Player && ((Player)entity).capabilities.isCreativeMode)
                return;

            Random rand = new Random(entity.getEntityId());

            int r600 = rand.nextInt(600);
            int r1200 = rand.nextInt(1200);

            if(HbmLivingProps.getRadiation(entity) > 600) {

                if((world.getTotalWorldTime() + r600) % 600 < 20 && canVomit(entity)) {
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setString("type", "vomit");
                    nbt.setString("mode", "blood");
                    nbt.setInteger("count", 25);
                    nbt.setInteger("entity", entity.getEntityId());
                    PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));

                    if((world.getTotalWorldTime() + r600) % 600 == 1) {
                        world.playSoundEffect(ix, iy, iz, "hbm:player.vomit", 1.0F, 1.0F);
                        entity.addPotionEffect(new PotionEffect(Potion.hunger.id, 60, 19));
                    }
                }

            } else if(HbmLivingProps.getRadiation(entity) > 200 && (world.getTotalWorldTime() + r1200) % 1200 < 20 && canVomit(entity)) {

                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setString("type", "vomit");
                nbt.setString("mode", "normal");
                nbt.setInteger("count", 15);
                nbt.setInteger("entity", entity.getEntityId());
                PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));

                if((world.getTotalWorldTime() + r1200) % 1200 == 1) {
                    world.playSoundEffect(ix, iy, iz, "hbm:player.vomit", 1.0F, 1.0F);
                    entity.addPotionEffect(new PotionEffect(Potion.hunger.id, 60, 19));
                }

            }

            if(HbmLivingProps.getRadiation(entity) > 900 && (world.getTotalWorldTime() + rand.nextInt(10)) % 10 == 0) {

                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setString("type", "sweat");
                nbt.setInteger("count", 1);
                nbt.setInteger("block", Block.getIdFromBlock(Blocks.redstone_block));
                nbt.setInteger("entity", entity.getEntityId());
                PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));

            }
        } else {
            float radiation = HbmLivingProps.getRadiation(entity);

            if(entity instanceof Player && radiation > 600) {

                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setString("type", "radiation");
                nbt.setInteger("count", radiation > 900 ? 4 : radiation > 800 ? 2 : 1);
                HBMNuclearTechCriticality.proxy.effectNT(nbt);
            }
        }
    }

    private static void handleDigamma(LivingEntity entity) {

        if(!entity.worldObj.isRemote) {

            float digamma = HbmLivingProps.getDigamma(entity);

            if(digamma < 0.01F)
                return;

            int chance = Math.max(10 - (int)(digamma), 1);

            if(chance == 1 || entity.getRNG().nextInt(chance) == 0) {

                NBTTagCompound data = new NBTTagCompound();
                data.setString("type", "sweat");
                data.setInteger("count", 1);
                data.setInteger("block", Block.getIdFromBlock(Blocks.soul_sand));
                data.setInteger("entity", entity.getEntityId());
                PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, 0, 0, 0),  new PacketDistributor.TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
            }
        }
    }

    private static void handleContagion(LivingEntity entity) {

        Level world = entity.worldObj;

        if(!entity.worldObj.isRemote) {

            Random rand = entity.getRNG();
            int minute = 60 * 20;
            int hour = 60 * minute;

            int contagion = HbmLivingProps.getContagion(entity);

            if(entity instanceof Player) {

                Player player = (Player) entity;
                int randSlot = rand.nextInt(player.inventory.mainInventory.length);
                ItemStack stack = player.inventory.getStackInSlot(randSlot);

                if(rand.nextInt(100) == 0) {
                    stack = player.inventory.armorItemInSlot(rand.nextInt(4));
                }

                //only affect unstackables (e.g. tools and armor) so that the NBT tag's stack restrictions isn't noticeable
                if(stack != null && stack.getMaxStackSize() == 1) {

                    if(contagion > 0) {

                        if(!stack.hasTagCompound())
                            stack.stackTagCompound = new NBTTagCompound();

                        stack.stackTagCompound.setBoolean("ntmContagion", true);

                    } else {

                        if(stack.hasTagCompound() && stack.stackTagCompound.getBoolean("ntmContagion")) {
                            if(!ArmorUtil.checkForHaz2(player) || !ArmorRegistry.hasProtection(player, 3, HazardClass.BACTERIA)) //liable to change to hazmat 1 at bob's pleasure
                                HbmLivingProps.setContagion(player, 3 * hour);
                        }
                    }
                }
            }

            if(contagion > 0) {
                HbmLivingProps.setContagion(entity, contagion - 1);

                //aerial transmission only happens once a second 5 minutes into the contagion
                if(contagion < (2 * hour + 55 * minute) && contagion % 20 == 0) {

                    double range = entity.isWet() ? 16D : 2D; //avoid rain, just avoid it

                    List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(range, range, range));

                    for(Entity ent : list) {

                        if(ent instanceof LivingEntity) {
                            LivingEntity living = (LivingEntity) ent;
                            if(HbmLivingProps.getContagion(living) <= 0) {
                                if(!ArmorUtil.checkForHaz2(living) || !ArmorRegistry.hasProtection(living, 3, HazardClass.BACTERIA)) //liable to change to hazmat 1 at bob's pleasure
                                    HbmLivingProps.setContagion(living, 3 * hour);
                            }
                        }

                        if(ent instanceof EntityItem) {
                            ItemStack stack = ((EntityItem)ent).getEntityItem();

                            if(!stack.hasTagCompound())
                                stack.stackTagCompound = new NBTTagCompound();

                            stack.stackTagCompound.setBoolean("ntmContagion", true);
                        }
                    }
                }

                //one hour in, add rare and subtle screen fuckery
                if(contagion < 2 * hour && rand.nextInt(1000) == 0) {
                    entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 20, 0));
                }

                //two hours in, give 'em the full blast
                if(contagion < 1 * hour && rand.nextInt(100) == 0) {
                    entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 60, 0));
                    entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 300, 4));
                }

                //T-30 minutes, take damage every 20 seconds
                if(contagion < 30 * minute && rand.nextInt(400) == 0) {
                    entity.attackEntityFrom(ModDamageSource.mku, 1F);
                }

                //T-5 minutes, take damage every 5 seconds
                if(contagion < 5 * minute && rand.nextInt(100) == 0) {
                    entity.attackEntityFrom(ModDamageSource.mku, 2F);
                }

                if(contagion < 30 * minute && (contagion + entity.getEntityId()) % 200 < 20 && canVomit(entity)) {
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setString("type", "vomit");
                    nbt.setString("mode", "blood");
                    nbt.setInteger("count", 25);
                    nbt.setInteger("entity", entity.getEntityId());
                    PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));

                    if((contagion + entity.getEntityId()) % 200 == 19)
                        world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "hbm:player.vomit", 1.0F, 1.0F);
                }

                //end of contagion, drop dead
                if(contagion == 0) {
                    entity.attackEntityFrom(ModDamageSource.mku, 1000F);
                }
            }
        }
    }

    private static void handleLungDisease(LivingEntity entity) {

        if(entity.worldObj.isRemote)
            return;

        if(entity instanceof Player && ((Player) entity).capabilities.isCreativeMode) {
            HbmLivingProps.setBlackLung(entity, 0);
            HbmLivingProps.setAsbestos(entity, 0);
            return;
        } else {

            int bl = HbmLivingProps.getBlackLung(entity);

            if(bl > 0 && bl < HbmLivingProps.maxBlacklung * 0.5)
                HbmLivingProps.setBlackLung(entity, HbmLivingProps.getBlackLung(entity) - 1);
        }

        double blacklung = Math.min(HbmLivingProps.getBlackLung(entity), HbmLivingProps.maxBlacklung);
        double asbestos = Math.min(HbmLivingProps.getAsbestos(entity), HbmLivingProps.maxAsbestos);
        double soot = PollutionHandler.getPollution(entity.worldObj, (int) Math.floor(entity.posX), (int) Math.floor(entity.posY + entity.getEyeHeight()), (int) Math.floor(entity.posZ), PollutionType.SOOT);

        if(!(entity instanceof Player)) soot = 0;

        if(ArmorRegistry.hasProtection(entity, 3, HazardClass.PARTICLE_COARSE)) soot = 0;

        boolean coughs = blacklung / HbmLivingProps.maxBlacklung > 0.25D || asbestos / HbmLivingProps.maxAsbestos > 0.25D || soot > 30;

        if(!coughs)
            return;

        boolean coughsCoal = blacklung / HbmLivingProps.maxBlacklung > 0.5D;
        boolean coughsALotOfCoal = blacklung / HbmLivingProps.maxBlacklung > 0.8D;
        boolean coughsBlood = asbestos / HbmLivingProps.maxAsbestos > 0.75D || blacklung / HbmLivingProps.maxBlacklung > 0.75D;

        double blacklungDelta = 1D - (blacklung / (double)HbmLivingProps.maxBlacklung);
        double asbestosDelta = 1D - (asbestos / (double)HbmLivingProps.maxAsbestos);
        double sootDelta = 1D - Math.min(soot / 100, 1D);

        double total = 1 - (blacklungDelta * asbestosDelta);

        Level world = entity.worldObj;

        if(total > 0.75D) {
            entity.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 2));
        }

        if(total > 0.95D) {
            entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 0));
        }

        total = 1 - (blacklungDelta * asbestosDelta * sootDelta);
        int freq = Math.max((int) (1000 - 950 * total), 20);

        if(world.getTotalWorldTime() % freq == entity.getEntityId() % freq) {
            world.playSoundEffect(entity.posX, entity.posY, entity.posZ, "hbm:player.cough", 1.0F, 1.0F);

            if(coughsBlood) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setString("type", "vomit");
                nbt.setString("mode", "blood");
                nbt.setInteger("count", 5);
                nbt.setInteger("entity", entity.getEntityId());
                PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
            }

            if(coughsCoal) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setString("type", "vomit");
                nbt.setString("mode", "smoke");
                nbt.setInteger("count", coughsALotOfCoal ? 50 : 10);
                nbt.setInteger("entity", entity.getEntityId());
                PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0),  new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
            }
        }
    }

    private static void handleOil(LivingEntity entity) {

        if(entity.worldObj.isRemote)
            return;

        int oil = HbmLivingProps.getOil(entity);

        if(oil > 0) {

            if(entity.isBurning()) {
                HbmLivingProps.setOil(entity, 0);
                entity.worldObj.newExplosion(null, entity.posX, entity.posY + entity.height / 2, entity.posZ, 3F, false, true);
            } else {
                HbmLivingProps.setOil(entity, oil - 1);
            }

            if(entity.tickCount % 5 == 0) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setString("type", "sweat");
                nbt.setInteger("count", 1);
                nbt.setInteger("block", Block.getIdFromBlock(Blocks.coal_block));
                nbt.setInteger("entity", entity.getEntityId());
                PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(nbt, 0, 0, 0), new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 25));
            }
        }
    }

    private static void handlePollution(LivingEntity entity) {

        if(!RadiationConfig.enablePollution) return;

        if(RadiationConfig.enablePoison && !ArmorRegistry.hasProtection(entity, 3, HazardClass.GAS_BLISTERING) && entity.tickCount % 60 == 0) {

            float poison = PollutionHandler.getPollution(entity.worldObj, (int) Math.floor(entity.posX), (int) Math.floor(entity.posY + entity.getEyeHeight()), (int) Math.floor(entity.posZ), PollutionType.POISON);

            if(poison > 10) {

                if(poison < 25) {
                    entity.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
                } else if(poison < 50) {
                    entity.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1));
                } else {
                    entity.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 2));
                }
            }
        }

        if(RadiationConfig.enableLeadPoisoning && !ArmorRegistry.hasProtection(entity, 3, HazardClass.PARTICLE_FINE) && entity.tickCount % 60 == 0) {

            float poison = PollutionHandler.getPollution(entity.worldObj, (int) Math.floor(entity.posX), (int) Math.floor(entity.posY + entity.getEyeHeight()), (int) Math.floor(entity.posZ), PollutionType.HEAVYMETAL);

            if(poison > 25) {

                if(poison < 50) {
                    entity.addPotionEffect(new PotionEffect(HbmPotion.lead.id, 100, 0));
                } else if(poison < 75) {
                    entity.addPotionEffect(new PotionEffect(HbmPotion.lead.id, 100, 2));
                } else {
                    entity.addPotionEffect(new PotionEffect(HbmPotion.lead.id, 100, 2));
                }
            }
        }
    }

    private static void handleTemperature(Entity entity) {

        if(!(entity instanceof LivingEntity)) return;
        if(entity.worldObj.isRemote) return;

        LivingEntity living = (LivingEntity) entity;
        HbmLivingProps props = HbmLivingProps.getData(living);
        Random rand = living.getRNG();

        if(!entity.isEntityAlive()) return;

        if(living.isImmuneToFire()) {
            props.fire = 0;
            props.phosphorus = 0;
        }

        double x = living.posX;
        double y = living.posY;
        double z = living.posZ;

        if(living.isInWater() || living.isWet()) props.fire = 0;

        if(props.fire > 0) {
            props.fire--;
            if((living.tickCount + living.getEntityId()) % 15 == 0) living.worldObj.playSoundEffect(living.posX, living.posY + living.height / 2, living.posZ, "random.fizz", 1F, 1.5F + rand.nextFloat() * 0.5F);
            if((living.tickCount + living.getEntityId()) % 40 == 0) living.attackEntityFrom(DamageSource.onFire, 2F);
            FlameCreator.composeEffect(entity.worldObj, x - living.width / 2 + living.width * rand.nextDouble(), y + rand.nextDouble() * living.height, z - living.width / 2 + living.width * rand.nextDouble(), FlameCreator.META_FIRE);
        }

        if(props.phosphorus > 0) {
            props.phosphorus--;
            if((living.tickCount + living.getEntityId()) % 15 == 0) living.worldObj.playSoundEffect(living.posX, living.posY + living.height / 2, living.posZ, "random.fizz", 1F, 1.5F + rand.nextFloat() * 0.5F);
            if((living.tickCount + living.getEntityId()) % 40 == 0) living.attackEntityFrom(DamageSource.onFire, 5F);
            FlameCreator.composeEffect(entity.worldObj, x - living.width / 2 + living.width * rand.nextDouble(), y + rand.nextDouble() * living.height, z - living.width / 2 + living.width * rand.nextDouble(), FlameCreator.META_FIRE);
        }

        if(props.balefire > 0) {
            props.balefire--;
            if((living.tickCount + living.getEntityId()) % 15 == 0) living.worldObj.playSoundEffect(living.posX, living.posY + living.height / 2, living.posZ, "random.fizz", 1F, 1.5F + rand.nextFloat() * 0.5F);
            ContaminationUtil.contaminate(living, HazardType.RADIATION, ContaminationType.CREATIVE, 5F);
            if((living.tickCount + living.getEntityId()) % 20 == 0) living.attackEntityFrom(DamageSource.onFire, 5F);
            FlameCreator.composeEffect(entity.worldObj, x - living.width / 2 + living.width * rand.nextDouble(), y + rand.nextDouble() * living.height, z - living.width / 2 + living.width * rand.nextDouble(), FlameCreator.META_BALEFIRE);
        }

        if(props.fire > 0 || props.phosphorus > 0 || props.balefire > 0) if(!entity.isEntityAlive()) ConfettiUtil.decideConfetti(living, DamageSource.onFire);
    }

    private static void handleDashing(Entity entity) {

        //AAAAAAAAAAAAAAAAAAAAEEEEEEEEEEEEEEEEEEEE
        if(entity instanceof Player) {
            Player player = (Player)entity;

            HbmPlayerProps props = HbmPlayerProps.getData(player);

            props.setDashCount(0);

            ArmorFSB chestplate = null;

            int armorDashCount = 0;
            int armorModDashCount = 0;

            if(ArmorFSB.hasFSBArmor(player)) {
                ItemStack plate = player.inventory.armorInventory[2];

                chestplate = (ArmorFSB)plate.getItem();
            }

            if(chestplate != null)
                armorDashCount = chestplate.dashCount;

            for(int armorSlot = 0; armorSlot < 4; armorSlot++) {
                ItemStack armorStack = player.inventory.armorInventory[armorSlot];

                if(armorStack != null && armorStack.getItem() instanceof ItemArmor) {

                    for(int modSlot = 0; modSlot < 8; modSlot++) {
                        ItemStack mod = ArmorModHandler.pryMods(armorStack)[modSlot];

                        if(mod != null && mod.getItem() instanceof IArmorModDash) {
                            int count = ((IArmorModDash)mod.getItem()).getDashes();
                            armorModDashCount += count;
                        }
                    }
                }
            }

            int dashCount = armorDashCount + armorModDashCount;

            boolean dashActivated = props.getKeyPressed(EnumKeybind.DASH);

            if(dashCount * 30 < props.getStamina())
                props.setStamina(dashCount * 30);

            if(dashCount > 0) {

                int perDash = 30;

                props.setDashCount(dashCount);

                int stamina = props.getStamina();

                if(props.getDashCooldown() <= 0) {

                    if(dashActivated && stamina >= perDash) {

                        Vec3 lookingIn = player.getLookVec();
                        Vec3 strafeVec = player.getLookVec();
                        strafeVec.rotateAroundY((float)Math.PI * 0.5F);

                        int forward = (int) Math.signum(player.moveForward);
                        int strafe = (int) Math.signum(player.moveStrafing);

                        if(forward == 0 && strafe == 0)
                            forward = 1;

                        player.addVelocity(lookingIn.xCoord * forward + strafeVec.xCoord * strafe, 0, lookingIn.zCoord * forward + strafeVec.zCoord * strafe);
                        player.motionY = 0;
                        player.fallDistance = 0F;
                        player.playSound("hbm:weapon.rocketFlame", 1.0F, 1.0F);

                        props.setDashCooldown(HbmPlayerProps.dashCooldownLength);
                        stamina -= perDash;
                    }
                } else {
                    props.setDashCooldown(props.getDashCooldown() - 1);
                    props.setKeyPressed(EnumKeybind.DASH, false);
                }

                if(stamina < props.getDashCount() * perDash) {
                    stamina++;

                    if(stamina % perDash == perDash-1) {

                        player.playSound("hbm:item.techBoop", 1.0F, (1.0F + ((1F/12F)*(stamina/perDash))));
                        stamina++;
                    }
                }

                props.setStamina(stamina);
            }

        }
    }

    private static void handlePlinking(Entity entity) {

        if(entity instanceof Player) {
            Player player = (Player)entity;
            HbmPlayerProps props = HbmPlayerProps.getData(player);

            if(props.plinkCooldown > 0)
                props.plinkCooldown--;
        }
    }

    private static boolean canVomit(Entity e) {
        if(e.isCreatureType(EnumCreatureType.waterCreature, false)) return false;
        return true;
    }
}
