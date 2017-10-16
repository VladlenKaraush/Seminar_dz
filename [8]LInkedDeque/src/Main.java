import java.util.NoSuchElementException;

class LinkedDeque<T>{

    private int size;
    private Node first;
    private Node last;

    public LinkedDeque() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(T t) {
        throwIfNull(t);

        Node newFirst = new Node();
        newFirst.t = t;

        if (first != null) {
            newFirst.next = first;
            first.previous = newFirst;
        }
        first = newFirst;
        if (last == null) last = first;

        size++;
    }

    public T removeFirst() {
        throwIfEmpty();

        Node oldFirst = first;
        first = first.next;

        if (first == null)
            last = null;
        else
            first.previous = null;

        size--;

        return oldFirst.t;
    }

    public void addLast(T t) {
        throwIfNull(t);

        Node newLast = new Node();
        newLast.t = t;

        if (last != null) {
            newLast.previous = last;
            last.next = newLast;
        }
        last = newLast;
        if (first == null) first = last;

        size++;
    }

    public T removeLast() {
        throwIfEmpty();

        Node oldLast = last;
        last = oldLast.previous;

        if (last == null)
            first = null;
        else
            last.next = null;

        size--;

        return oldLast.t;
    }

    private void throwIfEmpty() {
        if (first == null)
            throw new NoSuchElementException();
    }

    private void throwIfNull(T t) {
        if (t == null)
            throw new NullPointerException();
    }

    private class Node {
        T t;
        Node next;
        Node previous;
    }

}

public class Main {
    public static void main(String[] args) {
        LinkedDeque<String> d = new LinkedDeque<>();
        d.addFirst("q");
        d.addFirst("w");
        d.addLast("e");
        System.out.println(d.removeLast());
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
    }
}
