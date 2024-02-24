import java.io.*;
import java.util.ArrayList;

public class ListOfNumbers {

    private ArrayList<RDFTriple<Integer, Integer, Integer>> rdfTripleList;
    private String fileName;

    public ListOfNumbers() {
        // create an ArrayList of Pairs of Integers
        this.rdfTripleList = new ArrayList<>();
    }

    public ArrayList<RDFTriple<Integer, Integer, Integer>> getRdfTripleList() {
        return this.rdfTripleList;
    }

    public void createList() {
        for (int i = 0; i < 100; i++) {
            Integer number1 = (int) (Math.random() * 10000);
            Integer number2 = (int) (Math.random() * 10000);
            Integer number3 = (int) (Math.random() * 10000);
            // fill the existing list with RDFTriple objects
            // of three numbers.
            RDFTriple<Integer, Integer, Integer> item = new RDFTriple<>(number1, number2, number3);
            this.rdfTripleList.add(item);
        }
    }


    public ListOfNumbers(String fileName) {
        this();
        this.fileName = fileName;
    }

    public void readList() {
        rdfTripleList = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("numberfile.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] nums = line.split("\\s+");
                if (nums.length == 3) {
                    Integer number1 = Integer.parseInt(nums[0]);
                    Integer number2 = Integer.parseInt(nums[1]);
                    Integer number3 = Integer.parseInt(nums[2]);

                    RDFTriple<Integer, Integer, Integer> triple = new RDFTriple<>(number1, number2, number3);
                    rdfTripleList.add(triple);

                    System.out.println(number1 + " " + number2 + " " + number3);
                } else {
                    System.err.println("Error: Expected 3 numbers on line, got " + nums.length);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error closing reader: " + e.getMessage());
                }
            }
        }
    }


    public void writeList() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("outFile.txt"));
            for (int i = 0; i < rdfTripleList.size(); i++)
                out.println(rdfTripleList.get(i).getSubj() + " " + rdfTripleList.get(i).getPred() + " " + rdfTripleList.get(i).getObj());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }

//    public static void cat(String fileName) {
//        RandomAccessFile input = null;
//        String line = null;
//        File file = new File(fileName);
//        try {
//            input = new RandomAccessFile(file, "r");
//            while ((line = input.readLine()) != null) {
//                System.out.println(line);
//            }
//            return;
//        } finally {
//            if (input != null) {
//                input.close();
//            }
//        }
//    }

    public static void main(String[] args) {
        ListOfNumbers listOfNumbers = new ListOfNumbers("numberfile.txt");
        //ListOfNumbers.cat("numberfile.txt");
        listOfNumbers.createList();
        listOfNumbers.writeList();
        listOfNumbers.readList();
    }

}
