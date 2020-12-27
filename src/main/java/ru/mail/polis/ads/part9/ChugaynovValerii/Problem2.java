package ru.mail.polis.ads.part9.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/8166287

public class Problem2 {
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] used;
    private static int[] tin;
    private static int[] fup;
    private static int counter;
    private static boolean[] inCycle;
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        used = new boolean[n + 1];
        inCycle = new boolean[n + 1];
        tin = new int[n + 1];
        fup = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int vertex1 = in.nextInt();
            int vertex2 = in.nextInt();
            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }
        
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                dfs(i, -1);
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (inCycle[i]) {
                out.println("Yes\n" + i);
                return;
            }
        }
        out.println("No");
    }
    
    private static void dfs(int vertexFrom, int vertexFromParent) {
        used[vertexFrom] = true;
        tin[vertexFrom] = ++counter;
        fup[vertexFrom] = counter;
        for (int vertexTo : graph.get(vertexFrom)) {
            if (vertexFromParent != vertexTo) {
                if (used[vertexTo]) {
                    fup[vertexFrom] = Math.min(tin[vertexTo], fup[vertexFrom]);
                    inCycle[vertexFrom] = true;
                } else {
                    dfs(vertexTo, vertexFrom);
                    if (fup[vertexTo] <= fup[vertexFrom]) {
                        fup[vertexFrom] = fup[vertexTo];
                        inCycle[vertexFrom] = true;
                    }
                }
            }
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
