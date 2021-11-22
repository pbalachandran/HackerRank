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
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'poisonousPlants' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY p as parameter.
     */

    public static int poisonousPlants(List<Integer> p) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(p);
        return filterPlants(p);
    }

    public static int filterPlants(List<Integer> plants) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        plants.forEach(plant -> priorityQueue.add(plant));

        boolean isIgnore = false;
        Integer next;
        PriorityQueue<Integer> alive = new PriorityQueue<>();
        Integer current = priorityQueue.remove();
        while(!priorityQueue.isEmpty()) {
            next = priorityQueue.peek();

            if (current > next) {
                if (!isIgnore) {
                    alive.add(current);
                }
                alive.add(next);
            } else {
                alive.add(current);
                isIgnore = true;
            }
            current = next;
        }



        return 0;
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
