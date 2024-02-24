package fruit;

import java.util.Objects;

/**
 * @author Beacon
 * @date 10/2/23 5:26 PM
 */
public class Orange extends Citrus {

    private String type;

    public Orange() {
        super();
    }

    public Orange(String type, String taste, Boolean rotten) {
        super(taste, "orange", rotten);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orange)) return false;
        if (!super.equals(o)) return false;
        Orange orange = (Orange) o;
        return Objects.equals(type, orange.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", type='" + type + '\'';
    }
}
