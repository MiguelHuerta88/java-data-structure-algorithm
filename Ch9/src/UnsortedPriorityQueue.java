import java.util.Comparator;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList();

    public UnsortedPriorityQueue() {
        super();
    }

    public UnsortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the position of an entry having minimal key
     * @return
     */
    private Position<Entry<K,V>> findMin()
    {
        Position<Entry<K,V>> small = list.first();
        for (Position<Entry<K,V>> walk : list.positions()) { // positions() method not defined and could not finds any reference to it
            if (compare(walk.getElement(), small.getElement()) < 0) {
                small = walk;
            }
        }
        return small;
    }

    /**
     * Inserts a key-value pair and returns the netry created
     * @param key
     * @param value
     * @return
     * @throws IllegalArgumentException
     */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException
    {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    /**
     * Returns (but does not remove) and entry with minimal key
     * @return
     */
    public Entry<K,V> min() {
        if (list.isEmpty()) return null;
        return findMin().getElement();
    }

    /**
     * Removes and returns and entry with minimal key
     * @return
     */
    public Entry<K,V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }

    /**
     * Returns the number of items in the priority queue
     * @return
     */
    public int size()
    {
        return list.size();
    }
}
