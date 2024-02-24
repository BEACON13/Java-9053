package arraylists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import fruit.*;

public class FruitArraylist {

    // b.calculate the average sourness of all the lemon objects
    public static double averageSourness(List<Fruit> fruitList) {
        int sumSourness = 0;
        int countLemon = 0;
        for (Fruit fruit : fruitList) {
            if (fruit instanceof Lemon) {
                sumSourness += ((Lemon) fruit).getSourness();
                countLemon++;
            }
        }
        if (countLemon == 0) {
            return 0.0;
        } else {
            return sumSourness * 1.0 / countLemon;
        }
    }

    //c.remove the matching objects
    public static void removeMatchingObject(List<Fruit> fruitList, Fruit target) {
        Iterator<Fruit> iterator = fruitList.iterator();
        while (iterator.hasNext()) {
            Fruit fruit = iterator.next();
            if (target.equals(fruit)) {
                if (target.getId() == fruit.getId()) {
                    System.out.println("The same object: " + fruit.toString());
                } else {
                    System.out.println("Equal in value: " + fruit.toString());
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {

        // this ArrayList MUST be parameterized
        ArrayList<Fruit> fruitArrayList = new ArrayList<>();

        // this is the variable you should retain to compare
        // to the other objects in the arraylist

        //a
        Apple apple1 = new Apple("sweet", "crisp", "red", false);

        Apple apple2 = new Apple("tart", "soft", "green", true);
        Apple apple3 = new Apple("tart", "soft", "green", true);

        Random random = new Random();
        Lemon lemon1 = new Lemon(random.nextInt(101), "sour", false);
        Lemon lemon2 = new Lemon(random.nextInt(101), "sour", false);
        Lemon lemon3 = new Lemon(random.nextInt(101), "sour", false);

        Orange orange1 = new Orange("mandarin", "sweet", true);
        Orange orange2 = new Orange("mandarin", "sweet", true);

        fruitArrayList.add(apple1);
        fruitArrayList.add(apple2);
        fruitArrayList.add(apple3);
        fruitArrayList.add(lemon1);
        fruitArrayList.add(lemon2);
        fruitArrayList.add(lemon3);
        fruitArrayList.add(orange1);
        fruitArrayList.add(orange2);

        //b
        System.out.println("The average sourness of all the lemon is " + averageSourness(fruitArrayList));

        //c
        removeMatchingObject(fruitArrayList, apple2);

        //d
        System.out.println("New list after removing rotten apples");
        fruitArrayList.forEach(System.out::println);
    }
}
