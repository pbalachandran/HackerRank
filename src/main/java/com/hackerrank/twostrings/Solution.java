package com.hackerrank.twostrings;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {


    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        Map<Character, Boolean>  s1Map = populateDictionary(s1);
        Map<Character, Boolean>  s2Map = populateDictionary(s2);

        List<Character> s1Alphabets =
                s2Map.keySet().stream().filter(s2Key -> s1Map.get(s2Key) == true).collect(Collectors.toList());

        Iterator<Character> s2AlphabetsIter =
                s2Map.keySet().stream().filter(s2Key -> s2Map.get(s2Key) == true).collect(Collectors.toList()).iterator();

        while(s2AlphabetsIter.hasNext()) {
            if (s1Alphabets.contains(s2AlphabetsIter.next())) {
                return "YES";
            }
        }
        return "NO";
    }

    private static Map<Character, Boolean> populateDictionary(String s) {
        Map<Character, Boolean> map = new HashMap<>();
        map.put('a', false);
        map.put('b', false);
        map.put('c', false);
        map.put('d', false);
        map.put('e', false);
        map.put('f', false);
        map.put('g', false);
        map.put('h', false);
        map.put('i', false);
        map.put('j', false);
        map.put('k', false);
        map.put('l', false);
        map.put('m', false);
        map.put('n', false);
        map.put('o', false);
        map.put('p', false);
        map.put('q', false);
        map.put('r', false);
        map.put('s', false);
        map.put('t', false);
        map.put('u', false);
        map.put('v', false);
        map.put('w', false);
        map.put('x', false);
        map.put('y', false);
        map.put('z', false);

        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            if(map.keySet().contains(Character.valueOf(sChars[i]))) {
                map.put(Character.valueOf(sChars[i]), true);
            }
        }
        return map;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
