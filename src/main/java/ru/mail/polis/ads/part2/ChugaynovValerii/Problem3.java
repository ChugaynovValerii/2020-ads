package ru.mail.polis.ads.part2.ChugaynovValerii;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

//https://www.e-olymp.com/ru/submissions/7443306

public class Problem3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] arr = IntStream.generate(in::nextInt).limit(N).toArray();
        out.println(swapAmountInBubbleSort(arr));
    }
    
    private static int swapAmountInBubbleSort(int[] arr) {
        int amount = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    amount++;
                }
            }
        }
        return amount;
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
