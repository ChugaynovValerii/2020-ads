package ru.mail.polis.ads.part10.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/8074250

public class Problem2 {
    private static class Edge implements Comparable<Edge> {
        private final int from;
        private final int to;
        private final int weight;
        
        private Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge edge) {
            return weight - edge.weight;
        }
    }
    
    private static class Node {
        private boolean isVisited;
        private final Queue<Edge> edgesList;
        
        private Node() {
            edgesList = new PriorityQueue<>();
            isVisited = false;
        }
    }
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int verticesCount = in.nextInt();
        final int edgesCount = in.nextInt();
        
        if (edgesCount == 0) {
            System.out.println(0);
            return;
        }
        
        final Node[] nodes = new Node[verticesCount + 1];
        for (int i = 1; i <= verticesCount; ++i) {
            nodes[i] = new Node();
        }
        
        for (int i = 1; i <= edgesCount; ++i) {
            final int node1 = in.nextInt();
            final int node2 = in.nextInt();
            final int weight = in.nextInt();
            nodes[node1].edgesList.add(new Edge(node1, node2, weight));
            nodes[node2].edgesList.add(new Edge(node2, node1, weight));
        }
        
        final Queue<Edge> edges = new PriorityQueue<>(edgesCount);
        edges.add(nodes[1].edgesList.poll());
        nodes[1].isVisited = true;
        
        int visitedCount = 1;
        int sumWeight = 0;
        
        while (true) {
            final Edge edge = edges.poll();
            
            if (edge != null && !nodes[edge.to].isVisited) {
                sumWeight += edge.weight;
                nodes[edge.to].isVisited = true;
                if (++visitedCount == verticesCount) {
                    break;
                }
                
                if (!nodes[edge.to].edgesList.isEmpty()) {
                    edges.add(nodes[edge.to].edgesList.poll());
                }
            }
            
            if (edge != null && !nodes[edge.from].edgesList.isEmpty()) {
                edges.add(nodes[edge.from].edgesList.poll());
            }
        }
        out.println(sumWeight);
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
    
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}