public class ArrayQueue<E> implements Queue<E> {
    private E[] data;
    private int f = 0; // index of front element
    private int sz = 0; // current number of elements
    public static final int CAPACITY = 20;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        data = (E[])new Object[CAPACITY];
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public void enqueue(E e) throws IllegalStateException {
        if (sz == data.length) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (f + sz) % data.length;
        data[avail] = e;
        sz++;
    }

    /**
     * Returns but does not remove the first element in the queue (null if empty)
     */
    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    /**
     * removes and returns first element of the queue (null if empty);
     * @return E
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz--;
        return answer;
    }
}
