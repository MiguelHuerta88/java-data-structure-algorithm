import java.util.Comparator;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V>{
    // ------- Nest PQEntry class --------- //
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k; // key
        private V v; // value
        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        // Utilities not exposed
        protected void setValue(V value) {
            v = value;
        }

        protected void setKey(K key) {
            k = key;
        }
    } // -------- END of Nested PQEntry ---------- //

    // instance variables
    private Comparator<K> comp;

    /**
     * Create empty priority queue using the given comparator to order keys
     * @param c
     */
    protected AbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }

    /**
     * Creates empty priority queue based on the natural ordering of its keys
     */
    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    /**
     * Method for comparing two entries according to key
     * @param a
     * @param b
     * @return
     */
    protected int compare(Entry<K,V> a, Entry<K,V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    /**
     * Determines whether a key is valid
     * @param key
     * @return
     * @throws IllegalArgumentException
     */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
