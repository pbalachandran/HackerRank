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

    // Complete the isBalanced function below.
    static String isBalanced(String s) {

        boolean isLeft = false;
        boolean isRight = false;


        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        char[] brackets = s.toCharArray();
        for(char bracket: brackets) {
            if (leftBrackets.contains(bracket)) {
                if (isRight != false) {
                    return "NO";
                }
                isLeft = true;
                isRight = false;
                leftStack.push(bracket);
            } else {
                if (isLeft != true) {
                    return "NO";
                }
                isLeft = false;
                isRight = true;
                rightStack.push(bracket);
            }
        }

        Stack<Character> rightInvertedStack = new Stack<>();

        Iterator<Character> iter = rightStack.iterator();
        while(iter.hasNext()) {
            rightInvertedStack.push(rightStack.pop());
        }

        if (leftStack.size() != rightInvertedStack.size()) {
            return "NO";
        } else {
            Iterator<Character> leftIter = leftStack.iterator();
            Iterator<Character> rightInvertedIter = rightInvertedStack.iterator();

            while(leftIter.hasNext() && rightInvertedIter.hasNext()) {
                if (!isComplement(leftStack.pop(), rightInvertedStack.pop())) {
                    return "NO";
                }
            }
        }
        return "YES";
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
