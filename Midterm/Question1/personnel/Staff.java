package personnel;


import java.util.Date;
import java.util.Objects;

public class Staff extends Employee {
    private boolean contractor;

    private boolean fulltime;

    public Staff() {
        super();
    }

    public Staff(boolean contractor, boolean fulltime) {
        this.contractor = contractor;
        this.fulltime = fulltime;
    }

    public Staff(double salary, boolean contractor, boolean fulltime) {
        super(salary);
        this.contractor = contractor;
        this.fulltime = fulltime;
    }

    public Staff(int id, String name, Date birthDate, double salary, boolean contractor, boolean fulltime) {
        super(id, name, birthDate, salary);
        this.contractor = contractor;
        this.fulltime = fulltime;
    }

    public boolean isContractor() {
        return contractor;
    }

    public void setContractor(boolean contractor) {
        this.contractor = contractor;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        if (!super.equals(o)) return false;
        Staff staff = (Staff) o;
        return isContractor() == staff.isContractor() && isFulltime() == staff.isFulltime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isContractor(), isFulltime());
    }

    @Override
    public String toString() {
        return "Staff{" +
                "contractor=" + contractor +
                ", fulltime=" + fulltime +
                ", " + super.toString() +
                '}';
    }
}
