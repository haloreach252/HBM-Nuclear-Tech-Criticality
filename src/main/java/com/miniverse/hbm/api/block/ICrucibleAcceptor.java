package com.miniverse.hbm.api.block;

import com.miniverse.hbm.inventory.material.Mats.MaterialStack;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public interface ICrucibleAcceptor {

    /**
     * Determines whether a partial pour can be accepted at a given position.
     *
     * @param level The world (level) where the interaction occurs.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @param dX The precise x-coordinate of impact.
     * @param dY The precise y-coordinate of impact.
     * @param dZ The precise z-coordinate of impact.
     * @param side The direction from which the material is poured.
     * @param stack The material being poured.
     * @return True if the block can accept the partial pour.
     */
    boolean canAcceptPartialPour(Level level, int x, int y, int z, double dX, double dY, double dZ, Direction side, MaterialStack stack);

    /**
     * Handles pouring of materials into the block.
     *
     * @param level The world (level) where the interaction occurs.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @param dX The precise x-coordinate of impact.
     * @param dY The precise y-coordinate of impact.
     * @param dZ The precise z-coordinate of impact.
     * @param side The direction from which the material is poured.
     * @param stack The material being poured.
     * @return The remaining material after the pour operation.
     */
    MaterialStack pour(Level level, int x, int y, int z, double dX, double dY, double dZ, Direction side, MaterialStack stack);

    /**
     * Determines whether a partial flow can be accepted at a given position.
     *
     * @param level The world (level) where the interaction occurs.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @param side The direction from which the material is flowing.
     * @param stack The material being transferred.
     * @return True if the block can accept the partial flow.
     */
    boolean canAcceptPartialFlow(Level level, int x, int y, int z, Direction side, MaterialStack stack);

    /**
     * Handles the flow of materials into the block.
     *
     * @param level The world (level) where the interaction occurs.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @param side The direction from which the material is flowing.
     * @param stack The material being transferred.
     * @return The remaining material after the flow operation.
     */
    MaterialStack flow(Level level, int x, int y, int z, Direction side, MaterialStack stack);
}
