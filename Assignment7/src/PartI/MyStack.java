package PartI;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;


public class MyStack<E> {

    private ArrayList<E> ar;

    public MyStack() {
        ar = new ArrayList<E>();
    }

    public boolean isEmpty() {
        return ar.isEmpty();
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return ar.get(ar.size() - 1);
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return ar.remove(ar.size() - 1);
    }

    public E push(E item) {
        ar.add(item);
        return item;
    }

    public int search(Object o) {
        for (int i = ar.size() - 1; i >= 0; i--) {
            if (ar.get(i).equals(o)) {
                return ar.size() - i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.peek());
        System.out.println(stack.search(1));
        System.out.println(stack.pop());
        System.out.println(stack.peek());

        System.out.println("-------------MyStack--------------");

        MyStack<Integer> ms = new MyStack();

        ms.push(1);
        ms.push(2);
        ms.push(3);

        System.out.println(ms.peek());
        System.out.println(ms.search(1));
        System.out.println(ms.pop());
        System.out.println(ms.peek());
    }
}
