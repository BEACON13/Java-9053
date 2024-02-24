
public class Dog {
    private int age;
    private String owner;
    private String breed;

    public Dog() {
    }

    public Dog(int age, String owner, String breed) {
        this.age = age;
        this.owner = owner;
        this.breed = breed;
    }

    public static boolean hasSameOwner(Dog d1, Dog d2) {
        return d1.getOwner().equals(d2.getOwner());
    }

    public static double avgAge(Dog[] dogs) {
        double sumAge = 0;
        for (Dog dog : dogs) {
            sumAge += dog.getAge();
        }
        return sumAge / dogs.length;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String toString() {
        return this.breed + ": " + "Owner: " + this.getOwner() + ", Age:" + this.getAge();
    }

    public static void main(String[] args) {
        //f.create a Dog instance
        Dog dog = new Dog(8,"Dexter Morgan","Corgi");

        Dog[] dogs = new Dog[5];
        dogs[0] = new Dog(4, "Stephen Colbert", "Boxer");
        dogs[1] = new Dog(2, "Jack", "Akita");
        dogs[2] = new Dog(7, "Tom","Shepherd" );
        dogs[3] = new Dog(6, "Tom", "Husky");
        dogs[4] = dog;

        System.out.println("b. show how hasSameOwner() is used");
        //should print false
        System.out.println(Dog.hasSameOwner(dogs[0],dogs[2]));
        //should print true
        System.out.println(Dog.hasSameOwner(dogs[2], dogs[3]));

        //should be 5.4
        System.out.println("c. show how agvAge() is used");
        System.out.println(Dog.avgAge(dogs));

        System.out.println("e. show toString()");
        System.out.println(dogs[0].toString());

        System.out.println(dog);
    }
}