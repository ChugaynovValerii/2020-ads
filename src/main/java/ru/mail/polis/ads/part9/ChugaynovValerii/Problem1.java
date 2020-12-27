package ru.mail.polis.ads.part9.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/8164326

public class Problem1 {
    private static List<List<Integer>> graph;
    private static List<Integer> result;
    private static int[] color;
    private static boolean isFault;
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList<>(n);
        result = new ArrayList<>(n);
        color = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            graph.get(in.nextInt() - 1).add(in.nextInt());
        }
        for (int i = 0; i < n; i++) {
            dfs(i);
            if (isFault) {
                out.print(-1);
                return;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            out.print(result.get(i) + 1 + " ");
        }
    }
    
    private static void dfs(int vertexFrom) {
        if (color[vertexFrom] == 2) {
            return;
        }
        if (color[vertexFrom] == 1) {
            isFault = true;
            return;
        }
        color[vertexFrom] = 1;
        for (int vertexTo : graph.get(vertexFrom)) {
            dfs(vertexTo - 1);
        }
        result.add(vertexFrom);
        color[vertexFrom] = 2;
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
