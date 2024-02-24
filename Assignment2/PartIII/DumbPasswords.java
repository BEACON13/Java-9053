
public class DumbPasswords {

    public static void printDumbPasswords(int m, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < m; i++) {
            sb.append(i);
            for (int j = 1; j < m; j++) {
                sb.append(j);
                for (int k = 0; k < n; k++) {
                    sb.append((char) ('a' + k));
                    for (int l = 0; l < n; l++) {
                        sb.append((char) ('a' + l));
                        for (int o = 1; o < m + 1; o++) {
                            if (o > i && o > j) {
                                sb.append(o);
                                System.out.println(sb.toString());
                                sb.deleteCharAt(sb.length() - 1);
                            }
                        }
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        printDumbPasswords(4, 2);

    }
}
