package com.miniverse.hbm.api.block;

import com.miniverse.hbm.entity.item.EntityTNTPrimedBase;
import net.minecraft.world.level.Level;

public interface IFuckingExplode {

    /**
     * Anything that can be detonated by another explosion should implement this and spawn an EntityTNTPrimedBase
     * when hit by an explosion. This prevents chained explosions from causing a stack overflow.
     * The block can still safely immediately explode, as long as the source isn't another explosion.
     *
     * @param level The world (level) where the explosion occurs.
     * @param x The x-coordinate of the explosion.
     * @param y The y-coordinate of the explosion.
     * @param z The z-coordinate of the explosion.
     * @param entity The entity causing the explosion.
     */
    void explodeEntity(Level level, double x, double y, double z, EntityTNTPrimedBase entity);
}
