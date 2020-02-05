package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key, int z) {
        if (key == null) {
            return 0;
        }

        return Math.floorMod(key.hashCode(), z);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hash_code = hash(key, buckets.length);
        return buckets[hash_code].get(key);
    }

    private ArrayMap<K, V>[] resize(ArrayMap<K, V>[] bkt, int size){
        ArrayMap<K, V>[] r = new ArrayMap[size];

        for (ArrayMap<K,V> s : bkt){
            if(s!= null){
                for(K item : s.keySet()){
                    int new_hash = hash(item, r.length);
                    int old_hash = hash(item, bkt.length);
                    V value = bkt[old_hash].get(item);
                    r[new_hash] = new ArrayMap<>();
                    r[new_hash].put(item, value);
                }
            }
        }
        return r;
    }
    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int hash_code = hash(key, buckets.length);
        if(buckets[hash_code] == null){
            buckets[hash_code] = new ArrayMap<>();
        }
        if(!buckets[hash_code].containsKey(key)){
            size +=1;
        }
        buckets[hash_code].put(key, value);

        if(loadFactor()> MAX_LF){
            buckets = resize(buckets, buckets.length*2);
        }

    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
