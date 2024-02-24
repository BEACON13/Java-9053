import java.util.Scanner;

public class RestaurantMenu {

    public static double itemCost(char item) {
        double cost = 0.0;
        switch (item) {
            case 'H':
                cost = 5.25;
                break;
            case 'F':
                cost = 2.5;
                break;
            case 'C':
                cost = 7.0;
                break;
            case 'M':
                cost = 3.75;
                break;
            case 'B':
                cost = 6.25;
                break;
            case 'S':
                cost = 1.25;
                break;
            default:
                break;
        }
        return cost;
    }

    public static void main(String[] args) {

        /* you probably want to use user input for the
         * menu item using Scanner
         * I've written some code that will get the
         * menu item code and read it in as a char
         */
        double totalCost = 0.0;
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter menu item: ");
            char item = in.next().charAt(0);
            if (item == 'X') {
                break;
            } else {
                double cost = itemCost(item);
                if (cost != 0.0) {
                    totalCost += cost;
                    String formattedNumber = (totalCost % 1 == 0) ? String.format("%.0f", totalCost) : String.valueOf(totalCost);
                    System.out.println("Total = $" + formattedNumber);
                } else {
                    System.out.println("Invalid menu item");
                }
            }
        }
        String formattedNumber = (totalCost % 1 == 0) ? String.format("%.0f", totalCost) : String.valueOf(totalCost);
        System.out.println("Order complete. Total is $" + formattedNumber);
    }
}
