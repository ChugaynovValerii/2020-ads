package ru.mail.polis.ads.part5.ChugaynovValerii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7577912
public class Problem4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String template = in.next();
        String word = in.next();
        
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '*' || word.charAt(i) == '?') {
                String temp = template;
                template = word;
                word = temp;
            }
        }
        boolean[][] answers = new boolean[template.length() + 1][word.length() + 1];
        
        for (int i = 0; i < template.length(); i++) {
            answers[i][0] = true;
        }
        
        for (int i = 1; i < answers.length; i++) {
            for (int j = 1; j < answers[i].length; j++) {
                if (word.charAt(j - 1) == template.charAt(i - 1) || template.charAt(i - 1) == '?') {
                    answers[i][j] = answers[i - 1][j - 1];
                } else if (template.charAt(i - 1) == '*') {
                    answers[i][j] = answers[i - 1][j] || answers[i - 1][j - 1] || answers[i][j - 1];
                } else {
                    answers[i][j] = false;
                }
            }
        }
        out.println(answers[template.length()][word.length()] ? "YES" : "NO");
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
