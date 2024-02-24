package personnel;


import java.util.Date;
import java.util.Objects;

public class SpecialStudent extends Student{
    private boolean enrolled;

    public SpecialStudent(boolean enrolled) {
        super();
        this.enrolled = enrolled;
    }

    public SpecialStudent(String department, boolean enrolled) {
        super(department);
        this.enrolled = enrolled;
    }

    public SpecialStudent(int id, String name, Date birthDate, String department, boolean enrolled) {
        super(id, name, birthDate, department);
        this.enrolled = enrolled;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialStudent)) return false;
        if (!super.equals(o)) return false;
        SpecialStudent that = (SpecialStudent) o;
        return isEnrolled() == that.isEnrolled();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isEnrolled());
    }

    @Override
    public String toString() {
        return "SpecialStudent{" +
                "enrolled=" + enrolled +
                ", " + super.toString() +
                '}';
    }
}
