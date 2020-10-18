package ru.mail.polis.ads.part4.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7535353

public class Problem2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[n * m];
        String[] goodTrip = new String[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[(n - i - 1) * m + j] = in.nextInt();
            }
        }
        int[] res = new int[n * m];
        fillBase(arr, res, goodTrip, n, m);
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int left = res[i * m + j - 1];
                int down = res[(i - 1) * m + j];
                if (left >= down) {
                    res[i * m + j] = left + arr[i * m + j];
                    goodTrip[i * m + j] = goodTrip[i * m + j - 1] + "R";
                } else {
                    res[i * m + j] = down + arr[i * m + j];
                    goodTrip[i * m + j] = goodTrip[(i - 1) * m + j] + "F";
                }
            }
        }
        out.println(goodTrip[m * n - 1]);
    }
    
    private static void fillBase(int[] arr, int[] res, String[] goodTrip, int n, int m) {
        res[0] = arr[0];
        goodTrip[0] = "";
        for (int i = 1; i < n; i++) {
            res[i * m] = res[(i - 1) * m] + arr[i * m];
            goodTrip[i * m] = goodTrip[(i - 1) * m] + "F";
        }
        for (int j = 1; j < m; j++) {
            res[j] = res[j - 1] + arr[j];
            goodTrip[j] = goodTrip[j-1] + "R";
        }
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
