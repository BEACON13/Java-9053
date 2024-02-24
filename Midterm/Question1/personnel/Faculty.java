package personnel;


import java.util.Date;
import java.util.Objects;

public class Faculty extends Employee {
    private String title;

    private String department;

    private boolean tenured;

    public Faculty() {
        super();
    }

    public Faculty(double salary, String title, String department, boolean tenured) {
        super(salary);
        this.title = title;
        this.department = department;
        this.tenured = tenured;
    }

    public Faculty(int id, String name, Date birthDate, double salary, String title, String department, boolean tenured) {
        super(id, name, birthDate, salary);
        this.title = title;
        this.department = department;
        this.tenured = tenured;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isTenured() {
        return tenured;
    }

    public void setTenured(boolean tenured) {
        this.tenured = tenured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        if (!super.equals(o)) return false;
        Faculty faculty = (Faculty) o;
        return isTenured() == faculty.isTenured() && Objects.equals(getTitle(), faculty.getTitle()) && Objects.equals(getDepartment(), faculty.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getDepartment(), isTenured());
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", tenured=" + tenured +
                ", " + super.toString() +
                '}';
    }
}
