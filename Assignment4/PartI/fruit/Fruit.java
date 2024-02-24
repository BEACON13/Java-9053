package fruit;

import java.util.Objects;

/**
 * @author Beacon
 * @date 10/2/23 4:31 PM
 */
public class Fruit {

    private String color;

    private Boolean rotten;

    private static int nextId = 1;

    private int id;

    public Fruit() {
        id = nextId;
        nextId++;
    }

    public Fruit(String color, Boolean rotten) {
        this.color = color;
        this.rotten = rotten;
        id = nextId;
        nextId++;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean isRotten() {
        return rotten;
    }

    public void setRotten(Boolean rotten) {
        this.rotten = rotten;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fruit)) return false;
        Fruit fruit = (Fruit) o;
        return Objects.equals(color, fruit.color) && Objects.equals(rotten, fruit.rotten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, rotten);
    }

    @Override
    public String toString() {
        return this.getClass() +
                ", id=" + id +
                ", color='" + color + '\'' +
                ", rotten=" + rotten;
    }
}
