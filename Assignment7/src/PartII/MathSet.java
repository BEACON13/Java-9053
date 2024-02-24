package PartII;

import java.util.HashSet;
import java.util.Set;

public class MathSet<E> extends HashSet<E> {

    public Set<E> intersection(Set<E> s2) {
        Set<E> set = new HashSet<>(this);
        set.retainAll(s2);
        return set;
    }

    public Set<E> union(Set<E> s2) {
        Set<E> union = new HashSet<>(this);
        union.addAll(s2);
        return union;
    }

    public <T> Set<Pair<E, T>> cartesianProduct(Set<T> s2) {
        Set<Pair<E, T>> product = new HashSet<>();
        for (E item1 : this) {
            for (T item2 : s2) {
                product.add(new Pair<>(item1,item2));
            }
        }
        return product;
    }

    public static void main(String[] args) {
        // create two MathSet objects of the same type.
        // See if union and intersection works.
        MathSet<Integer> set1 = new MathSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);

        MathSet<Integer> set2 = new MathSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);

        System.out.println("Intersection: " + set1.intersection(set2));
        System.out.println("Union: " + set1.union(set2));

        // create another MathSet object of a different type
        // calculate the cartesian product of this set with one of the
        // above sets
        Set<String> set3 = new HashSet<>();
        set3.add("A");
        set3.add("B");

        System.out.println("Cartesian Product: " + set1.cartesianProduct(set3));
    }
}
