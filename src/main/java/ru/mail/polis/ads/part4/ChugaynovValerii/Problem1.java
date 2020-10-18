package ru.mail.polis.ads.part4.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7537476

public class Problem1 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        char[] seq = in.reader.readLine().toCharArray();
        if (seq.length == 0) {
            return;
        }
        int n = seq.length;
        int[][] lackBracketsCount = new int[n][n];
        char[][][] goodSeqs = new char[n][n][];
        for (int i = 0; i < n; i++) {
            lackBracketsCount[i][i] = 1;
            goodSeqs[i][i] = getCorrectSeq(seq[i]);
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int minSum = lackBracketsCount[i][i] + lackBracketsCount[i + 1][j];
                int kForMinSum = i;
                for (int k = i + 1; k < j; k++) {
                    int tempSum = lackBracketsCount[i][k] + lackBracketsCount[k + 1][j];
                    if (tempSum < minSum) {
                        minSum = tempSum;
                        kForMinSum = k;
                    }
                }
                
                if (isCorrect(seq[i], seq[j]) && lackBracketsCount[i + 1][j - 1] < minSum) {
                    lackBracketsCount[i][j] = lackBracketsCount[i + 1][j - 1];
                    if (goodSeqs[i + 1][j - 1] == null) {
                        goodSeqs[i][j] = new char[]{seq[i], seq[j]};
                    } else {
                        char[] leftBracketPlusSeq = mergeArrays(new char[]{seq[i]}, goodSeqs[i + 1][j - 1]);
                        goodSeqs[i][j] = mergeArrays(leftBracketPlusSeq, new char[]{seq[j]});
                    }
                } else {
                    lackBracketsCount[i][j] = minSum;
                    goodSeqs[i][j] = mergeArrays(goodSeqs[i][kForMinSum], goodSeqs[kForMinSum + 1][j]);
                }
            }
        }
        out.println(goodSeqs[0][n - 1]);
    }
    
    private static char[] mergeArrays(char[] left, char[] right) {
        char[] res = new char[left.length + right.length];
        System.arraycopy(left, 0, res, 0, left.length);
        System.arraycopy(right, 0, res, left.length, right.length);
        return res;
    }
    
    private static char[] getCorrectSeq(char bracket) {
        if (bracket == '(' || bracket == ')') {
            return new char[]{'(', ')'};
        } else {
            return new char[]{'[', ']'};
        }
    }
    
    private static boolean isCorrect(char left, char right) {
        return left == '(' && right == ')'
                || left == '[' && right == ']';
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
