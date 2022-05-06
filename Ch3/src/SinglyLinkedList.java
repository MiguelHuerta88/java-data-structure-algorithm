public class SinglyLinkedList<E> implements Cloneable{
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

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        SinglyLinkedList other = (SinglyLinkedList)o;
        if (size != other.size()) {
            return false;
        }

        Node walkA = head;
        Node walkB = other.head;

        while(walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) {
                return false;
            }
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;
    }

    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        // always use inherited Object.clone to create initial copy
        SinglyLinkedList<E> other = (SinglyLinkedList)super.clone();
        if (size > 0) {
            other.head = new Node<>(head.getElement(), null);
            Node<E> walk = head.getNext();
            Node<E> otherTail = other.head;
            while(walk != null) {
                Node<E> newest = new Node<>(walk.getElement(), null);
                otherTail.setNext(newest);
                otherTail = newest;
                walk = walk.getNext();
            }
        }
        return other;
    }

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addFirst("Miguel");
        list.addFirst("Susan");
        list.addFirst("Erik");
        list.addLast("Sammuel");
        System.out.print(list);

        list.removeFirst();
        list.removeFirst();
        System.out.print(list);

        SinglyLinkedList<String> listA = new SinglyLinkedList();
        SinglyLinkedList<String> listB = new SinglyLinkedList();
        listA.addFirst("Miguel");
        listA.addFirst("Susie");
        listB.addFirst("Miguel");
        System.out.println("Testing equality");
        System.out.print(listA.equals(listB));
    }
}
