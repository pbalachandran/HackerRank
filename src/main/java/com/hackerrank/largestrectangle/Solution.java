package com.hackerrank.largestrectangle;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static long largestRectangle(List<Integer> heights) {
        return computeMaxArea(heights);
    }

    private static long computeMaxArea(List<Integer> heights) {
        long maxArea = 0;
        Integer[] section = new Integer[heights.size()];

        long sectionArea;
        int sectionSize = 0;
        for (int i = 0; i < heights.size(); i++) {
            section[i] = heights.get(i);
            sectionSize++;

            int startIndex = 0;
            while (startIndex < sectionSize) {
                Integer[] toSort = new Integer[sectionSize - startIndex];
                System.arraycopy(section, startIndex, toSort, 0, sectionSize - startIndex);
                Arrays.sort(toSort);
                int min = toSort[0];
                sectionArea = min * (sectionSize - startIndex);
                if (sectionArea > maxArea) {
                    maxArea = sectionArea;
                }
                startIndex++;
            }
        }
        return maxArea;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

