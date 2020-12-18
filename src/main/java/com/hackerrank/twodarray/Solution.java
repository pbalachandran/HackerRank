package com.hackerrank.twodarray;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {
    private static Map<String, List<Integer>> hourGlasses;

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        createHourGlasses(arr);
        List<Integer> sums = createHourGlassSums();

        sums.sort(Integer::compareTo);
        return sums.get(sums.size() - 1);
    }

    private static List<Integer> createHourGlassSums() {
        Function<List<Integer>, Integer> sumFunc = (list) -> list.stream().reduce(0, Integer::sum);
        List<Integer> sums =
                hourGlasses.keySet().stream().map(key -> sumFunc.apply(hourGlasses.get(key))).collect(Collectors.toList());
        return sums;
    }

    private static void createHourGlasses(int[][] arr) {
        int indexKey = 0;

        hourGlasses = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i + 2 < 6 && j + 2 < 6) {
                    List<Integer> list =
                            Arrays.asList(arr[i][j], arr[i][j + 1], arr[i][j + 2],
                                          arr[i + 1][j + 1],
                                          arr[i + 2][j], arr[i + 2][j + 1], arr[i + 2][j + 2]);
                    hourGlasses.put(String.valueOf(indexKey++), list);
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j].trim());
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
