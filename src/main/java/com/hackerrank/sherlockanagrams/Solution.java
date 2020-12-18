package com.hackerrank.sherlockanagrams;

import java.io.*;
import java.util.*;

public class Solution {
    public static class Tuple {
        public int from;
        public int to;

        public Tuple(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int anagramPairs = 0;

        Map<Integer, List<String>> dictionary = constructWords(s);
        Iterator<Integer> sizeIterator = dictionary.keySet().iterator();
        while (sizeIterator.hasNext()) {
            Integer sizeKey = sizeIterator.next();
            String current, next;
            List<String> wordsOfSize = dictionary.get(sizeKey);
            int len = wordsOfSize.size();
            for (int i = 0; i < len; i++) {
                current = wordsOfSize.get(i);

                for (int j = i + 1; j < len; j++) {
                    next = wordsOfSize.get(j);
                    if (isAnagram(current, next)) {
                        System.out.println("Anagram: " + current + ":" + next);
                        anagramPairs++;
                    }
                }
            }
        }
        return anagramPairs;
    }

    private static boolean isAnagram(String left, String right) {

        Map<Character, Integer> ll = new HashMap<>();
        char[] leftChars = left.toCharArray();
        for (int i = 0; i < leftChars.length; i++) {
            Integer count = ll.get(leftChars[i]);
            if (count != null) {
                ll.put(leftChars[i], count + 1);
            } else {
                ll.put(leftChars[i], 1);
            }
        }

        Map<Character, Integer> rl = new HashMap<>();
        char[] rightChars = right.toCharArray();
        for (int i = 0; i < rightChars.length; i++) {
            Integer count = rl.get(rightChars[i]);
            if (count != null) {
                rl.put(rightChars[i], count + 1);
            } else {
                rl.put(rightChars[i], 1);
            }
        }

        Iterator<Character> iterator = ll.keySet().iterator();
        while(iterator.hasNext()) {
            Character key = iterator.next();
            Integer count = ll.get(key);

            if (!(rl.get(key) != null && rl.get(key) == count)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Integer, List<String>> constructWords(String s) {
        List<String> words;
        Map<Integer, List<String>> map = new HashMap<>();

        int begin = 0;
        int totalLength = s.length();
        while (begin < s.length()) {

            int len = 1;
            while (len <= totalLength - begin) {
                String word = s.substring(begin, len + begin);
                if (word.equals(s)) {
                    len++;
                    continue;
                } else {
                    words = map.get(len);
                    if (words != null) {
                        words.add(word);
                    } else {
                        words = new ArrayList<>();
                        words.add(word);
                        map.put(len, words);
                    }
                    len++;
                }
            }
            begin++;
        }
        return map;
    }

    private static List<Tuple> constructWordTuples(String s) {
        List<Tuple> wordTuples = new ArrayList<>();

        int sLength = s.length();
        int begin = 0;
        while (begin < sLength) {
            int len = 1;
            while (len <= sLength - begin) {
                Tuple tuple = new Tuple(begin, len + begin);
                if (tuple.to - tuple.from == s.length()) {
                    len++;
                    continue;
                } else {
                    wordTuples.add(tuple);
                    len++;
                }
            }
            begin++;
        }
        return wordTuples;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
