/**
 * Interface for Stack ADT
 * @param <E>
 */
public interface Stack<E> {
    /**
     * Size method
     * @return
     */
    int size();

    /**
     * isEmpty method
     * @return
     */
    boolean isEmpty();

    /**
     * Push method
     * @param e
     */
    void push(E e);

    /**
     * Top method
     * @return
     */
    E top();

    /**
     * Pop method
     * @return
     */
    E pop();
}
