import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E> {
    /**
     * Returns the number of elements in the list
     * @return int
     */
    int size();

    /**
     * Tests whether the list is empty
     * @return boolean
     */
    boolean isEmpty();

    /**
     * Returns the first Position in the list (or null, if empty)
     * @return Position<E>
     */
    Position<E> first();

    /**
     * Returns the last Position in the list ( or null, if empty);
     * @return Position<E>
     */
    Position<E> last();

    /**
     * Returns the Position immediately before Position p (or null, if empty)
     * @param p
     * @return Positions<E>
     * @throws IllegalArgumentException
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position immediately after Position p (or null, if empty)
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Inserts element e at the front of the list and returns its new Position
     * @param e
     * @return
     */
    Position<E> addFist(E e);

    /**
     * Inserts element e at the back of the list and returns its new Position
     * @param e
     * @return
     */
    Position<E> addLast(E e);

    /**
     * Inserts the element e immediately before Position p and returns its new Position
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Inserts the element e immediately after Position p and returns its new Position
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element stored at Position p and returns the replaced Element
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Remoed the element stored at Position p and returns it (invalidating p)
     * @param p
     * @return
     * @throws IllegalArgumentException
     */
    E remove(Position<E> p) throws IllegalArgumentException;

    public Iterator<E> iterator();
}
