package com.hackerrank.tokensplit;

import java.io.*;
import java.util.*;

public class Solution {
    private static void split(String s) {
        List<String> tokens = new ArrayList<>();

        char[] sChars = s.toCharArray();

        List<Character> word = new ArrayList<>();

        int index = 0;
        while (index < sChars.length) {
            Character c = sChars[index];
            if (isBreakWord(c)) {
                if (isValid(word)) {
                    tokens.add(extractToken(word));
                    word = new ArrayList<>();
                }
            } else {
                word.add(c);
            }
            index++;
        }

        if (isValid(word)) {
            tokens.add(extractToken(word));
        }

        printTokens(tokens);
    }

    private static boolean isValid(List<Character> word) {
        String s = "";
        Iterator<Character> iterator = word.iterator();
        while (iterator.hasNext()) {
            s += iterator.next();
        }
        return s.trim().length() > 0;
    }

    private static void printTokens(List<String> tokens) {
        System.out.println(tokens.size());
        tokens.stream().forEach(token -> System.out.println(token));
    }


    private static boolean isBreakWord(Character c) {
        char comma = ',';
        char questionMark = '?';
        char space = ' ';
        char apostrophe = '\'';
        char underscore = '_';
        char period = '.';
        char exclamation = '!';
        char atSign = '@';

        return c == comma
                || c == questionMark
                || c == space
                || c == apostrophe
                || c == underscore
                || c == period
                || c == exclamation
                || c == atSign;
    }

    private static String extractToken(List<Character> word) {
        StringBuffer buff = new StringBuffer();
        Iterator<Character> iterator = word.iterator();
        while (iterator.hasNext()) {
            buff.append(iterator.next());
        }
        return buff.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        split(s);
        scan.close();
    }
}
