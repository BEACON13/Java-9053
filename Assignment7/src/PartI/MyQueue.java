package PartI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyQueue<E> implements Queue<E> {

    private ArrayList<E> ar;

    public MyQueue() {
        ar = new ArrayList<>();
    }

    @Override
    public int size() {
        return ar.size();
    }

    @Override
    public boolean isEmpty() {
        return ar.isEmpty();
    }

    @Override
    public boolean offer(E e) {
        return ar.add(e);
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return ar.remove(0);
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return ar.remove(0);
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return ar.get(0);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return ar.get(0);
    }

    @Override
    public boolean contains(Object o) {
        return ar.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return ar.iterator();
    }

    @Override
    public Object[] toArray() {
        return ar.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return ar.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return ar.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return ar.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return ar.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return ar.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return ar.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return ar.retainAll(c);
    }

    @Override
    public void clear() {
        ar.clear();
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();

        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);

        System.out.println("size: " + myQueue.size());
        System.out.println("isEmpty: " + myQueue.isEmpty());

        System.out.println("element: " + myQueue.element());
        System.out.println("peek: " + myQueue.peek());


        System.out.println("poll: " + myQueue.poll());

        System.out.println(myQueue.element());
    }
}
