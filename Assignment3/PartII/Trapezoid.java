
public class Trapezoid {
    private static int nextId = 1;

    private int id;

    private double base1;

    private double base2;

    private double height;

    public Trapezoid() {
        this.id = nextId;
        nextId++;
    }

    public Trapezoid(double base1, double base2, double height) {
        this.id = nextId;
        nextId++;
        this.base1 = base1;
        this.base2 = base2;
        this.height = height;
    }

    public double getBase1() {
        return base1;
    }

    public void setBase1(double base1) {
        this.base1 = base1;
    }

    public double getBase2() {
        return base2;
    }

    public void setBase2(double base2) {
        this.base2 = base2;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return (base1 + base2) * height / 2;
    }

    public int getID() {
        return id;
    }

    public static void main(String[] args) {
        Trapezoid a = new Trapezoid();
        System.out.println(a.getID());
        Trapezoid b = new Trapezoid(1.0, 2.0, 5.0);
        System.out.println(b.getID());
        System.out.println(b.getArea());
        Trapezoid c = new Trapezoid(2.0, 3.0, 4.0);
        System.out.println(c.getID());
        System.out.println(c.getArea());
    }
}
