import java.util.Arrays;

class Permutations {

    //use to track row number in backtrack
    private static int currentIndex = 0;

    public static int getFactorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void backtrack(int[][] permutations, int[] array, int cur) {
        if (cur == array.length) {
            //finish a permutation, add it to the result
            System.arraycopy(array, 0, permutations[currentIndex], 0, array.length);
            currentIndex++;
        } else {
            for (int i = cur; i < array.length; i++) {
                swap(array, i, cur);
                backtrack(permutations, array, cur + 1);
                swap(array, i, cur);
            }
        }
    }

    public static int[][] getPermutations(int[] array) {
        //The number of permutations of an array is equal to the factorial of its length
        int[][] permutations = new int[getFactorial(array.length)][array.length];
        backtrack(permutations, array, 0);
        return permutations;
    }


    public static void main(String[] args) {
        int[] startingArray = {1, 2, 3, 4, 5};
        int[][] result = getPermutations(startingArray);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }
}
