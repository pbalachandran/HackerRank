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
        Stack<Integer> living = new Stack<>();
        living.addAll(p);
        return filterPlants(living);
    }

    public static int filterPlants(Stack<Integer> living) {
        int days = 0;

        int cIndex = living.size() - 1;
        int pIndex = cIndex - 1;

        boolean isModified = false;
        int current, previous;
        while (true) {
            current = living.get(cIndex);
            previous = living.get(pIndex);
            if (current > previous) {
                isModified = true;
                living.removeElementAt(cIndex);
                cIndex = pIndex;
                pIndex = pIndex - 1;
            } else {
                cIndex--;
                pIndex--;
            }

            if (cIndex == 0) {
                if (isModified) {
                    days++;

                    if (living.size() == 1) {
                        break;
                    } else {
                        cIndex = living.size() - 1;
                        pIndex = cIndex - 1;
                        isModified = false;
                    }
                } else {
                    break;
                }
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
