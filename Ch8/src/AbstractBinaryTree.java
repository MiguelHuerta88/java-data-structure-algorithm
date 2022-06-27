import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    /**
     * Find sibling of position p
     * @param p
     * @return
     */
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) return null;
        if (p == left(parent))
            return right(parent);
        else
            return left(parent);
    }

    /**
     * Returns the number of children of Position p
     * @param p
     * @return
     */
    public int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    /**
     * Returns an iterable collection of the Positions representing p's children
     * @param p
     * @return
     */
    public Iterable<Position<E>> children(Position<E> p) {
        // have to use the java.util implementations not ours
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }

    /**
     * Adds Positions of the subtree rooted at the Position p to the given snapshot
     * Inorder => Left, Root, Right (On Binary Trees)
     * @param p
     * @param snapshot
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) {
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if (right(p) != null) {
            inorderSubtree(right(p), snapshot);
        }
    }

    /**
     * Returns an iterable collection of positions of the tree, reported in inorder
     * @return
     */
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);
        }

        return snapshot;
    }

    public static <E> int layout(BinaryTree<E> T, Position<E> p, int d, int x)
    {
        if (T.left(p) != null) {
            x = layout(T, T.left(p), d + 1, x); // resulting x will be increased
        }
        p.getElement().setX(x++); // post increment x
        p.getElement().setY(d);
        if (T.right(p)) {
            x = layout(T, T.right(p), d + 1, x); // result x will be increased
        }
        return x;
    }
}
