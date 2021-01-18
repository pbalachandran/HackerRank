package com.hackerrank.commonchild;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        List<Tuple> s1Tuples = populateMap(s1);
        List<Tuple> s2Tuples = populateMap(s2);

        int longestCommonString = 0;
        for (int i = 0; i < s1Tuples.size(); i++) {
            Tuple t1 = s1Tuples.get(i);

            if (s2Tuples.contains(t1)) {
                Tuple t2 = findTuple(t1.character, s2Tuples);
                int s1Count = t1.count;
                int s2Count = t2.count;
                longestCommonString += Math.min(s1Count, s2Count);
            }
        }
        return longestCommonString;
    }

    private static Tuple findTuple(Character character, List<Tuple> target) {
        return target
                .stream()
                .filter(tuple -> tuple.character.equals(character))
                .findFirst()
                .orElse(null);
    }

    private static LinkedList<Tuple> populateMap(String s) {
        LinkedList<Tuple> tuples = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            Tuple t = findTuple(character, tuples);
            if (t != null) {
                t.count += 1;
            } else {
                tuples.add(new Tuple(character, 1));
            }
        }
        return tuples;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    public static class Tuple {
        public Character character;
        public Integer count;

        public Tuple(Character character, Integer count) {
            this.character = character;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return Objects.equals(character, tuple.character);
        }

        @Override
        public int hashCode() {
            return Objects.hash(character);
        }
    }
}
