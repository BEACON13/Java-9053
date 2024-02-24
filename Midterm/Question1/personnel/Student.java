package personnel;

import java.util.Date;
import java.util.Objects;

public abstract class Student extends Person {
    private String department;

    public Student() {
        super();
    }

    public Student(String department) {
        super();
        this.department = department;
    }

    public Student(int id, String name, Date birthDate, String department) {
        super(id, name, birthDate);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(getDepartment(), student.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDepartment());
    }

    @Override
    public String toString() {
        return "Student{" +
                "department='" + department + '\'' +
                ", " + super.toString() +
                '}';
    }
}
