package ru.mail.polis.ads.part2.ChugaynovValerii;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

//https://www.e-olymp.com/ru/submissions/7443294

public class Problem1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] arr = IntStream.generate(in::nextInt).limit(N).toArray();
        mergeSort(arr);
        Arrays.stream(arr).forEach(num -> out.print(num + " "));
    }
    
    
    private static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int leftSize = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, leftSize);
        int[] right = Arrays.copyOfRange(arr, leftSize, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }
    
    private static void merge(int[] res, int[] left, int[] right) {
        int unfilled = res.length;
        int resI = 0;
        int leftI = 0;
        int rightI = 0;
        while (unfilled-- > 0) {
            if (leftI >= left.length) {
                res[resI++] = right[rightI++];
            } else if (rightI >= right.length) {
                res[resI++] = left[leftI++];
            } else if (left[leftI] <= right[rightI]) {
                res[resI++] = left[leftI++];
            } else {
                res[resI++] = right[rightI++];
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
