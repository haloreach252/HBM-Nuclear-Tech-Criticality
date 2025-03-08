package com.miniverse.hbm.api.ntl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.miniverse.hbm.api.ntl.StorageStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * StorageManifest class that stores item metadata and tracks items by their NBT data.
 */
public class StorageManifest {

    public HashMap<Integer, MetaNode> itemMeta = new HashMap<>();

    /**
     * Writes item stack to the storage manifest, tracking items by their ID and NBT data.
     *
     * @param stack The item stack to write.
     */
    public void writeStack(ItemStack stack) {
        int id = Item.getId(stack.getItem());

        MetaNode meta = itemMeta.get(id);

        if (meta == null) {
            meta = new MetaNode();
            itemMeta.put(id, meta);
        }

        NBTNode nbt = meta.metaNBT.get(stack.getDamageValue());

        if (nbt == null) {
            nbt = new NBTNode();
            meta.metaNBT.put(stack.getDamageValue(), nbt);
        }

        CompoundTag compound = stack.hasTag() ? (CompoundTag) stack.getTag().copy() : null;
        long amount = nbt.nbtAmount.containsKey(compound) ? nbt.nbtAmount.get(compound) : 0;

        amount += stack.getCount();

        nbt.nbtAmount.put(compound, amount);
    }

    /**
     * Retrieves a list of all stored stacks in the manifest.
     *
     * @return A list of all stored item stacks with their respective amounts.
     */
    public List<StorageStack> getStacks() {
        List<StorageStack> stacks = new ArrayList<>();

        for (Entry<Integer, MetaNode> itemNode : itemMeta.entrySet()) {
            for (Entry<Integer, NBTNode> metaNode : itemNode.getValue().metaNBT.entrySet()) {
                for (Entry<CompoundTag, Long> nbtNode : metaNode.getValue().nbtAmount.entrySet()) {

                    ItemStack itemStack = new ItemStack(Item.byId(itemNode.getKey()), 1, nbtNode.getKey());
                    itemStack.setTag(nbtNode.getKey());
                    StorageStack stack = new StorageStack(itemStack, nbtNode.getValue());
                    stacks.add(stack);
                }
            }
        }

        return stacks;
    }

    /**
     * MetaNode class to store metadata for items.
     */
    public class MetaNode {

        public HashMap<Integer, NBTNode> metaNBT = new HashMap<>();
    }

    /**
     * NBTNode class to store NBT data and the associated amounts.
     */
    public class NBTNode {

        public HashMap<CompoundTag, Long> nbtAmount = new HashMap<>();
    }
}
