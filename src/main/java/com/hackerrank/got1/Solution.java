package com.hackerrank.got1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    static String got(String s) {
        boolean isPalindrome = true;
        boolean isOneOdd = false;

        Map<Character, Integer> map = populateMap(s);

        Iterator<Character> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Character key = iterator.next();
            int remainder = map.get(key) % 2;
            if (remainder == 1) {
                if (isOneOdd) {
                    isPalindrome = false;
                    break;
                } else {
                    isOneOdd = true;
                }
            }
        }
        return isPalindrome ? "YES" : "NO";
    }

    private static Map<Character, Integer> populateMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (map.keySet().contains(character)) {
                int count = map.get(character);
                count++;
                map.put(character, count);
            } else {
                map.put(character, 1);
            }
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = got(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
