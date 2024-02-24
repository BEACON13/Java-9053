package personnel;

import java.util.Date;
import java.util.Objects;

public abstract class Employee extends Person {
    private double salary;

    public Employee(){
        super();
    }

    public Employee(double salary) {
        super();
        this.salary = salary;
    }

    public Employee(int id, String name, Date birthDate, double salary) {
        super(id, name, birthDate);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(getSalary(), employee.getSalary()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSalary());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", " + super.toString() +
                '}';
    }
}
