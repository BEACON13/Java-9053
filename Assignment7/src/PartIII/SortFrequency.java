package PartIII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortFrequency {

    public static void sortByFrequency(ArrayList<Integer> ar) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : ar) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        /*
         * I provided two different sort here.
         * The second one have an extra part "thenComparingInt(o -> o)"
         *
         * With the first sort, we will find some numbers aren’t “sorted”.
         * If two different number have the same frequency,the order of them is not guaranteed to be sorted.
         * because our original sorting rules only focus on frequency.
         *
         * For example if we have [1, 3, 3, 2, 2, 4]
         * The result can be either [1, 4, 3, 3, 2, 2] or [1, 4, 2, 3, 2, 3]
         * Because 2 and 3 have exactly the same frequency, we just make sure they are on the right of 1 and 4.
         *
         * I add this part "thenComparingInt(o -> o)", so if two number have the same frequency,
         * they will be sorted again according the number itself.
         */

        //1 original one according to the instruction of assignment
        ar.sort(Comparator.comparingInt(map::get));

        //2
        //ar.sort(Comparator.comparingInt((Integer o) -> map.get(o)).thenComparingInt(o -> -o));
    }

    public static void main(String[] args) {
        ArrayList<Integer> ar = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            ar.add((int) (Math.random() * 10));
        }
        System.out.println(ar.toString());
        sortByFrequency(ar);
        System.out.println(ar.toString());
    }
}
