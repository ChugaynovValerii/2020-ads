package ru.mail.polis.ads.part10.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/8072911

public class Problem1 {
    private static final List<List<Edge>> graph = new ArrayList<>();
    private static boolean[] used;
    private static int[] tin;
    private static int[] fup;
    private static int counter;
    
    public static class Edge {
        private final int id;
        private final int end;
        
        public Edge(int id, int end) {
            this.id = id;
            this.end = end;
        }
    }
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        used = new boolean[m + 1];
        tin = new int[n + 1];
        fup = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < m + 1; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            graph.get(v1).add(new Edge(i, v2));
            graph.get(v2).add(new Edge(i, v1));
        }
        for (int i = 1; i < n + 1; i++) {
            if (tin[i] == 0) {
                dfs(i, -1, 0);
            }
        }
        out.println(counter);
        for (int i = 1; i < m + 1; i++) {
            if (used[i]) {
                out.print(i + " ");
            }
        }
    }
    
    private static void dfs(int vId, int edgeId, int c) {
        tin[vId] = ++c;
        fup[vId] = c;
        for (Edge edge : graph.get(vId)) {
            if (edge.id != edgeId) {
                if (tin[edge.end] != 0) {
                    fup[vId] = Math.min(fup[vId], tin[edge.end]);
                } else {
                    dfs(edge.end, edge.id, c);
                    fup[vId] = Math.min(fup[vId], fup[edge.end]);
                }
            }
        }
        if (edgeId != -1 && fup[vId] == tin[vId]) {
            counter++;
            used[edgeId] = true;
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
