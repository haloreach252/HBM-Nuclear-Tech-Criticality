package com.miniverse.hbm.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BufferUtil {

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    // Writes a string to a byte buffer by encoding its length and raw bytes.
    public static void writeString(ByteBuf buf, String value) {
        if (value == null) {
            buf.writeInt(-1);
            return;
        }
        byte[] bytes = value.getBytes(CHARSET);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    // Reads a string from a byte buffer via the encoded length and raw bytes.
    public static String readString(ByteBuf buf) {
        final int count = buf.readInt();
        if (count < 0) return null;
        final byte[] bytes = new byte[count];
        buf.readBytes(bytes);
        return new String(bytes, CHARSET);
    }

    /**
     * Writes an integer array to a buffer.
     */
    public static void writeIntArray(ByteBuf buf, int[] array) {
        buf.writeInt(array.length);
        for (int value : array) {
            buf.writeInt(value);
        }
    }

    /**
     * Reads an integer array from a buffer.
     */
    public static int[] readIntArray(ByteBuf buf) {
        int length = buf.readInt();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = buf.readInt();
        }
        return array;
    }

    /**
     * Writes a Vec3 to a buffer.
     */
    public static void writeVec3(ByteBuf buf, Vec3 vector) {
        buf.writeBoolean(vector != null);
        if (vector == null) return;
        buf.writeDouble(vector.x());
        buf.writeDouble(vector.y());
        buf.writeDouble(vector.z());
    }

    /**
     * Reads a Vec3 from a buffer.
     */
    public static Vec3 readVec3(ByteBuf buf) {
        boolean vectorExists = buf.readBoolean();
        if (!vectorExists) {
            return null;
        }
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        return new Vec3(x, y, z);
    }

    /**
     * Writes a CompoundTag (NBT) to a buffer using compressed data.
     */
    public static void writeNBT(ByteBuf buf, CompoundTag compound) {
        if (compound != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                NbtIo.writeCompressed(compound, baos);
                byte[] nbtData = baos.toByteArray();
                buf.writeShort(nbtData.length);
                buf.writeBytes(nbtData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            buf.writeShort(-1);
        }
    }

    /**
     * Reads a CompoundTag (NBT) from a buffer.
     */
    public static CompoundTag readNBT(ByteBuf buf) {
        short nbtLength = buf.readShort();
        if (nbtLength == -1) // Indicates no compound was written.
            return new CompoundTag();
        byte[] tags = new byte[nbtLength];
        buf.readBytes(tags);
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(tags);
            return NbtIo.readCompressed(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CompoundTag();
    }

    /**
     * Writes an ItemStack to the buffer.
     * <p>
     * Instead of using numeric IDs (no longer supported), this implementation writes the
     * item's ResourceLocation as a string.
     */
    public static void writeItemStack(ByteBuf buf, ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            buf.writeBoolean(false);
        } else {
            buf.writeBoolean(true);
            // Write the item's ResourceLocation string.
            ResourceLocation itemRL = Registry.ITEM.getKey(stack.getItem());
            writeString(buf, itemRL.toString());
            // Write the count (as a byte; typical stacks are small).
            buf.writeByte(stack.getCount());
            // Write the damage/meta value.
            buf.writeShort(stack.getDamageValue());
            // Write NBT data if the item is damageable or shares NBT.
            CompoundTag tag = stack.getTag();
            if (stack.getItem().isDamageable(stack) || stack.getItem().shouldOverrideMultiplayerNbt()) {
                writeNBT(buf, tag);
            } else {
                writeNBT(buf, null);
            }
        }
    }

    /**
     * Reads an ItemStack from the buffer.
     */
    public static ItemStack readItemStack(ByteBuf buf) {
        boolean exists = buf.readBoolean();
        if (!exists) return ItemStack.EMPTY;
        String itemStr = readString(buf);
        ResourceLocation rl = new ResourceLocation(itemStr);
        Item item = Registry.ITEM.get(rl);
        int quantity = buf.readByte();
        short meta = buf.readShort();
        ItemStack stack = new ItemStack(item, quantity, meta);
        CompoundTag tag = readNBT(buf);
        if (!tag.isEmpty()) {
            stack.setTag(tag);
        }
        return stack;
    }
}
