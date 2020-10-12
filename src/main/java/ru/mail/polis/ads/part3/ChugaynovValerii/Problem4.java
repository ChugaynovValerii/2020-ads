package ru.mail.polis.ads.part3.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7491288

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) {
            out.write(isInArr(in.nextInt(), arr) ? "YES\n" : "NO\n");
        }
        out.flush();
    }
    
    private static boolean isInArr(int x, int[] arr) {
        int start = 0;
        int end = arr.length;
        while (end > start) {
            int mid = (start + end) / 2;
            if (arr[mid] > x) {
                end = mid;
            } else if (arr[mid] < x) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
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
