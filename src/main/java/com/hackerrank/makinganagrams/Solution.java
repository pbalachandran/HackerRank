package com.hackerrank.makinganagrams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    static int makingAnagrams(String a, String b) {
        // Complete the function
        Map<Character, Integer> aMap = populateMap(a);
        Map<Character, Integer> bMap = populateMap(b);
        return compare(aMap, bMap);
    }

    private static int compare(Map<Character, Integer> aMap, Map<Character, Integer> bMap) {
        int deletions = countDeletions(aMap, bMap);
        return deletions;
    }

    private static int countDeletions(Map<Character, Integer> aMap, Map<Character, Integer> bMap) {
        int deletions = 0;
        Iterator<Character> iterator = aMap.keySet().iterator();
        while (iterator.hasNext()) {
            Character key = iterator.next();
            int aCount = aMap.get(key);
            if (bMap.get(key) == null) {
                deletions += aCount;
            } else {
                int bCount = bMap.get(key);
                if (aCount != bCount) {
                    deletions += Math.abs(aCount - bCount);
                }
            }
        }

        iterator = bMap.keySet().iterator();
        while (iterator.hasNext()) {
            Character key = iterator.next();
            int bCount = bMap.get(key);
            if (aMap.get(key) == null) {
                deletions += bCount;
            }
        }
        return deletions;
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

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = makingAnagrams(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
