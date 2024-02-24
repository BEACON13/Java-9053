
public class Encoder {


    public static String encodeNumeric(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                int num = c - 'a' + 1;
                sb.append(num).append(".");
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0 && (sb.charAt(sb.length() - 1) == '.' || sb.charAt(sb.length() - 1) == ' ')) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String encodeROT13(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                char shift = (char) (((c - 'a' + 13) % 26) + 'a');
                sb.append(shift);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
