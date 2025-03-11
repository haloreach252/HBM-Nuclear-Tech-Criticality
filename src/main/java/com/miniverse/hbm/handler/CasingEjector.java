package com.miniverse.hbm.handler;

import java.util.HashMap;
import java.util.Random;

import com.miniverse.hbm.particle.ParticleSpentCasing;
import com.miniverse.hbm.particle.SpentCasing;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * CasingEjector configures the spawn position, initial motion, and random rotation
 * <p>
 * for spent casings (brass or otherwise) created when a gun is fired.
 * <p>
 * The class maintains a static mapping of ejectors (each with a unique id) and provides
 * <p>
 * methods to adjust the ejection offset, motion, angle randomness, and (deprecated) parameters.
 * <p>
 * When spawnCasing is called (client‐side only) it builds the particle with the proper randomized
 * <p>
 * rotation and then spawns it using the client’s particle engine.
 */
public class CasingEjector implements Cloneable {

    public static HashMap<Integer, CasingEjector> mappings = new HashMap<>();
    public static final Random rand = new Random();

    private int id;
    private static int nextId = 0;
    private Vec3 posOffset = new Vec3(0, 0, 0);
    private Vec3 initialMotion = new Vec3(0, 0, 0);

    // Deprecated members that were once used to control (fixed) casing behavior. @Deprecated private int casingAmount = 1; @Deprecated private boolean afterReload = false; @Deprecated private int delay = 0;

    // These factors determine the random angular variance applied to the casing private float randomYaw = 0F; private float randomPitch = 0F;

    public CasingEjector() {
        this.id = nextId;
        nextId++;
        mappings.put(id, this);
    }

    // Sets the position offset where the casing is spawned relative to the firing position. public CasingEjector setOffset(double x, double y, double z) { return setOffset(new Vec3(x, y, z)); }

    public CasingEjector setOffset(Vec3 vec) {
        this.posOffset = vec;
        return this;
    }

    // Sets the initial motion (velocity) of the casing particle. public CasingEjector setMotion(double x, double y, double z) { return setMotion(new Vec3(x, y, z)); }

    public CasingEjector setMotion(Vec3 vec) {
        this.initialMotion = vec;
        return this;
    }

    // (Deprecated) Controls the number of casings to eject. @Deprecated public CasingEjector setAmount(int am) { this.casingAmount = am; return this; }

    // (Deprecated) Indicates that ejection happens after a reload. @Deprecated public CasingEjector setAfterReload() { this.afterReload = true; return this; }

    // (Deprecated) Sets a delay parameter. @Deprecated public CasingEjector setDelay(int delay) { this.delay = delay; return this; }

    // Sets the range (in radians) from which random yaw and pitch offsets are chosen. public CasingEjector setAngleRange(float yaw, float pitch) { this.randomYaw = yaw; this.randomPitch = pitch; return this; }

    public int getId() {
        return this.id;
    }

    public Vec3 getOffset() {
        return this.posOffset;
    }

    public Vec3 getMotion() {
        return this.initialMotion;
    }

    public int getAmount() {
        return this.casingAmount;
    }

    public boolean getAfterReload() {
        return this.afterReload;
    }

    public int getDelay() {
        return this.delay;
    }

    public float getYawFactor() {
        return this.randomYaw;
    }

    public float getPitchFactor() {
        return this.randomPitch;
    }

    /**
     * Client‐side only: spawns the casing particle.
     *
     * @param textureManager the texture manager needed to render the particle
     * @param config         the casing configuration (color, texture, etc.)
     * @param world          the current level (world)
     * @param x              x coordinate of spawn
     * @param y              y coordinate of spawn
     * @param z              z coordinate of spawn
     * @param pitch          the firing pitch (in radians)
     * @param yaw            the firing yaw (in radians)
     * @param crouched       whether the player was crouched (affects horizontal offset)
     */
    @OnlyIn(Dist.CLIENT)
    public void spawnCasing(TextureManager textureManager, SpentCasing config, Level world, double x, double y, double z, float pitch, float yaw, boolean crouched) { // Rotate the initial motion vector by adding randomness in both pitch and yaw. Vec3 rotatedMotionVec = rotateVector(getMotion(), pitch + (float) rand.nextGaussian() * getPitchFactor(), yaw + (float) rand.nextGaussian() * getYawFactor(), getPitchFactor(), getYawFactor()); // Create the particle. (The constructor parameters are those of the mod’s custom particle.) ParticleSpentCasing casing = new ParticleSpentCasing(textureManager, world, x, y, z, rotatedMotionVec.x, rotatedMotionVec.y, rotatedMotionVec.z, (float) (world.getRandom().nextGaussian() * 5F), (float) (world.getRandom().nextGaussian() * 10F), config, false, 0, 0, 0);

        // Offset the particle’s starting position so that the casing appears at the correct spot. offsetCasing(casing, getOffset(), pitch, yaw, crouched);

        // Set the particle’s rotation (stored in degrees) casing.rotationPitch = (float) Math.toDegrees(pitch); casing.rotationYaw = (float) Math.toDegrees(yaw);

        // Spawn the particle using the modern particle engine. Minecraft.getInstance().particleEngine.add(casing); }

        /**

         Offsets the casing particle’s position based on the configured offset.

         This uses a quaternion built from the pitch and yaw so that the offset rotates appropriately. */
        @OnlyIn(Dist.CLIENT) private static void offsetCasing (ParticleSpentCasing casing, Vec3 offset,float pitch,
        float yaw, boolean crouched)
        { // If the player is crouching, ignore the horizontal offset. float oX = (float) (crouched ? 0 : offset.x);

            // Convert the input angles from radians to degrees for the Quaternion constructor. float pitchDeg = (float) Math.toDegrees(pitch); float yawDeg = (float) Math.toDegrees(yaw);

            // Create a quaternion that represents a pitch rotation about the X axis... Quaternion pitchQuat = new Quaternion(new Vector3f(1, 0, 0), pitchDeg, true); // ...and a yaw rotation about the Y axis (note the negative sign). Quaternion yawQuat = new Quaternion(new Vector3f(0, 1, 0), -yawDeg, true); // Combine them so the resulting rotation is the same as applying pitch then yaw. Quaternion combinedQuat = yawQuat.copy(); combinedQuat.mul(pitchQuat);

            // Build the initial offset vector. (Only the x component is affected by the crouched state.) Vector3f offsetVec = new Vector3f(oX, (float) offset.y, (float) offset.z); // Rotate the offset vector. combinedQuat.transform(offsetVec);

            // Add the rotated offset to the particle’s current position. casing.setPosition(casing.posX + offsetVec.x, casing.posY + offsetVec.y, casing.posZ + offsetVec.z); }

            /**

             Rotates a motion vector by applying a randomized pitch and yaw.

             We create a combined quaternion (yaw then pitch) and use it to transform the original vector.

             @param vector the original vector

             @param pitch the pitch rotation (radians, with added randomness)

             @param yaw the yaw rotation (radians, with added randomness)

             @param pitchFactor a factor that controls how much randomness is applied to the pitch

             @param yawFactor a factor that controls how much randomness is applied to the yaw

             @return the rotated vector */private static Vec3 rotateVector (Vec3 vector,float pitch, float yaw,
            float pitchFactor, float yawFactor)
            { // Build a randomized vector (note: the randomness is applied similarly for the X and Z components using yawFactor, // and to Y using pitchFactor). float x = (float) (vector.x + rand.nextGaussian() * yawFactor); float y = (float) (vector.y + rand.nextGaussian() * pitchFactor); float z = (float) (vector.z + rand.nextGaussian() * yawFactor); Vector3f vectorF = new Vector3f(x, y, z);

                // Convert the angles from radians to degrees. float pitchDeg = (float) Math.toDegrees(pitch); float yawDeg = (float) Math.toDegrees(yaw); // Build the pitch and yaw quaternions. Quaternion pitchQuat = new Quaternion(new Vector3f(1, 0, 0), pitchDeg, true); Quaternion yawQuat = new Quaternion(new Vector3f(0, 1, 0), -yawDeg, true); // Combine them: applying pitch then yaw. Quaternion combinedQuat = yawQuat.copy(); combinedQuat.mul(pitchQuat);

                // Rotate the vector. combinedQuat.transform(vectorF); // Return a new Vec3 built from the rotated components. return new Vec3(vectorF.x, vectorF.y, vectorF.z); }

                // Returns the CasingEjector associated with the given id. public static CasingEjector fromId(int id) { return mappings.get(id); }

                @Override public CasingEjector clone () {
                try {
                    return (CasingEjector) super.clone();
                } catch (CloneNotSupportedException e) {
                    return new CasingEjector();
                }
            }
            }
