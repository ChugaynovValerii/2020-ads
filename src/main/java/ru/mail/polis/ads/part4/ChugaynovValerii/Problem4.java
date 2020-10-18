package ru.mail.polis.ads.part4.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7532544

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n + 2];
        int[] maxCost = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        for (int i = 1; i < n + 2; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i >= k ? i - k : 0; j < i; j++) {
                if (maxCost[j] > max) {
                    max = maxCost[j];
                }
            }
            maxCost[i] = arr[i] + max;
        }
        out.println(maxCost[n + 1]);
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
