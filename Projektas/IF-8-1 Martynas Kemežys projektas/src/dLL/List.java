package dLL;

public interface List<E> {

    boolean add(E e);

    boolean add(int k, E e);

    E remove(int k);

    int size();

    boolean isEmpty();

    void clear();

    E get(int k);

    E getNext();

    E set(int k, E e);
}
