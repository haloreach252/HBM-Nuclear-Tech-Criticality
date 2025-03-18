package com.miniverse.hbm.items.armor;

import java.util.List;

import com.miniverse.hbm.capabilities.HbmPlayerProps;
import com.miniverse.hbm.network.PacketDispatcher;
import com.miniverse.hbm.network.packet.toclient.AuxParticlePacketNT;
import com.miniverse.hbm.client.model.armor.ModelArmorBJ;
import com.miniverse.hbm.util.ArmorUtil;
import com.miniverse.hbm.util.I18nUtil;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Consumer;

public class ArmorBJJetpack extends ArmorBJ {

    public ArmorBJJetpack(ArmorMaterial material, EquipmentSlot slot, String texture, long maxPower, long chargeRate, long consumption, long drain, Properties properties) {
        super(material, slot, texture, maxPower, chargeRate, consumption, drain, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @OnlyIn(Dist.CLIENT)
            private ModelArmorBJ model;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {
                if(model == null) {
                    model = new ModelArmorBJ(5);

                    // Set up model properties based on entity
                    model.prepareMobModel(entityLiving, 0, 0, 1.0F);
                    model.setupAnim(entityLiving, 0, 0, 0, 0, 0);
                }

                model.prepareMobModel(entityLiving, 0, 0, 1.0F);
                model.setupAnim(entityLiving, 0, 0, 0, 0, 0);

                return model;
            }
        });
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);

        HbmPlayerProps props = HbmPlayerProps.getData(player);

        if(!level.isClientSide()) {
            if(hasFSBArmor(player) && props.isJetpackActive()) {
                CompoundTag data = new CompoundTag();
                data.putString("type", "jetpack_bj");
                data.putInt("player", player.getId());

                PacketDispatcher.wrapper.send(
                        PacketDistributor.NEAR.with(
                                PacketDistributor.TargetPoint.p(
                                        player.getX(), player.getY(), player.getZ(),
                                        100, player.level().dimension()
                                )
                        ),
                        new AuxParticlePacketNT(data, player.getX(), player.getY(), player.getZ())
                );
            }
        }

        if(hasFSBArmor(player)) {
            ArmorUtil.resetFlightTime(player);

            if(props.isJetpackActive()) {
                if(player.getDeltaMovement().y < 0.4D) {
                    player.setDeltaMovement(
                            player.getDeltaMovement().x,
                            player.getDeltaMovement().y + 0.1D,
                            player.getDeltaMovement().z
                    );
                }

                player.fallDistance = 0;

                level.playSound(
                        null,
                        player.getX(), player.getY(), player.getZ(),
                        level.registryAccess()
                                .registryOrThrow(net.minecraft.core.Registry.SOUND_EVENT_REGISTRY)
                                .get(new ResourceLocation("hbm:weapon.immolatorShoot")),
                        SoundSource.PLAYERS,
                        0.125F,
                        1.5F
                );

            } else if(player.isShiftKeyDown()) {
                if(player.getDeltaMovement().y < -0.08) {
                    double mo = player.getDeltaMovement().y * -0.4;

                    player.setDeltaMovement(
                            player.getDeltaMovement().x,
                            player.getDeltaMovement().y + mo,
                            player.getDeltaMovement().z
                    );

                    Vec3 vec = player.getLookAngle();

                    player.setDeltaMovement(
                            player.getDeltaMovement().x + vec.x * mo,
                            player.getDeltaMovement().y + vec.y * mo,
                            player.getDeltaMovement().z + vec.z * mo
                    );
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("armor.electricJetpack").withStyle(ChatFormatting.RED).withStyle(s -> s.withInsertion("+ ")));
        list.add(Component.translatable("armor.glider").withStyle(ChatFormatting.GRAY).withStyle(s -> s.withInsertion("+ ")));
    }
}