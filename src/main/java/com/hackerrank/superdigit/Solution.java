package com.hackerrank.superdigit;

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

    public static int superDigit(String n, int k) {
        int singleSD = Integer.valueOf(compute(n));
        int completeSD = singleSD * k;
        return Integer.valueOf(compute(String.valueOf(completeSD)));
    }

    private static String compute(String superDigit) {
        int total = 0;
        for (int i = 0; i < superDigit.length(); i++) {
            total += Integer.valueOf(superDigit.substring(i, i+1));
        }
        if (total < 10) {
            return String.valueOf(total);
        } else {
            return compute(String.valueOf(total));
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String n = firstMultipleInput[0];

        int k = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
