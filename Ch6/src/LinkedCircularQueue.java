public class LinkedCircularQueue<E> implements CircularQueue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    @Override
    public void rotate() {
        enqueue(dequeue());
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.fist();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
}
