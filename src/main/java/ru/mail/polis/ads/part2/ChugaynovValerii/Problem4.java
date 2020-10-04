package ru.mail.polis.ads.part2.ChugaynovValerii;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7446892

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int k = in.nextInt();
        try {
            BigInteger[] arr = Arrays
                    .stream(in.reader.readLine().split(" "))
                    .map(BigInteger::new)
                    .toArray(BigInteger[]::new);
            out.println(findOrderStatistic(arr, arr.length - k));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static BigInteger findOrderStatistic(BigInteger[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        int mid = hoarPartition(arr, left, right);
        while (k != mid) {
            if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = hoarPartition(arr, left, right);
        }
        return arr[mid];
    }
    
    private static int hoarPartition(BigInteger[] arr, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        BigInteger x = arr[randomIndex];
        while (left <= right) {
            while (arr[left].compareTo(x) < 0) {
                left++;
            }
            while (arr[right].compareTo(x) > 0) {
                right--;
            }
            if (left >= right) {
                break;
            }
            BigInteger temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        return right;
    }
    
    
    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;
        
        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }
        
        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
    
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
