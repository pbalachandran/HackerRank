package com.hackerrank.trial;

import java.util.*;

public class Solution {

    private static Map<Integer, List<String>> constructWords(char[] chars) {
        List<String> words;
        Map<Integer, List<String>> map = new HashMap<>();

        int len = 1;
        for (int i = 0; i < chars.length; i++) {
            String word = String.valueOf(chars[i]);
            for (int k = 0; k < len; k++) {
                for (int j = i + 1; j < chars.length; j++) {
                    word += String.valueOf(chars[j]);
                    System.out.println(word);
                }
            }
        }
        return map;
    }


    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};
        Map<Integer, List<String>> dictionary = constructWords(chars);
    }
}
