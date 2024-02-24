public class PressureChange {


    public static void main(String[] arguments) {

        double fD = .04;
        double diameter = .0254;
        double dyanmicViscosity = 8.9E-4;
        double flowRate = 5E-4;
        double length = 5;
        double pressureChange = 0;
        double pi = Math.PI;

        pressureChange = (128.0 * dyanmicViscosity * flowRate * length) / (pi * Math.pow(diameter, 4));

        System.out.println("Water's change in pressure over a length of "
                + length +
                " m is " + pressureChange + " pascals");
    }
}