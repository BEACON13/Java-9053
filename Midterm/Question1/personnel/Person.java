package personnel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Person {
    private int id;
    private String name;
    private Date birthdate;

    public Person() {
    }

    public Person(int id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && Objects.equals(getName(), person.getName()) && Objects.equals(getBirthdate(), person.getBirthdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthdate());
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(birthdate);
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + formattedDate +
                '}';
    }
}
