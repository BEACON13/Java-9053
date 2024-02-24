package fruit;

public class Inheritance {

    public static void main(String[] args) {
        // Here's some scratch space to experiment/debug your Fruit objects
        Fruit fruit = new Fruit("red", false);
        fruit.getColor();
        fruit.getId();
        System.out.println(fruit);
        Fruit fruit1 = new Fruit("yellow", true);
        System.out.println(fruit1);

        System.out.println("------------APPLE------------");
        Fruit fruit2 = new Apple("good", "tex", "red", false);
        System.out.println(fruit2);
        Apple apple = new Apple("good apple", "texA", "green", false);
        System.out.println(apple);

        System.out.println("------------Citrus-------------");
        Fruit fruit3 = new Citrus("good", "yellow", false);
        System.out.println(fruit3);
        Citrus citrus = new Citrus("bad", "y", true);
        System.out.println(citrus);

        System.out.println("--------------Orange--------------");
        Fruit fruit4 = new Orange("a", "sweet", true);
        System.out.println(fruit4);
        Citrus citrus2 = new Orange("c", "sour", false);
        System.out.println(citrus2);
        Orange orange = new Orange("b", "sour", false);
        System.out.println(orange);

        System.out.println("--------------Lemon--------------");
        Fruit fruit5 = new Lemon(2, "sweet", true);
        System.out.println(fruit5);
        Citrus citrus3 = new Lemon(5, "sour", false);
        System.out.println(citrus3);
        Lemon lemon = new Lemon(5, "sour", false);
        System.out.println(lemon);

        System.out.println(citrus3.equals(lemon));
        System.out.println(lemon.equals(citrus3));
        System.out.println(fruit5.equals(lemon));
        System.out.println(lemon.equals(lemon));
    }

}
