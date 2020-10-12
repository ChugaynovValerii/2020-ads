package ru.mail.polis.ads.part3.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7491763

public class Problem1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.println(isHeap(arr) ? "YES" : "NO");
    }
    
    private static boolean isHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            if (2 * i + 1 < n && arr[i] > arr[2 * i + 1]) {
                return false;
            }
            if (2 * i + 2 < n && arr[i] > arr[2 * i + 2]) {
                return false;
            }
        }
        return true;
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
