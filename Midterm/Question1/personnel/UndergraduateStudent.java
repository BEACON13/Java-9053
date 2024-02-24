package personnel;

import java.util.Date;
import java.util.Objects;

public class UndergraduateStudent extends Student {
    private int year;

    public UndergraduateStudent() {
        super();
    }

    public UndergraduateStudent(String department, int year) {
        super(department);
        this.year = year;
    }

    public UndergraduateStudent(int id, String name, Date birthDate, String department, int year) {
        super(id, name, birthDate, department);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UndergraduateStudent)) return false;
        if (!super.equals(o)) return false;
        UndergraduateStudent that = (UndergraduateStudent) o;
        return getYear() == that.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getYear());
    }

    @Override
    public String toString() {
        return "UndergraduateStudent{" +
                "year=" + year +
                ", " + super.toString() +
                '}';
    }
}
