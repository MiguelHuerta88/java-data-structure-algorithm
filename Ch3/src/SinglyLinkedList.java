public class SinglyLinkedList<E> {
    // nested class
    private static class Node<E> {
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }
    // END of nested Node class

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList() {}

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E fist() {
        return (isEmpty()) ? null : head.getElement();
    }

    public E last() {
        return (isEmpty()) ? null : tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E first = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return first;
    }

    public String toString() {
        String content = "Printing out the contents of our list\n";
        if (!isEmpty()) {
            Node<E> tempHead = head;
            while(tempHead.getNext() != null) {
                content += "Value: " + tempHead.getElement() + "\n";
                tempHead = tempHead.getNext();
            }
            content += "Value: " + tempHead.getElement() + "\n";
        }

        return content;
    }

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addFirst("Miguel");
        list.addFirst("Susan");
        list.addFirst("Erik");
        list.addLast("Sammuel");
        System.out.print(list.toString());

        list.removeFirst();
        list.removeFirst();
        System.out.print(list.toString());
    }
}
