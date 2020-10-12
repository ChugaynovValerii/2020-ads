package ru.mail.polis.ads.part3.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7491947

public class Problem5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] coordinates = new int[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = in.nextInt();
        }
        out.println(findMaxMinDistance(coordinates, k));
    }
    
    private static int findMaxMinDistance(int[] coordinates, int k) {
        int lowBoundOfDistance = 0;
        int highBoundOfDistance = coordinates[coordinates.length - 1] - lowBoundOfDistance + 1;
        while (highBoundOfDistance - lowBoundOfDistance > 1) {
            int checkedDistance = (highBoundOfDistance + lowBoundOfDistance) / 2;
            int countOnThisDistance = countOnThisDistance(checkedDistance, coordinates);
            if (countOnThisDistance >= k) {
                lowBoundOfDistance = checkedDistance;
            } else {
                highBoundOfDistance = checkedDistance;
            }
        }
        return lowBoundOfDistance;
    }
    
    private static int countOnThisDistance(int checkedDistance, int[] coordinates) {
        int prevCoordinate = coordinates[0];
        int count = 1;
        for (int i = 1; i < coordinates.length; i++) {
            if (coordinates[i] - prevCoordinate >= checkedDistance) {
                count++;
                prevCoordinate = coordinates[i];
            }
        }
        return count;
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
