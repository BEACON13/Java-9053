package personnel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Personnel {

    // the ArrayList should be parameterized here. This warning
    // should go away
    public static ArrayList<Person> readPersonnel(String filename) {
        ArrayList<Person> persons = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        File file = new File(filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] attributes = line.split(",");

                String className = attributes[0];
                int id = Integer.parseInt(attributes[1]);
                String name = attributes[2];
                Date birthdate = formatter.parse(attributes[3]);

                switch (className) {
                    case "UndergraduateStudent":
                        String ugDepartment = attributes[4];
                        int year = Integer.parseInt(attributes[5]);
                        persons.add(new UndergraduateStudent(id, name, birthdate, ugDepartment, year));
                        break;
                    case "GraduateStudent":
                        String gradDepartment = attributes[4];
                        boolean phDStudent = Boolean.parseBoolean(attributes[5]);
                        persons.add(new GraduateStudent(id, name, birthdate, gradDepartment, phDStudent));
                        break;
                    case "Faculty":
                        double facultySalary = Double.parseDouble(attributes[4]);
                        String title = attributes[5];
                        String facultyDepartment = attributes[6];
                        boolean tenured = Boolean.parseBoolean(attributes[7]);
                        persons.add(new Faculty(id, name, birthdate, facultySalary, title, facultyDepartment, tenured));
                        break;
                    case "Staff":
                        double staffSalary = Double.parseDouble(attributes[4]);
                        boolean contractor = Boolean.parseBoolean(attributes[5]);
                        boolean fulltime = Boolean.parseBoolean(attributes[6]);
                        persons.add(new Staff(id, name, birthdate, staffSalary, contractor, fulltime));
                        break;
                    case "Administrator":
                        double adminSalary = Double.parseDouble(attributes[4]);
                        String adminTitle = attributes[5];
                        double budget = Double.parseDouble(attributes[6]);
                        persons.add(new Administrator(id, name, birthdate, adminSalary, adminTitle, budget));
                        break;
                    case "SpecialStudent":
                        String specialStudentDepartment = attributes[4];
                        boolean enrolled = Boolean.parseBoolean(attributes[5]);
                        persons.add(new SpecialStudent(id, name, birthdate, specialStudentDepartment, enrolled));
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected value: " + className);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Unable to read the file: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Unable to parse date " + e.getMessage());
        }
        return persons;
    }

    public static void sortAndPrint(ArrayList<Person> personnel) {
        // Sort by name
        personnel.sort(Comparator.comparing(Person::getName));
        System.out.println("Sorted by Name:");
        personnel.forEach(System.out::println);

        // Sort by birthdate
        personnel.sort(Comparator.comparing(Person::getBirthdate));
        System.out.println("\nSorted by Birthdate:");
        personnel.forEach(System.out::println);

        // Create a list of Faculty members and sort by salary
        List<Faculty> facultyList = personnel.stream()
                .filter(p -> p instanceof Faculty)
                .map(p -> (Faculty) p).sorted(Comparator.comparing(Faculty::getSalary))
                .collect(Collectors.toList());

        System.out.println("\nFaculty sorted by Salary:");
        facultyList.forEach(System.out::println);
    }


    public static void main(String[] args) {
        ArrayList<Person> personnel = readPersonnel("personnel.csv");
        System.out.println("Data from the file:");
        System.out.println(personnel);
        System.out.println("After Sorting");
        sortAndPrint(personnel);
    }
}
