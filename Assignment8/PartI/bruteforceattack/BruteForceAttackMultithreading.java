package bruteforceattack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Collections.synchronizedSet;


public class BruteForceAttackMultithreading {

    static final char startLower = 'a';
    static final char startUpper = 'A';
    static final char startNumber = '0';
    static final char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static AtomicInteger numFound = new AtomicInteger(0);

    public static char getChar(int i) {
        return letters[i];

    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static int getInt(char c) {
        if ((c >= 'a') && (c <= 'z')) {
            return c - 'a';
        } else if ((c >= 'A') && (c <= 'Z')) {
            return 26 + (c - 'A');
        } else if ((c >= '0') && (c <= '9')) {
            return 52 + (c - '0');
        } else {
            return 0;
        }
    }

    public static Set<String> hashedpasswords(String filename) {
        Set<String> hashSet = new HashSet<String>();
        try {
            FileReader fr = new FileReader("hashedpassword.txt");
            BufferedReader br = new BufferedReader(fr);
            String inline = br.readLine();
            while (inline != null) {
                hashSet.add(inline);
                inline = br.readLine();
            }
        } catch (Exception e) {

        }
        return hashSet;

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        long startTime = System.currentTimeMillis();

        Set<String> passwordSet = synchronizedSet(hashedpasswords("hashedpassword.txt"));

        // Number of threads
        int threadCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        //characters in password
        int len = 5;
        long max = (long) Math.pow(26, len);
        long range = max / threadCount;

        for (int i = 0; i < threadCount; i++) {
            long start = i * range;
            long end = (i < threadCount - 1) ? start + range : max;
            Runnable worker = new BruteForceAttackMultithreadingWorker(start, end, passwordSet, len);
            executor.execute(worker);
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }


        System.out.println("found " + numFound.get() + " out of " + passwordSet.size());

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");

    }

    private static class BruteForceAttackMultithreadingWorker implements Runnable {
        private long start;

        private long end;

        private Set<String> passwordSet;

        private int len;

        BruteForceAttackMultithreadingWorker(long start, long end, Set<String> passwordSet, int len) {
            this.start = start;
            this.end = end;
            this.passwordSet = passwordSet;
            this.len = len;

        }


        @Override
        public void run() {
            MessageDigest localDigest;
            try {
                localDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("SHA-256 algorithm not found", e);
            }

            byte[] pass = new byte[len];
            for (int k = 0; k < pass.length; k++) {
                pass[k] = startLower;
            }

            for (long j = start; j < end; j++) {
                int v = (int) (j % 26L);
                if ((v == 0) && (j != 0)) {

                    pass[0] = startLower;
                    for (int k = 1; k < pass.length; k++) {
                        if (pass[k] == 'z') {
                            if (k != pass.length - 1) {
                                pass[k] = startLower;
                                continue;
                            } else {
                                break;
                            }
                        } else {
                            int val = getInt((char) pass[k]);
                            val++;
                            pass[k] = (byte) letters[val];
                            break;
                        }
                    }
                } else {
                    pass[0] = (byte) letters[v];
                }

                byte[] encodedhash = localDigest.digest(pass);

                String hashpass = BruteForceAttackMultithreading.bytesToHex(encodedhash);
                if (passwordSet.contains(hashpass)) {
                    String passString = new String(pass);
                    System.out.println("found password " + passString);
                    numFound.incrementAndGet();
                }
            }
        }
    }
}
