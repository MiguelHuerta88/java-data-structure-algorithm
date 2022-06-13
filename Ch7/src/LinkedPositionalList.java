import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E> {
    //--------- nested NODE class ------------//
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }
    //--------- END nested NODE class ------------//

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    /**
     * Constructs a new empty LinkedPositionalList (DoublyLinked Implementation)
     */
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Validate the position and returns it as a node
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>)p; // safe cast
        if (node.getNext() == null) {
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    /**
     * Returns the given node as a Position (or null, if it is a sentinel);
     * @param node
     * @return
     */
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    @Override
    public Position<E> addFist(E e) {
        return addBetween(e, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        // help with garbage collection
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    /**
     * All code below is related to Iterator,Iterable to allow usages of foreach loop
     */
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();
        private Position<E> recent = null;

        public boolean hasNext() {
            return (cursor != null);
        }

        public Position<E> next() throws NoSuchElementException
        {
            if (cursor == null) throw new NoSuchElementException("Nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        public void remove() throws IllegalStateException
        {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    } // end of nested PositionIterator class

    private class PositionIterable implements Iterable<Position<E>>
    {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    private class ElementIterator implements Iterator<E>
    {
        Iterator<Position<E>> posIterator = new PositionIterator();
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        }

        public void remove() {
            posIterator.remove();
        }
    }

    // returns an iterator of the elemtns stored in the list
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
