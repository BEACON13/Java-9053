
public class EstimateLog {


    public static double estimateLog(double x, double tolerance) {
        double result = 0;
        int n = 1;
        while (Math.abs(Math.log(1 + x) - result) >= tolerance) {
            result += Math.pow(-1, n + 1) * Math.pow(x, n) / n;
            ++n;
        }
        //Considering the precision of the result here is 0.0001, I have displayed only four decimal places.
        System.out.println("ln(" + String.format("%.1f", 1 + x) + ") is " + String.format("%.4f", result));
        System.out.println("It requires " + n + " iterations to estimate ln(" + String.format("%.1f", 1 + x) + ") within .0001");
        return result;

    }

    public static void main(String[] args) {
        double tolerance = 0.0001;
        for (double i = 0.0; i <= 0.9; i += 0.1) {
            double result = estimateLog(i, tolerance);
        }
    }

}
