package com.hackerrank.frequencyqueries;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    private enum OPERATION {
        ADD,
        REMOVE
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    private static void mapIt(Integer element, OPERATION operation) {
        int delta = operation == OPERATION.ADD ? 1 : -1;
        if (map.keySet().contains(element)) {
            int count = map.get(element);
            map.put(element, count + delta);
        } else {
            if (operation == OPERATION.ADD) {
                map.put(element, delta);
            }
        }
    }

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> frequencies = new ArrayList<>();

        List<Integer> elements = new ArrayList<>();

        queries.stream().forEach(query -> {
            Integer operation = query.get(0);

            if (operation.equals(3)) {
                Integer frequency = query.get(1);
                if (map.values().contains(frequency)) {
                    frequencies.add(1);
                } else {
                    frequencies.add(0);
                }
            } else {
                Integer element = query.get(1);
                if (operation.equals(1)) {
                    elements.add(element);
                    mapIt(element, OPERATION.ADD);
                } else if (operation.equals(2)) {
                    elements.remove(element);
                    mapIt(element, OPERATION.REMOVE);
                }
            }
        });
        return frequencies;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
