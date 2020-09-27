package ru.mail.polis.ads.part1.ChugaynovValerii;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Problem2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            char[] expression = in.next().toCharArray();
            Tree<Character> root = fillTreeOfChars(expression);
            out.println(getTreeInWide(root));
        }
    }
    
    private static Tree<Character> fillTreeOfChars(char[] expression) {
        Deque<Tree<Character>> stack = new ArrayDeque<>();
        for (char c : expression) {
            if (Character.isLowerCase(c)) {
                stack.push(new Tree<>(c, null, null));
            } else if (Character.isUpperCase(c)) {
                Tree<Character> right = stack.pop();
                Tree<Character> left = stack.pop();
                stack.push(new Tree<>(c, left, right));
            }
        }
        return stack.pop();
    }
    
    private static String getTreeInWide(Tree<Character> root) {
        Deque<Tree<Character>> queue = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            Tree<Character> node = queue.remove();
            result.append(node.value);
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        return result.reverse().toString();
    }
    
    private static class Tree<T> {
        T value;
        Tree<T> left;
        Tree<T> right;
        
        public Tree(T value, Tree<T> left, Tree<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
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
