package ru.mail.polis.ads.part1.ChugaynovValerii;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String expression = in.next();
        Stack<Integer> stack = new Stack<>();
        while (!expression.equals("exit")) {
            switch (expression) {
                case "push":
                    stack.push(in.nextInt());
                    out.write("ok\n");
                    break;
                case "pop":
                    if (stack.isEmpty()) {
                        out.write("error\n");
                    } else {
                        out.write(stack.pop() + "\n");
                    }
                    break;
                case "back":
                    if (stack.isEmpty()) {
                        out.write("error\n");
                    } else {
                        out.write(stack.back() + "\n");
                    }
                    break;
                case "size":
                    out.write(stack.getSize() + "\n");
                    break;
                case "clear":
                    stack.clear();
                    out.write("ok\n");
            }
            expression = in.next();
        }
        out.write("bye");
    }
    
    private static class Stack<T> {
        private static final double CAPACITY_FACTOR = 1.5;
        private static final int INITIAL_CAPACITY = 10;
        private Object[] stackArray;
        private int curIndex;
        
        public Stack(int initCapacity) {
            stackArray = new Object[initCapacity];
            curIndex = -1;
        }
        
        public Stack() {
            this(INITIAL_CAPACITY);
        }
        
        public T pop() {
            T value = back();
            curIndex--;
            return value;
        }
        
        @SuppressWarnings("unchecked")
        public T back() {
            if (curIndex < 0) {
                throw new IndexOutOfBoundsException("Stack is empty");
            }
            return (T) stackArray[curIndex];
        }
        
        public void push(T elem) {
            if (++curIndex >= stackArray.length) {
                stackArray = Arrays.copyOf(stackArray, (int) (curIndex * CAPACITY_FACTOR));
            }
            stackArray[curIndex] = elem;
        }
        
        public boolean isEmpty() {
            return curIndex == -1;
        }
        
        public int getSize() {
            return curIndex + 1;
        }
        
        public void clear() {
            curIndex = -1;
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
