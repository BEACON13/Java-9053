package fruit;

import java.util.Objects;

/**
 * @author Beacon
 * @date 10/2/23 4:39 PM
 */
public class Apple extends Fruit {

    private String taste;

    private String texture;

    public Apple() {
        super();
    }

    public Apple(String taste, String texture, String color, Boolean rotten) {
        super(color, rotten);
        this.taste = taste;
        this.texture = texture;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apple)) return false;
        if (!super.equals(o)) return false;
        Apple apple = (Apple) o;
        return Objects.equals(taste, apple.taste) && Objects.equals(texture, apple.texture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taste, texture);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", taste='" + taste + '\'' +
                ", texture='" + texture + '\'';
    }
}
