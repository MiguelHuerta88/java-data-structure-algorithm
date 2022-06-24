import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E>{
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return  0;
        } else {
            return 1 + depth(parent(p));
        }
    }

    /**
     * Bad implementation of computing height.
     * Worst case n^2
     * @return
     */
    private int heightBad() {
        int h = 0;
        for (Position<E> p : positions()) {
            if (isExternal(p)) {
                h = Math.max(h, depth(p));
            }
        }
        return h;
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();
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

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions() {

        return preorder();
    }

    /**
     * Adds positions of the subtree rooted as Position p to the given snapshot
     * Preorder => root, left, right
     * @param p
     * @param snapshot
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot)
    {
        snapshot.add(p);
        for (Position<E> c: children(p)) {
            preorderSubtree(c, snapshot);
        }
    }

    /**
     * Returns Iterable collection of positions of the tree, reported in preorder
     * @return
     */
    public Iterable<Position<E>> preorder()
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot); // fill snapshot recursively
        }

        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot
     * postorder => left, right, root explore all subtrees before we go to root
     * @param p
     * @param snapshot
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot)
    {
        for (Position<E> c: children(p)) {
            postorderSubtree(c, snapshot);
        }
        snapshot.add(p);
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in postorder
     * @return
     */
    public Iterable<Position<E>> postorder()
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot); // fill snapshot recursively
        }
        return snapshot;
    }

    /**
     * Retunrs an iterable collection of positions of the tree in breadth-first order
     * Breath-Fist => Level Order Traversal
     * @return
     */
    public Iterable<Position<E>> breadthfirst()
    {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root()); // start with root
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.dequeue(); // remove from front of queue
                snapshot.add(p); // report this position
                for (Position<E> c : children(p)) {
                    fringe.enqueue(c); // add children to the back of the queue
                }
            }
        }

        return snapshot;
    }
}
