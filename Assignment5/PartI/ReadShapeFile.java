import java.io.*;
import java.util.ArrayList;

import shapes.*;

/* your tasks:
 * create a class called ShapeException
 * createShape should throw a ShapeException
 * in main(), you should catch the ShapeException
 *
 */
public class ReadShapeFile {

    public static GeometricObject createShape(String shape) throws ShapeException {

        /* if vehicleName is "Circle" return Circle();
         * if vehicleName is "Rectangle" return Rectangle();
         * if vehicleName is "Square" return Square();
         * if it is not any one of these, it should throw
         * a ShapeException
         */
        GeometricObject res;
        if (shape.equals("Circle")) {
            res = new Circle();
        } else if (shape.equals("Rectangle")) {
            res = new Rectangle();
        } else if (shape.equals("Square")) {
            res = new Square();
        } else {
            throw new ShapeException();
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<GeometricObject> shapeList = new ArrayList<GeometricObject>();
        File f = new File("shapes.txt");

        String inString = null;

        /* create a loop to read the file line-by-line */

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            inString = br.readLine();
            while (inString != null) {
                try {
                    GeometricObject shape = createShape(inString);
                    shapeList.add(shape);
                } catch (ShapeException e) {
                    System.err.println("Cannot create Shape: " + inString);
                }finally {
                    inString = br.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Unable to read the file: " + e.getMessage());
        }

        System.out.println(shapeList);
        System.out.println(shapeList.size());
    }
}