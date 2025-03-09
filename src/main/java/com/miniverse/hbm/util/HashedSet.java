package com.miniverse.hbm.util;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A crude implementation of a HashSet with a few key differences:
 * - Instead of storing objects as keys, the objects are stored as values in the underlying HashMap with the hash code as the key.
 *   Consequently, objects with matching hash codes are considered the same, skipping the equals check.
 * - No equals check means that collisions are possible, so be careful.
 * - The underlying HashMap is accessible, allowing retrieval of instances if a hash is supplied.
 *
 * This implementation was only intended for the drone request network code.
 *
 * @param <T> The type of objects maintained by this set.
 */
public class HashedSet<T> implements Set<T> {

    private final HashMap<Integer, T> map = new HashMap<>();

    public static class HashedIterator<T> implements Iterator<T> {
        private final Iterator<Entry<Integer, T>> iterator;

        public HashedIterator(HashedSet<T> set) {
            this.iterator = set.map.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            return this.iterator.next().getValue();
        }

        @Override
        public void remove() {
            this.iterator.remove();
        }
    }

    public HashedSet() { }

    public HashedSet(Set<? extends T> reachableNodes) {
        this.addAll(reachableNodes);
    }

    public HashMap<Integer, T> getMap() {
        return this.map;
    }

    @Override
    public boolean add(T e) {
        boolean contains = this.contains(e);
        this.map.put(e.hashCode(), e);
        return contains;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean ret = false;
        for (T o : c) {
            if (add(o)) {
                ret = true;
            }
        }
        return ret;
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        return this.map.containsKey(o.hashCode());
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new HashedIterator<>(this);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        T obj = this.map.get(o.hashCode());
        if (obj != null) {
            this.map.remove(o.hashCode());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException("removeAll is not implemented");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException("retainAll is not implemented");
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public Object[] toArray() {
        throw new NotImplementedException("toArray is not implemented");
    }

    @Override
    public <U> U[] toArray(U[] a) {
        throw new NotImplementedException("toArray is not implemented");
    }
}
