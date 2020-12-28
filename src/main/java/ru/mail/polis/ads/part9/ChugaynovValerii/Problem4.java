package ru.mail.polis.ads.part9.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/8166939

public class Problem4 {
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int vertex1 = in.nextInt();
        int vertex2 = in.nextInt();
        
        int[][] edges = new int[2 * m][3];
        for (int i = 0; i < m; i++) {
            edges[m + i][1] = edges[i][0] = in.nextInt();
            edges[m + i][0] = edges[i][1] = in.nextInt();
            edges[m + i][2] = edges[i][2] = in.nextInt();
        }
        List<List<Integer>> shortestWays = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            shortestWays.add(new ArrayList<>());
        }
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[vertex1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int[] edge : edges) {
                int start = edge[0];
                int finish = edge[1];
                int weight = edge[2];
                if (dist[start] != Integer.MAX_VALUE) {
                    if (dist[finish] > dist[start] + weight) {
                        dist[finish] = dist[start] + weight;
                        shortestWays.set(finish, new ArrayList<>(shortestWays.get(start)));
                        shortestWays.get(finish).add(start);
                    }
                }
            }
        }
        
        if (dist[vertex2] != Integer.MAX_VALUE) {
            out.println(dist[vertex2]);
            for (int step : shortestWays.get(vertex2)) {
                out.print(step + " ");
            }
            out.print(vertex2);
        } else {
            out.print(-1);
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
