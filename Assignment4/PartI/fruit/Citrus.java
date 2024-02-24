package fruit;

import java.util.Objects;

/**
 * @author Beacon
 * @date 10/2/23 5:13 PM
 */
public class Citrus extends Fruit {

    private String taste;

    public Citrus() {
        super();
    }

    public Citrus(String taste, String color, Boolean rotten) {
        super(color, rotten);
        this.taste = taste;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citrus)) return false;
        if (!super.equals(o)) return false;
        Citrus citrus = (Citrus) o;
        return Objects.equals(taste, citrus.taste);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taste);
    }

    public String toString() {
        return super.toString() +
                ", taste='" + taste + '\'';
    }
}
