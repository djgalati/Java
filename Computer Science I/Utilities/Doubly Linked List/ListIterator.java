package doublylinkedlistapp;

public interface ListIterator<T> {

    void add(T e);

    boolean hasNext();

    boolean hasPrevious();

    T next();

    T previous();

    void remove();

    void set(T e);

}
