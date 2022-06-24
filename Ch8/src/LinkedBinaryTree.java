public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{
    // --------- Nested Node class --------//
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /**
         * Constructor for Node
         * @param e
         * @param above
         * @param leftChild
         * @param rightChild
         */
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        // ----- Accessor methods ------ //
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // ------ Update methods ------ //
        public void setElement(E e) {
            element = e;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }
    // --------- END Nested Node class --------//

    /**
     * Factory method to create a new node storing element e
     * @param e
     * @param parent
     * @param left
     * @param right
     * @return
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // --------- LinkedBinaryTree instance variables ------- //
    protected Node<E> root = null;
    private int size;

    /**
     * Constructor
     */
    public LinkedBinaryTree() {}

    /**
     * Validate the position and return it as a node
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>)p; // safe case
        if (node.getParent() == node)
            throw new IllegalArgumentException("p is not longer in the tree");
        return node;
    }

    public int size() {
        return size;
    }

    public Position<E> root() {
        return root;
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position
     * @param e
     * @return
     * @throws IllegalStateException
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Create a new left child of Position p storing element e returns its Position
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public  Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);

        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Create a new right child of Position p storing element e, returns its Position
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at position p with e and returs the replaced element;
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2 as left and right subtrees of external p
     * @param p
     * @param t1
     * @param t2
     * @throws IllegalArgumentException
     */
    public void attach(Position<E>p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();

        // attach t1 as subtree
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }

        // attach t2 as subtree
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removed the node at Position p and replaces it with its child if any
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent()); // child grandparents become its parent
        if (node == root) {
            root = child; // child becomes root
        } else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null); // help garbage collector
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // our conversion for defunct node
        return temp;
    }
}
