package com.hackerrank.poisonousplants;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

class Result {
    public static int poisonousPlants(List<Integer> p) {
        LinkedList<Integer> living = new LinkedList<>();
        living.addAll(p);
        return filterPlants(living);
    }

    public static int filterPlants(LinkedList<Integer> living) {
        int days = 0;
        while (true) {
            boolean isModified = false;
            LinkedList<Integer> alive = new LinkedList<>();

            Integer current = living.remove();
            alive.add(current);
            while (!living.isEmpty()) {
                Integer next = living.remove();
                if (current >= next) {
                    alive.add(next);
                } else {
                    isModified = true;
                }
                current = next;
            }

            if (!isModified) {
                break;
            } else {
                days++;
                living = alive;
            }
        }
        return days;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
