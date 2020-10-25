package ru.mail.polis.ads.part5.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7573603

public class Problem1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        double res = Double.parseDouble(in.next());
        double approxRes = 1.0;
        double xStart = 1.0;
        double xEnd = res;
        double xRes = (xStart + xEnd) / 2;
        while (Math.abs(approxRes - res) >= 0.000001) {
            approxRes = xRes * xRes + Math.sqrt(xRes);
            if (approxRes < res) {
                xStart = xRes;
            } else {
                xEnd = xRes;
            }
            xRes = (xStart + xEnd) / 2;
        }
        out.println(xStart);
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
