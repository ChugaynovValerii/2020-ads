package ru.mail.polis.ads.part5.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7577652
public class Problem3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(in.next());
        }
        if (N < 2) {
            out.println(N);
            return;
        }
        int[] res = new int[N];
        res[0] = 1;
        int max = 1;
        for (int i = 1; i < N; i++) {
            int maxLen = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] != 0 && arr[i] % arr[j] == 0) {
                    maxLen = Math.max(res[j], maxLen);
                }
            }
            res[i] = maxLen + 1;
            max = Math.max(res[i], max);
        }
        out.println(max);
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
