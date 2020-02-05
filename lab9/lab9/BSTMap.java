package lab9;

import com.sun.jdi.Value;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Ke Shang
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }else if(key.compareTo(p.key)>0){
            return getHelper(key, p.right);
        }else if(key.compareTo(p.key)<0){
            return getHelper(key, p.left);
        }else{
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null){
            size +=1;
            return new Node(key, value);
        }else if(key.compareTo(p.key)>0){
            p.right = putHelper(key, value, p.right);
        }else if(key.compareTo(p.key)< 0){
            p.left =  putHelper(key, value, p.left);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
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

    public Node Node_position(K key, Node p){
        if(p == null){
            return null;
        }else if(key.compareTo(p.key)>0){
            if(p.right.key == key){
                return p;
            }else {
                return Node_position(key, p.right);
            }
        }else if(key.compareTo(p.key)<0){
            if(p.left.key == key){
                return p;
            }else {
                return Node_position(key, p.left);
            }
        }else{
            return p;
        }
    }

    /*find the min node of the BST */
    public Node min(Node p){
        if(p.left == null){
            return p;
        }
        return min(p.left);
    }

    public Node delete_min(Node p){
        if(p.left == null){
            return p.right;
        }
        p.left = delete_min(p.left);
        size -=1;
        return p;
    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */

    public Node remove(K key, Node p) {
        if(p == null){
            return null;
        }
        if(p.key.compareTo(key)>0){
            p.left = remove(key, p.left);
        } else if(p.key.compareTo(key) <0){
            p.right = remove(key, p.right);
        } else {

            if(p.left == null){
                size -=1;
                return p.right;
            } else if(p.right == null){
                size -=1;
                return p.left;
            } else{
                Node temp = min(p.right);

                p.right = delete_min(p.right);
                temp.right = p.right;
                temp.left = p.left;
                p = temp;

                return temp;
            }
        }
        return p;
    }
    @Override
    public V remove(K key) {
        V value = get(key);
        if(value != null){
            root = remove(key, root);
        }
        return value;

    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        bstmap.put("break", 50);
        bstmap.put("great", 20);
        int x = bstmap.remove("cat");
    }
}
