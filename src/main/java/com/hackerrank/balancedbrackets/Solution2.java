package com.hackerrank.balancedbrackets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution2 {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Character> leftBrackets = Arrays.asList('{', '(', '[');
    private static List<Character> rightBrackets = Arrays.asList('}', ')', ']');

    private static Character findLeftBracketComplement(Character rightBracket) {
        Character leftBracketComplement = null;
        if (rightBracket == '}') {
            leftBracketComplement = '{';
        } else if (rightBracket == ')') {
            leftBracketComplement = '(';
        } else if (rightBracket == ']') {
            leftBracketComplement = '[';
        }
        return leftBracketComplement;
    }

    public static String isBalanced(String s) {
        int index = 0;
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        boolean isBalanced = true;
        if (s.length() % 2 != 0) {
            isBalanced = false;
        } else {
            while (index < s.length()) {
                Character bracket = s.charAt(index++);
                if (leftBrackets.contains(bracket)) {
                    left.add(bracket);
                } else {
                    right.add(bracket);
                    if (!left.isEmpty()) {
                        Character leftTop = left.peek();
                        if (leftTop != null && leftTop == findLeftBracketComplement(bracket)) {
                            left.pop();
                            right.pop();
                        } else {
                            return "NO";
                        }
                    } else {
                        return "NO";
                    }
                }
            }
            isBalanced = left.isEmpty() && right.isEmpty();
        }
        return isBalanced ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
