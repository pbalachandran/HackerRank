package com.hackerrank.balancedbrackets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

    private static List<Character> leftBrackets = Arrays.asList('{', '(', '[');
    private static List<Character> rightBrackets = Arrays.asList('}', ')', ']');

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        char[] brackets = s.toCharArray();
        for(char bracket: brackets) {
            if (leftBrackets.contains(bracket)) {
                leftStack.push(bracket);
            } else {
                rightStack.push(bracket);
            }

            if (!isBalanced(leftStack, rightStack)) {
                return "NO";
            }
        }

        if (!leftStack.isEmpty() || !rightStack.isEmpty()) {
            return "NO";
        }

        return "YES";
    }

    public static boolean isBalanced(Stack<Character> leftStack, Stack<Character> rightStack) {
        Character left = !leftStack.isEmpty() ? leftStack.peek() : null;
        Character right = !rightStack.isEmpty() ? rightStack.peek() : null;

        if (left != null & right != null) {
            if (isComplement(leftStack.peek(), rightStack.peek())) {
                leftStack.pop();
                rightStack.pop();
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private static boolean isComplement(Character left, Character right) {
        return ((left == '{' && right == '}')
                || (left == '(' && right == ')')
                || (left == '[' && right == ']'));
    }

    private static final Scanner scanner = new Scanner(System.in);

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
