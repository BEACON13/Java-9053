import java.util.Arrays;

public class WordReverser {

    public static String reverseSentence(String sentence) {
        String[] strings = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : strings) {
            char[] charArray = word.toLowerCase().toCharArray();
            for (int i = 0; i < charArray.length / 2; i++) {
                swap(charArray, i, charArray.length - i - 1);
            }
            sb.append(String.valueOf(charArray)).append(" ");
        }
        String str = sb.toString();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void swap(char[] word, int a, int b) {
        char temp = word[a];
        word[a] = word[b];
        word[b] = temp;
    }

    public static void main(String[] args) {
        String result = reverseSentence("The quick brown fox jumps over the lazy dog");
        System.out.println(result);
    }

}
