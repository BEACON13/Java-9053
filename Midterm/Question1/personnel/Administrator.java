package personnel;


import java.util.Date;
import java.util.Objects;

public class Administrator extends Employee{
    private String title;

    private double budget;

    public Administrator(){
        super();
    }

    public Administrator(String title, double budget) {
        super();
        this.title = title;
        this.budget = budget;
    }

    public Administrator(double salary, String title, double budget) {
        super(salary);
        this.title = title;
        this.budget = budget;
    }

    public Administrator(int id, String name, Date birthDate, double salary, String title, double budget) {
        super(id, name, birthDate, salary);
        this.title = title;
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        if (!super.equals(o)) return false;
        Administrator that = (Administrator) o;
        return Double.compare(budget, that.budget) == 0 && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, budget);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "title='" + title + '\'' +
                ", budget=" + budget +
                ", " + super.toString() +
                '}';
    }
}
