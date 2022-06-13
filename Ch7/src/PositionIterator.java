import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class should be nested within LinkedPositionalList
 * @param <E>
 */
private class PositionIterator<E> implements Iterator<Position<E>> {
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
        recent == null;
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