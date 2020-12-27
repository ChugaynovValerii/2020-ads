package ru.mail.polis.ads.part9.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/8166644

public class Problem3 {
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
            edges[i][2] = in.nextInt();
        }
        
        int[] dist = new int[n + 1];
        dist[1] = 0;
        for (int i = 2; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
    
        for (int i = 2; i <= n; i++) {
            for (int[] edge : edges) {
                int start = edge[0];
                if (dist[start] != Integer.MAX_VALUE) {
                    int finish = edge[1];
                    int weight = edge[2];
                    dist[finish] = Math.min(dist[finish], dist[start] + weight);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            out.print((dist[i] == Integer.MAX_VALUE ? 30000 : dist[i]) + " ");
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

