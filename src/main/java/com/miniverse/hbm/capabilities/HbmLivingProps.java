package com.miniverse.hbm.capabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.config.RadiationConfig;
import com.miniverse.hbm.interfaces.ITodo;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

@SuppressWarnings("removal")
@AutoRegisterCapability
public class HbmLivingProps {

    public static final ResourceLocation CAPABILITY_ID = new ResourceLocation(HBMNuclearTechCriticality.MODID, "living_props");
    public static final UUID DIGAMMA_UUID = UUID.fromString("2a3d8aec-5ab9-4218-9b8b-ca812bdf378b");

    private float radiation;
    private float digamma;
    private int asbestos;
    public static final int maxAsbestos = 60 * 60 * 20;
    private int blacklung;
    public static final int maxBlacklung = 2 * 60 * 60 * 20;
    private float radEnv;
    private float radBuf;
    private int bombTimer;
    private int contagion;
    private int oil;
    public int fire;
    public int phosphorus;
    public int balefire;
    private final List<ContaminationEffect> contamination = new ArrayList<>();

    /// RADIATION ///
    public float getRadiation() {
        return RadiationConfig.enableContamination.get() ? radiation : 0;
    }

    public void setRadiation(float rad) {
        if (RadiationConfig.enableContamination.get())
            this.radiation = rad;
    }

    public void incrementRadiation(float rad) {
        if (!RadiationConfig.enableContamination.get()) {
            return;
        }

        this.radiation = Mth.clamp(this.radiation + rad, 0, 2500);
    }

    public float getRadEnv() {
        return radEnv;
    }

    public void setRadEnv(float rad) {
        this.radEnv = rad;
    }

    public float getRadBuf() {
        return radBuf;
    }

    public void setRadBuf(float rad) {
        this.radBuf = rad;
    }

    /// CONTAMINATION ///
    public List<ContaminationEffect> getCont() {
        return this.contamination;
    }



    public float getDigamma() {
        return digamma;
    }

    @ITodo("This needs to award achievements stored elsewhere")
    public void setDigamma(LivingEntity entity, float digamma) {
        if (entity.level().isClientSide)
            return;

        if (entity instanceof EntityDuck)
            digamma = 0.0F;

        this.digamma = digamma;
        float healthMod = (float) Math.pow(0.5, digamma) - 1F;

        AttributeInstance attribute = entity.getAttribute(Attributes.MAX_HEALTH);
        if (attribute != null) {
            attribute.removeModifier(DIGAMMA_UUID);
            attribute.addTransientModifier(new AttributeModifier(DIGAMMA_UUID, "digamma", healthMod, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        if (entity.getHealth() > entity.getMaxHealth() && entity.getMaxHealth() > 0) {
            entity.setHealth(entity.getMaxHealth());
        }

        if ((entity.getMaxHealth() <= 0 || digamma >= 10.0F) && entity.isAlive()) {
            entity.setAbsorptionAmount(0);
            entity.hurt(ModDamageSource.digamma, 500F);
            entity.kill();
        }

        if (entity instanceof ServerPlayer player) {
            /*
            if (digamma > 0) player.awardStat(MainRegistry.digammaSee);
            if (digamma >= 2) player.awardStat(MainRegistry.digammaFeel);
            if (digamma >= 10) player.awardStat(MainRegistry.digammaKnow);*/
        }
    }

    public void saveNBTData(CompoundTag compound) {
        compound.putFloat("radiation", radiation);
        compound.putFloat("digamma", digamma);
        compound.putInt("asbestos", asbestos);
        compound.putInt("blacklung", blacklung);
        compound.putInt("bombTimer", bombTimer);
        compound.putInt("contagion", contagion);
        compound.putInt("oil", oil);
    }

    public void loadNBTData(CompoundTag compound) {
        this.radiation = compound.getFloat("radiation");
        this.digamma = compound.getFloat("digamma");
        this.asbestos = compound.getInt("asbestos");
        this.blacklung = compound.getInt("blacklung");
        this.bombTimer = compound.getInt("bombTimer");
        this.contagion = compound.getInt("contagion");
        this.oil = compound.getInt("oil");
    }

    public static class Provider extends CapabilityProvider<Provider> implements ICapabilitySerializable<CompoundTag> {
        private final LazyOptional<HbmLivingProps> instance = LazyOptional.of(HbmLivingProps::new);

        public Provider() {
            super(Provider.class);
        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return CustomCapabilities.HBM_LIVING_CAPABILITY.orEmpty(cap, instance);
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            instance.ifPresent(props -> props.saveNBTData(tag));
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            instance.ifPresent(props -> props.loadNBTData(nbt));
        }
    }

    public static class ContaminationEffect implements INBTSerializable<CompoundTag> {
        public float maxRad;
        public int maxTime;
        public int time;
        public boolean ignoreArmor;

        public ContaminationEffect(float rad, int time, boolean ignoreArmor) {
            this.maxRad = rad;
            this.maxTime = this.time = time;
            this.ignoreArmor = ignoreArmor;
        }

        public float getRad() {
            return maxRad * ((float)time / (float)maxTime);
        }

        public void serialize(ByteBuf buf) {
            buf.writeFloat(this.maxRad);
            buf.writeInt(this.maxTime);
            buf.writeInt(this.time);
            buf.writeBoolean(ignoreArmor);
        }

        public static ContaminationEffect deserialize(ByteBuf buf) {
            float maxRad = buf.readFloat();
            int maxTime = buf.readInt();
            int time = buf.readInt();
            boolean ignoreArmor = buf.readBoolean();
            ContaminationEffect effect = new ContaminationEffect(maxRad, maxTime, ignoreArmor);
            effect.time = time;
            return effect;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putFloat("maxRad", this.maxRad);
            tag.putInt("maxTime", this.maxTime);
            tag.putInt("time", this.time);
            tag.putBoolean("ignoreArmor", this.ignoreArmor);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag tag) {
            this.maxRad = tag.getFloat("maxRad");
            this.maxTime = tag.getInt("maxTime");
            this.time = tag.getInt("time");
            this.ignoreArmor = tag.getBoolean("ignoreArmor");
        }

        public void saveToNBT(CompoundTag nbt, int index) {
            CompoundTag effectTag = serializeNBT();
            nbt.put("cont_" + index, effectTag);
        }

        public static ContaminationEffect loadFromNBT(CompoundTag nbt, int index) {
            CompoundTag effectTag = nbt.getCompound("cont_" + index);
            ContaminationEffect effect = new ContaminationEffect(
                    effectTag.getFloat("maxRad"),
                    effectTag.getInt("maxTime"),
                    effectTag.getBoolean("ignoreArmor")
            );
            effect.time = effectTag.getInt("time");
            return effect;
        }
    }
}