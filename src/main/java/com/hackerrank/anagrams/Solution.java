package com.hackerrank.anagrams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    public static class Tuple {
        public int aCount;
        public int bCount;

        public Tuple() {
        }

        public Tuple(int aCount, int bCount) {
            this.aCount = aCount;
            this.bCount = bCount;
        }
    }

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int deletions = 0;

        Map<Character, Tuple> alphabets = new HashMap<>();
        alphabets.put('a', new Tuple());
        alphabets.put('b', new Tuple());
        alphabets.put('c', new Tuple());
        alphabets.put('d', new Tuple());
        alphabets.put('e', new Tuple());
        alphabets.put('f', new Tuple());
        alphabets.put('g', new Tuple());
        alphabets.put('h', new Tuple());
        alphabets.put('i', new Tuple());
        alphabets.put('j', new Tuple());
        alphabets.put('k', new Tuple());
        alphabets.put('l', new Tuple());
        alphabets.put('m', new Tuple());
        alphabets.put('n', new Tuple());
        alphabets.put('o', new Tuple());
        alphabets.put('p', new Tuple());
        alphabets.put('q', new Tuple());
        alphabets.put('r', new Tuple());
        alphabets.put('s', new Tuple());
        alphabets.put('t', new Tuple());
        alphabets.put('u', new Tuple());
        alphabets.put('v', new Tuple());
        alphabets.put('w', new Tuple());
        alphabets.put('x', new Tuple());
        alphabets.put('y', new Tuple());
        alphabets.put('z', new Tuple());

        char[] aArray = a.toCharArray();
        for(char aChar: aArray) {
            Tuple t = alphabets.get(aChar);
            t.aCount += 1;
            alphabets.put(aChar, t);
        }

        char[] bArray = b.toCharArray();
        for(char bChar: bArray) {
            Tuple t = alphabets.get(bChar);
            t.bCount += 1;
            alphabets.put(bChar, t);
        }

        List<Tuple> tuples = alphabets
                .values()
                .stream()
                .filter(tuple -> !(tuple.aCount == tuple.bCount))
                .collect(Collectors.toList());

        Iterator<Tuple> iterator = tuples.iterator();
        while(iterator.hasNext()) {
            Tuple t = iterator.next();
            deletions +=  Math.abs(t.aCount - t.bCount);
        }
        return deletions;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
