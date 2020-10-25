package ru.mail.polis.ads.part5.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7577558

public class Problem2 {
    static long w;
    static long h;
    static long n;
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        w = in.nextInt();
        h = in.nextInt();
        n = in.nextInt();
        long answerMax = Math.min(h, w) * n;
        long answerMin = Math.max(h, w);
        while (answerMax > answerMin) {
            long answer = (answerMax + answerMin) / 2;
            if ((answer / w) * (answer / h) >= n) {
                answerMax = answer;
            } else {
                answerMin = answer + 1;
            }
        }
        out.println(answerMin);
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
