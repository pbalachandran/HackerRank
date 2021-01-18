package com.hackerrank.trial;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubStrings {

    public static List<String> generateSubstrings(String s) {
        List<String> list = new ArrayList<>();

        int start = 0;
        while(start < s.length()) {
            int end = start + 1;
            while(end <= s.length()) {
                String substring = s.substring(start, end);
                list.add(substring);
                end++;
            }
            start++;
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> strings = generateSubstrings("abcd");
        System.out.println(strings);
    }
}
