package dLL;

public class DoubleLinkedList<E extends Comparable<E>>
        implements List<E>, Iterable<E>, Cloneable {

    private Node<E> first;   // rodyklė į pirmutinį mazgą
    private Node<E> last;    // rodyklė į paskutinį mazgą
    private Node<E> current; // rodyklė į einamą mazgą
    private int size;        // sąrašo dydis

    public DoubleLinkedList() {
    }
    
    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;   // nuliniai objektai nepriimami
        }
        if (first == null) {
            first = new Node<>(e, first, last);
            last = first;
        } else {
            Node<E> e1 = new Node(e, null, last);
            last.next = e1;
            last = e1;
        }
        size++;
        return true;
    }
    public List<E> subList(int fromIndex, int toIndex) 
    { 
        DoubleLinkedList list = new DoubleLinkedList(); 
        Node<E> test = null; 
        if(fromIndex > toIndex || toIndex > size) return null; 
        for (int i = fromIndex; i <= toIndex; i++) { 
           test = first.findNode(i); 
           list.add(test.element); 
        } 
        return list; 
    } 
     public E removeFirst() {
        if (size == 0) throw new IndexOutOfBoundsException("List empty!");
        Node<E> tmp = first;
        first = first.next;
        first.previuos = null;
        size--;
        return tmp.element;
    }
     public E removeLast() {
        if (size == 0) throw new IndexOutOfBoundsException("List empty!");
        Node<E> tmp = last;
        last = last.previuos;
        last.next = null;
        size--;
        return tmp.element;
    }
    public boolean addFirst(E e) {
        if(e == null) return false;
        Node<E> tmp = new Node(e, first, null);
        if(first != null ) {first.previuos = tmp;}
        first = tmp;
        if(last == null) { last = tmp;}
        size++;
        return true;
    }
    public E findPreviuos(E e){
        if(e == null) return null;
        Node<E> actual = null, before = null;
        for (int i = 0; i < size; i++) { 
            actual = first.findNode(i); 
            if(actual.element.equals(e)) 
            {
             before = first.findNode(i-1);
             return before.element;
            } 
        } 
        return null;
    }
        public E findBefore(E e) {
        if(e == null) return null;
            for (int i = 0; i < size; i++) { 
            if(first.findNode(i).element.equals(e)) return first.findNode(i).previuos.element;
            } 
        return null;
        }

    public boolean removeLastOccurrence(E e) { 
        Node<E> actual = null, einamasis = null; 
        if (e == null) { return false;} 
        for (int i = 0; i < size; i++) { 
            einamasis = first.findNode(i); 
            if(einamasis.element.equals(e) && e != first.element) 
            { 
             actual = einamasis;   //prieš audi 
            } 
        } 
        if(actual.previuos == null)//jei neturi previuos tai reiškias pirmas buvo
        { 
            first = first.next; 
            size --; 
            return true; 
        } 
        if (actual.next == null)//jeigu neturi next reiškias paskutinis buvo
        { 
        last = null; 
        size --; 
        return true; 
        } 
        actual.previuos.next = actual.next;
        actual.next.previuos = actual.previuos;
    size--; 
    return true; 
    } 
    public boolean addLast(E e) { 
        if (e == null) { 
            return false;
        } 
        add(e);
        return true;
    } 
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
        current = null;
    }
    public boolean FromRigthSide(int k) {
        if( (size / 2) > k)
        {
        return true;
        }
        return false;
    }
    @Override
    public E get(int k) {
        if (k < 0 || k >= size) {
            return null;
        }
        current = first.findNode(k);
        return current.element;
    }

    @Override
    public E set(int k, E e) {
        if (k < 0 || k >= size)
            return null;

        Node<E> ref = first.findNode(k);
        E old = ref.element;
        ref.element = e;
        return old;
    }
    @Override
    public boolean add(int k, E e) {
        if (e == null || k < 0 || k >= size)
            return false;

        if (k == 0) {
            first = new Node(e, first, last);
            return true;
        }

        Node<E> ref = first;
        for (int i = 0; i < k - 1; i++)
            ref = ref.next;

        ref.next = new Node(e, ref.next, last);
        return true;
    }
    @Override
    public E getNext() {
        if (current == null) {
            return null;
        }
        current = current.next;
        if (current == null) {
            return null;
        }
        return current.element;
    }
    @Override
    public E remove(int k) {
        if (k < 0 || k >= size)
            throw new IndexOutOfBoundsException("Index was outside the bounds of list or list is empty");

        Node<E> ref = first;
        E element = null;
        for (int i = 0; i < k - 1; i++)
            ref = ref.next;

        if (k == 0) {
            element = ref.element;
            first = ref.next;
        } else {
            element = ref.next.element;
            ref.next = ref.next.next;
        }
        size--;
        return element;
    }

    @Override
    public DoubleLinkedList<E> clone() {
        DoubleLinkedList<E> cl = null;
        try {
            cl = (DoubleLinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            Ks.ern("Blogai veikia metodas clone()");
            System.exit(1);
        }
        if (first == null) {
            return cl;
        }
        cl.first = new Node<>(this.first.element, null, null);
        Node<E> e2 = cl.first;
        for (Node<E> e1 = first.next; e1 != null; e1 = e1.next) {
            e2.next = new Node<>(e2.element, null, null);
            e2 = e2.next;
            e2.element = e1.element;
        }
        cl.last = e2.next;
        cl.size = this.size;
        return cl;
    }

    public Object[] toArray() {
        Object[] a = new Object[size];
        int i = 0;
        for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
            a[i++] = e1.element;
        }
        return a;
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new Iterator();
    }

    class Iterator implements java.util.Iterator<E> {

        private Node<E> iterPosition;

        Iterator() {
            iterPosition = first;
        }

        @Override
        public boolean hasNext() {
            return iterPosition != null;
        }

        @Override
        public E next() {
            E d = iterPosition.element;
            iterPosition = iterPosition.next;
            return d;
        }
        public E previuos() {
            E d = iterPosition.element;
            iterPosition = iterPosition.previuos;
            return d;
        }

        @Override
        public void remove() { 
            if (iterPosition == null) throw new IllegalStateException();
            Node x = iterPosition.previuos;
            Node y = iterPosition.next;
            x.next = y;
            y.previuos = x;
            if (current == iterPosition)
                current = y;
            else
                size--;
            iterPosition = null;
        }
    }

    private static class Node<E> {

        private E element;    // ji nematoma už klasės ListKTU ribų
        private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą
        private Node<E> previuos;

        Node(E data, Node<E> next, Node<E> previuos) { //mazgo konstruktorius
            this.element = data;
            this.next = next;
            this.previuos = previuos;
        }

        public Node<E> findNode(int k) {
            Node<E> e = this;
            for (int i = 0; i < k; i++) {
                e = e.next;
            }
            return e;
        }
        public Node<E> findPreviuos(int k) {
            Node<E> e = this;
            for (int i = 0; i < k; i++) {
                e = e.previuos;
            }
            return e;
        }
    } 
}
