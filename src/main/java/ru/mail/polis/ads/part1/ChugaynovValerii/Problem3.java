package ru.mail.polis.ads.part1.ChugaynovValerii;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String expression = in.next();
        Stack<Character> stack = new Stack<>();
        boolean isCorrect = true;
        for (int i = 0; i < expression.length(); i++) {
            char tempSymbol = expression.charAt(i);
            if (tempSymbol == '(') {
                stack.push(tempSymbol);
            } else if (tempSymbol == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    isCorrect = false;
                    break;
                }
            }
        }
        out.write(isCorrect && stack.isEmpty() ? "YES" : "NO");
    }
    
    private static class Stack<T> {
        private static final double CAPACITY_FACTOR = 1.5;
        private static final int INITIAL_CAPACITY = 10;
        private Object[] stackArray;
        private int size;
        
        public Stack() {
            stackArray = new Object[INITIAL_CAPACITY];
            size = -1;
        }
        
        @SuppressWarnings("unchecked")
        public T pop() {
            if (size < 0) {
                throw new IndexOutOfBoundsException("Stack is empty");
            }
            return (T) stackArray[size--];
        }
        
        public void push(T elem) {
            if (++size >= stackArray.length) {
                stackArray = Arrays.copyOf(stackArray, (int) (size * CAPACITY_FACTOR));
            }
            stackArray[size] = elem;
        }
        
        public boolean isEmpty() {
            return size == -1;
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
