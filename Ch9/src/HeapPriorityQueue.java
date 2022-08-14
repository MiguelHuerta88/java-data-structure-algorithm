import java.util.Comparator;
import java.util.ArrayList;

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    /**
     * Primary collection of priority queue entries
     */
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

    /**
     * Created an empty priority queue based on the natural ordering of its keys
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue based on the given comparator to order keys
     * @param comp
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the given key-value pairs
     * @param keys
     * @param values
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for(int j = 0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }

    /**
     * Performs a bottom-up construction of the heap in linear time
     */
    protected void heapify() {
        int startIndex = parent(size() - 1); // start at PARENT of last entry
        for(int j = startIndex; j >= 0; j--) { // loop until processing the root
            downheap(j);
        }
    }

    // protected utilities
    protected int parent(int j) {
        return (j-1) / 2;
    }

    protected int left(int j) {
        return 2 * j + 1;
    }

    protected int right(int j) {
        return 2 * j + 2;
    }

    protected boolean hasLeft(int j) {
        return left(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        return right(j) < heap.size();

    }

    /**
     * Exchanges the entries at indices i and j of the array list
     * @param i
     * @param j
     */
    protected void swap(int i, int j) {
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moved the entry at index j higher, if necessary, to restore the heap property
     * @param j
     */
    protected void upheap(int j) {
        while (j > 0) { // continue until reaching root ( or break statement)
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break; // heap property verified
            swap(j, p);
            j = p; // continue from the parents location
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property
     * @param j
     */
    public void downheap(int j) {
        while (hasLeft(j)) { // continue to bottom or break statement
            int leftIndex = left(j);
            int smallChildIndex = leftIndex; // although right may be smaller
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex; // right child is smaller
                }
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
                break; // heap property has been restored
            }
            swap(j, smallChildIndex); // continue at position of the child
            j = smallChildIndex;
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue
     */
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does nmot remove) and entry with minimal key if any*
     * @return
     */
    public Entry<K,V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and retuns the entry created
     * @param key
     * @param value
     * @return
     * @throws IllegalArgumentException
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method ( could through exception)
        Entry<K,V> newest = new PQEntry<>(key, value);
        heap.add(newest); // add to the end of the list
        upheap(heap.size() - 1); // upheap newly added entry
        return newest;
    }

    /**
     * Removed and returns an entry with minimal key (if any)
     * @return
     */
    public Entry<K,V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K,V> answer = heap.get(0);
        swap(0, heap.size() - 1); // put minimum item at the end
        heap.remove(heap.size() - 1); // and remove it from the list
        downheap(0); // then fix the new root
        return answer;
    }

    /**
     * Sorts sequences S, using initially empty priority queue P to produce the order
     * @param S
     * @param P
     * @param <E>
     */
    public static <E> void pqSort(PositionalList<E> S, PriorityQueue<E, ?> P) {
        int n = S.size();
        for (int j = 0; j < n; j++) {
            E element = S.remove(S.first());
            P.insert(element, null); // element is key; null value
        }

        for (int j = 0; j <  n; j++) {
            E element = P.removeMin().getKey();
            S.addLast(element); // the smallest key in P is next places in S
        }
    }
}
