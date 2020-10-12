package ru.mail.polis.ads.part3.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7499553

public class Problem3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = 0;
        Heap<Integer> maxHeap = new Heap<>(100, Integer::compareTo);
        maxHeap.insert(Integer.MIN_VALUE);
        Heap<Integer> minHeap = new Heap<>(100, (i1, i2) -> -Integer.compare(i1, i2));
        minHeap.insert(Integer.MAX_VALUE);
        for (String number = in.next(); number != null; number = in.next()) {
            int x = Integer.parseInt(number);
            if (count++ % 2 == 0) {
                if (x > minHeap.peek()) {
                    maxHeap.insert(minHeap.extract());
                    minHeap.insert(x);
                } else {
                    maxHeap.insert(x);
                }
                out.println(maxHeap.peek());
            } else {
                if (x < maxHeap.peek()) {
                    minHeap.insert(maxHeap.extract());
                    maxHeap.insert(x);
                } else {
                    minHeap.insert(x);
                }
                out.println((minHeap.peek() + maxHeap.peek()) / 2);
            }
        }
    }
    
    static class Heap<T> {
        private Object[] arr;
        private int lastIndex;
        private final Comparator<T> comparator;
        
        public Heap(int initCapacity, Comparator<T> comparator) {
            if (initCapacity < 0) {
                throw new IllegalArgumentException("Capacity of heap can't be negative");
            }
            lastIndex = -1;
            this.comparator = comparator;
            arr = new Object[initCapacity];
        }
        
        public void insert(T x) {
            if (++lastIndex >= arr.length) {
                arr = Arrays.copyOf(arr, lastIndex * 2);
            }
            arr[lastIndex] = x;
            siftUp(lastIndex);
        }
        
        @SuppressWarnings("unchecked")
        public void replaceIfLessThanBiggest(T x) {
            if (comparator.compare(x, (T) arr[0]) < 0) {
                extract();
                insert(x);
            }
        }
        
        @SuppressWarnings("unchecked")
        public T extract() {
            T value = (T) arr[0];
            arr[0] = arr[lastIndex--];
            sink(0);
            return value;
        }
        
        @SuppressWarnings("unchecked")
        public T peek() {
            return (T) arr[0];
        }
        
        @SuppressWarnings("unchecked")
        private void siftUp(int index) {
            while (index != 0) {
                int parentIndex = (index - 1) / 2;
                if (comparator.compare((T) arr[index], (T) arr[parentIndex]) > 0) {
                    Object temp = arr[index];
                    arr[index] = arr[parentIndex];
                    arr[parentIndex] = temp;
                    index = parentIndex;
                } else {
                    break;
                }
            }
        }
        
        @SuppressWarnings("unchecked")
        private void sink(int index) {
            int maxChildIndex = 2 * index + 1;
            while (maxChildIndex <= lastIndex) {
                if (maxChildIndex + 1 <= lastIndex
                        && comparator.compare((T) arr[maxChildIndex + 1], (T) arr[maxChildIndex]) > 0) {
                    maxChildIndex++;
                }
                if (comparator.compare((T) arr[index], (T) arr[maxChildIndex]) < 0) {
                    Object temp = arr[index];
                    arr[index] = arr[maxChildIndex];
                    arr[maxChildIndex] = temp;
                    index = maxChildIndex;
                    maxChildIndex = 2 * index + 1;
                } else {
                    break;
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
                    String s = reader.readLine();
                    if (s == null) {
                        return null;
                    }
                    tokenizer = new StringTokenizer(s);
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
