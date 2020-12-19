package com.hackerrank.sherlockvalidstring;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> dictionary = new HashMap<>();
        dictionary.put('a', 0);
        dictionary.put('b', 0);
        dictionary.put('c', 0);
        dictionary.put('d', 0);
        dictionary.put('e', 0);
        dictionary.put('f', 0);
        dictionary.put('g', 0);
        dictionary.put('h', 0);
        dictionary.put('i', 0);
        dictionary.put('j', 0);
        dictionary.put('k', 0);
        dictionary.put('l', 0);
        dictionary.put('m', 0);
        dictionary.put('n', 0);
        dictionary.put('o', 0);
        dictionary.put('p', 0);
        dictionary.put('q', 0);
        dictionary.put('r', 0);
        dictionary.put('s', 0);
        dictionary.put('t', 0);
        dictionary.put('u', 0);
        dictionary.put('v', 0);
        dictionary.put('w', 0);
        dictionary.put('x', 0);
        dictionary.put('y', 0);
        dictionary.put('z', 0);


        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            if (dictionary.get(sChars[i]) == 0) {
                dictionary.put(sChars[i], 1);
            } else {
                int count = dictionary.get(sChars[i]);
                dictionary.put(sChars[i], count + 1);
            }
        }

        List<Integer> distinctCounts = dictionary.values().stream().distinct().filter(value -> value > 0).collect(Collectors.toList());
        if (distinctCounts.size() == 1) {
            return "YES";
        } else if (distinctCounts.size() == 2) {
            distinctCounts.sort(Integer::compareTo);

            List<Character> smallerCountCharacterList =
                    dictionary.keySet().stream().filter(key -> dictionary.get(key) == distinctCounts.get(0)).collect(Collectors.toList());

            List<Character> largerCountCharacterList =
                    dictionary.keySet().stream().filter(key -> dictionary.get(key) == distinctCounts.get(1)).collect(Collectors.toList());

            if (smallerCountCharacterList.size() == 1 && largerCountCharacterList.size() == 1) {
                if (dictionary.get(smallerCountCharacterList.get(0)) == 1 || dictionary.get(largerCountCharacterList.get(0)) == 1) {
                    return "YES";
                }
            } else if (smallerCountCharacterList.size() == 1 && largerCountCharacterList.size() > 1) {
                if (dictionary.get(smallerCountCharacterList.get(0)) == 1) {
                    return "YES";
                } else if (Math.abs(dictionary.get(smallerCountCharacterList.get(0)) - dictionary.get(largerCountCharacterList.get(0))) == 1) {
                    return "YES";
                }
            } else if (largerCountCharacterList.size() == 1 && smallerCountCharacterList.size() > 1) {
                if (dictionary.get(largerCountCharacterList.get(0)) == 1) {
                    return "YES";
                } else if (Math.abs(dictionary.get(largerCountCharacterList.get(0)) - dictionary.get(smallerCountCharacterList.get(0))) == 1) {
                    return "YES";
                }
            }
        }
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
