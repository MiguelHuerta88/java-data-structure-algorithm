public interface AdaptablePriorityQueue<K,V>{
    int size();

    boolean isEmpty();

    Entry<K,V> insert(K key, V val) throws IllegalArgumentException;

    void remove(K key, V val) throws IllegalArgumentException;

    void replaceKey(Entry<K,V> entry, K key) throws IllegalArgumentException;

    void replaceValue(Entry<K,V> entry, V value) throws IllegalArgumentException;
}
