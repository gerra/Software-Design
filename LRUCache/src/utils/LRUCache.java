package utils;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;

public class LRUCache<K, V> {
    private HashMap<K, CacheEntry<K, V>> map = new HashMap<>();

    private int size = 0;
    private CacheEntry<K, V> first;
    private CacheEntry<K, V> last;

    private int maxSize;

    /**
     * Construct LRUCache with {@code maxSize} capacity. Every access to LRUCache element, move it to the head of queue
     * @param maxSize maximum number of entries
     */
    public LRUCache(int maxSize) {
        assert (maxSize > 0);
        this.maxSize = maxSize;
    }

    /* Public methods */

    /**
     * Returns the value for key if it exists in the cache or can be created by {@link #create(Object)}. If a value was returned, it is moved to the head of the queue. This returns null if a value is not cached and cannot be created.
     * @param key key for search
     * @return cached value or {@code null} if a value is not cached and cannot be created
     */
    @Nullable
    public final V get(@NotNull K key) {
        assert (key != null);
        CacheEntry<K, V> existingEntry = map.get(key);
        if (existingEntry == null) {
            V createdValue = create(key);
            if (createdValue != null) {
                return put(key, createdValue);
            }
            return null;
        } else {
            liftToHead(existingEntry);
            return existingEntry.value;
        }
    }

    /**
     * Caches value for key. The value is moved to the head of the queue.
     * @param key key for caching
     * @param value caching value
     * @return the previous value mapped by key
     */
    @Nullable
    public final V put(@NotNull K key, @Nullable V value) {
        assert (key != null);
        CacheEntry<K, V> existingEntry = map.get(key);
        if (existingEntry == null) {
            CacheEntry<K, V> newEntry = new CacheEntry<>(key, value);
            checkSizeAndDeleteOldest();
            if (first == null) {
                first = newEntry;
                last = newEntry;
            } else {
                CacheEntry<K, V> second = first;
                first = newEntry;
                second.prev = first;
                first.next = second;
            }
            map.put(key, newEntry);
            size++;
            return null;
        } else {
            liftToHead(existingEntry);
            V oldValue = existingEntry.value;
            existingEntry.value = value;
            return oldValue;
        }
    }

    /**
     * Removes the entry for key if it exists.
     * @param key key for caching
     * @return the previous value mapped by key
     */
    public V remove(@NotNull K key) {
        assert (key != null);
        CacheEntry<K, V> existingEntry = map.get(key);
        if (existingEntry != null) {
            map.remove(key);
            size--;
            liftToHead(existingEntry);
            if (first == last) {
                first = null;
            } else {
                CacheEntry<K, V> second = first.next;
                second.prev = null;
                first = second;
            }
            return existingEntry.value;
        }
        return null;
    }

    /**
     * Clear cache
     */
    public final void evictAll() {
        while (first != null) {
            CacheEntry<K, V> next = first.next;
            first.next = first.prev = null;
            first = next;
        }
        first = last = null;
        map.clear();
        size = 0;
    }

    /**
     * Returns the number of entries in the cache
     * @return number of entries
     */
    public int size() {
        return size;
    }

    /**
     * Set new maximal size deleting the oldest elements if new maximum size less than current one
     * @param newMaxSize the new maximum size of cache
     */
    public void resize(int newMaxSize) {
        assert (newMaxSize > 0);
        int diff = maxSize - newMaxSize;
        maxSize = newMaxSize;
        for (int i = 0; i < diff; i++) {
            checkSizeAndDeleteOldest();
        }
    }

    /* Protected methods */

    /**
     * Create object by key. Used for extended classes.
     * @param key associated key
     * @return object associated by key or {@code null} if object wasn't created
     */
    @Nullable
    protected V create(@NotNull K key) {
        assert (key != null);
        return null;
    }

    /* Private methods */
    private void liftToHead(CacheEntry<K, V> entry) {
        if (entry == first) {
            return;
        }
        if (entry == last) {
            entry.prev.next = null;
            last = entry.prev;
        } else {
            entry.prev.next = entry.next;
            entry.next.prev = entry.prev;
        }
        entry.prev = null;
        entry.next = first;

        first.prev = entry;
        first = entry;
    }

    private void checkSizeAndDeleteOldest() {
        if (size >= maxSize) {
            map.remove(last.key);
            size--;
            if (first != last) {
                last.prev.next = null;
            }
            last = last.prev;
        }
    }

    /* Helper classes */
    private static class CacheEntry<K, V> {
        private K key;
        private V value;
        private CacheEntry<K, V> prev;
        private CacheEntry<K, V> next;

        public CacheEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
