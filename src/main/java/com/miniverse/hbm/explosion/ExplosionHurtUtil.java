package com.miniverse.hbm.explosion;

import java.util.List;

import com.miniverse.hbm.util.ContaminationUtil;
import com.miniverse.hbm.util.ContaminationUtil.ContaminationType;
import com.miniverse.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ExplosionHurtUtil {

    /**
     * Adds radiation to entities in an AoE
     * @param world
     * @param x
     * @param y
     * @param z
     * @param outer The least amount of radiation received on the very edge of the AoE
     * @param inner The greatest amount of radiation received on the very center of the AoE
     * @param radius
     */
    public static void doRadiation(Level world, double x, double y, double z, float outer, float inner, double radius) {

        // Create an AABB for the AoE
        AABB aabb = new AABB(
                x - radius, y - radius, z - radius,
                x + radius, y + radius, z + radius
        );

        // Get all living entities within the AABB
        List<LivingEntity> entities = world.getEntitiesOfClass(
                LivingEntity.class,
                aabb
        );

        for (LivingEntity entity : entities) {

            // Create a vector from the entity to the explosion center
            Vec3 vec = new Vec3(
                    x - entity.getX(),
                    y - entity.getY(),
                    z - entity.getZ()
            );

            double dist = vec.length();

            if (dist > radius)
                continue;

            // Linear interpolation between outer and inner radiation values based on distance
            double interpolation = 1 - (dist / radius);
            float rad = (float) (outer + (inner - outer) * interpolation);

            // Apply radiation contamination to the entity
            ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE, rad);
        }
    }
}