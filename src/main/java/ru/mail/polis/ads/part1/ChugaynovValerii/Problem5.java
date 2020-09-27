package ru.mail.polis.ads.part1.ChugaynovValerii;

import java.io.*;
import java.util.StringTokenizer;

public class Problem5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String expression = in.next();
        Queue<Integer> queue = new Queue<>();
        while (!expression.equals("exit")) {
            switch (expression) {
                case "push":
                    queue.push(in.nextInt());
                    out.write("ok\n");
                    break;
                case "pop":
                    out.write(queue.pop() + "\n");
                    break;
                case "front":
                    out.write(queue.front() + "\n");
                    break;
                case "size":
                    out.write(queue.getSize() + "\n");
                    break;
                case "clear":
                    queue.clear();
                    out.write("ok\n");
            }
            expression = in.next();
        }
        out.write("bye");
    }
    
    
    private static class Queue<T> {
        private static final int INITIAL_CAPACITY = 100;
        private int lastIndex;
        private int firstIndex;
        private final Object[] queueArray;
        
        public Queue() {
            queueArray = new Object[INITIAL_CAPACITY];
            firstIndex = 0;
            lastIndex = firstIndex - 1;
        }
        
        public T pop() {
            T value = front();
            firstIndex++;
            return value;
        }
        
        @SuppressWarnings("unchecked")
        public T front() {
            if (firstIndex > lastIndex) {
                throw new IndexOutOfBoundsException("Queue is empty");
            }
            return (T) queueArray[firstIndex % INITIAL_CAPACITY];
        }
        
        public void push(T elem) {
            queueArray[++lastIndex % INITIAL_CAPACITY] = elem;
        }
        
        public boolean isEmpty() {
            return getSize() == 0;
        }
        
        public int getSize() {
            return lastIndex - firstIndex + 1;
        }
        
        public void clear() {
            lastIndex = firstIndex - 1;
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