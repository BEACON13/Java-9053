package personnel;

import java.util.Date;
import java.util.Objects;

public class GraduateStudent extends Student {
    private boolean phDStudent;

    public GraduateStudent(boolean phDStudent) {
        super();
        this.phDStudent = phDStudent;
    }

    public GraduateStudent(String department, boolean phDStudent) {
        super(department);
        this.phDStudent = phDStudent;
    }

    public GraduateStudent(int id, String name, Date birthDate, String department, boolean phDStudent) {
        super(id, name, birthDate, department);
        this.phDStudent = phDStudent;
    }

    public boolean getPhDStudent() {
        return phDStudent;
    }

    public void setPhDStudent(boolean phDStudent) {
        this.phDStudent = phDStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraduateStudent)) return false;
        if (!super.equals(o)) return false;
        GraduateStudent that = (GraduateStudent) o;
        return Objects.equals(getPhDStudent(), that.getPhDStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPhDStudent());
    }

    @Override
    public String toString() {
        return "GraduateStudent{" +
                "phDStudent=" + phDStudent +
                ", " + super.toString() +
                '}';
    }
}
